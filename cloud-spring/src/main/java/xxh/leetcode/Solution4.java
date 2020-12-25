package xxh.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: elyuan
 * @Date: 2020/12/5 9:12 下午
 */
public class Solution4 {


    public static int leastInterval(char[] tasks, int n) {
        if (n == 0) {
            return tasks.length;
        }
        Map<String, Integer> map = new HashMap<>();
        for (char task : tasks) {
            String mapKey = String.valueOf(task);
            if (map.containsKey(mapKey)) {
                map.put(mapKey, map.get(mapKey) + 1);
            } else {
                map.put(mapKey, 1);
            }
        }
        Integer num = map.values().stream().max(Integer::compareTo).get();
        long number = map.values().stream().filter(e->e==num.intValue()).count();
        int maxLength = (int)((num - 1) * n+(number-1));
        if (maxLength < tasks.length - 1) {
            return tasks.length;
        }else{
            return (int)((num - 1) * n+num+(number-1));
        }
    }

    public static void main(String[] args) {
//        char[] charArray = {'a', 'a', 'a', 'b','b','b','c','c','c'};
//        int n = 2;
//        System.out.println(leastInterval(charArray, n));
        System.out.println(-8>>1);


    }


}
