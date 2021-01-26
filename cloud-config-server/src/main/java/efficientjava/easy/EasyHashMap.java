package efficientjava.easy;

import javax.print.DocFlavor;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: elyuan
 * @Date: 2021/1/22 5:31 下午
 */
public class EasyHashMap<K,V> {






    static class Node<K,V> implements Map.Entry<K,V>{
        final int hash;
        final K key;
        V value;
        Node<K,V>  next;
        Node(int hash,K key,V value,Node<K,V> next){
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        @Override
        public final K getKey(){
            return key;
        }
        @Override
        public final V getValue(){
            return value;
        }
        @Override
        public final int hashCode(){
            return Objects.hashCode(key) ^ Objects.hash(value);
        }

        @Override
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
    }

    public static void main(String[] args) {
        for(int i =0;i < 100;i++){
            System.out.println(Objects.hashCode(i) ^ Objects.hash(i));
        }
    }




}
