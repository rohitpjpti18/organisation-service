package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.enums.AddressType;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
    @JsonIgnore
    private Long id;
    private ContactDto contact;
    private AddressType addressType;
    private Instant createdAt;
    private Long createdBy;
    private Instant lastUpdatedAt;
    private Long lastUpdatedBy;
    private Instant approvedAt;
    private Long approvedBy;
}
