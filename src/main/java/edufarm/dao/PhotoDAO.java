package edufarm.dao;

import edufarm.model.Photo;

import java.util.List;

/**
 * {@link edufarm.model.Photo} 도메인 모델에 대한 DAO.CRUD 인터페이스.
 *
 * @author Kiwon Lee <bwv1050@gmail.com>
 * @see edufarm.model.Photo
 * @see edufarm.dao.PhotoDAOHibernate
 */
public interface PhotoDAO {
    Photo get(int id);

    void add(Photo photo);

    void update(Photo photo);

    void delete(Photo photo);

    List<Photo> list();
}
