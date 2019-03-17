package top.fomeiherz;

import top.fomeiherz.connect.ESClient;

import java.io.IOException;
import java.util.Properties;

public class BaseTest {

    static {
        Properties prop = new Properties();
        try {
            prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ESClient.host = prop.getProperty("elasticsearch_host");
        ESClient.port = Integer.parseInt(prop.getProperty("elasticsearch_port"));
    }

}
