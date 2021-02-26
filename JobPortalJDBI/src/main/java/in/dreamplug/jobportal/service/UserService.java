package in.dreamplug.jobportal.service;


import in.dreamplug.jobportal.dao.UserDao;
import in.dreamplug.jobportal.domain.user.User;
import lombok.AllArgsConstructor;
import org.jdbi.v3.core.Jdbi;


import javax.ws.rs.WebApplicationException;
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



}
