# GraphQLDemo

A simple insurance demo with GraphQL

## Build & Run

Build & run the demo application using Docker:

```bash
docker build -t graphqldemo .
docker run -p 8080:8080 graphqldemo:latest
```

After starting up the demo application the GraphQL GUI is available on [http://localhost:8080/gui](http://localhost:8080/gui).

## Examples
### REST Services
http://localhost:8080/users
http://localhost:8080/policies
http://localhost:8080/countries

http://localhost:8080/user?id=1
http://localhost:8080/saveUser?name=Gross
http://localhost:8080/updateUser?id=1&name=Schöne

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
  deleteUser(id: 3) 
}
