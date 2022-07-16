package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.question.QuestionTypeDetailType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTypeDetailTypeDto implements Serializable {
    private Long id;
    private String questionDescription;
    private QuestionDto question;
    private Boolean isApproved;

    public void fill(QuestionTypeDetailType q) {
        if(q != null) {
            q.setId(id);
            q.setQuestionDescription(questionDescription);
            question.fill(q.getQuestion());
            q.setIsApproved(isApproved);
        }
    }
}
