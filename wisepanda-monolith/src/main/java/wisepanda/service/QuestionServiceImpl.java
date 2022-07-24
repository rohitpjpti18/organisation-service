package wisepanda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisepanda.common.ErrorConstants;
import wisepanda.data.dao.GeneralDao;
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
import wisepanda.utils.InputValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private GeneralDao generalDao;

    @Override
    public List<Question> bulkAddQuestion(List<QuestionDto> questionsDto) {
        return null;
    }

    @Override
    public Question addQuestion(QuestionDto data) throws InValidDataException {
        if(data == null) throw new InValidDataException(new HashMap<String, String>());
        Question q = new Question();
        data.fill(q);
        return generalDao.question.saveAndFlush(q);
    }

    @Override
    public QuestionMultipleChoice addQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws
            InValidDataException,
            BadInputException {
        data = InputValidator.validate(data);
        
        QuestionMultipleChoice q = new QuestionMultipleChoice();
        data.fill(q);
        q.setQuestion(getQuestion(data.getQuestion()));
        
        return generalDao.questionTypeMultipleChoice.saveAndFlush(q);
    }

    @Override
    public QuestionMultipleChoice getQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws DataNotFoundException, BadInputException {
        //data = InputValidator.validate(data);
        if(data == null) new BadInputException(ErrorConstants.DATA_PROVIDED + data);
        QuestionMultipleChoice qm = new QuestionMultipleChoice();
        qm = null;

        if(data.getQuestion() != null && data.getQuestion().getId() != null) {
            qm = generalDao.questionTypeMultipleChoice.findByQuestionId(data.getQuestion().getId());
        }else if(data.getId() != null) {
            Optional<QuestionMultipleChoice> oqm = generalDao.questionTypeMultipleChoice.findById(data.getId());
            if(oqm.isPresent()) qm = oqm.get();
        }

        if(qm == null) {
            throw new DataNotFoundException(ErrorConstants.NOT_FOUND_QUES_MULTIPLE_CHOICE);
        }

        return qm;
    }

    @Override
    public List<Long> getQuestionId(List<Long> topicTagIds) throws WiseNoteException {
        List<Long> questions = generalDao.questionTags.findByTopicId(topicTagIds);

        return questions;
    }


    @Override
    public Question getQuestion(QuestionDto data) throws
            InValidDataException,
            BadInputException {
        if(data == null) throw new BadInputException(ErrorConstants.DATA_PROVIDED + "is null");

        if(data.getId() != null) {
            Optional<Question> q = generalDao.question.findById(data.getId());
            if(!q.isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put(ErrorConstants.DATA_NOT_FOUND, ErrorConstants.NOT_FOUND_QUESTION);
                throw new InValidDataException(error);
            }
            return q.get();
        }else{
            throw new BadInputException(ErrorConstants.DATA_PROVIDED + data);
        }
    }


    @Override
    public TopicTag addTopicTag(TopicTagDto data) throws WiseNoteException {
        data = InputValidator.validate(data);
        TopicTag t = new TopicTag();
        data.fill(t);
        return generalDao.topicTag.saveAndFlush(t);
    }
}
