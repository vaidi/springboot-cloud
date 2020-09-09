package xxh.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: elyuan
 * @Date: 2020/9/1 10:30 上午
 */
@Configuration
public class CmdServiceConditionConfig {

    /**
     * 当WindowCondition方法中的matches返回true的时候，
     * WindowCmdService会被注入，否则不注入。
     */
    @Bean("cmdService")
    @Conditional(WindowCondition.class)
    public CmdService windowCmdService(){
        return new WindowCmdService();
    }


    /**
     * 当LinuxCondition方法中的matches返回true的时候，
     * LinuxCmdService会被注入，否则不注入。
     */
    @Bean("cmdService")
    @Conditional(LinuxCondition.class)
    public CmdService LinuxCmdService(){
        return new LinuxCmdService();
    }

    @Bean("cmdService")
    @Conditional(MacCondition.class)
    public CmdService macCmdService(){
        return new MacCmdService();
    }

}
