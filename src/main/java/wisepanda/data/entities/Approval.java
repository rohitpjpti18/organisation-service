package wisepanda.data.entities;

import javax.persistence.Column;
import javax.persistence.Id;
import java.time.Instant;

public class Approval {
    @Id
    private Long id;

    @Column(name="ENTITY_NAME")
    private String entityName;

    @Column(name="ENTITY_ID")
    private Long entityId;

    @Column(name="WRITE_TIME")
    private Instant writeTime;

    @Column(name="WRITTEN_BY")
    private Long writtenBy;

    @Column(name="APPROVED_AT")
    private Instant approvedAt;

    @Column(name="APPROVED_BY")
    private Long approvedBy;
}
