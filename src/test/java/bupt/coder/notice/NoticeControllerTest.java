package bupt.coder.notice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class NoticeControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private String baseUrl = "/Notice/";

    @Before
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void put() throws Exception {
        //language=JSON
        String content = "{\n" +
                "  \"message\": \"我是一条新消息\",\n" +
                "  \"date\": \"2019-05-11\",\n" +
                "  \"author\": \"TheShy\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(baseUrl).content(content).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void delete() throws Exception {
        long id = 1;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(baseUrl + id).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void post() throws Exception {
        long id = 1;
        //language=JSON
        String content = "{\n" +
                "  \"message\": \"更新一条信息\",\n" +
                "  \"date\": \"2019-06-11\",\n" +
                "  \"author\": \"TheShy\"\n" +
                "}";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(baseUrl + id).content(content).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void find() throws Exception {
        long id = 1;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + id).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
        JsonNode jsonNode = objectMapper.readTree(mvcResult.getResponse().getContentAsString());
        Assert.assertEquals("TheShy", jsonNode.get("author").asText());
    }


    @Test
    public void findAll() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(baseUrl).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assert.assertEquals(200, mvcResult.getResponse().getStatus());
    }

}
