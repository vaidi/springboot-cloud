package xxh.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xxh.easyexcel.bean.MobileNo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: elyuan
 * @Date: 2020/11/12 11:30 上午
 */
public class DemoDataListener extends AnalysisEventListener<MobileNo> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);

    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<MobileNo> list = new ArrayList<MobileNo>();

    public DemoDataListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数

    }


    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     * @param context
     */
    @Override
    public void invoke(MobileNo data, AnalysisContext context) {
        StringBuilder sb = new StringBuilder();
        AtomicInteger atomicInteger = new AtomicInteger();
        MainTest.send(data.getMobile(),data.getNo(),sb,atomicInteger);
        System.out.println("失败的号码："+sb);
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }


}
