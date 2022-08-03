package wisepanda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import wisepanda.common.Constants;
import wisepanda.common.ErrorConstants;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.app.ServiceResponse;
import wisepanda.data.dto.question.QuestionDto;
import wisepanda.data.dto.question.QuestionMultipleChoiceDto;
import wisepanda.data.dto.question.QuestionTagsDto;
import wisepanda.data.dto.question.TopicTagDto;
import wisepanda.data.entities.Organisation;
import wisepanda.data.entities.question.Question;
import wisepanda.data.entities.question.QuestionMultipleChoice;
import wisepanda.data.entities.question.QuestionTags;
import wisepanda.data.entities.question.TopicTag;
import wisepanda.enums.ErrorType;
import wisepanda.exceptions.InValidDataException;
import wisepanda.exceptions.WiseNoteException;
import wisepanda.utils.InputValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService{
    private static final Logger log = LogManager.getLogger(QuestionServiceImpl.class);

    @Autowired
    private GeneralDao generalDao;

    @Override
    public ServiceResponse bulkAddQuestion(List<QuestionDto> questionsDto) throws WiseNoteException {
        return null;
    }

    @Override
    public ServiceResponse addQuestion(QuestionDto data) throws WiseNoteException {
        data = InputValidator.validate(data);

        Question q = new Question();
        data.fill(q);
        q = generalDao.question.saveAndFlush(q);
        ServiceResponse s = new ServiceResponse();
        s.setHttpStatus(HttpStatus.OK);
        s.setResult(q);

        return s;
    }

    @Override
    public Question getQuestion(QuestionDto data) throws WiseNoteException{
        if(data == null) throw new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);

        if(data.getId() != null) {
            Optional<Question> q = generalDao.question.findById(data.getId());
            if(!q.isPresent()) {
                Map<String, String> error = new HashMap<>();
                error.put(ErrorConstants.DATA_NOT_FOUND, ErrorConstants.NOT_FOUND_QUESTION);
                throw new InValidDataException(error);
            }
            return q.get();
        }else{
            Map<String, String> error = new HashMap<>();
            error.put(ErrorType.ERROR_INPUT_INVALID.code, "Id is null");
            throw new InValidDataException(error);
        }
    }

    @Override
    public ServiceResponse updateQuestion(QuestionDto data) throws WiseNoteException {
        data = InputValidator.validate(data);

        ServiceResponse r = new ServiceResponse();
        Question q = generalDao.question.findById(data.getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND));
        Organisation o = generalDao.organisation.findById(data.getOrganisation().getId()).orElseThrow(() -> new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND));
        q.setOrganisation(o);
        q.setIsApproved(data.getIsApproved());
        q.setQuestionName(data.getQuestionName());

        r.setResult(generalDao.question.saveAndFlush(q));
        r.setHttpStatus(HttpStatus.OK);

        return r;
    }

    @Override
    public ServiceResponse deleteQuestion(QuestionDto data, String deleteBy) throws WiseNoteException {
        switch(deleteBy) {
            case Constants.DELETE_BY_QUESTION_ID: {
                if(data.getId() == null)
                    throw new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);

                generalDao.question.deleteById(data.getId());
                ServiceResponse s = new ServiceResponse();
                s.setHttpStatus(HttpStatus.OK);
                s.setResult("Question with id: " + data.getId() + " deleted successfully." );
                return s;
            }
            case Constants.DELETE_BY_QUESTION_NAME: {
                if(data.getQuestionName() == null)
                    throw new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
                break;
            }
            case Constants.DELETE_BY_ORGANISATION_ID: {
                if(data.getOrganisation().getId() == null) 
                    throw new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
                break;
            }
            default: {
                throw new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
            }
        }
        return null;
    }

    @Override
    public QuestionMultipleChoice addQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws WiseNoteException {
        data = InputValidator.validate(data);
        
        QuestionMultipleChoice q = new QuestionMultipleChoice();
        data.fill(q);
        q.setQuestion(getQuestion(data.getQuestion()));
        
        return generalDao.questionTypeMultipleChoice.saveAndFlush(q);
    }

    @Override
    public QuestionMultipleChoice getQuestionMultipleChoice(QuestionMultipleChoiceDto data) throws WiseNoteException {
        //data = InputValidator.validate(data);
        if(data == null) new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
        QuestionMultipleChoice qm = new QuestionMultipleChoice();
        qm = null;

        if(data.getQuestion() != null && data.getQuestion().getId() != null) {
            qm = generalDao.questionTypeMultipleChoice.findByQuestionId(data.getQuestion().getId());
        }else if(data.getId() != null) {
            Optional<QuestionMultipleChoice> oqm = generalDao.questionTypeMultipleChoice.findById(data.getId());
            if(oqm.isPresent()) qm = oqm.get();
        }

        if(qm == null) {
            throw new WiseNoteException(ErrorType.ERROR_INPUT_INVALID);
        }

        return qm;
    }
    

    @Override
    public TopicTag addTopicTag(TopicTagDto data) throws WiseNoteException {
        data = InputValidator.validate(data);
        TopicTag t = new TopicTag();
        data.fill(t);
        return generalDao.topicTag.saveAndFlush(t);
    }

    @Override
    public ServiceResponse addQuestionTags(QuestionTagsDto data) throws WiseNoteException {
        data = InputValidator.validate(data);

        // Check if 
        //      1. question and topicTag with given id exists or not 
        //      2. questiontag with given topicTag and question is already present;
        Optional<TopicTag> ot = generalDao.topicTag.findById(data.getTopicTag().getId());
        Optional<Question> oq = generalDao.question.findById(data.getQuestion().getId());
        Optional<QuestionTags> oqt = generalDao.questionTags.findByQuesAndTag(data.getQuestion().getId(), data.getTopicTag().getId());
        log.info("Hello");

        if(!ot.isPresent()) {
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND, "Data doesnot exist for topic tag id: " + Long.toString(data.getTopicTag().getId()));
            throw err;
        }else if(!oq.isPresent()){
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_ENTITY_NOT_FOUND);
            throw err;
        }else if(oqt.isPresent()){
            WiseNoteException err = new WiseNoteException(ErrorType.ERROR_DUPLICATE_DATA, "Question Tag Data with question id: " 
            +  Long.toString(data.getQuestion().getId()) 
            + " topic tag id: " 
            + Long.toString(data.getTopicTag().getId()) 
            + " already exists.");
            throw err;
        }
        

        QuestionTags q = new QuestionTags();
        q.setTopicTag(ot.get());
        q.setQuestion(oq.get());
        log.info(q);
        q = generalDao.questionTags.saveAndFlush(q);
        log.info(q);
        ServiceResponse s = new ServiceResponse();
        s.setHttpStatus(HttpStatus.OK);
        s.setResult(q);

        return s;
    }

    @Override
    public List<Long> getQuestionId(List<Long> topicTagIds) throws WiseNoteException {
        List<Long> questions = generalDao.questionTags.findByTopicId(topicTagIds);

        return questions;
    }
}
