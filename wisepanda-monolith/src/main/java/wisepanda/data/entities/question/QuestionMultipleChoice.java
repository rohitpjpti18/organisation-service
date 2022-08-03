package wisepanda.data.entities.question;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="question_type_multiple_choice")
@Entity
public class QuestionMultipleChoice {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_question_type_multiple_choice_seq")
    @SequenceGenerator(name = "s_question_type_multiple_choice_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="question_description")
    @NonNull
    private String questionDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="question_id")
    @ToString.Exclude
    private Question question;

    @Column(name="multiple_choice")
    private Boolean multipleChoice;

    @Column(name="option_1")
    @NonNull
    private String option1;

    @Column(name="option_2")
    @NonNull
    private String option2;

    @Column(name="option_3")
    private String option3;

    @Column(name="option_4")
    private String option4;

    @Column(name="option_5")
    private String option5;

    @Column(name="option_6")
    private String option6;

    @Column(name="is_approved")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionMultipleChoice that = (QuestionMultipleChoice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
