package wisepanda.service;

import wisepanda.data.dto.ServiceResponse;
import wisepanda.data.dto.question.QuestionDto;
import wisepanda.data.dto.question.QuestionMultipleChoiceDto;
import wisepanda.data.dto.question.QuestionTagsDto;
import wisepanda.data.dto.question.TopicTagDto;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.QuestionMultipleChoice;
import wisepanda.data.entities.question.TopicTag;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;

import java.util.List;

public interface QuestionService {
    List<Question> bulkAddQuestion(List<QuestionDto> questionsDto) throws WiseNoteException;
    Question addQuestion(QuestionDto data) throws WiseNoteException;

    QuestionMultipleChoice addQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws WiseNoteException;

    QuestionMultipleChoice getQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws WiseNoteException;

    List<Long> getQuestionId(List<Long> topicTagIds) throws WiseNoteException;

    Question getQuestion(QuestionDto data) throws WiseNoteException;

    TopicTag addTopicTag(TopicTagDto data) throws WiseNoteException;
    ServiceResponse addQuestionTags(QuestionTagsDto data) throws WiseNoteException;
}
