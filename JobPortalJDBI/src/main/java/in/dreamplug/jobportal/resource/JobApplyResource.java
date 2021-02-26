package in.dreamplug.jobportal.resource;

import com.codahale.metrics.annotation.Timed;
import in.dreamplug.jobportal.domain.track.Track;
import in.dreamplug.jobportal.service.JobApplyService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Timed
@Slf4j
@Path("/apply/")
public class JobApplyResource {

    JobApplyService jobApplyService;

    @POST
    public Response insertJob(Track track){
        Track createdEntry = jobApplyService.insert(track);
        return Response.status(Response.Status.CREATED).entity(createdEntry).build();
    }

    @PATCH
    public Response updateJob(@HeaderParam ("X-User-Id") final String userId, @HeaderParam ("X-Job-Id") final String jobId, Track track){
        Track updatedTrack = jobApplyService.updateTrack(track, userId, jobId);
        return Response.status(Response.Status.ACCEPTED).entity(updatedTrack).build();
    }

}
