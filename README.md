# GraphQLDemo

Shows the functionality of GraphQL on the basis of distributed insurance data sources.

## Run

### Build & Run

Build & run the demo application using Docker Compose:

```bash
docker-compose build
docker-compose up -d
```

After starting up the demo application the GraphQL GUI is available on [http://localhost:8081/gui](http://localhost:8081/gui).

### Stop Service

```bash
docker-compose down
```

## Showcase

### REST Services

[http://localhost:8081/users](http://localhost:8081/users)

[http://localhost:8081/policies](http://localhost:8081/policies)

[http://localhost:8081/allCountries](http://localhost:8081/allCountries)

[http://localhost:8081/user?id=1](http://localhost:8081/user?id=1)

[http://localhost:8081/saveUser?name=Gross](http://localhost:8081/saveUser?name=Gross)

[http://localhost:8081/updateUser?id=1&name=Schöne](http://localhost:8081/updateUser?id=1&name=Schöne)

[http://localhost:8081/updatePolicy?id=1&name=Haftpflicht](http://localhost:8081/updatePolicy?id=1&name=Haftpflicht)

[http://localhost:8081/deleteUser?id=1](http://localhost:8081/deleteUser?id=1)

[http://localhost:8081/deletePolicy?id=1](http://localhost:8081/deletePolicy?id=1)

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
