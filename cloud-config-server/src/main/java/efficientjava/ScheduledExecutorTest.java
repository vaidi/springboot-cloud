package efficientjava;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: elyuan
 * @Date: 2021/1/12 11:50 上午
 */
public class ScheduledExecutorTest {

    public static void main(String[] args) {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.
                        Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(()->{
            System.out.println("开始干啊 ");
        },20,2, TimeUnit.SECONDS);

    }

}
