package in.dreamplug.jobportal.resource;


import com.codahale.metrics.annotation.Timed;
import in.dreamplug.jobportal.domain.job.Job;
import in.dreamplug.jobportal.domain.user.User;
import in.dreamplug.jobportal.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@AllArgsConstructor
@Path("/user/")
@Slf4j
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Timed
public class UserResource {

    private UserService userService;

    //  Create a new application
    @POST
    public Response create(final @NotNull User user) {
        final User createdUser = userService.insert(user);
        return Response.status(Response.Status.CREATED).entity(createdUser).build();
    }

    @GET
    @Path("{userId}")
    public User getByUserId(@PathParam("userId") final String userId){
        return userService.getByUserId(userId).orElseThrow(() -> new WebApplicationException("User not found", 404));
    }

    @PATCH
    public User updateUser(@HeaderParam ("X-User-Id") final String userId, User user){
        return userService.updateUser(userId, user);
    }


}
