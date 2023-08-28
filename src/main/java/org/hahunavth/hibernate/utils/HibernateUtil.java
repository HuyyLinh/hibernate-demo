package org.hahunavth.hibernate.utils;

import org.hahunavth.hibernate.entities.*;
import org.hahunavth.hibernate.strategies.SQLPhysicalNamingStrategy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = null;
        try {
            Configuration configuration = new Configuration();
            configuration.setPhysicalNamingStrategy(new SQLPhysicalNamingStrategy());
            sessionFactory = configuration
                    .configure()
                    .addAnnotatedClass(EntityWithList.class)
                    .addAnnotatedClass(Phone.class)
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(Account.class)
                    .addAnnotatedClass(Review.class)
                    .addAnnotatedClass(Tour.class)
                    .addAnnotatedClass(Category.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }

    /**
     * Try to create a session and save an account
     */
    public static void main(String[] args) {
        SessionFactory sessionFactory = getSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            System.out.println("Session created");

            Account acc = new Account("uname1", "password");
            Integer accId = (Integer) session.save(acc);

            for (Account acc2 : (Iterable<Account>) session.createQuery("FROM Account").list()) {
                System.out.println("Email " + acc2.getEmail());
            }

            session.close();
            System.out.println("Close session by Linh" + session);


            //            Collection
            EntityWithList entity = new EntityWithList();
            entity.setId(1);
            entity.getArrayString().add("1st String");
            entity.getArrayString().add("2st String");
//            Add Phone
            Phone phone = new Phone();
            phone.setId(1);
            phone.setNumber( "4321112");
            phone.setType("iPhone1");
            Phone phone2 = new Phone();
            phone2.setId(2);
            phone2.setNumber("53211123");
            phone2.setType("iPhone5");
            Phone phone3 = new Phone();
            phone3.setId(3);
            phone3.setNumber("53211123");
            phone3.setType("iPhone3");
            Phone phone4 = new Phone();
            phone4.setId(4);
            phone4.setNumber("53211123");
            phone4.setType("iPhone3");

// add to list
//            entity.getPhones().add(phone);
//            entity.getPhones().add(phone2);
//            Add to set
            entity.getPhoneSet().add(phone);
            entity.getPhoneSet().add(phone2);
            entity.getPhoneSet().add(phone3);
            entity.getPhoneSet().add(phone4);
//            Commit
            Session session1 = sessionFactory.getCurrentSession();
            Transaction tx = session1.beginTransaction();
            session1.save(entity);
            tx.commit();
            Session session2 = sessionFactory.getCurrentSession();
            Transaction tx2 = session2.beginTransaction();
            EntityWithList entity2 = session2.get(EntityWithList.class, 1);
            System.out.println("Get entity phone" + entity2.getPhoneSet());

            tx2.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
