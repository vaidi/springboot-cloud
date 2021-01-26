package efficientjava.easy;

import sun.misc.Unsafe;
import sun.tools.tree.UplevelReference;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.UnaryOperator;

/**
 * @Author: elyuan
 * @Date: 2021/1/25 9:47 下午
 */
public class EasyAtomicReference<V> implements Serializable {



   // private static final Unsafe UNSAFE = Unsafe.getUnsafe();
    private static final Unsafe UNSAFE = null;
    private static  long valueOffset;
    private volatile V value;

    static {
        try {
            valueOffset = UNSAFE.objectFieldOffset(EasyAtomicReference.class.getDeclaredField("value"));
        }catch (Exception e){
            //throw new Error(e);
        }
    }

    public EasyAtomicReference(V initialValue){
        value = initialValue;
    }
    public EasyAtomicReference(){}

    public final V get(){
        return value;
    }

    public final void set(V newValue){
        value = newValue;
    }

    public final void lazySet(V newValue){
        UNSAFE.putOrderedObject(this,valueOffset,newValue);
    }
    public final boolean compareAndSet(V expect,V update){
        return UNSAFE.compareAndSwapObject(this,valueOffset,expect,update);
    }

    public final boolean wearCompareAndSet(V expect,V value){
        System.out.println("偏移量:"+valueOffset);
        return UNSAFE.compareAndSwapObject(this,valueOffset,expect, value);
    }

    public final V getAndSet(V newValue){
        return (V)UNSAFE.getAndSetObject(this,valueOffset,newValue);
    }
    public final V getAndUpdate(UnaryOperator<V> updateFunction){
        V prev,next;
        do{
            prev = get();
            next = updateFunction.apply(prev);
        }while (!compareAndSet(prev,next));
        return prev;
    }


    public static void main(String[] args) {
        Person p1 = new Person(101);
        Person p2 = new Person(102);
        // 新建AtomicReference对象，初始化它的值为p1对象
        AtomicReference ar = new AtomicReference(p1);
        //更改p1的id.
        p1.setId(106);
        // 通过CAS设置ar。如果ar的值为p1的话，则将其设置为p2。
        ar.compareAndSet(p1, p2);
        Person p3 = (Person)ar.get();
        ar.compareAndSet(p3,p1);
        ar.compareAndSet(p1,p3);


    }





    static class Person {
        volatile long id;

        public Person(long id) {
            this.id = id;
        }
        @Override
        public String toString() {
            return "id:" + id;
        }

        public void setId(long id) {
            this.id = id;
        }

    }






}
