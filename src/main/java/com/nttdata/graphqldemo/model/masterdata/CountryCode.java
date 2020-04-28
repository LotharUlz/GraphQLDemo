package com.nttdata.graphqldemo.model.masterdata;

import io.leangen.graphql.annotations.GraphQLQuery;

public class CountryCode {
	
    @GraphQLQuery(name = "id", description = "A country codes id")
	private String id;
    
    @GraphQLQuery(name = "name", description = "A country codes name")
	private String name;
        
    public String getId() {
    	return id;
	}

    public void setId(String id) {
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
        return "CountryCode{" +
           "id=" + id +
            ", name='" + name + '\'' +
           '}';
    }
}
