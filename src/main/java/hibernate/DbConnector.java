package hibernate;

import hibernate.entities.Pair_Candle;
import hibernate.entities.tics.Tic;
import hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DbConnector {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public void putInDatabase(List<Pair_Candle> list) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Pair_Candle pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putIndicatorsInDatabase(List<Tic> list) {
        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Tic pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public List<Pair_Candle> getPairCandle5minNANOData() {
        Session session = sessionFactory.openSession();
        Query data = session.createQuery("FROM NANOUSDT_5minCandle ORDER BY openTime ASC");
        return data.getResultList();
    }


    public List<Pair_Candle> getPairCandle5minAionData() {
        Session session = sessionFactory.openSession();
        Query data = session.createQuery("FROM AIONUSDT_5minCandle ORDER BY openTime ASC");
        return data.getResultList();
    }

    public List<Tic> getIndicatorsList5Nano() {
        Session session = sessionFactory.openSession();
        Query data = session.createQuery("FROM Tic5nano ORDER BY closeTime ASC");
        return data.getResultList();
    }

    public List<Tic> getIndicatorsList5Aion() {
        Session session = sessionFactory.openSession();
        Query data = session.createQuery("FROM Tic5aion ORDER BY closeTime ASC");
        return data.getResultList();
    }

}
