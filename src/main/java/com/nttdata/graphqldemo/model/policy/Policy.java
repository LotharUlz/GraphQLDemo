package com.nttdata.graphqldemo.model.policy;

import javax.persistence.*;
import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "policies", schema = "policy")
public class Policy {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @GraphQLQuery(name = "id", description = "An policy's id")
    private Long id;

    @GraphQLQuery(name = "name", description = "An policy's name")
    @Column(name="name")
    private String name;

    @GraphQLQuery(name = "userId", description = "An policy's name")
    @Column(name="userId")
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}
