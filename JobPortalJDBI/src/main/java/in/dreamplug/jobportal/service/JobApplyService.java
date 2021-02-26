package in.dreamplug.jobportal.service;

import in.dreamplug.jobportal.dao.TrackDao;
import in.dreamplug.jobportal.domain.job.Job;
import in.dreamplug.jobportal.domain.track.Track;
import in.dreamplug.jobportal.domain.user.User;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;

import javax.ws.rs.WebApplicationException;
import java.util.Optional;

@AllArgsConstructor
public class JobApplyService {

    final Jdbi jdbi;
    final UserService userService;
    final JobService jobService;

    public Track insert(Track track){

        User checkUser = userService.getByUserId(track.getUserId()).orElseThrow(() -> new WebApplicationException("User not found",404));
        Job checkJob = jobService.getById(track.getJobId()).orElseThrow(() -> new WebApplicationException("Job not found",404));

        track.prePersist();
        final long id = jdbi.withExtension(TrackDao.class, dao-> dao.insert(track));
        track.setId(id);
        return track;
    }

    public Optional<Track> checkTrack(Track track){
        return jdbi.withExtension(TrackDao.class, dao -> dao.findTrack(track));
    }

    public Track updateTrack(Track track, String userId, String jobId){
        track.setUserId(userId);
        track.setJobId(jobId);
        Track existingTrack = checkTrack(track).orElseThrow(() -> new WebApplicationException("Not applied for the job", 404));
        existingTrack.merge(track);
        existingTrack.preUpdate();
        jdbi.useExtension(TrackDao.class, dao -> dao.updateTrack(existingTrack));
        return existingTrack;
    }

}
