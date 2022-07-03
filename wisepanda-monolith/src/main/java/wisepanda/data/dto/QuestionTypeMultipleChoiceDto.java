package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTypeMultipleChoiceDto implements Serializable {
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
}
