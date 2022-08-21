package wisepanda.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import wisepanda.common.ErrorConstants;
import wisepanda.data.dao.GeneralDao;
import wisepanda.data.dto.OrganisationDto;
import wisepanda.data.entities.Organisation;
import wisepanda.data.entities.contact.Contact;
import wisepanda.exceptions.InValidDataException;
import wisepanda.utils.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class OrganisationServiceImpl implements OrganisationService {
    private static final Logger log = LogManager.getLogger(ContactServiceImpl.class);

    @Autowired
    private GeneralDao generalDao;

    @Autowired
    private InputValidator validationService;

    @Override
    public Organisation addOrganisation(OrganisationDto data) throws InValidDataException {
        data = validationService.validate(data);
        log.info(data);

        Organisation o = new Organisation();
        data.fill(o);
        return generalDao.organisation.saveAndFlush(o);
    }

    public Map<String, String> validateOrganisation(OrganisationDto data) {
        Map<String, String> errors = new HashMap<>();

        if(data.getName() == null) errors.put(ErrorConstants.EMPTY_ORG_NAME, ErrorConstants.EMPTY_ORG_NAME_MSG);
        if(data.getContact() == null || data.getContact().getId() == null) errors.put(ErrorConstants.EMPTY_CONTACT, ErrorConstants.EMPTY_CONTACT_MSG);
        else {
            Optional<Contact> c = generalDao.contact.findById(data.getContact().getId());
            if(!c.isPresent()) {
                errors.put(ErrorConstants.CONTACT_NOT_FOUND, ErrorConstants.CONTACT_NOT_FOUND_MSG);
            }
        }

        return errors;
    }
}
