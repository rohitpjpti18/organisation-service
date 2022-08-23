package wisepanda.data.dto.question;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import wisepanda.data.dto.OrganisationDto;
import wisepanda.data.entities.question.Question;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto implements Serializable {
    private Long id = 0l;
    @JsonAlias({"question_name"})
    private String questionName;
    private OrganisationDto organisation = new OrganisationDto();
    @JsonAlias({"is_approved"})
    private Boolean isApproved = false;

    public QuestionDto(Question q) {
        if(q != null) {
            this.id = q.getId();
            this.questionName = q.getQuestionName();
            this.isApproved = q.getIsApproved();
            this.organisation = new OrganisationDto(q.getOrganisation());
        }
    }

    public void fill(Question q) {
        if(q != null) {
            q.setId(id);
            q.setQuestionName(questionName);
            organisation.fill(q.getOrganisation());
            q.setIsApproved(isApproved);
        }
    }
}
