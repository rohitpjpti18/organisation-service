package wisepanda.data.entities;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name="QUESTION_TYPE_MULTIPLE_CHOICE")
@Entity
public class QuestionTypeMultipleChoice {
    @Id
    private Long id;

    @Column(name="QUESTION_DESCRIPTION")
    @NonNull
    private String questionDescription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="QUESTION_ID")
    @ToString.Exclude
    private Question question;

    @Column(name="MULTIPLE_CHOICE")
    private Boolean multipleChoice;

    @Column(name="OPTION_1")
    @NonNull
    private String option1;

    @Column(name="OPTION_2")
    @NonNull
    private String option2;

    @Column(name="OPTION_3")
    private String option3;

    @Column(name="OPTION_4")
    private String option4;

    @Column(name="OPTION_5")
    private String option5;

    @Column(name="OPTION_6")
    private String option6;

    @Column(name="IS_APPROVED")
    private Boolean isApproved;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTypeMultipleChoice that = (QuestionTypeMultipleChoice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
