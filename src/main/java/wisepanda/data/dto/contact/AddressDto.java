package wisepanda.data.dto.contact;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.contact.Address;
import wisepanda.enums.AddressType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto implements Serializable {
    private Long id;
    private ContactDto contact;
    @JsonAlias({"address_type"})
    private AddressType addressType;
    private String detail;
    @JsonAlias({"is_approved"})
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
