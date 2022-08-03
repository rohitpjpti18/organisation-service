package wisepanda.data.entities.contact;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="country_code")
@Entity
public class CountryCode {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_country_code_seq")
    @SequenceGenerator(name = "s_country_code_seq")
    private Long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "country_code", unique = true)
    private String countryCode;

    @Column(name="is_approved")
    private Boolean isApproved = false;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryCode that = (CountryCode) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
