package com.chat.server.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Person implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "person")
    private List<Message> messages;


    public Person(){}

    public Person(String name, Long id){
        this.name = name;
        this.id = id;
    }

    public Person(String name){
        this.name = name;
    }

    public Person(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
