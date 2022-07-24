package wisepanda.service;

import wisepanda.data.dto.QuestionDto;
import wisepanda.data.dto.QuestionMultipleChoiceDto;
import wisepanda.data.dto.TopicTagDto;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.QuestionMultipleChoice;
import wisepanda.data.entities.question.TopicTag;
import wisepanda.exceptions.BadInputException;
import wisepanda.exceptions.DataNotFoundException;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;

import java.util.List;

public interface QuestionService {
    List<Question> bulkAddQuestion(List<QuestionDto> questionsDto);
    Question addQuestion(QuestionDto data) throws InValidDataException;

    QuestionMultipleChoice addQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws InValidDataException, BadInputException;

    QuestionMultipleChoice getQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws DataNotFoundException, InValidDataException, BadInputException;

    List<Long> getQuestionId(List<Long> topicTagIds) throws WiseNoteException;

    Question getQuestion(QuestionDto data) throws
            InValidDataException,
            BadInputException;

    TopicTag addTopicTag(TopicTagDto data) throws WiseNoteException;
}
