package in.dreamplug.jobservice.core;

import in.dreamplug.jobservice.domain.Job;

import java.util.List;
import java.util.Optional;

public interface JobRepo {


    Optional<Job> getByJobID(String jobId);

    Job addNewJob(Job job);

    Job updateJob(String jobId, Job job);

    List<Job> getJobs(Optional<String> jobId, Optional<String> keyword, Optional<String> location, Optional<String> company);

}
