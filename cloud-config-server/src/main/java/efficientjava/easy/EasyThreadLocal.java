package efficientjava.easy;

import org.apache.http.ssl.PrivateKeyStrategy;

import java.lang.ref.WeakReference;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

/**
 * @Author: elyuan
 * 变量副本
 * @Date: 2021/1/18 6:19 下午
 * 最主要的点是threadLocalMap  然后清理方式 探测式清理 和启发式清理
 *
 *
 */
public class EasyThreadLocal<T> {

    private final int threadLocalHashCode = nextHashCode();
    private static AtomicInteger nextHashCode = new AtomicInteger();
    private static final int HASH_INCREMENT = 0x61c88647;


    public EasyThreadLocal() {
    }

    private int nextHashCode() {
        return nextHashCode.getAndAdd(HASH_INCREMENT);
    }


    protected T initialValue() {
        return null;
    }

    public static <S> EasyThreadLocal<S> withInitial(Supplier<? extends S> supplier) {
        return new SuppliedThreadLocal<>(supplier);
    }

    public T get() {
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if(map != null){
            ThreadLocalMap.Entry e = map.getEntry(this);
            if(e != null){
                T value = (T)e.value;
                return value;
            }
        }
        return setInitialValue();
    }

    private T setInitialValue() {
        T value = initialValue();
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if(map != null){
            map.set(this,value);
        }else{
            createMap(t,value);
        }
        return value;
    }

    private void createMap(Thread t, T value) {
        ThreadLocalMap localMap = new ThreadLocalMap(this, value);
        Constant.map.put(t,localMap);
    }

    public void set(T value){
        Thread t = Thread.currentThread();
        ThreadLocalMap map = getMap(t);
        if(map != null){
            map.set(this,value);
        }else{
            createMap(t,value);
        }
    }

     ThreadLocalMap getMap(Thread t) {
         return Constant.map.get(t);
    }


    public void remove(){
        ThreadLocalMap m = getMap(Thread.currentThread());
        if(m != null){
            m.remove(this);
        }
    }


    static class SuppliedThreadLocal<T> extends EasyThreadLocal<T> {

        private final Supplier<? extends T> supplier;

        SuppliedThreadLocal(Supplier<? extends T> supplier) {
            this.supplier = Objects.requireNonNull(supplier);
        }

        @Override
        protected T initialValue() {
            return supplier.get();
        }
    }

    static class ThreadLocalMap {

        private static final int INITIAL_CAPACITY = 16;
        private Entry[] table;
        private int size = 0;
        private int threshold;

        private void setThreshold(int len) {
            threshold = len * 2 / 3;
        }

        private static int nextIndex(int i, int len) {
            return (i + 1 < len) ? i + 1 : 0;
        }
        private static int prevIndex(int i,int len){
            return i-1>=0?i-1:len-1;
        }
        ThreadLocalMap(EasyThreadLocal<?> firstKey,Object firstValue){
            table = new Entry[INITIAL_CAPACITY];
            /**
             * 这里为什么使用与运算  为了减少hash碰撞
             */
            int i = firstKey.threadLocalHashCode&(INITIAL_CAPACITY-1);
            table[i]  = new Entry(firstKey,firstValue);
            size =1;
            setThreshold(INITIAL_CAPACITY);
        }
        //这个方法暂时还没有研究明白
        private ThreadLocalMap(ThreadLocalMap parentMap){
            Entry[] parentTable = parentMap.table;
            int len = parentTable.length;
            setThreshold(len);
            table = new Entry[len];
            for(int j=0; j< len ;j++){
                Entry e = parentTable[j];
                if(e != null){
                    EasyThreadLocal<Object> key =(EasyThreadLocal<Object>) e.get();
                    if(key != null){
                        Object value = key.childValue(e.value);
                        Entry c = new Entry(key,value);
                        int h = key.threadLocalHashCode&(len -1);
                        while (table[h] != null){
                            h = nextIndex(h,len);
                        }
                        table[h] = c;
                        size++;
                    }
                }
            }
        }


