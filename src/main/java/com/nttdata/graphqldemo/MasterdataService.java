package com.nttdata.graphqldemo;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
@RestController
public class MasterdataService {

	//public static final String countryUrl = "http://localhost:8080/countries";
	public static final String countryUrl = "https://api.printful.com/countries";
	
	@RequestMapping("/countries")
	public List<CountryCode> getCountriesRequest() {
		
		CountryCode country1 = new CountryCode();
		country1.setId("DE");
		country1.setName("Germany");
		
		CountryCode country2 = new CountryCode();
		country2.setId("CH");
		country2.setName("Switzerland");
		
		CountryCode country3 = new CountryCode();
		country3.setId("AT");
		country3.setName("Austria");
		
		CountryCode country4 = new CountryCode();
		country4.setId("IT");
		country4.setName("Italy");
		
		CountryCode country5 = new CountryCode();
		country5.setId("FR");
		country5.setName("France");
		
		return List.of(country1, country2, country3, country4, country5);
	}
}
