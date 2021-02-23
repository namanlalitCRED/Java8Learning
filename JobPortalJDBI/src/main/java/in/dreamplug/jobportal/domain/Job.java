package in.dreamplug.jobportal.domain;


import com.codahale.metrics.annotation.Timed;
import in.dreamplug.jobportal.Mergeable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class Job extends ExternalBase implements Mergeable<Job> {

    @NotNull
    private String title;

    @NotNull
    private String company;

    @NotNull
    private String description;

    @NotNull
    private String keyword;

    @NotNull
    private String location;


    @Override
    public void merge(Job job) {

        if (StringUtils.isNotBlank(job.getTitle())) {
            this.title = job.getTitle();
        }
        if (StringUtils.isNotBlank(job.getCompany())) {
            this.company = job.getCompany();
        }
        if (StringUtils.isNotBlank(job.getDescription())) {
            this.description = job.getDescription();
        }
        if (StringUtils.isNotBlank(job.getKeyword())) {
            this.keyword = job.getKeyword();
        }
        if (StringUtils.isNotBlank(job.getLocation())) {
            this.location = job.getLocation();
        }
    }
}
