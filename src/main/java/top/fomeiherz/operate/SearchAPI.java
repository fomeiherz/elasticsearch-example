package top.fomeiherz.operate;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import top.fomeiherz.connect.ESClient;

import java.io.IOException;

public class SearchAPI {

    /**
     * Match all query.
     *
     * @return SearchResponse
     * @throws IOException
     */
    public SearchResponse matchIndicesQuery() throws IOException {
        SearchRequest request = new SearchRequest("posts");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.matchAllQuery());
        request.source(builder);
        return ESClient.getClient().search(request, RequestOptions.DEFAULT);
    }

    /**
     * Match by term.
     *
     * @return SearchResponse
     * @throws IOException
     */
    public SearchResponse matchTermQuery() throws IOException {
        SearchRequest request = new SearchRequest("posts");
        SearchSourceBuilder builder = new SearchSourceBuilder();
        builder.query(QueryBuilders.termQuery("user", "kimchy"));
        // Start search from
        builder.from(0);
        // Number of search hits
        builder.size(10);
        // Sort by _score
        builder.sort(new ScoreSortBuilder().order(SortOrder.ASC));
        // Which field get included or excluded
        String[] includeFields = new String[] {"title", "user", "innerObject.*"};
        String[] excludeFields = new String[] {"_type"};
        builder.fetchSource(includeFields, excludeFields);
        request.source(builder);
        return ESClient.getClient().search(request, RequestOptions.DEFAULT);
    }

    /**
     * Search by MatchQueryBuilder.
     *
     * @return SearchResponse
     * @throws IOException
     */
    public SearchResponse matchQueryBuilder() throws IOException {
        SearchRequest request = new SearchRequest("posts");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("user", "kimchy")
                .fuzziness(Fuzziness.AUTO)
                .prefixLength(3)
                .maxExpansions(10);
        searchSourceBuilder.query(matchQueryBuilder);
        // Sort by field
        searchSourceBuilder.sort(new FieldSortBuilder("_uid").order(SortOrder.ASC));
        // Don't contain _source
        searchSourceBuilder.fetchSource(false);
        request.source(searchSourceBuilder);
        return ESClient.getClient().search(request, RequestOptions.DEFAULT);
    }

}
