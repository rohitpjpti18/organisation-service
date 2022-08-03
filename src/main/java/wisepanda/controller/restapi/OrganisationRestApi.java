package wisepanda.controller.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.OrganisationDto;
import wisepanda.data.entities.Organisation;
import wisepanda.exceptions.InValidDataException;
import wisepanda.service.OrganisationService;

@RestController
@RequestMapping(ApiConstants.REST_URL_ORGANISATION_ROOT)
public class OrganisationRestApi {
    @Autowired
    private OrganisationService organisationService;

    @PostMapping(ApiConstants.REST_URL_ORGANISATION)
    public ResponseEntity<Object> addNewOrganisation(@RequestBody OrganisationDto data) throws InValidDataException {
        Organisation o = organisationService.addOrganisation(data);

        return new ResponseEntity<Object>(new OrganisationDto(o), HttpStatus.ACCEPTED);
    }
}
