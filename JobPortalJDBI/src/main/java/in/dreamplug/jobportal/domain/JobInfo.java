package in.dreamplug.jobportal.domain;

import in.dreamplug.jobportal.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JobInfo {

    @NotNull
    private String jobId;

    @NotNull
    private String title;

    @NotNull
    private String company;

    @NotNull
    private String location;

    @NotNull
    private String description;

    @NotNull
    private String keyword;

    @NotNull
    private List<User> users;

}
