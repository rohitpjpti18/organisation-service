package wisepanda.data.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    public QuestionTypeMultipleChoiceRepository questionTypeMultipleChoice;

    @Autowired
    public TopicTagRepository topicTag;

    @Autowired
    public QuestionTagsRepository questionTags;
}
