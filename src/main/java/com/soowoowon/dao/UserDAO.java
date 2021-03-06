package com.soowoowon.dao;

import com.soowoowon.model.User;

import java.util.List;

/**
 * {@link com.soowoowon.model.User}의 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.User
 * @see com.soowoowon.dao.UserDAOHibernate
 */
public interface UserDAO {
    void add(User user);

    void update(User user);

    User get(int id);

    User get(String email);

    void delete(User user);

    List<User> getAll();

    int count(String email);
}
