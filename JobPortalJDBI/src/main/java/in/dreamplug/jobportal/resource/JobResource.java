package in.dreamplug.jobportal.resource;

import com.codahale.metrics.annotation.Timed;
import in.dreamplug.jobportal.domain.job.Job;
import in.dreamplug.jobportal.service.JobService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Path("/jobs/")
@Slf4j
@Produces (MediaType.APPLICATION_JSON)
@Consumes (MediaType.APPLICATION_JSON)
@Timed
public class JobResource {

    private JobService jobService;

//  Create a new job
    @POST
    public Response create(final Job job) {
        final Job createdJob = jobService.insert(job);
        return Response.status(Response.Status.CREATED).entity(createdJob).build();
    }

//  Update a new job
    @PATCH
    @Path("{jobId}")
    public Response update(@PathParam("jobId") final String jobId, @NotNull final Job job){
        job.setJobId(jobId);
        final Job updatedJob = jobService.updateJob(job);
        return Response.status(Response.Status.CREATED).entity(updatedJob).build();
    }

//  Find a job by jobId
    @GET
    @Path("/{jobId}")
    public Job findByID(@PathParam("jobId") final String jobId){
        return jobService.getById(jobId).orElseThrow( () -> new WebApplicationException("Job not Found", 404));
    }

    @GET
    public List<Job> getAllJobs(){
        return jobService.findAllJobs();
    }


}
