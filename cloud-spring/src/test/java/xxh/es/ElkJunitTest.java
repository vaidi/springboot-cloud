package xxh.es;

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





}
