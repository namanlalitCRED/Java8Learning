package in.dreamplug.jobportal.domain.track;


import in.dreamplug.jobportal.Mergeable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import javax.ws.rs.DefaultValue;

@Getter
@Setter
@NoArgsConstructor
public class Track extends ExternalBase implements Mergeable<Track> {

    @NotNull
    private String userId;

    @NotNull
    private String jobId;

    @NotNull
    private String status;


    @Override
    public void merge(Track track) {

//        if (StringUtils.isNotBlank(track.getUserId())) {
//            this.userId = track.getUserId();
//        }
//        if (StringUtils.isNotBlank(track.getJobId())) {
//            this.jobId = track.getJobId();
//        }
        if (StringUtils.isNotBlank(track.getStatus())) {
            this.status = track.getStatus();
        }

    }
}
