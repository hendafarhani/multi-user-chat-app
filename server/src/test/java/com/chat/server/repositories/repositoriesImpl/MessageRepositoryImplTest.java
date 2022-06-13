package com.chat.server.repositories.repositoriesImpl;

import com.chat.server.entities.Message;
import com.chat.server.entities.Person;
import com.chat.server.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class MessageRepositoryImplTest {

    private Session mockedSession;

    private Transaction mockedTransaction;

    @Mock
    private MessageRepositoryImpl messageRepository;

    @Before
    public void setUp() {
        mockedSession = Mockito.mock(Session.class);
        mockedTransaction = Mockito.mock(Transaction.class);
        Mockito.when(HibernateUtil.getCurrentSession()).thenReturn(mockedSession);
        Mockito.when(mockedSession.beginTransaction()).thenReturn(mockedTransaction);
        Mockito.when(messageRepository.save(Mockito.any(Message.class)))
                .thenAnswer(i -> i.getArguments()[0]));
    }

    @Test
    public void shouldSaveMessage(){
        Message message = new Message();
        message.setTextMsg("Hello !");
        message.setDate(new Date());
        Person person = new Person();
        person.setName("Sam");
        message.setPerson(person);
        messageRepository = new MessageRepositoryImpl();
        messageRepository.save(message);
    }

}
