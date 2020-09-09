package xxh.importn;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Author: elyuan
 * @Date: 2020/8/31 7:05 下午
 */
public class MyImportDefinitionRegister implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        BeanDefinition definition1 = BeanDefinitionBuilder.rootBeanDefinition(DemoServiceImpl.class).getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("aa", definition1);

        BeanDefinition definition2 = BeanDefinitionBuilder.rootBeanDefinition(DemoService2Impl.class).getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition("bb", definition2);
    }
}
