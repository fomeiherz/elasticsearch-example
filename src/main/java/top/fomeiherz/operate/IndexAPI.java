package top.fomeiherz.operate;

import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import top.fomeiherz.connect.ESClient;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Index API
 */
public class IndexAPI {

    /**
     * Insert string value
     *
     * @throws IOException
     */
    public void insertString() throws IOException {
        IndexRequest request = new IndexRequest()
                .index("posts")
                .type("doc")
                .id("1");
        String jsonString = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2013-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        request.source(jsonString, XContentType.JSON);
        ESClient.getClient().index(request, RequestOptions.DEFAULT);
    }

    /**
     * Insert an instance of Map<String, Object>
     *
     * @throws IOException
     */
    public void insertMap() throws IOException {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "Joey");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest request = new IndexRequest("posts", "doc", "2").source(jsonMap);
        ESClient.getClient().index(request, RequestOptions.DEFAULT);
    }

    /**
     * XContentBuilder is Elasticsearch built-in helper to generate JSON content.
     *
     * @throws IOException
     */
    public void insertXContentBuilder() throws IOException {
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.field("user", "yyyy");
            builder.timeField("postDate", new Date());
            builder.field("message", "Id: 3 message");
        }
        builder.endObject();
        IndexRequest request = new IndexRequest("posts", "doc", "3").source(builder);
        ESClient.getClient().index(request, RequestOptions.DEFAULT);
    }

    /**
     * Source provided as object key-pairs
     *
     * @throws IOException
     */
    public void insertSourceObject() throws IOException {
        IndexRequest request = new IndexRequest("posts", "doc", "4")
                .source(
                        "user", "Milk",
                        "postDate", new Date(),
                        "message", "Id: 4 message."
                ).opType(DocWriteRequest.OpType.CREATE);
        ESClient.getClient().index(request, RequestOptions.DEFAULT);
    }

}
