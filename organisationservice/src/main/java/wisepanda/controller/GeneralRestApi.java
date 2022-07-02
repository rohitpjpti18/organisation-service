package wisepanda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import wisepanda.common.ApiConstants;
import wisepanda.data.dto.CountryCodeDto;
import wisepanda.data.entities.contact.CountryCode;
import wisepanda.service.GeneralOrgServiceJpaImpl;

@RestController
@RequestMapping(ApiConstants.GENERAL_REST_API_BASE)
public class GeneralRestApi {

    @Autowired
    private GeneralOrgServiceJpaImpl generalService;

    @PostMapping(value=ApiConstants.ADD_COUNTRY_CODE)
    public ResponseEntity addEntity(CountryCodeDto countryCodeDto) throws Exception{
        CountryCode c = generalService.addCountryCode(countryCodeDto);

        return new ResponseEntity(c, HttpStatus.ACCEPTED);
    }

}
