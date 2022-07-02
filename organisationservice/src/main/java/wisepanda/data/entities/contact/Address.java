package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.data.enums.AddressType;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
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

    @Column(name="CREATED_AT")
    @NonNull
    private Instant createdAt;

    @Column(name="CREATED_BY")
    @NonNull
    private Long createdBy;

    @Column(name="LAST_UPDATED_AT")
    @NonNull
    private Instant lastUpdatedAt;

    @Column(name="LAST_UPDATED_BY")
    @NonNull
    private Long lastUpdatedBy;

    @Column(name="APPROVED_AT")
    @NonNull
    private Instant approvedAt;

    @Column(name="APPROVED_BY")
    @NonNull
    private Long approvedBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(id, address.id) && addressType == address.addressType && createdAt.equals(address.createdAt) && createdBy.equals(address.createdBy) && lastUpdatedAt.equals(address.lastUpdatedAt) && lastUpdatedBy.equals(address.lastUpdatedBy) && approvedAt.equals(address.approvedAt) && approvedBy.equals(address.approvedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressType, createdAt, createdBy, lastUpdatedAt, lastUpdatedBy, approvedAt, approvedBy);
    }
}
