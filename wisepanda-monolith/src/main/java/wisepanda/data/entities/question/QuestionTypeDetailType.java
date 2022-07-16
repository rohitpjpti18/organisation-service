package wisepanda.data.entities.question;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="question_type_detail_type")
@Entity
public class QuestionTypeDetailType {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_type_detail_type_seq")
    @SequenceGenerator(name = "question_type_detail_type_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="question_description")
    @NonNull
    private String questionDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id")
    @ToString.Exclude
    private Question question;

    @Column(name="is_approved")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTypeDetailType that = (QuestionTypeDetailType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
