import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;
import xxh.ConfigServer;
import xxh.scop.SingletonBean;

/**
 * @Author: elyuan
 * @Date: 2021/1/8 11:50 上午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigServer.class)
public class ApplicationTest {


    private static Logger logger = LoggerFactory.getLogger(ApplicationTest.class);
    @Autowired
    private ApplicationContext context;



    @Test
    public void testPrototype() {
        Environment environment = context.getEnvironment();
        String[] activeProfiles = environment.getActiveProfiles();
        String spring = environment.getProperty("spring.profiles.active");
        System.err.println("xxh");
        logger.error("testPrototype:{},spring:{}",activeProfiles,spring);


    }


    @Test
    public void testSingleton() {
        SingletonBean singletonbean1 = (SingletonBean) context.getBean("singletonBean");
        SingletonBean singletonbean2 = (SingletonBean) context.getBean("singletonBean");
        System.out.println(singletonbean1);
        System.out.println(singletonbean2);
    }
}
