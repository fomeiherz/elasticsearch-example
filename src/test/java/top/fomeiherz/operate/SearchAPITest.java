package top.fomeiherz.operate;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.junit.Test;
import top.fomeiherz.BaseTest;

import java.io.IOException;

public class SearchAPITest extends BaseTest {

    /**
     * Print hit content.
     *
     * @param response
     */
    private static void printHits(SearchResponse response) {
        // Reverse response hits
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.toString());
        }
    }

    @Test
    public void matchIndicesQuery() throws IOException {
        printHits(new SearchAPI().matchIndicesQuery());
    }

    @Test
    public void matchTermQuery() throws IOException {
        printHits(new SearchAPI().matchTermQuery());
    }

    @Test
    public void matchQueryBuilder() throws IOException {
        printHits(new SearchAPI().matchQueryBuilder());
    }
}
