package in.dreamplug.jobportal.domain.user;

import com.google.common.base.Strings;
import in.dreamplug.jobportal.domain.Base;

import java.sql.Timestamp;
import java.util.UUID;

public class ExternalBase extends Base {

    private String userId;

    public ExternalBase(Long id, Timestamp createdAt, Timestamp updatedAt, String userId){
        super(id, createdAt, updatedAt);
        this.userId = userId;
    }

    public void prePersist() {
        super.prePersist();
        if (Strings.isNullOrEmpty(this.userId)) {
            this.userId = UUID.randomUUID().toString();
        }

    }

    public void preUpdate() {
        super.preUpdate();
        if (Strings.isNullOrEmpty(this.userId)) {
            this.userId = UUID.randomUUID().toString();
        }

    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ExternalBase(){
    }


}
