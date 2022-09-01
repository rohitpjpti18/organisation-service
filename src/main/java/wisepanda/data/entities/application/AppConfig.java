package wisepanda.data.entities.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.Instant;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="app_config")
@Entity
public class AppConfig {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_app_config_seq")
    @SequenceGenerator(name = "s_app_config_seq", allocationSize = 1)
    private Long id;

    @Column(name="application")
    private String application;

    @Column(name="group")
    private String group;

    @Column(name="key")
    private String key;

    @Column(name="value")
    private String value;

    @Column(name="created_on")
    private Instant createdOn;

    @Column(name="created_by")
    private Instant createdBy;
}