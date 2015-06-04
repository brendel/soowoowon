package com.soowoowon.service;

import com.soowoowon.dao.UserDAO;
import com.soowoowon.model.User;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * {@link com.soowoowon.model.User}의 서비스 계층, {@link com.soowoowon.service.UserService}의 구현.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.User
 * @see com.soowoowon.service.UserService
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void add(User user) {
        String displayName = user.getDisplayName();
        displayName = Jsoup.clean(displayName, Whitelist.none());
        user.setDisplayName(displayName);
        user.setJoined(new Date());
        user.setLastVisited(new Date());
        user.setRole((byte) 1);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userDAO.add(user);
    }

    @Override
    public void update(User user) {
        String displayName = user.getDisplayName();
        displayName = Jsoup.clean(displayName, Whitelist.none());
        user.setDisplayName(displayName);
        userDAO.update(user);
    }

    @Override
    public boolean updatePassword(User user, String oldPassword, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!passwordEncoder.matches(oldPassword, user.getPassword()))
            return false;
        user.setPassword(passwordEncoder.encode(newPassword));
        userDAO.update(user);
        return true;
    }

    @Override
    public User get(int id) {
        return userDAO.get(id);
    }

    @Override
    public User get(String email) {
        return userDAO.get(email);
    }

    @Override
    public List<User> list() {
        return userDAO.getAll();
    }

    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Override
    public boolean isExist(String email) {
        return userDAO.count(email) == 1;
    }
}
