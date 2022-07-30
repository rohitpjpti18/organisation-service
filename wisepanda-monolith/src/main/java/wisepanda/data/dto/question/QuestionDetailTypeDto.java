package wisepanda.data.dto.question;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.question.QuestionDetailType;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDetailTypeDto implements Serializable {
    private Long id;
    private String questionDescription;
    private QuestionDto question;
    private Boolean isApproved;

    public void fill(QuestionDetailType q) {
        if(q != null) {
            q.setId(id);
            q.setQuestionDescription(questionDescription);
            question.fill(q.getQuestion());
            q.setIsApproved(isApproved);
        }
    }
}
