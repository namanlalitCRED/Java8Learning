package in.dreamplug.jobportal.dao;

import in.dreamplug.jobportal.domain.UserInfo;
import in.dreamplug.jobportal.domain.job.Job;
import in.dreamplug.jobportal.domain.track.Track;
import in.dreamplug.jobportal.domain.user.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface TrackDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO user_applications (external_id, user_id, job_id, status, created_at, updated_at) VALUES (:t.externalId, :t.userId, :t.jobId, :t.status, :t.createdAt, :t.updatedAt)")
    long insert(@BindBean("t") Track track);


    @SqlQuery("Select external_id, user_id, job_id, status, created_at, updated_at from user_applications where user_id = :t.userId and job_id = :t.jobId")
    @RegisterBeanMapper(Track.class)
    Optional<Track> findTrack(@BindBean("t") Track track);

    @SqlUpdate ("UPDATE user_applications SET status = :t.status, updated_at = :t.updatedAt WHERE external_id = :t.externalId")
    void updateTrack(@BindBean ("t") Track track);

    @SqlQuery("select jobs.job_id, title, company, keyword, location, description, created_at, updated_at from jobs join (select user_applications.job_id from user_applications where user_id = :userId ) j on j.job_id = jobs.job_id")
    @RegisterBeanMapper(Job.class)
    List<Job> getJobsByUserId(@Bind("userId") String userId);

    @SqlQuery("select users.user_id, first_name, last_name, email, mobile_number, created_at, updated_at from users join (select user_applications.user_id from user_applications where user_applications.job_id = :jobId ) j on j.user_id = users.user_id")
    @RegisterBeanMapper(User.class)
    List<User> getUsersByJobId(@Bind("jobId") String jobId);

}
