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
@Table(name="phone_number")
@Entity
public class PhoneNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_phone_number_seq")
    @SequenceGenerator(name = "s_phone_number_seq")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id")
    @ToString.Exclude
    private Contact contact;

    @Column(name="number")
    private String number;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private PhoneNumberType type;

    @ManyToOne
    @JoinColumn(name = "country_code_id")
    private CountryCode countryCode;

    @Column(name="is_verified")
    private Boolean isVerified;

    @Column(name="is_active")
    private Boolean isActive = false;

    @Column(name="activated_on")
    private Instant activatedOn;

    @Column(name="activation_method")
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
