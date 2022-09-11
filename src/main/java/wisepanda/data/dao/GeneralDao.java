package wisepanda.data.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import wisepanda.data.entities.application.AppConfig;
import wisepanda.data.repositories.*;


@Component
public class GeneralDao {
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
}
