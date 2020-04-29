package com.nttdata.graphqldemo.model.partner;

import javax.persistence.*;
import io.leangen.graphql.annotations.GraphQLQuery;

@Entity
@Table(name = "users", schema = "partner")
public class User {

    @Id 
    @GeneratedValue
    @Column(name="id")
    @GraphQLQuery(name = "id", description = "A user's id")
    private Long id;

    @GraphQLQuery(name = "name", description = "A user's name")
    @Column(name="name")
    private String name;
    
    @Column(name="countryCodeId")
    private String countryCodeId;
    
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

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }

	public String getCountryCodeId() {
		return countryCodeId;
	}

	public void setCountryCodeId(String countryCodeId) {
		this.countryCodeId = countryCodeId;
	}
}
