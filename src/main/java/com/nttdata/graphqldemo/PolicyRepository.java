package com.nttdata.graphqldemo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PolicyRepository extends JpaRepository<Policy, Long> {
}
