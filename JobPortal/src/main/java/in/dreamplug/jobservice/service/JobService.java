package in.dreamplug.jobservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.google.common.base.Charsets;
import in.dreamplug.jobservice.core.JobRepo;
import in.dreamplug.jobservice.domain.Job;
import io.dropwizard.util.Resources;

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
    public List<Job> getJobs(Optional<String> jobId, Optional<String> keyword, Optional<String> location, Optional<String> company) {


        List<Job> filteredList = jobs;


        if(jobId.isPresent()){
            System.out.println(jobId.get());
            filteredList = filteredList.stream()
                    .filter(job -> job.getJobId().equals(jobId.get()))
                    .collect(Collectors.toList());
        }

        if(keyword.isPresent()){
            System.out.println(keyword.get());
            filteredList = filteredList.stream()
                    .filter(job -> job.getKeyword().toLowerCase(Locale.ROOT).contains(keyword.get().toLowerCase(Locale.ROOT)))
                    .collect(Collectors.toList());
        }

        if(location.isPresent()){
            System.out.println(location.get());
            filteredList = filteredList.stream()
                    .filter(job -> job.getLocation().equalsIgnoreCase(location.get()))
                    .collect(Collectors.toList());
        }
        if(company.isPresent()){
            System.out.println(company.get());
            filteredList = filteredList.stream()
                    .filter(job -> job.getCompany().equalsIgnoreCase(company.get()))
                    .collect(Collectors.toList());
        }

        return filteredList.stream().sorted(Comparator.comparing(Job::getUpdatedAt).reversed()).collect(Collectors.toList());

    }


}
