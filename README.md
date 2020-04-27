# GraphQLDemo

A simple insurance demo with GraphQL

## Build & Run

Build & run the demo application using Docker:

```bash
docker build -t graphqldemo:latest .
docker run -p 8080:8080 graphqldemo:latest
```

After starting up the demo application the GraphQL GUI is available on [http://localhost:8080/gui](http://localhost:8080/gui).

## Examples
### REST Services
http://localhost:8080/users

http://localhost:8080/policies

http://localhost:8080/allCountries

http://localhost:8080/user?id=1

http://localhost:8080/saveUser?name=Gross

http://localhost:8080/updateUser?id=1&name=Schöne

http://localhost:8080/updatePolicy?id=1&name=Haftpflicht

http://localhost:8080/deleteUser?id=1

http://localhost:8080/deletePolicy?id=1

### GraphQL Queries

{
  users {
    id
    name
    isMale
    country {
      id
      name
    }
    policies {
      id
      name
    }
  }
}

{ user(id: 1) { id, name, isMale } }

{ policy(id: 1) { id, name } }

mutation {
  saveUser(user: { name: "Schreiner" }) {
    id
    isMale
  }
}

mutation {
  updateCountry(user: {id: 3}, countryId: "AT") {
    id
    isMale
    country {id, name}
  }
}

mutation {
  savePolicy(policy: { name: "Haftpflicht"}, userName: "Meier") {
    id
    name
  }
}

mutation {
  savePolicy(policy: { name: "Hausrat"}, userId: 1) {
    id
    name
  }
}

mutation {
  updateUser(id: 2, user: { name: "Gross" }) {
    id
    isMale
  }
}

mutation {
  updatePolicy(id: 2, policy: { name: "Unfall" }) {
    id
    isMale
  }
}

mutation {
  deleteUser(id: 3) 
}
