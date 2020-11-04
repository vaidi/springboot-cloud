package xxh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import xxh.condition.CmdService;

/**
 * @Author: xxh——el
 * @Date: 2020/6/30 2:21 下午
 * 用来研究spring的相关的一个内容
 *https://blog.csdn.net/f641385712/article/details/92797058
 *
 *
 *
 */
@SpringBootApplication
@EnableCaching
public class SpringStudyApplication {
    public static void main(String[] args) {
        ApplicationContext ctx =  (ApplicationContext) SpringApplication.run(SpringStudyApplication.class, args);
        CmdService cmdService = ctx.getBean(CmdService.class);
        cmdService.print();
        System.out.println("主程序启动完毕，请进行下面步骤!");
        //SpringApplication.run(SpringStudyApplication.class,args);
    }
}
