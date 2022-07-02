package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.data.enums.PhoneNumberType;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="PHONE_NUMBER")
@Entity
public class PhoneNumber {

    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_ID")
    @ToString.Exclude
    private Contact contact;

    @Column(name="NUMBER")
    @NonNull
    private String number;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_CODE_ID")
    private CountryCode countryCode;

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
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(id, that.id) && number.equals(that.number) && type == that.type && createdAt.equals(that.createdAt) && createdBy.equals(that.createdBy) && lastUpdatedAt.equals(that.lastUpdatedAt) && lastUpdatedBy.equals(that.lastUpdatedBy) && approvedAt.equals(that.approvedAt) && approvedBy.equals(that.approvedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, type, createdAt, createdBy, lastUpdatedAt, lastUpdatedBy, approvedAt, approvedBy);
    }
}
