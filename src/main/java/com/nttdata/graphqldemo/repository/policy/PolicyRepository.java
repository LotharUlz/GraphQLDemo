package com.nttdata.graphqldemo.repository.policy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nttdata.graphqldemo.model.policy.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Long> {
}
