package xxh.condition;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import xxh.SpringStudyApplication;

/**
 * @Author: elyuan
 * @Date: 2020/9/1 10:31 上午
 */
public class MainTest {

    public static void main(String[] args) {
        ApplicationContext ctx =  (ApplicationContext) SpringApplication.run(SpringStudyApplication.class, args);
        CmdService cmdService = ctx.getBean(CmdService.class);
        cmdService.print();
    }

}
