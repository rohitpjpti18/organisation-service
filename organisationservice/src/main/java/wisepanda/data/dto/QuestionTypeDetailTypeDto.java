package wisepanda.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTypeDetailTypeDto implements Serializable {
    private Long id;
    private String questionDescription;
    private QuestionDto question;
    private Boolean isApproved;
}
