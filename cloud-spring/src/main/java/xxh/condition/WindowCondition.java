package xxh.condition;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @Author: elyuan
 * @Date: 2020/9/1 10:26 上午
 */
public class WindowCondition implements Condition {
    /**
     * 这里写自己的逻辑，只有返回true，才会启用配置
     */
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return context.getEnvironment().getProperty("os.name").contains("Windows");
    }

}
