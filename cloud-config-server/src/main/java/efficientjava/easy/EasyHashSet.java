package efficientjava.easy;

import org.springframework.web.method.support.HandlerMethodReturnValueHandler;

import java.util.HashMap;
import java.util.Iterator;

/**
 * @Author: elyuan
 * @Date: 2021/1/22 5:01 下午
 */
public class EasyHashSet<E> {

    private transient HashMap<E,Object> map;

    private static final Object present = new Object();


    public EasyHashSet(){
        map = new HashMap<>();
    }

    public Iterator<E> iterator(){
        return map.keySet().iterator();
    }
    public int size(){
        return map.size();
    }
    public boolean contains(Object o){
        return map.containsKey(o);
    }
    public boolean add(E e){
        return map.put(e,present) == null;
    }
    public boolean remove(Object o){
        return map.remove(o) == present;
    }
    public void clear(){
        map.clear();
    }




    public static void main(String[] args) {
        EasyHashSet set1 =new EasyHashSet();
        EasyHashSet set2 =new EasyHashSet();

    }

}
