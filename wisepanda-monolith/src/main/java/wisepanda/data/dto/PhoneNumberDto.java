package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.PhoneNumber;
import wisepanda.data.enums.PhoneNumberType;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto implements Serializable {
    @JsonIgnore
    private Long id;
    private ContactDto contact;
    private String number;
    private PhoneNumberType type;
    private CountryCodeDto countryCode;

    public void fill(PhoneNumber p) {
        if(p != null){
            p.setId(id);
            contact.fill(p.getContact());
            p.setNumber(number);
            p.setType(type);
            countryCode.fill(p.getCountryCode());
        }
    }
}
