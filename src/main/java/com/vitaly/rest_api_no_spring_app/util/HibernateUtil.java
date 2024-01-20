package com.vitaly.rest_api_no_spring_app.util;
//  20-Jan-24
// gh crazym8nd


import com.vitaly.rest_api_no_spring_app.model.Event;
import com.vitaly.rest_api_no_spring_app.model.File;
import com.vitaly.rest_api_no_spring_app.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
    private HibernateUtil() {}

    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            final StandardServiceRegistry registry =
                    new StandardServiceRegistryBuilder().build();
            try{sessionFactory = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Event.class)
                    .addAnnotatedClass(File.class)
                    .buildMetadata()
                    .buildSessionFactory();
            }
            catch (Exception e){
                StandardServiceRegistryBuilder.destroy(registry);
            }
        }
        return sessionFactory;
    }
}
