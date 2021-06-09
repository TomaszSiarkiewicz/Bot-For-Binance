package hibernate.util;

import hibernate.entities.aionusdt.AIONUSDT_1minCandle;
import hibernate.entities.aionusdt.AIONUSDT_5minCandle;
import hibernate.entities.btcusdt.*;
import hibernate.entities.ftm.FTMUSDT_5minCandle;
import hibernate.entities.iost.IOSTUSDT_5minCandle;
import hibernate.entities.nano.NANOUSDT_5minCandle;
import hibernate.entities.psgusdt.PSGUSDT_5minCandle;
import hibernate.entities.tics.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                /* PostgresSQL connection */
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/binance");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "password");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
                /* MySQL connection */
//                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
//                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/binance?serverTimezone=CET");
//                settings.put(Environment.USER, "root");
//                settings.put(Environment.PASS, "password");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");

                settings.put(Environment.SHOW_SQL, "false");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);


                // annotated classes
                configuration.addAnnotatedClass(AIONUSDT_1minCandle.class);
                configuration.addAnnotatedClass(AIONUSDT_5minCandle.class);
                configuration.addAnnotatedClass(BTCUSDT_1minCandle.class);
                configuration.addAnnotatedClass(BTCUSDT_3minCandle.class);
                configuration.addAnnotatedClass(BTCUSDT_5minCandle.class);
                configuration.addAnnotatedClass(BTCUSDT_15minCandle.class);
                configuration.addAnnotatedClass(BTCUSDT_30minCandle.class);
                configuration.addAnnotatedClass(BTCUSDT_1hCandle.class);
                configuration.addAnnotatedClass(PSGUSDT_5minCandle.class);
                configuration.addAnnotatedClass(IOSTUSDT_5minCandle.class);
                configuration.addAnnotatedClass(NANOUSDT_5minCandle.class);
                configuration.addAnnotatedClass(FTMUSDT_5minCandle.class);
                configuration.addAnnotatedClass(Tic15.class);
                configuration.addAnnotatedClass(Tic5btc.class);
                configuration.addAnnotatedClass(Tic5aion.class);
                configuration.addAnnotatedClass(Tic5psg.class);
                configuration.addAnnotatedClass(Tic5iost.class);
                configuration.addAnnotatedClass(Tic5nano.class);
                configuration.addAnnotatedClass(Tic5ftm.class);
                configuration.addAnnotatedClass(Tic30.class);


                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}