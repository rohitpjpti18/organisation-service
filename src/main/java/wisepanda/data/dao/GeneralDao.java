package wisepanda.data.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import wisepanda.common.Constants;
import wisepanda.data.entities.application.AppConfig;
import wisepanda.data.entities.question.Question;
import wisepanda.data.repositories.*;
import wisepanda.service.AppConfigService;


@Component
public class GeneralDao {
    private static final Logger log = LogManager.getLogger(GeneralDao.class);

    @Autowired
    public AddressRepository address;

    @Autowired
    public ContactRepository contact;

    @Autowired
    public CountryCodeRepository countryCode;

    @Autowired
    public EmailRepository email;

    @Autowired
    public OrganisationRepository organisation;

    @Autowired
    public PhoneNumberRepository phoneNumber;

    @Autowired
    public SchoolRepository school;

    @Autowired
    public QuestionRepository question;

    @Autowired
    public QuestionTypeDetailTypeRepository questionTypeDetailType;

    @Autowired
    public QuestionMultipleChoiceRepository questionTypeMultipleChoice;

    @Autowired
    public TopicTagRepository topicTag;

    @Autowired
    public QuestionTagsRepository questionTags;

    @Autowired
    public AppConfigRepository appConfig;

    @PersistenceContext
    private EntityManager entityManager;

    public List<AppConfig> getAppConfig(int start, int size) {
        System.out.println("here");
        Query q = entityManager.createQuery("FROM AppConfig", AppConfig.class);
        //q.setFirstResult(start);
        //q.setMaxResults(size);

        System.out.println("Here");

        return (List<AppConfig>) q.getResultList();
    }

    public List<Question> getQuestionByTopicTags(List<String> tags, int count) {
        Query q = entityManager.createNativeQuery(AppConfigService.get(Constants.GROUP_QUESTION_TAGS, Constants.KEY_GETQUESTIONS_FOR_TAGS_SQL));


        q.setParameter(1, tags);
        q.setParameter(2, count);

        List<Object[]> result = q.getResultList();
        log.debug(result);
        List<List<Object>> finalList = new ArrayList<>();
        List<Long> ids = new ArrayList<>();
        for(Object[] row: result) {
            List<Object> rowVals = new ArrayList<>();
            rowVals.add((BigInteger)row[0]);
            rowVals.add((String) row[1]);
            rowVals.add((BigInteger)row[2]);

            finalList.add(rowVals);

            ids.add(((BigInteger)row[0]).longValue());
        }
        List<Question> questions = question.findAllById(ids);
        return questions;
    }
}
