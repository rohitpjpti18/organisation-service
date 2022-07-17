package wisepanda.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.Address;
import wisepanda.data.enums.AddressType;

import java.io.Serializable;
import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
    @JsonIgnore
    private Long id;
    @JsonIgnore
    private ContactDto contact;
    private AddressType addressType;
    private String detail;
    private Boolean isApproved = false;

    public AddressDto(Address a) {
        this.id = a.getId();
        this.contact = new ContactDto(a.getContact());
        this.addressType = a.getAddressType();
        this.isApproved = a.getIsApproved();
        this.detail = a.getDetail();
    }

    public void fill(Address a) {
        if(a != null){
            a.setId(id);
            contact.fill(a.getContact());
            a.setAddressType(addressType);
            a.setIsApproved(this.isApproved);
            a.setDetail(detail);
        }
    }
}
