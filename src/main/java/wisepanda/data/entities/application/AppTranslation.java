package wisepanda.data.entities.application;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="app_translation")
@Entity
public class AppTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_app_translation_seq")
    @SequenceGenerator(name = "s_app_translation_seq", allocationSize = 1)
    private Long id;

    @Column(name="application")
    private String application;

    @Column(name="ui_group")
    private String uiGroup;

    @Column(name="key")
    private String key; 

    @Column(name="english")
    private String english;

    @Column(name="hindi")
    private String hindi;

    @Column(name="created_on")
    private Instant createdOn;

    @Column(name="created_by")
    private String createdBy;

}
