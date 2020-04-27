package com.nttdata.graphqldemo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
public class Policy {

    @Id @GeneratedValue
    @GraphQLQuery(name = "id", description = "An policy's id")
    private Long id;

    @GraphQLQuery(name = "name", description = "An policy's name")
    private String name;

    @ManyToOne
    @GraphQLQuery(name = "user", description = "An policy's user")
    private User user;
    
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
