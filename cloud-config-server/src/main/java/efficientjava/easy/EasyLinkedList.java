package efficientjava.easy;

import jdk.nashorn.internal.runtime.RewriteException;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.w3c.dom.Node;

import java.util.*;

/**
 * @Author: elyuan
 * @Date: 2021/1/22 2:35 下午
 */
public class EasyLinkedList<E> {

    transient int size = 0;
    transient Node<E> first;
    transient Node<E> last;
    protected transient int modCount = 0;

    public EasyLinkedList() {
    }

    public boolean add(E e) {
        linkLast(e);
        return true;
    }


    public boolean addAll(Collection<? extends E> c){
        return addAll(size,c);
    }

    private boolean addAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);
        Object[] a = c.toArray();
        int numNew = a.length;
        if(numNew == 0){
            return false;
        }
        Node<E> pred,succ;
        if(index == size){
            succ = null;
            pred = last;
        }else{
            succ = node(index);
            pred = succ.next;
        }
        for(Object o :a){
            E e =(E)o;
            Node<E> newNode = new Node<>(pred,e,null);
            if(pred == null){
                first = newNode;
            }else{
                pred.next = newNode;
            }
            pred = newNode;
        }
        if(succ == null){
            last = pred;
        }else{
            pred.next = succ;
            succ.prev = pred;
        }
        size += numNew;
        modCount++;
        return true;
    }

    private void checkPositionIndex(int index) {
        if(!isPositionIndex(index)){
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isPositionIndex(int index) {
        return index>=0 && index<= size;
    }

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }


    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }
    public E remove(int index){
        checkElementIndex(index);
        return unlink(node(index));
    }

    public E getFirst(){
        final Node<E> x = first;
        if(x == null){
            throw new NoSuchElementException();
        }
        return first.item;
    }


    public E removeFirst(){
        final Node<E> f = first;
        if(f == null){
            throw new NoSuchElementException();
        }
        return unlinkFirst(f);
    }

    private E unlinkFirst(Node<E> f) {
        final Node<E> next = f.next;
        final E element = f.item;
        first = next;
        if(next == null){
            last = null;
        }else{
            next.prev = null;
        }
        f.next = null;
        f.item = null;
        size--;
        modCount++;
        return element;
    }

    public E removeLast(){
        final Node<E> l = last;
        if(l == null){
            throw new NoSuchElementException();
        }
        return unlinkLast(l);
    }


    public void addFirst(E e){
        linkFirst(e);
    }

    public void addLast(E e){
        linkLast(e);
    }

    private void linkFirst(E e) {
        final Node<E> f = first;
        final Node<E> newNode =new Node<>(null,e,f);
        first = newNode;
        if(f == null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        size++;
        modCount++;
    }


    private E unlinkLast(Node<E> l) {
        final Node<E> prev = l.prev;
        E element = l.item;
        l.item = null;
        //help for gc
        l.prev = null;
        last = prev;
        if(prev == null){
            first = null;
        }else{
            prev.next = null;
        }
        size--;
        modCount++;
        return element;
    }


    private E unlink(Node<E> x) {
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;
        if(prev == null){
            first = next;
        }else{
            prev.next = next;
            x.prev = null;
        }
        x.item = null;
        size --;
        modCount ++;
        return element;
    }


    private Node<E> node(int index) {
        if(index < (size>>1)){
            Node<E> x = first;
            for(int i=0;i<index;i++){
                x = x.next;
            }
            return x;
        }else{
            Node<E> x = last;
            for(int i = size-1;i>index;i--){
                x = x.prev;
            }
            return x;
        }
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }


    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }


    public static void main(String[] args) {
        EasyLinkedList<String> list = new EasyLinkedList<>();
        list.add("xxh");
        list.add("str");
        System.out.println("first:" + list.getFirst() + ",0:" + list.get(0));


    }

}
