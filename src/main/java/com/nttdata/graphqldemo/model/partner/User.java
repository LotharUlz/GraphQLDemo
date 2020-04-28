package com.nttdata.graphqldemo.model.partner;

import com.nttdata.graphqldemo.model.policy.Policy;

import javax.persistence.*;
import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue
    @GraphQLQuery(name = "id", description = "A user's id")
    private Long id;

    @GraphQLQuery(name = "name", description = "A user's name")
    private String name;
    
    //@OneToMany
    //@GraphQLQuery(name = "policies", description = "A user's policies")
    //private List<Policy> policies = new ArrayList<Policy>();
    
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

    /*public List<Policy> getPolicies() {
    	return policies;
    }
    
    public void setPolicies(List<Policy> policies) {
    	this.policies = policies;
    }*/
     
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