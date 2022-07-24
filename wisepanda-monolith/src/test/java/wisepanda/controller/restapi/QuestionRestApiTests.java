package wisepanda.controller.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import net.minidev.json.JSONObject;
import wisepanda.common.ApiConstants;
import wisepanda.data.dto.QuestionDto;
import wisepanda.data.dto.ServiceResponse;
import wisepanda.data.dto.TopicTagDto;
import wisepanda.exceptions.WiseNoteException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuestionRestApiTests {
    private static final Logger log = LogManager.getLogger(QuestionRestApiTests.class);

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String rootUrl;

    private HttpHeaders header;

    private JSONObject request;

    public void setup(HttpHeaders header) {
        header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    public void questionApiAddQuestionTest() throws WiseNoteException {
        rootUrl = "http://127.0.0.1:" + Integer.toString(port) + ApiConstants.REST_URL_QUESTION_ROOT + ApiConstants.REST_URL_QUESTION;
        setup(header);

        for(int i = 0; i<10; i++){
            request = new JSONObject();
            request.put("questionName", "some_value"+Integer.toString(i));
            QuestionDto q = restTemplate.postForObject(rootUrl, request, QuestionDto.class);
            assertEquals(q.getQuestionName(), "some_value"+Integer.toString(i));
            assertNotNull(q);
        }
    }

    @Test
    public void questionApiAddTagsTest() throws WiseNoteException {
        rootUrl = "http://127.0.0.1:" + Integer.toString(port) + ApiConstants.REST_URL_QUESTION_ROOT + ApiConstants.REST_URL_TOPIC_TAG;
        setup(header);

        String[] values = new String[]{"CBSE", "XI", "PHYSICS", "UNITS_AND_MEASUREMENT", "SIGNIFICANT_FIGURE"};

        for(int i = 0; i<values.length; i++) {
            request = new JSONObject();        
            request.put("tagName", values[i]);
            ServiceResponse t = restTemplate.postForObject(rootUrl, request, ServiceResponse.class);
            log.info(t);
            assertEquals(((Map<String,String>)(t.getResult())).get("tagName"), values[i]);
        }
    }

    @Test
    public void questionApiAddQuesTagsTest() throws WiseNoteException {
        
    }
}
