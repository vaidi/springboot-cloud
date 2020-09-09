package xxh.main;

import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;

/**
 * @Author: elyuan
 * @Date: 2020/9/1 11:26 上午
 */
public class PropertyMapperMain {

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        sleep(100);
        System.out.println(stopWatch.getLastTaskTimeMillis());
    }



    private void mapper(@Nullable Container container) {
        StringBuilder local = new StringBuilder();
        List<String> binaries = new ArrayList<>();

        PropertyMapper mapper = PropertyMapper.get();
        mapper.from(container::getLocation).to(local::append);
//        mapper.from(container).whenNonNull().as(Container::getLocation).whenNonNull().as(String::trim).to(local::append);
//        mapper.from(container).whenNonNull().as(Container::getIntegers).whenNonNull().to(is -> is.stream().map(Integer::toBinaryString).collect(Collectors.toCollection(() -> binaries)));
        System.out.println("local = " + local.toString());
        System.out.println("binaries = " + binaries);
    }




}
