package hibernate;

import hibernate.entities.*;
import hibernate.entities.tics.*;
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
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putIndicatorsInDatabaset15(List<Tic15> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void putIndicatorsInDatabaset5Psg(List<Tic5psg> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void putIndicatorsInDatabaset5Iost(List<Tic5iost> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void putIndicatorsInDatabaset5Nano(List<Tic5nano> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putIndicatorsInDatabaset5(List<Tic5btc> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putIndicatorsInDatabaset5Aion(List<Tic5aion> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void putIndicatorsInDatabaset5Ftm(List<Tic5ftm> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putIndicatorsInDatabaset30(List<Tic30> list) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            for (Object pair : list) {
                session.save(pair);
            }
            session.getTransaction().commit();
            session.close();
//            sessionFactory.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pair_Candle> getPairCandle5minBTCData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM BTCUSDT_5minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }
    public List<Pair_Candle> getPairCandle5minFTMData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM FTMUSDT_5minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }
    public List<Pair_Candle> getPairCandle5minNANOData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM NANOUSDT_5minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }
    public List<Pair_Candle> getPairCandle5minIOSTData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM IOSTUSDT_5minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }
    public List<Pair_Candle> getPairCandle5minPSGData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM PSGUSDT_5minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }

    public List<Pair_Candle> getPairCandle5minAionData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM AIONUSDT_5minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }

    public List<Pair_Candle> getPairCandle15minData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM BTCUSDT_15minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }

    public List<Pair_Candle> getPairCandle30minData() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM BTCUSDT_30minCandle ORDER BY openTime ASC");
        List<Pair_Candle> list = data.getResultList();
        return list;
    }

    public List<Tic> getIndicatorsList5Btt() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic5btc ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }
    public List<Tic> getIndicatorsList5Ftm() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic5ftm ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }
    public List<Tic> getIndicatorsList5Nano() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic5nano ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }

    public List<Tic> getIndicatorsList5Aion() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic5aion ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }
     public List<Tic> getIndicatorsList5Iost() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic5iost ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }
    public List<Tic> getIndicatorsList5Psg() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic5psg ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }

    public List<Tic> getIndicatorsList15() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic15 ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }

    public List<Tic> getIndicatorsList30() {
        Session session = sessionFactory.openSession();

        Query data = session.createQuery("FROM Tic30 ORDER BY closeTime ASC");
        List<Tic> list = data.getResultList();
        return list;
    }
}
