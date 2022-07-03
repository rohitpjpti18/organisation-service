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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
