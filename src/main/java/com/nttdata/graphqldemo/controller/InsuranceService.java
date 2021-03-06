package com.nttdata.graphqldemo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

import com.nttdata.graphqldemo.model.partner.*;
import com.nttdata.graphqldemo.model.policy.*;
import com.nttdata.graphqldemo.model.masterdata.*;
import com.nttdata.graphqldemo.repository.partner.*;
import com.nttdata.graphqldemo.repository.policy.*;

@Service
@GraphQLApi
@RestController
public class InsuranceService {

	private final UserRepository userRepository;
	private final PolicyRepository policyRepository;
	
	// cache data from external webservice
	private List<CountryCode> allCountries;

	public InsuranceService(UserRepository foodRepository, PolicyRepository ingredientRepository) {
		this.userRepository = foodRepository;
		this.policyRepository = ingredientRepository;
	}

	@RequestMapping("/users")
	public String getUsersRequest() {
		return this.getUsers().toString();
	}

	@RequestMapping("/user")
	public String getUserRequest(@RequestParam(name = "id") Long id) {
		return this.getUserById(id).toString();
	}
	
	@RequestMapping("/allCountries")
	public List<CountryCode> getAllCountriesRequest() {
		return this.allCountries;
	}
	
	@RequestMapping("/saveUser")
	public String saveUserRequest(@RequestParam(name = "name") String name) {
		User user = new User();
		user.setName(name);
		this.saveUser(user);
		return "User has been created: " + this.getUserById(user.getId()).toString();
	}
	
	@RequestMapping("/updateUser")
	public String updateUserRequest(@RequestParam(name = "id") long id, @RequestParam(name = "name") String name) {
		Optional<User> userToUpdate = userRepository.findById(id);
		userToUpdate.get().setName(name);
        this.updateUser(id, userToUpdate.get());
		return "User has been updated: " + this.getUserById(id).toString();
	}
	
