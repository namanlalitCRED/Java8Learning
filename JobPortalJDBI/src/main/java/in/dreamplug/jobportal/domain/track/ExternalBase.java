package in.dreamplug.jobportal.domain.track;

import com.google.common.base.Strings;
import in.dreamplug.jobportal.domain.Base;

import java.sql.Timestamp;
import java.util.UUID;

public class ExternalBase extends Base {

    private String externalId;

    public ExternalBase(Long id, Timestamp createdAt, Timestamp updatedAt, String externalId){
        super(id, createdAt, updatedAt);
        this.externalId =externalId;
    }

    public void prePersist() {
        super.prePersist();
        if (Strings.isNullOrEmpty(this.externalId)) {
            this.externalId = UUID.randomUUID().toString();
        }

    }

    public void preUpdate() {
        super.preUpdate();
        if (Strings.isNullOrEmpty(this.externalId)) {
            this.externalId = UUID.randomUUID().toString();
        }

    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public ExternalBase(){
    }


}
