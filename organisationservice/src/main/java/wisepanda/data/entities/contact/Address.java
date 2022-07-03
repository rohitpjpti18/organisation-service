package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.data.enums.AddressType;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="ADDRESS")
@Entity
public class Address {
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    @ToString.Exclude
    private Contact contact;

    @Column(name="TYPE")
    private AddressType addressType;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
