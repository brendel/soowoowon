package edufarm.service;

import edufarm.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 스프링 시큐리티 UserDetailsService 인터페이스의 구현. {@link edufarm.model.User} 클래스를 랩핑한다.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.dao.UserDAO
 * @see edufarm.model.User
 */
@Service
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        edufarm.model.User user = userDAO.get(s);

        return new User(
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                getRoles(user.getRole())
        );
    }

    public List<GrantedAuthority> getRoles(int role) {
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        if (role == 9) {
            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            roles.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (role == 5) {
            roles.add(new SimpleGrantedAuthority("ROLE_MODERATOR"));
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        } else if (role == 1) {
            roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return roles;
    }
}