        private Entry getEntry(EasyThreadLocal<?> key){
            int i = key.threadLocalHashCode & (table.length -1);
            Entry e = table[i];
            if(e != null && e.get() == key){
                return e;
            }else{
                return getEntryAfterMiss(key,i,e);
            }
        }

        private Entry getEntryAfterMiss(EasyThreadLocal<?> key, int i, Entry e) {
            Entry[] tab = table;
            int len = tab.length;
            while (e != null){
                EasyThreadLocal<?> k = e.get();
                if(k == key){
                    return e;
                }
                if(k == null){
                    // 对应的当前entry的key被清理 就引发 探针式清理
                    expungeStaleEntry(i);
                }else{
                    i = nextIndex(i,len);
                }
                e = tab[i];
            }
            return null;

        }


        private void set(EasyThreadLocal<?> key,Object value){
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode & (len -1);
            for(Entry e = tab[i];e != null; e = tab[i = nextIndex(i,len)]){
                EasyThreadLocal<?> k = e.get();
                //槽数据不为空，key值与当前ThreadLocal通过hash计算获取的key值一致，这里直接更新该槽位的数据
                if(k == key){
                    e.value = value;
                    return;
                }
                // 对应entry 中的key过期  key为null
                if(k == null){
                    //替换过期数据的逻辑
                    replaceStaleEntry(key,value,i);
                    return;
                }
            }
            tab[i] = new Entry(key,value);
            int sz = ++ size;
            if(!cleanSomeSlots(i,sz)  && sz >= threshold){
                rehash();
            }
        }

        private void replaceStaleEntry(EasyThreadLocal<?> key, Object value, int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            Entry e;
            int slotToExpunge = staleSlot;
            //找到过期要清理的起始下标
            for(int i = prevIndex(staleSlot,len);(e = tab[i]) != null; i = prevIndex(i,len)){
                if(e.get() == null){
                    slotToExpunge = i;
                }
            }
            for(int i = nextIndex(staleSlot,len);(e = tab[i]) != null; i = nextIndex(i,len)){
                EasyThreadLocal<?> k = e.get();
                //key值相等 过期插槽进行交换 探针插槽是不是最头的位置 如果不是要更新啊
                if(k == key){
                    e.value = value;
                    tab[i] = tab[staleSlot];
                    tab[staleSlot] = e;
                    if(slotToExpunge == staleSlot){
                        slotToExpunge =i;
                    }
                    cleanSomeSlots(expungeStaleEntry(slotToExpunge),len);
                    return;
                }
                //插槽探针后移的标志
                if(k == null && slotToExpunge == staleSlot){
                    slotToExpunge = i;
                }

            }
            tab[staleSlot].value = null;
            tab[staleSlot] = new Entry(key,value);
            if(slotToExpunge != staleSlot){
                cleanSomeSlots(expungeStaleEntry(slotToExpunge),len);
            }


        }

        /**
         * 先进行一次圈量的探针式清理
         */
        private void rehash() {
            expungeStaleEntries();
            if(size >= threshold - threshold/4){
                resize();
            }
        }

        private void resize() {
            Entry[] oldTab = table;
            int oldLen = oldTab.length;
            int newLen = oldLen*2;
            Entry[] newTab = new Entry[newLen];
            int count = 0;
            for (Entry e : oldTab) {
                if (e != null) {
                    EasyThreadLocal<?> k = e.get();
                    if (k == null) {
                        e.value = null;
                    } else {
                        int h = k.threadLocalHashCode & (newLen - 1);
                        while (newTab[h] != null) {
                            h = nextIndex(h, newLen);
                        }
                        newTab[h] = e;
                        count++;
                    }
                }
            }
            setThreshold(newLen);
            size = count;
            table = newTab;
        }

        private void expungeStaleEntries() {
            Entry[] tab = table;
            int len = tab.length;
            for(int j =0; j<len; j++){
                Entry e = tab[j];
                if(e != null && e.get() == null){
                    expungeStaleEntry(j);
                }
            }
        }

