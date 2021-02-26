package in.dreamplug.jobportal.domain.job;

import com.google.common.base.Strings;
import in.dreamplug.jobportal.domain.Base;

import java.sql.Timestamp;
import java.util.UUID;

public class ExternalBase extends Base {

    private String jobId;

    public ExternalBase(Long id, Timestamp createdAt, Timestamp updatedAt, String jobId){
        super(id, createdAt, updatedAt);
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

    public ExternalBase(){
    }


}
