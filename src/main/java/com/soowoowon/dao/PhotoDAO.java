package com.soowoowon.dao;

import com.soowoowon.model.Photo;

import java.util.List;

/**
 * {@link com.soowoowon.model.Photo} 도메인 모델에 대한 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see com.soowoowon.model.Photo
 * @see com.soowoowon.dao.PhotoDAOHibernate
 */
public interface PhotoDAO {
    Photo get(int id);

    void add(Photo photo);

    void update(Photo photo);

    void delete(Photo photo);

    List<Photo> list();
}
