package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import wisepanda.data.entities.question.Question;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto implements Serializable {
    private Long id;
    private String questionName;
    private Boolean isApproved;

    public void fill(Question q) {
        if(q != null) {
            q.setId(id);
            q.setQuestionName(questionName);
            q.setIsApproved(isApproved);
        }
    }
}
