package in.dreamplug.jobportal.service;

import in.dreamplug.jobportal.dao.TrackDao;
import in.dreamplug.jobportal.domain.JobInfo;
import in.dreamplug.jobportal.domain.UserInfo;
import in.dreamplug.jobportal.domain.job.Job;
import in.dreamplug.jobportal.domain.track.Track;
import in.dreamplug.jobportal.domain.user.User;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.WebApplicationException;
import java.util.List;

@AllArgsConstructor
public class TrackApplicationService {

    private Jdbi jdbi;
    private UserService userService;
    private UserInfo userInfo;
    private JobInfo jobInfo;
    private JobService jobService;

    public UserInfo findJobs(String userId){
        User checkUser = userService.getByUserId(userId).orElseThrow(()-> new WebApplicationException("User not found"));
        List<Job> jobsList = jdbi.withExtension(TrackDao.class, dao-> dao.getJobsByUserId(userId));
        userInfo.setUserId(checkUser.getUserId());
        userInfo.setFirstName(checkUser.getFirstName());
        userInfo.setLastName(checkUser.getLastName());
        userInfo.setEmail(checkUser.getEmail());
        userInfo.setMobileNumber(checkUser.getMobileNumber());
        userInfo.setJobs(jobsList);
        return userInfo;
    }

    public JobInfo findUsers(String jobId){
        Job checkJob = jobService.getById(jobId).orElseThrow(() -> new WebApplicationException("Job not found"));
        List<User> userList = jdbi.withExtension(TrackDao.class, dao-> dao.getUsersByJobId(jobId));
        jobInfo.setUsers(userList);
        jobInfo.setJobId(checkJob.getJobId());
        jobInfo.setTitle(checkJob.getTitle());
        jobInfo.setCompany(checkJob.getCompany());
        jobInfo.setLocation(checkJob.getLocation());
        jobInfo.setDescription(checkJob.getDescription());
        jobInfo.setKeyword(checkJob.getKeyword());
        return jobInfo;
    }



}
