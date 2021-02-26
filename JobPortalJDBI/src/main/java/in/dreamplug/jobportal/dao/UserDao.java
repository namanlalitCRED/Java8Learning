package in.dreamplug.jobportal.dao;

import in.dreamplug.jobportal.domain.user.User;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface UserDao {

    @GetGeneratedKeys
    @SqlUpdate("INSERT INTO users ( user_id, first_name, last_name, email, mobile_number, created_at, updated_at) VALUES (:u.userId, :u.firstName, :u.lastName, :u.email, :u.mobileNumber, :u.createdAt, :u.updatedAt)")
    long insert(@BindBean("u") User user);


    @SqlQuery("SELECT user_id, first_name, last_name, email, mobile_number, created_at, updated_at FROM users WHERE user_id = :userId")
    @RegisterBeanMapper(User.class)
    Optional<User> findUserByUserId(@Bind("userId") String userId);

    @SqlUpdate ("UPDATE users SET first_name= :u.firstName, last_name = :u.lastName, email = :u.email, mobile_number = :u.mobileNumber, updated_at = :u.updatedAt WHERE user_id = :u.userId")
    void update(@BindBean ("u") User user);

}
