package wisepanda.service;


import wisepanda.data.dto.OrganisationDto;
import wisepanda.data.entities.Organisation;
import wisepanda.exceptions.InValidDataException;

public interface OrganisationService {
    Organisation addOrganisation(OrganisationDto data) throws InValidDataException;
}
