package lk.IJse.Module.FactoryConfig;

import lk.IJse.Module.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class factoryConfiguration {
    private static factoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private factoryConfiguration() {
        Configuration configuration =
                new Configuration().configure()
                        .addAnnotatedClass(User.class).addAnnotatedClass(Books.class).addAnnotatedClass(Borrowers.class).addAnnotatedClass(Branch.class).addAnnotatedClass(Admin.class);
        sessionFactory = configuration.buildSessionFactory();
    }
    public static factoryConfiguration getInstance(){
        return (factoryConfiguration == null) ?
                factoryConfiguration =
                        new factoryConfiguration() : factoryConfiguration;
    }

    public Session getSessionFactory() {
        return sessionFactory.openSession();
    }
}
