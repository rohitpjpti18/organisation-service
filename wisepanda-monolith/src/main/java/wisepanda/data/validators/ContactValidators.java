package wisepanda.data.validators;

import wisepanda.common.ErrorConstants;
import wisepanda.data.dto.CountryCodeDto;
import wisepanda.data.entities.contact.CountryCode;

import java.util.HashMap;
import java.util.Map;

public class ContactValidators {

    public static boolean isValidCountryCode(CountryCode c) {
        if(!validateCountry(new CountryCodeDto(c)).isEmpty()) return false;
        return true;
    }

    public static boolean isValidCountryCode(CountryCodeDto c) {
        if(!validateCountry(c).isEmpty()) return false;
        return true;
    }

    public static Map<String, String> validateCountry(CountryCodeDto c) {
        Map<String, String> errors = new HashMap<>();

        if(!c.getCountryCode().startsWith("+")){
            errors.put(ErrorConstants.INVALID_COUNTRY_CODE, ErrorConstants.INVALID_COUNTRY_CODE_MSG);
        }

        return errors;
    }
}
