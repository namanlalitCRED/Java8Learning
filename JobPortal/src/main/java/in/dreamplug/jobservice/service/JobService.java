package in.dreamplug.jobservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.base.Charsets;
import in.dreamplug.jobservice.core.JobRepo;
import in.dreamplug.jobservice.domain.Job;
import io.dropwizard.util.Resources;
import org.w3c.dom.ls.LSOutput;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class JobService implements JobRepo {
    private static final String data_source = "sample_job.json";
    private List<Job> jobs;

    public JobService(){
        try{
            initData();
        }catch (IOException e){
            throw new RuntimeException(data_source + " is missing or there is some other bug while reading it. ", e);
        }
    }

    private void initData() throws IOException{
        URL url = Resources.getResource(data_source);
        String json = Resources.toString(url, Charsets.UTF_8);
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType collectionType = objectMapper
                .getTypeFactory()
                .constructCollectionType(List.class, Job.class);
        jobs = objectMapper.readValue(json, collectionType);
    }


    @Override
    public List<Job> getAllJobs() {

        return jobs.stream()
                .sorted(Comparator.comparing(Job::getUpdatedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Job> getByJobID(String jobId) {
        return jobs.stream()
                .filter(job -> job.getJobId().equals(jobId))
                .findFirst();
    }

    @Override
    public Job addNewJob(Job job) {
        job.prePersist();
        jobs.add(job);
        return job;
    }

    @Override
    public Job updateJob(String jobId, Job job) {
        Job existingJob = getByJobID(jobId).orElseThrow(() -> new WebApplicationException("Job not Found", 404));
        existingJob.merge(job);
        existingJob.preUpdate();
        return existingJob;
    }

    @Override
    public List<Job> getJobs(String jobId, String keyword, String location, String company) {
        List<Job> lst = jobs.stream()
                .filter(job -> job.getJobId().equals(jobId))
                .filter(job -> job.getKeyword().matches(keyword))
                .filter(job -> job.getLocation().equalsIgnoreCase(location))
                .filter(job -> job.getCompany().equalsIgnoreCase(company))
                .sorted(Comparator.comparing(Job::getUpdatedAt).reversed())
                .collect(Collectors.toList());
        return lst;
    }

    @Override
    public List<Job> getJobsTest(Optional<String> jobId, Optional<String> keyword, Optional<String> location, Optional<String> company) {

        List<Optional<String>> params = Arrays.asList(jobId, keyword, location, company);
        params.stream().filter(Optional ::isPresent).collect(Collectors.toList());

        params.stream().forEach(System.out::println);

        List<Job> lst = jobs.stream()
                .filter(job -> jobId.isPresent() ? job.getJobId().equals(jobId.get()) : Boolean.parseBoolean(job.getLocation().toLowerCase(Locale.ROOT)))
                .filter(job -> keyword.isPresent() ? job.getKeyword().toLowerCase(Locale.ROOT).matches(keyword.get()): Boolean.parseBoolean(job.getLocation().toLowerCase(Locale.ROOT)))
                .filter(job -> location.isPresent() ?job.getLocation().equalsIgnoreCase(location.get()): Boolean.parseBoolean(job.getLocation().toLowerCase(Locale.ROOT)))
                .filter(job -> company.isPresent() ? job.getCompany().equalsIgnoreCase(company.get()): Boolean.parseBoolean(job.getLocation().toLowerCase(Locale.ROOT)))
                .collect(Collectors.toList());
        return lst;
    }


}
