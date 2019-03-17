package top.fomeiherz.connect;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class ESClient {

    public static String host;
    public static int port;

    /**
     * Get a RestHighLevelClient
     *
     * @return an instance of RestHighLevelClient
     */
    public static RestHighLevelClient getClient() {
        return new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(host, port, "http")
                )
        );
    }

}
