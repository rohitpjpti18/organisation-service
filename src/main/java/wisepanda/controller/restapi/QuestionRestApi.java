package wisepanda.controller.restapi;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wisepanda.common.ApiConstants;
import wisepanda.data.dto.app.ServiceResponse;
import wisepanda.data.dto.question.QuestionDto;
import wisepanda.data.dto.question.QuestionTagsDto;
import wisepanda.data.dto.question.TopicTagDto;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.TopicTag;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;
import wisepanda.service.QuestionService;

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
    public ResponseEntity<Object> addTopicTag(@RequestBody TopicTagDto data) throws WiseNoteException {
        TopicTag t = questionService.addTopicTag(data);
        ServiceResponse s = new ServiceResponse();
        s.setResult(new TopicTagDto(t));

        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping(ApiConstants.REST_URL_QUESTION_TAG)
    public ResponseEntity<ServiceResponse> addQuestionTag(@RequestBody QuestionTagsDto data) throws WiseNoteException {
        System.out.println(data);
        ServiceResponse s = questionService.addQuestionTags(data);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
}