        //启发式清理   启发式清理  清理不完的时候可以在rehash再清理掉
        private boolean cleanSomeSlots(int i, int n) {
            boolean removed = false;
            Entry[] tab = table;
            int len = tab.length;
            do{
                i = nextIndex(i,len);
                Entry e = tab[i];
                if(e !=null && e.get() == null){
                    n = len;
                    removed = true;
                    i = expungeStaleEntry(i);
                }
            }while ((n >>>= 1) != 0);
            return removed;
        }

        private void remove(EasyThreadLocal<?> key){
            Entry[] tab = table;
            int len = tab.length;
            int i = key.threadLocalHashCode&(len-1);
            for(Entry e = tab[i]; e !=null; e = tab[i = nextIndex(i,len)]){
                if(e.get() == key){
                    e.clear();
                    expungeStaleEntry(i);
                    return;
                }
            }
        }

        /**
         * 探测式清理
         * 遍历散列数组，从开始位置向后探测清理过期数据，将过期数据的entry设置为null
         * 沿途中碰到未过期的数据则将次数据rehash后重新在table数组中定位，如果定位的位置已经有了数据
         * 则会将未过期的数据放到最靠近此位置的entry=null的桶中
         * @param staleSlot 最开是引用为null的下标的位置
         * @return
         */
        private int expungeStaleEntry(int staleSlot) {
            Entry[] tab = table;
            int len = tab.length;
            tab[staleSlot].value = null;
            tab[staleSlot] = null;
            size --;
            Entry e;
            int i;
            for(i = nextIndex(staleSlot,len); ( e = tab[i])!= null;i = nextIndex(i,len)){
                EasyThreadLocal<?> k = e.get();
                if(k == null){
                    e.value = null;
                    tab[i] = null;
                    size--;
                }else{
                    //hash碰撞的时候 把i的位置entry设置为null 从新计算了槽的位置
                    int h = k.threadLocalHashCode & (len -1);
                    if(h != i){
                        tab[i] = null;
                        while (tab[h] != null){
                            h = nextIndex(h,len);
                        }
                        tab[h] = e;
                    }
                }
            }
            return i;
        }


        static class Entry extends WeakReference<EasyThreadLocal<?>> {
            Object value;

            Entry(EasyThreadLocal<?> k, Object v) {
                super(k);
                value = v;
            }

        }


    }

    T childValue(T parentValue){
        throw  new UnsupportedOperationException();
    }

    /**
     * 用来子父线程之间的传输
     */
    private static InheritableThreadLocal<String> FULL_LINK = new InheritableThreadLocal<>();




    private static EasyThreadLocal<String> threadLocal = new EasyThreadLocal<>();

    public static void main(String[] args) {
        //使用固定大小为1的线程池 说明上一个的线程属性会被下一个属性复用
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for(int i =0; i<2;i++){
            MyThread myThread = new MyThread();
            executorService.execute(myThread);


        }




        int n = 16;
        do{

            System.out.println("干起来n:"+n);
        }while ((n >>>= 1) != 0);
        System.out.println(1>>>1);
        EasyThreadLocal<String> easyLocal = new EasyThreadLocal<>();
        easyLocal.set("xxh");
        EasyThreadLocal<String> easyLocal1 = new EasyThreadLocal<>();
        easyLocal1.set("string");
        EasyThreadLocal<String> easyLocal2 = new EasyThreadLocal<>();
        easyLocal2.set("string");
        System.out.println(easyLocal.get());
        System.out.println("code1:"+easyLocal.threadLocalHashCode+",code2:"+
                easyLocal1.threadLocalHashCode+",code2:"+easyLocal2.threadLocalHashCode);
        AtomicInteger nextHashCode = new AtomicInteger();
        final int HASH_INCREMENT = 0x61c88647;
        for(int i =0;i<5;i++){
            System.out.println(nextHashCode.getAndAdd(HASH_INCREMENT));
        }
    }

    private static class MyThread extends Thread{
        private static boolean flag = true;

        @Override
        public void run() {
            if(flag){
                threadLocal.set("a:"+this.getName()+". session info." +"current:"+Thread.currentThread().getName());
                flag = false;
            }
            System.out.println(this.getName() +" 线程是"+threadLocal.get());


            super.run();
        }
    }

}
