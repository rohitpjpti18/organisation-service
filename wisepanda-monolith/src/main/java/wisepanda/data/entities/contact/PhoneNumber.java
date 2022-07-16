package wisepanda.data.entities.contact;

import lombok.*;
import wisepanda.data.enums.ActivationMethod;
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
    private String number;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;

    @ManyToOne
    @JoinColumn(name = "COUNTRY_CODE_ID")
    private CountryCode countryCode;

    @Column(name="IS_VERIFIED")
    private Boolean isVerified;

    @Column(name="IS_ACTIVE")
    private Boolean isActive;

    @Column(name="ACTIVATED_ON")
    private Instant instant;

    @Column(name="ACTIVATION_METHOD")
    private ActivationMethod activationMethod;

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
