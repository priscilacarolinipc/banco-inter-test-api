package com.prova.provaapp.repository;

import com.prova.provaapp.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Transactional(readOnly = true)
    Job findByName(String name);

}


