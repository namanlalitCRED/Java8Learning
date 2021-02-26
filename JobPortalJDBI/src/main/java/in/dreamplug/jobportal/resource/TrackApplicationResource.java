package in.dreamplug.jobportal.resource;


import in.dreamplug.jobportal.domain.JobInfo;
import in.dreamplug.jobportal.domain.UserInfo;
import in.dreamplug.jobportal.domain.user.User;
import in.dreamplug.jobportal.service.TrackApplicationService;
import lombok.AllArgsConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/applications/")
public class TrackApplicationResource {

    private TrackApplicationService trackApplicationService;

    @GET
    @Path("/user/{userId}")
    public UserInfo getListOfJobsAppliedByUser(@PathParam("userId") final String userId){
        return trackApplicationService.findJobs(userId);
    }

    @GET
    @Path("/job/{jobId}")
    public JobInfo getListOfUsersForAJob(@PathParam("jobId") final String jobId){
        return trackApplicationService.findUsers(jobId);
    }


}
