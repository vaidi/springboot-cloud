package efficientjava.easy;


import org.jetbrains.annotations.Contract;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.yaml.snakeyaml.events.Event;
import sun.net.www.protocol.http.HttpURLConnection;

import java.util.*;
import java.util.function.Consumer;

/**
 * @Author: elyuan
 * @Date: 2021/1/15 10:52 上午
 */
public class EasyArrayList<E> {


    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_ELEMENTDATA ={};

    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA ={};
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
    transient Object[] elementData;
    private int size;
    protected transient int modCount = 0;
    public EasyArrayList(int initialCapacity){
        if(initialCapacity >0){
            this.elementData = new Object[initialCapacity];
        }else if(initialCapacity == 0){
            this.elementData = EMPTY_ELEMENTDATA;
        }else{
            throw new IllegalArgumentException("initialCapacity is err"+initialCapacity);
        }
    }
    public EasyArrayList(){
        this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }
    public EasyArrayList(Collection<? extends E> c){
        elementData = c.toArray();
        if((size = elementData.length) != 0){
            if(elementData.getClass() != Object[].class){
                elementData = Arrays.copyOf(elementData,size,Object[].class);
            }
        }else{
            this.elementData = EMPTY_ELEMENTDATA;
        }
    }

    public boolean add(E e){
        ensureCapacityInternal(size+1);
        elementData[size++] = e;
        return true;
    }

    public E get(int index){
        rangeCheck(index);
        return elementData(index);
    }

    public E remove(int index){
        rangeCheck(index);
        modCount++;
        E oldValue = elementData(index);
        int numMoved = size - index -1;
        if(numMoved >0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    public boolean addAll(Collection<? extends E> c){
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(size+numNew);
        System.arraycopy(a,0,elementData,size,numNew);
        size += numNew;
        return numNew != 0;
    }

    public int lastIndexOf(Object o){
        if(o == null){
            for(int i = size -1; i >= 0;i--){
                if(elementData[i] == null){
                    return i;
                }
            }
        }else{
            for(int i = size-1; i>=0;i--){
                if(o.equals(elementData[i])){
                    return i;
                }
            }
        }
        return -1;
    }




    public boolean remove(Object o){
        if(o == null){
            for(int index = 0;index <size;index++){
                if(elementData[index] == null){
                    fastRemove(index);
                    return true;
                }
            }
        }else{
            for(int index = 0;index<size;index++){
                if(o.equals(elementData[index])){
                    fastRemove(index);
                    return true;
                }

            }
        }
        return false;
    }

    private void fastRemove(int index) {
        modCount++;
        int numMoved = size -index -1;
        if(numMoved > 0){
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
    }

    private void clear(){
        modCount ++;
        for(int i =0 ; i< size;i++){
            elementData[i] = null;
        }
        size =0;
    }



    private E elementData(int index) {
        return (E)elementData[index];
    }


    private void rangeCheck(int index) {
        if(index >= this.size){
            throw new IndexOutOfBoundsException();
        }
    }


    private void ensureCapacityInternal(int minCapacity) {
        minCapacity = calculateCapacity(elementData,minCapacity);
        ensureExplicitCapacity(minCapacity);

    }

    private void ensureExplicitCapacity(int minCapacity) {
        modCount ++;
        if(minCapacity - elementData.length >0){
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity>>1);
        if(newCapacity - minCapacity < 0){
            newCapacity = minCapacity;
        }
        if(newCapacity - MAX_ARRAY_SIZE >0){
            newCapacity = Integer.MAX_VALUE;
        }
        elementData = Arrays.copyOf(elementData,newCapacity);
    }

    private int calculateCapacity(Object[] elementData, int minCapacity) {
        if(elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA){
            return Math.max(DEFAULT_CAPACITY,minCapacity);
        }
        return minCapacity;
    }

    public Iterator<E> iterator(){return new Itr();}

    private class Itr implements Iterator<E>{
        int cursor;
        int lastRet = -1;
        int expectedModCount = modCount;
        Itr(){}

        @Override
        public boolean hasNext() {
            return cursor!=size;
        }

        @Override
        public E next() {
            checkForComodification();
            int i = cursor;
            if(i >= size){
                throw new NoSuchElementException();
            }
            Object[] elementData = EasyArrayList.this.elementData;
            if(i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            cursor = i+1;
            return (E)elementData[lastRet=i];
        }

        @Override
        public void remove(){
            if(lastRet <0){
                throw new IllegalArgumentException();
            }
            checkForComodification();
            try{
                EasyArrayList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                expectedModCount = modCount;
            }catch (IndexOutOfBoundsException e){
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void forEachRemaining(Consumer<? super E> consumer){
            Objects.requireNonNull(consumer);
            final int size = EasyArrayList.this.size;
            int i = cursor;
            if(i >= size){
                return;
            }
            final Object[] elementData = EasyArrayList.this.elementData;
            if(i >= elementData.length){
                throw new ConcurrentModificationException();
            }
            while (i != size && modCount == expectedModCount){
                consumer.accept((E)elementData[i++]);
            }
            cursor =i;
            lastRet = i-1;
            checkForComodification();
        }



        private void checkForComodification() {
            if(modCount != expectedModCount){
                throw new ConcurrentModificationException();
            }
        }
    }

    public static void main(String[] args) {
        EasyArrayList<String> easyArrayList = new EasyArrayList<>();
        easyArrayList.add("xxh");
        easyArrayList.add("elyuan");
        easyArrayList.remove(0);
        Iterator<String> iterator = easyArrayList.iterator();
        iterator.forEachRemaining(e->{
            System.out.println("哈哈:"+e);
        });
        while (iterator.hasNext()){
            System.out.println("迭代器:"+iterator.next());
        }





        System.out.println("0:"+easyArrayList.get(0));


        ArrayList list = new ArrayList<String>();
        list.add("xxh");
        list.add("str");

       list.remove(0);
//
//
//        List a1 = new ArrayList();
//        a1.add(new Object());
//        a1.add(new Integer(1111));
//        a1.add(new String("xxh"));
//
//        List<Object> a2 = a1;
//        a2.add(new Object());
//        a2.add(new Integer(222));
//        a2.add(new String("new string"));
//
//
//        List<Integer> a3 = a1;
//        a3.add(new Integer(333));
//        a1.stream().forEach(System.out::println);

//        List<?> a4 = a1;
//        a1.remove(0);
//        a4.clear();
//       // a4.add(1);
//        a1.stream().forEach(System.out::println);

    }

}
