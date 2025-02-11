package cn.itcast.hotel;

import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static cn.itcast.hotel.constants.HotelConstants.HOTELMAPPING;

public class HotelIndexTest {
    private RestHighLevelClient client;

    // 创建索引库
    @Test
    void createHotelIndex() throws IOException {
        // 创建request对象
        CreateIndexRequest request = new CreateIndexRequest("hotel");
        // 设置请求参数和请求体格式
        // HOTELMAPPING为之前在浏览器中写的DSL语句，XContentType.JSON是请求体格式
        request.source(HOTELMAPPING, XContentType.JSON);
        // 发送请求
        // client.indices()：获取索引库操作对象
        client.indices().create(request, RequestOptions.DEFAULT);
    }

    // 删除索引库
    @Test
    void deleteHotelIndex() throws IOException {
        // 创建request对象
        DeleteIndexRequest request = new DeleteIndexRequest("hotel");
        // 发送请求
        // client.indices()：获取索引库操作对象
        client.indices().delete(request, RequestOptions.DEFAULT);
    }

    // 判断索引库是否存在
    @Test
    void isExistHotelIndex() throws IOException {
        // 创建request对象
        GetIndexRequest request = new GetIndexRequest("hotel");
        // 发送请求
        // client.indices()：获取索引库操作对象
        boolean exists = client.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }


    @Test
    void test() {
        System.out.println(client);
    }

    @BeforeEach
    void setUp() {
        this.client = new RestHighLevelClient(RestClient.builder(
                HttpHost.create("http://localhost:9200")
        ));
    }

    @AfterEach
    void tearDown() throws IOException {
        this.client.close();
    }
}
