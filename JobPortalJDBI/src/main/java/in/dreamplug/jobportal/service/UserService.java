package in.dreamplug.jobportal.service;


import in.dreamplug.jobportal.dao.TrackDao;
import in.dreamplug.jobportal.dao.UserDao;
import in.dreamplug.jobportal.domain.job.Job;
import in.dreamplug.jobportal.domain.user.User;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;


import javax.validation.constraints.NotNull;
import javax.ws.rs.WebApplicationException;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private Jdbi jdbi;

    public User insert(User user){
        user.prePersist();
        final long id = jdbi.withExtension(UserDao.class, dao -> dao.insert(user));
        user.setId(id);
        return user;
    }

    public User updateUser(String userId, User user){
        user.setUserId(userId);
        User existingUser = getByUserId(userId).orElseThrow(() -> new WebApplicationException("User not found"));
        existingUser.preUpdate();
        existingUser.merge(user);
        jdbi.useExtension(UserDao.class, dao-> dao.update(existingUser));
        return existingUser;
    }

    public Optional<User> getByUserId(String userId){
        return jdbi.withExtension(UserDao.class, dao-> dao.findUserByUserId(userId));
    }


//    public User findJobsPerUser(String userId){
//        User checkUser = checkUserByUserId(userId).orElseThrow( () -> new WebApplicationException("User not found", 404));
//        final List<Job> jobsApplied = jdbi.withExtension(TrackDao.class, dao -> dao.getJobByUserId(userId));
//        checkUser.setJobs(jobsApplied);
//        checkUser.setUserId(userId);
//        return checkUser;
//    }
//
//    public Optional<User> checkUserByUserId(String userId){
//        System.out.println(userId);
//        return jdbi.withExtension(UserDao.class, dao -> dao.findUserByUserId(userId));
//    }


}
