package wisepanda.controller.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wisepanda.common.ApiConstants;
import wisepanda.data.dto.QuestionDto;
import wisepanda.data.dto.QuestionTagsDto;
import wisepanda.data.dto.ServiceResponse;
import wisepanda.data.dto.TopicTagDto;
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
    public ResponseEntity<QuestionDto> addQuestion(@RequestBody QuestionDto data) throws InValidDataException {
        Question q = questionService.addQuestion(data);
        return new ResponseEntity<>(new QuestionDto(q), HttpStatus.OK);
    }

    @PostMapping(ApiConstants.REST_URL_TOPIC_TAG)
    public ResponseEntity addTopicTag(@RequestBody TopicTagDto data) throws WiseNoteException {
        TopicTag t = questionService.addTopicTag(data);
        ServiceResponse s = new ServiceResponse();
        s.setResult(new TopicTagDto(t));

        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    @PostMapping(ApiConstants.REST_URL_QUESTION_TAG)
    public ResponseEntity addQuestionTag(@RequestBody QuestionTagsDto data) throws WiseNoteException {
        return null;
    }
}
