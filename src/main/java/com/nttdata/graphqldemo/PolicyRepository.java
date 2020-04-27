package com.nttdata.graphqldemo;

import javax.annotation.sql.DataSourceDefinition;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@DataSourceDefinition(
        name="java:app/MyDatasource",
        className="org.h2.jdbcx.JdbcDataSource",
        url="jdbc:h2:mem:test")
interface PolicyRepository extends JpaRepository<Policy, Long> {
}
