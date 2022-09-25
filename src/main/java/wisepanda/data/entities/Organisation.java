package wisepanda.data.entities;

import lombok.*;
import wisepanda.data.entities.contact.Contact;

import javax.persistence.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="organisation")
@Entity
public class Organisation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_organisation_seq")
    @SequenceGenerator(name = "s_organisation_seq", allocationSize = 1)
    private Long id;

    @Column(name="name")
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="contact_id")
    @ToString.Exclude
    private Contact contact;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organisation that = (Organisation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
