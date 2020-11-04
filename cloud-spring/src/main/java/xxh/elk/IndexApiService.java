package xxh.elk;

import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.WriteRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;

/**
 * @Author: elyuan
 * @Date: 2020/11/3 4:07 下午
 */
@Service
public class IndexApiService {





    public IndexRequest getRequest(Integer param) throws IOException {
        IndexRequest indexRequest = null;
        if(param == null){
            XContentBuilder builder = XContentFactory.jsonBuilder();
            builder.startObject();
            {
                builder.field("user", "kimchy");
                builder.timeValueField("postDate", "readableFieldName", new TimeValue(1000L));
                //builder.timeField("postDate", new Date());
                builder.field("message", "trying out Elasticsearch");
            }
            builder.endObject();
            indexRequest = new IndexRequest("posts").id("1").source(builder);

        }
        if(param ==1){
            indexRequest = new IndexRequest("posts");
            indexRequest.id("1");
            String jsonString = "{" +
                    "\"user\":\"kimchy\"," +
                    "\"postDate\":\"2013-01-30\"," +
                    "\"message\":\"trying out Elasticsearch\"" +
                    "}";
            indexRequest.source(jsonString, XContentType.JSON);
        }
        if(param ==2){
            indexRequest = new IndexRequest("posts")
                    .id("1")
                    .source("user", "kimchy",
                            "postDate", new Date(),
                            "message", "trying out Elasticsearch");

        }
        indexRequest.routing("routing");
        indexRequest.timeout(TimeValue.timeValueSeconds(1));
        indexRequest.timeout("1s");
        indexRequest.setRefreshPolicy(WriteRequest.RefreshPolicy.WAIT_UNTIL);
        indexRequest.setRefreshPolicy("wait_for");
        indexRequest.opType(DocWriteRequest.OpType.CREATE);
        indexRequest.opType("create");
        indexRequest.versionType(VersionType.EXTERNAL);
        return indexRequest;

    }

}
