package wisepanda.data.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.question.QuestionMultipleChoice;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionMultipleChoiceDto implements Serializable {
    private Long id;
    private String questionDescription;
    private QuestionDto question;
    private Boolean multipleChoice;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String option5;
    private String option6;
    private Boolean isApproved;

    public void fill(QuestionMultipleChoice q){
        if(q != null) {
            q.setId(id);
            q.setQuestionDescription(questionDescription);
            question.fill(q.getQuestion());
            q.setOption1(option1);
            q.setOption2(option2);
            q.setOption3(option3);
            q.setOption4(option4);
            q.setOption5(option5);
            q.setOption6(option6);
            q.setIsApproved(isApproved);
        }
    }
}
