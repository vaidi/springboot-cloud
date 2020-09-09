package xxh.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: elyuan
 * @Date: 2020/9/1 10:39 上午
 */
@Slf4j
public class MacCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        Environment en = conditionContext.getEnvironment();
        //这个条件可以写在配置文件中
        String port = en.getProperty("server.port");
        System.out.println(port);
        log.info("en:{}",en);
        log.info("context:{}",conditionContext);
        log.info("annotatedTypeMetadata:{}",annotatedTypeMetadata);
        return true;
    }
}
