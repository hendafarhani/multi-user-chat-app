package com.chat.server.repositories.repositoriesImpl;

import com.chat.server.entities.Person;
import com.chat.server.repositories.Person.PersonRepository;
import com.chat.server.utils.HibernateUtil;
import org.hibernate.Session;

import java.util.List;


public class PersonRepositoryImpl implements PersonRepository {

    private Session session;

    @Override
    public void save(Person person) throws Exception {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        try {
            session.persist(person);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Person loadPersonByName(String name) throws Exception {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("select new com.chat.server.entities.Person(p.name, p.id) from Person p where p.name =:name", Person.class).setParameter("name", name).getResultList();
        if (personList.size() == 1) {
            return personList.get(0);
        } else {
            throw new Exception("user by name " + name + "is saved twice");
        }
    }

    @Override
    public Person loadByName(String name) throws Exception {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        List<Person> personList = session.createQuery("select new com.chat.server.entities.Person(p.name) from Person p where p.name =:name", Person.class).setParameter("name", name).getResultList();
        if (personList.size() > 1) {
            throw new Exception("user by name " + name + "is saved twice");
        }
        return personList.size() == 1 ? personList.get(0) : null;
    }
}