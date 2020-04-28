package com.nttdata.graphqldemo.model.policy;

import com.nttdata.graphqldemo.model.partner.User;

import javax.persistence.*;
import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "policies")
public class Policy {

    @Id @GeneratedValue
    @GraphQLQuery(name = "id", description = "An policy's id")
    private Long id;

    @GraphQLQuery(name = "name", description = "An policy's name")
    private String name;

    //@ManyToOne
    //@GraphQLQuery(name = "user", description = "An policy's user")
    //private User user;
    @GraphQLQuery(name = "userId", description = "An policy's name")
    private long userId;
    
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

    /*public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public Long getUser() {
        return userId;
    }

    public void setUser(long user) {
        this.userId = user;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
