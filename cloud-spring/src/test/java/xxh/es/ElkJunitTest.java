package xxh.es;

import com.alibaba.excel.EasyExcel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xxh.SpringStudyApplication;
import xxh.easyexcel.DemoDataListener;
import xxh.easyexcel.bean.MobileNo;
import xxh.elk.IndexApiService;

import java.io.IOException;

/**
 * @Author: elyuan
 * @Date: 2020/11/3 4:27 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringStudyApplication.class)
public class ElkJunitTest {


    @Autowired
    private RestHighLevelClient client;

    @Autowired
    private IndexApiService indexApiService;


    @Test
    public void testIndexApi() throws IOException {
        IndexRequest request = indexApiService.getRequest(2);
        IndexResponse indexResponse = client.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse);

    }


    @Test
    public void testIndexApi11() throws IOException {
        String fileName = "/Users/erlong/xxh.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, MobileNo.class, new DemoDataListener()).sheet().doRead();

    }




}
