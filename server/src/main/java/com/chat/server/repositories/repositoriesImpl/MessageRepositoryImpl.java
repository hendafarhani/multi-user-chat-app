package com.chat.server.repositories.repositoriesImpl;

import com.chat.server.entities.Message;
import com.chat.server.repositories.Message.MessageRepository;
import com.chat.server.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class MessageRepositoryImpl implements MessageRepository {

    private Session session;

    @Override
    public void save(Message message) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        try {
            session.persist(message);
            session.getTransaction().commit();
        } catch (Exception x) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<Message> loadAll() {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        // Retrieve last 100 recent messages
        List<Message> messageList = session.createQuery("select new com.chat.server.entities.Message(m.textMsg, m.person.id, m.person.name, m.date) from Message m order by m.date desc limit 100", Message.class).getResultList();
        return messageList;
    }
}
