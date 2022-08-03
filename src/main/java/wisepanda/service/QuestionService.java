package wisepanda.service;

import wisepanda.data.dto.app.ServiceResponse;
import wisepanda.data.dto.question.QuestionDto;
import wisepanda.data.dto.question.QuestionMultipleChoiceDto;
import wisepanda.data.dto.question.QuestionTagsDto;
import wisepanda.data.dto.question.TopicTagDto;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.QuestionMultipleChoice;
import wisepanda.data.entities.question.TopicTag;
import wisepanda.exceptions.WiseNoteException;

import java.util.List;

public interface QuestionService {
    ServiceResponse bulkAddQuestion(List<QuestionDto> questionsDto) throws WiseNoteException;
    ServiceResponse addQuestion(QuestionDto data) throws WiseNoteException;
    ServiceResponse updateQuestion(QuestionDto data) throws WiseNoteException;
    ServiceResponse deleteQuestion(QuestionDto data, String deleteBy) throws WiseNoteException;

    QuestionMultipleChoice addQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws WiseNoteException;

    QuestionMultipleChoice getQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws WiseNoteException;

    List<Long> getQuestionId(List<Long> topicTagIds) throws WiseNoteException;

    Question getQuestion(QuestionDto data) throws WiseNoteException;

    TopicTag addTopicTag(TopicTagDto data) throws WiseNoteException;
    ServiceResponse addQuestionTags(QuestionTagsDto data) throws WiseNoteException;

}
