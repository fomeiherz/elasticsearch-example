package top.fomeiherz.operate;

import org.junit.Test;
import top.fomeiherz.BaseTest;

import java.io.IOException;

public class IndexAPITest extends BaseTest {

    @Test
    public void insertString() throws IOException {
        new IndexAPI().insertString();
    }

    @Test
    public void insertMap() throws IOException {
        new IndexAPI().insertMap();
    }

    @Test
    public void insertXContentBuilder() throws IOException {
        new IndexAPI().insertXContentBuilder();
    }

    @Test
    public void insertSourceObject() throws IOException {
        new IndexAPI().insertSourceObject();
    }
}
