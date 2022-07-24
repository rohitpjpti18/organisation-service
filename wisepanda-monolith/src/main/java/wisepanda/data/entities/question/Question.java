package wisepanda.data.entities.question;


import lombok.*;
import wisepanda.data.entities.Organisation;

import javax.persistence.*;
import java.util.Objects;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="question")
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_question_seq")
    @SequenceGenerator(name = "s_question_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="question_name")
    private String questionName;

    @ManyToOne
    @JoinColumn(name="organisation_id")
    private Organisation organisation;

    @Column(name="is_approved")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
