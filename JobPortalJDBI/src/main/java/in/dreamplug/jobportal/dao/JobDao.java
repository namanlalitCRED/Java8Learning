package in.dreamplug.jobportal.dao;

import in.dreamplug.jobportal.domain.job.Job;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.Optional;

public interface JobDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO jobs (job_id, title, company, keyword, description, location, created_at, updated_at) VALUES (:j.jobId, :j.title, :j.company, :j.keyword, :j.description, :j.location,:j.createdAt, :j.updatedAt)")
    long insert(@BindBean("j") Job job);


    @SqlQuery("SELECT job_id, title, company, keyword, description, location, created_at, updated_at FROM jobs WHERE job_id = :jobId")
    @RegisterBeanMapper(Job.class)
    Optional<Job> getByJobId(@Bind("jobId") String jobId);

    @SqlUpdate ("UPDATE jobs SET title= :j.title, company = :j.company, keyword = :j.keyword, description = :j.description, location = :j.location, updated_at = :j.updatedAt WHERE job_id = :j.jobId")
    void update(@BindBean ("j") Job job);

    @SqlQuery("SELECT job_id, title, company, keyword, description, location, created_at, updated_at FROM jobs")
    @RegisterBeanMapper(Job.class)
    List<Job> getAllJobs();

}
