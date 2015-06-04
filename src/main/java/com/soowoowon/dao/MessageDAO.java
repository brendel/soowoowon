package com.soowoowon.dao;

import com.soowoowon.model.Message;

import java.util.List;

/**
 * {@link Message}의 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see Message
 * @see com.soowoowon.dao.MessageDAOHibernate
 */
public interface MessageDAO {
    void add(Message message);

    void update(Message message);

    void delete(Message message);

    List<Message> list(String displayName);

    List<Message> list();

    List<Message> list(int num);
}
