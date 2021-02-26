package in.dreamplug.jobportal.domain;

import in.dreamplug.jobportal.domain.job.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {

    @NotNull
    private String userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String mobileNumber;

    @NotNull
    private List<Job> jobs;
}
