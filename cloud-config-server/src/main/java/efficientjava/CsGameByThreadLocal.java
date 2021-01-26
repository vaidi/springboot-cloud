package efficientjava;

import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: elyuan
 * @Date: 2021/1/18 11:03 上午
 */
public class CsGameByThreadLocal {

    private static final Integer BULLET_NUMBER = 1500;
    private static final Integer KILLED_ENEMIES =0;
    private static final Integer LIFE_VALUE = 10;
    private static final Integer total_players = 10;
    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();
    private static final Random random = new Random(1);

    private static final ThreadLocal<Integer> BULLET_NUMBER_THREAD_LOCAL = ThreadLocal.withInitial(() -> BULLET_NUMBER);

    private static final ThreadLocal<Integer> KILLED_ENEMIES_THREAD_LOCAL = ThreadLocal.withInitial(()->KILLED_ENEMIES);

    private static final ThreadLocal<Integer> LIFE_VALUE_THREAD_LOCAL = ThreadLocal.withInitial(()->LIFE_VALUE);

    private static class Player extends Thread{


        @Override
        public void run() {
            Integer bullets = BULLET_NUMBER_THREAD_LOCAL.get()-RANDOM.nextInt(BULLET_NUMBER);
            Integer killEnemies = KILLED_ENEMIES_THREAD_LOCAL.get()+RANDOM.nextInt(total_players/2);
            Integer lifeValue = LIFE_VALUE_THREAD_LOCAL.get()-RANDOM.nextInt(LIFE_VALUE);
            System.out.println("bullets:"+bullets+",killEnemies:"+killEnemies+",:"+random.nextInt(BULLET_NUMBER)+"，random:"+RANDOM.nextInt(100)+":"+RANDOM.nextInt(100));



            BULLET_NUMBER_THREAD_LOCAL.remove();
            KILLED_ENEMIES_THREAD_LOCAL.remove();
            LIFE_VALUE_THREAD_LOCAL.remove();
        }
    }

    public static void main(String[] args) {
        for(int i =0; i< total_players;i++){
            new Player().start();
        }


    }


}
