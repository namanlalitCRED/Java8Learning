package in.dreamplug.jobportal.domain.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import in.dreamplug.jobportal.Mergeable;
import in.dreamplug.jobportal.domain.job.Job;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class User extends ExternalBase implements Mergeable<User> {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String mobileNumber;

    @NotNull
    private String email;

    @Override
    public void merge(User user) {

        if (StringUtils.isNotBlank(user.getFirstName())) {
            this.firstName = user.getFirstName();
        }
        if (StringUtils.isNotBlank(user.getLastName())) {
            this.lastName = user.getLastName();
        }
        if (StringUtils.isNotBlank(user.getMobileNumber())) {
            this.mobileNumber = user.getMobileNumber();
        }
        if (StringUtils.isNotBlank(user.getEmail())) {
            this.email = user.getEmail();
        }

    }
}
