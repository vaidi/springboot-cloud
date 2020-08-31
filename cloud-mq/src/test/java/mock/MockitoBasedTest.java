package mock;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

/**
 * @Author: elyuan
 * @Date: 2020/8/16 6:25 下午
 */
public abstract class MockitoBasedTest {
    @Before
    public void setUp() throws Exception {
        // 初始化测试用例类中由Mockito的注解标注的所有模拟对象
        MockitoAnnotations.initMocks(this);
    }
}
