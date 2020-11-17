package lessonthree.hibernate.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class DBService {
    private static final Logger logger = LogManager.getLogger(DBService.class);

    private SessionFactory sessionFactory;
    private Session session;

    @PostConstruct
    public void init() {
        sessionFactory = new Configuration()
                .configure("hibernate.h2.cfg.xml")
                .buildSessionFactory();
        session = sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public boolean saveOrUpdate(Object o) {
        try {

//            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(o);
            session.getTransaction().commit();
//            session.close();
            return true;
        }catch (HibernateException e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    public  <T> T get(Class<T> tClass, Long id) {
//        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T result = session.get(tClass, id);
        session.getTransaction().commit();
        return result;
    }

    public <T> List<T> executeQuery(String query, Class<T> tClass) {
//        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query<T> executeQuery =  session.createQuery(query, tClass);
        List<T> result = executeQuery.getResultList();
        session.getTransaction().commit();
        return  result;
    }

}
