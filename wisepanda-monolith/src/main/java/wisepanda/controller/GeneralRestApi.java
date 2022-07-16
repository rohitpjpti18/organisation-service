package wisepanda.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.CountryCodeDto;
import wisepanda.data.dto.PhoneNumberDto;
import wisepanda.data.entities.contact.CountryCode;
import wisepanda.exceptions.DataNotValidException;
import wisepanda.service.GeneralOrgServiceJpaImpl;

@RestController
@RequestMapping(ApiConstants.GENERAL_REST_API_BASE)
public class GeneralRestApi {
    private static final Logger log = LogManager.getLogger(GeneralRestApi.class);

    @Autowired
    private GeneralOrgServiceJpaImpl generalService;

    @PostMapping(value=ApiConstants.ADD_COUNTRY_CODE)
    public ResponseEntity addEntity(@RequestBody CountryCodeDto countryCodeDto) throws DataNotValidException, Exception {
        CountryCode c = generalService.addCountryCode(countryCodeDto);
        return new ResponseEntity(new CountryCodeDto(c), HttpStatus.ACCEPTED);
    }

    @PostMapping(value=ApiConstants.ADD_PHONE_NUMBER)
    public ResponseEntity addPhoneNumber(@RequestBody PhoneNumberDto phoneNumber) throws
}
