package in.dreamplug.jobportal.service;

import in.dreamplug.jobportal.dao.JobDao;
import in.dreamplug.jobportal.domain.Job;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public Job updateJob(final String jobId, final Job job){
        Job existingJob = getById(jobId).orElseThrow(() -> new WebApplicationException("Job not Found", 404));
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
    public List<Job> findJobs(Optional<String> title,
                              Optional<String> keyword,
                              Optional<String> company,
                              Optional<String> location,
                              Integer pageNumber,
                              Integer pageSize){


        List<Job> filteredList = findAllJobs();
        int offsetValue = 5;

        if(title.isPresent()){
            filteredList = filteredList.stream()
                    .filter(job -> job.getTitle().toLowerCase(Locale.ROOT).contains(title.get().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        }

        if(keyword.isPresent()){
            filteredList = filteredList.stream()
                    .filter(job -> job.getKeyword().toLowerCase(Locale.ROOT).contains(keyword.get().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        }

        if(location.isPresent()){
            filteredList = filteredList.stream()
                    .filter(job -> job.getLocation().equalsIgnoreCase(location.get()))
                    .collect(Collectors.toList());
        }
        if(company.isPresent()){
            filteredList = filteredList.stream()
                    .filter(job -> job.getCompany().equalsIgnoreCase(company.get()))
                    .collect(Collectors.toList());
        }

        pageNumber = pageNumber == 0 ? 1 : pageNumber;
        int startingIndex = ((pageNumber-1) * offsetValue);

        List<Job> finalList = new ArrayList<>();


        if(startingIndex < filteredList.size()){
            finalList =  filteredList.stream().sorted(Comparator.comparing(Job::getUpdatedAt).reversed()).collect(Collectors.toList());
            finalList = finalList.subList(startingIndex, Math.min(startingIndex+pageSize, filteredList.size()));
        }

        return finalList;
    }




}
