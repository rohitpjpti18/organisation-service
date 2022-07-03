package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.CountryCode;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryCodeDto implements Serializable {
    private Long id;
    private String countryName;
    private String countryCode;

    public void fill(CountryCode c) {
        c.setId(id);
        c.setCountryName(countryName);
        c.setCountryCode(countryCode);
    }
}
