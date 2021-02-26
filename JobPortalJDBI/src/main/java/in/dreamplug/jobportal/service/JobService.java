package in.dreamplug.jobportal.service;

import in.dreamplug.jobportal.dao.JobDao;
import in.dreamplug.jobportal.domain.job.Job;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.WebApplicationException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JobService {
    private Jdbi jdbi;

//  Create a new job
    public Job insert(final Job job) {
        job.prePersist();
        final Long id = jdbi.withExtension(JobDao.class, dao -> dao.insert(job));
        job.setId(id);
        return job;
    }

//   Update a new job
    public Job updateJob(final Job job){
        Job existingJob = getById(job.getJobId()).orElseThrow(() -> new WebApplicationException("Job not Found", 404));
        existingJob.merge(job);
        existingJob.preUpdate();
        jdbi.useExtension(JobDao.class, dao -> dao.update(existingJob));
        return existingJob;
    }

//   Get job by ID
    public Optional<Job> getById(final String jobId) {
        return jdbi.withExtension(JobDao.class, dao -> dao.getByJobId(jobId));
    }


//   Get all jobs
    public List<Job> findAllJobs(){
        return jdbi.withExtension(JobDao.class, dao -> dao.getAllJobs());
    }


//  Get jobs based on filters
//    public List<Job> findJobs(Optional<String> title,
//                              Optional<String> keyword,
//                              Optional<String> company,
//                              Optional<String> location,
//                              Integer pageNumber,
//                              Integer pageSize){
//
//
//        List<Job> filteredList = findAllJobs();
//        int offsetValue = 5;
//        int startingIndex = ((pageNumber-1) * offsetValue);
//
//        List<Job> finalList = new ArrayList<>();
//
//
//        if(startingIndex < filteredList.size()){
//            finalList =  filteredList.stream().sorted(Comparator.comparing(Job::getUpdatedAt).reversed()).collect(Collectors.toList());
//            finalList = finalList.subList(startingIndex, Math.min(startingIndex+pageSize, filteredList.size()));
//        }
//
//        return finalList;
//    }




}
