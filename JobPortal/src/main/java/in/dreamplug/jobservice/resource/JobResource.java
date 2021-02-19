package in.dreamplug.jobservice.resource;


import in.dreamplug.jobservice.core.JobRepo;
import in.dreamplug.jobservice.domain.Job;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Path("/jobs")
@Produces(MediaType.APPLICATION_JSON)
public class JobResource {

//  Creating a reference of the JobRepo, so that it can be accessed from here
    private final JobRepo jobRepo;

    public JobResource(JobRepo jobRepo){
        this.jobRepo = jobRepo;
    }



    @GET
    public List<Job> getJobs(
            @QueryParam("jobId") Optional<String> jobId,
            @QueryParam("keyword") Optional<String> keyword,
            @QueryParam("location") Optional<String> location,
            @QueryParam("company") Optional<String> company,
            @QueryParam("pageNumber") @DefaultValue("1") Integer pageNumber,
            @QueryParam("pageSize") @DefaultValue("5") Integer pageSize) {

        return jobRepo.getJobs(jobId, keyword, location, company, pageNumber, pageSize);

    }


    @GET
    @Path("/{jobId}")
    public Job job(@PathParam("jobId") final String jobId) {
        return jobRepo.getByJobID(jobId).orElseThrow( () -> new WebApplicationException("Job not Found", 404));
    }

    @POST
    public Response create(Job job){
        final Job createdJob = jobRepo.addNewJob(job);
        return Response.status(Response.Status.CREATED).entity(createdJob).build();
    }

    @PATCH
    @Path("{jobId}")
    public Response update(@PathParam("jobId") final String jobId, @NotNull final Job job){
        final Job updatedJob = jobRepo.updateJob(jobId, job);
        return Response.status(Response.Status.CREATED).entity(updatedJob).build();
    }



}
