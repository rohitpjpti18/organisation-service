package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.enums.PhoneNumberType;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumberDto implements Serializable {
    private Long id;
    private ContactDto contact;
    private String number;
    private PhoneNumberType type;
    private CountryCodeDto countryCode;
    private Instant createdAt;
    private Long createdBy;
    private Instant lastUpdatedAt;
    private Long lastUpdatedBy;
    private Instant approvedAt;
    private Long approvedBy;
}
