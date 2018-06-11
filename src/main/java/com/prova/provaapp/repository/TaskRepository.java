package com.prova.provaapp.repository;

import com.prova.provaapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Transactional(readOnly = true)
    Task findByName(String name);
}
