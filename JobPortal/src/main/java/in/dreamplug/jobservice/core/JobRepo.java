package in.dreamplug.jobservice.core;

import in.dreamplug.jobservice.domain.Job;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface JobRepo {

    List<Job> getAllJobs();

    Optional<Job> getByJobID(String jobId);

    Job addNewJob(Job job);

    Job updateJob(String jobId, Job job);

    List<Job> getJobs(String jobId, String keyword, String location, String company);

    List<Job> getJobsTest(Optional<String> jobId, Optional<String> keyword, Optional<String> location, Optional<String> company);
}
