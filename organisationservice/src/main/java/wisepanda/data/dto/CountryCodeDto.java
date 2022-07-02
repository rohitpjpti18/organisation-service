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
    private Instant createdAt;
    private Long createdBy;
    private Instant lastUpdatedAt;
    private Long lastUpdatedBy;
    private Instant approvedAt;
    private Long approvedBy;

    public void fill(CountryCode c) {
        c.setId(id);
        c.setCountryName(countryName);
        c.setCountryCode(countryCode);
        c.setCreatedAt(createdAt);
        c.setCreatedBy(createdBy);
        c.setLastUpdatedAt(lastUpdatedAt);
        c.setLastUpdatedBy(lastUpdatedBy);
        c.setApprovedAt(approvedAt);
        c.setApprovedBy(approvedBy);
    }
}
