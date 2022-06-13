package com.chat.server.repositories.Person;

import com.chat.server.entities.Person;

public interface PersonRepository {

     void save(Person person) throws Exception;

     Person loadPersonByName(String name) throws Exception;

     Person loadByName(String name) throws Exception;
}
