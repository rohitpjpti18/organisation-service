package wisepanda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.*;
import wisepanda.data.entities.*;
import wisepanda.data.entities.contact.*;
import wisepanda.data.entities.question.*;
import wisepanda.data.validators.ContactValidators;
import wisepanda.exceptions.DataNotValidException;

import java.util.Optional;

@Service
public class GeneralOrgServiceJpaImpl {

    private static final Logger log = LogManager.getLogger(GeneralOrgServiceJpaImpl.class);

    @Autowired
    private GeneralDao generalDao;

    public CountryCode addCountryCode(CountryCodeDto countryDto) throws Exception{

        if(!ContactValidators.isValidCountryCode(countryDto)){
            throw new DataNotValidException(ContactValidators.validateCountry(countryDto));
        }

        Optional<CountryCode> o = generalDao.countryCode.findByCountryCode(countryDto.getCountryCode());

        if(o.isPresent()) {
            return o.get();
        }else{
            CountryCode c = new CountryCode();
            countryDto.fill(c);
            log.info(c);
            return generalDao.countryCode.saveAndFlush(c);
        }
    }

    public Address addNewAddress(AddressDto data) throws Exception{
        Address a = new Address();
        data.fill(a);
        return generalDao.address.saveAndFlush(a);
    }

    public Email addEmailAddress(EmailDto data) throws Exception {
        Email e = new Email();
        data.fill(e);
        return generalDao.email.saveAndFlush(e);
    }

    public PhoneNumber addPhoneNumber(PhoneNumberDto data) throws Exception {
        PhoneNumber p = new PhoneNumber();
        data.fill(p);
        return generalDao.phoneNumber.saveAndFlush(p);
    }

    public Contact addContact(ContactDto data) throws Exception {
        Contact c = new Contact();
        data.fill(c);
        return generalDao.contact.saveAndFlush(c);
    }

    public Organisation addOrganisation(OrganisationDto data) throws Exception {
        Organisation o = new Organisation();
        data.fill(o);
        return generalDao.organisation.saveAndFlush(o);
    }

    public School addSchool(SchoolDto data) throws Exception {
        School s = new School();
        data.fill(s);
        return generalDao.school.saveAndFlush(s);
    }

    public Question addQuestion(QuestionDto data) throws Exception {
        Question q = new Question();
        data.fill(q);
        return generalDao.question.saveAndFlush(q);
    }

    public TopicTag addTopicTag(TopicTagDto data) throws Exception {
        TopicTag t = new TopicTag();
        data.fill(t);
        return generalDao.topicTag.saveAndFlush(t);
    }

    public QuestionTags addQuestionTags(QuestionTagsDto data) throws Exception {
        QuestionTags q = new QuestionTags();
        data.fill(q);
        return generalDao.questionTags.saveAndFlush(q);
    }

    public QuestionTypeDetailType addQuestionTypeDetailType(QuestionTypeDetailTypeDto data) throws Exception {
        QuestionDto q = data.getQuestion();
        Question qEntity;
        if(q.getId() != null && q.getId() != 0) {
            Optional<Question> o = generalDao.question.findById(q.getId());

            if(o.isPresent()) qEntity = o.get();
            else qEntity = new Question();
        } else {
            qEntity = addQuestion(q);
        }

        QuestionTypeDetailType qm = new QuestionTypeDetailType();
        data.fill(qm);

        return generalDao.questionTypeDetailType.saveAndFlush(qm);
    }

    public QuestionTypeMultipleChoice addQuestionTypeMultipleChoice(QuestionTypeMultipleChoiceDto data) throws Exception {
        QuestionDto q = data.getQuestion();
        Question qEntity;
        if(q.getId() != null && q.getId() != 0) {
            Optional<Question> o = generalDao.question.findById(q.getId());

            if(o.isPresent()) qEntity = o.get();
            else qEntity = new Question();
        } else {
            qEntity = addQuestion(q);
        }

        QuestionTypeMultipleChoice qm = new QuestionTypeMultipleChoice();
        data.fill(qm);
        qm.setQuestion(qEntity);

        return generalDao.questionTypeMultipleChoice.saveAndFlush(qm);
    }
}
