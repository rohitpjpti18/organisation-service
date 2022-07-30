package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.enums.AddressType;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="address")
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_address_seq")
    @SequenceGenerator(name = "s_address_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private Contact contact;

    @Column(name="type")
    private AddressType addressType;

    @Column(name="details")
    private String detail;

    @Column(name="is_approved")
    private Boolean isApproved = false;

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
