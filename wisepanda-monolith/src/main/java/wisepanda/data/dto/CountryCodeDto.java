package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Long id;
    private String countryName;
    private String countryCode;
    private Boolean isApproved = false;

    public CountryCodeDto(CountryCode c) {
        id = c.getId();
        countryName = c.getCountryName();
        countryCode = c.getCountryCode();
        isApproved = c.getIsApproved();
    }

    public void fill(CountryCode c) {
        c.setId(this.id);
        c.setCountryName(this.countryName);
        c.setCountryCode(this.countryCode);
        c.setIsApproved(this.isApproved);
    }
}
