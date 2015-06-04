package edufarm.service;

import edufarm.model.User;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * {@link edufarm.model.User}의 서비스 인터페이스.
 * <p>prepost 애노테이션이 붙어있는 메소드들은 특정 권한이 있어야만 사용가능하다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.User
 * @see edufarm.service.UserServiceImpl
 */
public interface UserService {
    void add(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #user.email")
    void update(User user);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #user.email")
    boolean updatePassword(User user, String oldPassword, String newPassword);

    @PostAuthorize("hasRole('ROLE_USER')")
    User get(int id);

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #email")
    User get(String email);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    List<User> list();

    @PreAuthorize("hasRole('ROLE_ADMIN') or authentication.name == #user.email")
    void delete(User user);

    boolean isExist(String email);
}