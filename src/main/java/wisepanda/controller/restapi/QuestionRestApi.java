package wisepanda.controller.restapi;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.app.ServiceResponse;
import wisepanda.data.dto.question.QuestionDto;
import wisepanda.data.dto.question.QuestionTagsDto;
import wisepanda.data.dto.question.TopicTagDto;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.TopicTag;
import wisepanda.exceptions.WiseNoteException;
import wisepanda.service.QuestionService;
import wisepanda.utils.StringUtil;

@RestController
@RequestMapping(ApiConstants.REST_URL_QUESTION_ROOT)
public class QuestionRestApi {

    @Autowired
    private QuestionService questionService;

    @PostMapping(ApiConstants.REST_URL_QUESTION)
    public ResponseEntity<ServiceResponse> addQuestion(@RequestBody QuestionDto data) throws WiseNoteException {
        ServiceResponse q = questionService.addQuestion(data);
        return new ResponseEntity<>(q, q.getHttpStatus());
    }

    @PutMapping(ApiConstants.REST_URL_QUESTION)
    public ResponseEntity<ServiceResponse> updateQuestion(@RequestBody QuestionDto data) throws WiseNoteException {
        ServiceResponse q = questionService.updateQuestion(data);

        return new ResponseEntity<>(q, q.getHttpStatus());
    }

    @DeleteMapping(ApiConstants.REST_URL_QUESTION)
    public ResponseEntity<ServiceResponse> deleteQuestion(@RequestParam("delete-by")String deleteBy, @RequestBody QuestionDto data) throws WiseNoteException {
        ServiceResponse s = questionService.deleteQuestion(data, deleteBy);

        return new ResponseEntity<>(s, s.getHttpStatus());
    }


    @PostMapping(ApiConstants.REST_URL_TOPIC_TAG)
    public ResponseEntity<ServiceResponse> addTopicTag(@RequestBody TopicTagDto data) throws WiseNoteException {
        List<TopicTag> t = questionService.addTopicTag(data);
        ServiceResponse s = new ServiceResponse();
        s.setHttpStatus(HttpStatus.OK);
        s.setTimestamp(LocalDateTime.now());
        s.setResult(t);

        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping(ApiConstants.REST_URL_QUESTION_TAG)
    public ResponseEntity<ServiceResponse> addQuestionTag(@RequestBody QuestionTagsDto data) throws WiseNoteException {
        System.out.println(data);
        ServiceResponse s = questionService.addQuestionTags(data);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping(ApiConstants.REST_URL_QUESTION_TAG)
    public ResponseEntity<ServiceResponse> getQuestionTag(@RequestParam Integer id) throws WiseNoteException {
        QuestionTagsDto data = new QuestionTagsDto();
        data.setQuestion(new QuestionDto());
        data.getQuestion().setId(Long.valueOf(id));


        Map<String, Object> r = questionService.getQuestionTags(data);
        ServiceResponse s = new ServiceResponse();
        s.setResult(r);
        s.setHttpStatus(HttpStatus.OK);

        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @GetMapping(ApiConstants.REST_URL_QUESTIONS)
    public ResponseEntity<ServiceResponse> getQuestions(
            @RequestParam(value = "base_tags") String bt,
            @RequestParam(value="unit_tags",required = false) String ut,
            @RequestParam(value="sub_unit_tags",required = false) String sut) throws WiseNoteException {
        List<String> tags = new ArrayList<>();
        List<String> unit_tags = new ArrayList<>();
        List<String> sub_unit_tags = new ArrayList<>();

        if(bt != null) tags = StringUtil.splitStr(bt);
        if(ut != null) unit_tags = StringUtil.splitStr(ut);
        if(sut != null) sub_unit_tags = StringUtil.splitStr(sut);

        List<Question> result = questionService.getAllQuestionByTags(tags, unit_tags, sub_unit_tags);
        Map<String, List<Question>> q = new HashMap<>();
        q.put("questions", result);

        ServiceResponse s = new ServiceResponse();
        s.setResult(q);

        return new ResponseEntity<>(s, s.getHttpStatus());
    }
}
