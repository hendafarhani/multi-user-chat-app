package com.chat.server.utils;

import com.chat.server.entities.Message;
import com.chat.server.entities.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class HibernateUtil {

    public static Session getCurrentSession() {
        Map<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        settings.put("dialect", "org.hibernate.dialect.SQLServerDialect");
        settings.put("hibernate.connection.url",
                "jdbc:sqlserver://TNTNNHFARHANI01:1433;databasename=chat_web_app;encrypt=true;trustServerCertificate=true");
        settings.put("hibernate.connection.username", "guest");
        settings.put("hibernate.connection.password", "guest");
        settings.put("hibernate.current_session_context_class", "thread");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.format_sql", "true");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();

        MetadataSources metadataSources = new MetadataSources(serviceRegistry);
        metadataSources.addAnnotatedClass(Person.class);
        metadataSources.addAnnotatedClass(Message.class);

        Metadata metadata = metadataSources.buildMetadata();
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.getCurrentSession();
        return session;
    }
}