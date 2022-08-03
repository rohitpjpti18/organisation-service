package wisepanda.data.dto.contact;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.CountryCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryCodeDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonProperty("country-name")
    private String countryName;
    @JsonProperty("country-code")
    private String countryCode;
    @JsonProperty("is-approved")
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