	@RequestMapping("/updatePolicy")
	public String updatePolicyRequest(@RequestParam(name = "id") long id, @RequestParam(name = "name") String name) {
		Optional<Policy> policyToUpdate = policyRepository.findById(id);
		policyToUpdate.get().setName(name);
        this.updatePolicy(id, policyToUpdate.get());
		return "Policy has been updated: " + this.getPolicyById(id).toString();
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUserRequest(@RequestParam(name = "id") Long id) {
		this.deleteUser(id);
		return "User has been deleted. Id: " + id;
	}
	
	@RequestMapping("/deletePolicy")
	public String deletePolicyRequest(@RequestParam(name = "id") Long id) {
		this.deletePolicy(id);
		return "Policy has been deleted. Id: " + id;
	}
	
	@GraphQLMutation(name = "saveUser")
	public User saveUser(@GraphQLArgument(name = "user") User user) {
		return userRepository.saveAndFlush(user);
	}

	@GraphQLMutation(name = "savePolicy")
	public Policy savePolicy(@GraphQLArgument(name = "policy") Policy policy) {
		return policyRepository.saveAndFlush(policy);
	}
	
	@GraphQLMutation(name = "savePolicy")
	public Policy savePolicy(@GraphQLArgument(name = "policy") Policy policy,
			@GraphQLArgument(name="userName") String userName) {
		List<User> users = this.userRepository.findAll();
		policy.setUserId(
			users.stream().filter(x -> x.getName().equalsIgnoreCase(userName)).findFirst().get().getId()
		);
		
		return policyRepository.save(policy);
	}
	
	@GraphQLMutation(name = "updateUser")
	public User updateUser(@GraphQLArgument(name = "id") long id, @GraphQLArgument(name = "user") User user) {
		User userToUpdate = userRepository.findById(id).get();
		userToUpdate.setName(user.getName());
		return userRepository.save(userToUpdate);
	}

	@GraphQLMutation(name = "updatePolicy")
	public Policy updatePolicy(@GraphQLArgument(name = "id") long id, @GraphQLArgument(name = "policy") Policy policy) {
		Policy policyToUpdate = policyRepository.findById(id).get();
		policyToUpdate.setName(policy.getName());
		return policyRepository.save(policyToUpdate);
	}
	
	@GraphQLMutation(name = "deleteUser")
	public String deleteUser(@GraphQLArgument(name = "id") Long id) {
		userRepository.deleteById(id);
		return "User has been deleted, id: " + id;
	}

	@GraphQLMutation(name = "deletePolicy")
	public String deletePolicy(@GraphQLArgument(name = "id") Long id) {
		policyRepository.deleteById(id);
		return "Policy has been deleted, id: " + id;
	}
	
	@GraphQLMutation(name = "updateCountry")
    public User updateCountry(@GraphQLArgument(name = "user") User user, @GraphQLArgument(name = "countryId") String countryId) {
		User userToUpdate = userRepository.findById(user.getId()).get();
		userToUpdate.setCountryCodeId(countryId);
		return userRepository.save(userToUpdate);
	}

	@GraphQLQuery(name = "users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GraphQLQuery(name = "policies")
	public List<Policy> getPolicies() {
		return policyRepository.findAll();
	}

    @GraphQLQuery(name = "policyUser")
    public User user(@GraphQLContext Policy policy) {
        if (policy.getUserId() == null) {
            return null;
        }
        return userRepository.findById(policy.getUserId()).orElseThrow();
    }

    @GraphQLQuery(name = "userPolicies")
    public List<Policy> policy(@GraphQLContext User user) {
        if (user.getId() == null) {
            return null;
		}
		return policyRepository.findAll().stream().
								filter(x -> x.getUserId().equals(user.getId())).
								collect(Collectors.toList());
    }

	@GraphQLQuery(name = "user")
	public Optional<User> getUserById(@GraphQLArgument(name = "id") Long id) {
		return userRepository.findById(id);
	}

	@GraphQLQuery(name = "policy")
	public Optional<Policy> getPolicyById(@GraphQLArgument(name = "id") Long id) {
		return policyRepository.findById(id);
	}
	
	@GraphQLQuery(name = "isMale") // Calculated property of User
	public boolean isMale(@GraphQLContext User user) {
		return user.getName() != null && user.getName().length() < 10;
	}
	
	@GraphQLQuery(name = "country") // Calculated property of User
	public CountryCode country(@GraphQLContext User user) {
		if (this.allCountries == null) {
			this.allCountries = this.initAllCountryCodes();
		}
		
        return this.allCountries.stream()
        		.filter(x -> x.getId().equalsIgnoreCase(user.getCountryCodeId()))
        		.findFirst().get();
	}
	
	private List<CountryCode> initAllCountryCodes() {
		String json = this.jsonGetRequest(MasterdataService.countryUrl);
		
		if (json == null) {
			return null;
		}
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = null;
		try {
			rootNode = mapper.readTree(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
		
		List<CountryCode> allCountryCodes = new ArrayList<CountryCode>();
        JsonNode firstNode = rootNode.get("result");
        Iterator<JsonNode> it = firstNode.elements();
        while (it.hasNext()) {
        	CountryCode currentCountryCode = new CountryCode();
            JsonNode currentNode = it.next();
        	currentCountryCode.setId(currentNode.get("code").asText());
        	currentCountryCode.setName(currentNode.get("name").asText());
            allCountryCodes.add(currentCountryCode); 
        }
        
        return allCountryCodes;
	}
	
	private String jsonGetRequest(String urlQueryString) {
		String json = null;
		try {
			URL url = new URL(urlQueryString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		    connection.setDoOutput(true);
		    connection.setInstanceFollowRedirects(false);
		    connection.setRequestMethod("GET");
		    connection.setRequestProperty("Content-Type", "application/json");
		    connection.setRequestProperty("charset", "utf-8");
		    connection.connect();
		    InputStream inStream = connection.getInputStream();
		    json = streamToString(inStream); // input stream to string
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		}
			return json;
	}
	  
	  private String streamToString(InputStream inputStream) {
		  @SuppressWarnings("resource")
		  String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
		  return text;
	  }
}
