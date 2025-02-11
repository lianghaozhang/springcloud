package cn.itcast.hotel;

import cn.itcast.hotel.pojo.Hotel;
import cn.itcast.hotel.pojo.HotelDoc;
import cn.itcast.hotel.service.IHotelService;
import com.alibaba.fastjson.JSON;
import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class HotelDocumentTest {

    @Autowired
    private IHotelService hotelService;

    private RestHighLevelClient client;

    // 添加文档
    @Test
    void addHotelDoc() throws IOException {
        // 因为Hotel这个实体类中的经度和纬度是两个变量，而es中的location字段是经度和纬度的拼接字符串，所以要做一次转换
        HotelDoc hotelDoc = new HotelDoc(hotelService.getById(38609L));
        // 创建请求，并制定索引库名和文档id
        IndexRequest request = new IndexRequest("hotel").id(hotelDoc.getId().toString());
        // 构造请求参数，使用JSON.toJSONString将对象序列化成JSON字符串
        request.source(JSON.toJSONString(hotelDoc), XContentType.JSON);
        client.index(request, RequestOptions.DEFAULT);
    }

    // 查询文档
    @Test
    void getHotelDoc() throws IOException {
        // 准备请求
        GetRequest request = new GetRequest("hotel", "38609");
        // 发送请求获取相应
        GetResponse response = client.get(request, RequestOptions.DEFAULT);
        // 解析响应结果，getSourceAsString这个方法是把响应结果中的_source转成json返回
        String res = response.getSourceAsString();
        // 利用fastjson进行反序列化变成HotelDoc对象
        HotelDoc hotelDoc = JSON.parseObject(res, HotelDoc.class);
        System.out.println(hotelDoc);
    }

    // 更新文档（局部更新）
    @Test
    void updateHotelDoc() throws IOException {
        UpdateRequest request = new UpdateRequest("hotel", "38609");
        request.doc(
                "city", "火星",
                "starName", "一伯钻"
        );
        client.update(request, RequestOptions.DEFAULT);
    }

    // 删除文档
    @Test
    void deleteHotelDoc() throws IOException {
        DeleteRequest request = new DeleteRequest("hotel", "38609");
        client.delete(request, RequestOptions.DEFAULT);
    }

    // 批量添加文档
    @Test
    void bulkAddHotelDoc() throws IOException {
        // 创建请求
        BulkRequest request = new BulkRequest();
        // 将Hotel类型转换为HotelDoc后批量添加请求
        // hotelService.list()：mybatis plus获取数据库全部数据的方法
        List<Hotel> list = hotelService.list();
        for (Hotel hotel : list) {
            HotelDoc hotelDoc = new HotelDoc(hotel);
            request.add(new IndexRequest("hotel")
                    .id(hotelDoc.getId().toString())
                    .source(JSON.toJSONString(hotelDoc), XContentType.JSON));
        }
        // 发送请求
        client.bulk(request, RequestOptions.DEFAULT);
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
