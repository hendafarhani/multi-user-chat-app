package com.chat.server.repositories.Person;

import com.chat.server.enums.DatabaseTypeEnum;
import com.chat.server.repositories.repositoriesImpl.PersonRepositoryImpl;

public class PersonRepositoryFactory {

    public PersonRepository personRepository;

    public PersonRepositoryFactory(String databaseType){
                this.personRepository =  DatabaseTypeEnum.SQL.getType().equals(databaseType)? new PersonRepositoryImpl(): null;
    }
}
