package in.dreamplug.jobservice.domain;

import com.google.common.base.Strings;

import java.sql.Timestamp;
import java.util.UUID;

public class ExternalBase extends Base{

    private String jobId;

    public ExternalBase(Timestamp createdAt, Timestamp updatedAt, String jobId){
        super(createdAt, updatedAt);
        this.jobId = jobId;
    }

    public void prePersist() {
        super.prePersist();
        if (Strings.isNullOrEmpty(this.jobId)) {
            this.jobId = UUID.randomUUID().toString();
        }

    }

    public void preUpdate() {
        super.preUpdate();
        if (Strings.isNullOrEmpty(this.jobId)) {
            this.jobId = UUID.randomUUID().toString();
        }

    }

    public String getJobId() {
        return this.jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public ExternalBase() {
    }


}
