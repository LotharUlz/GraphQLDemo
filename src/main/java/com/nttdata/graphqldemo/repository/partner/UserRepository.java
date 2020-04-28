package com.nttdata.graphqldemo.repository.partner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import  com.nttdata.graphqldemo.model.partner.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
