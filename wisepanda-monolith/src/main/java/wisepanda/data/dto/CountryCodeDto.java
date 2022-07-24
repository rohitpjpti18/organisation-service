package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonAlias({"country_name"})
    private String countryName;
    @JsonAlias({"country_code"})
    private String countryCode;
    @JsonAlias({"is_approved"})
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
