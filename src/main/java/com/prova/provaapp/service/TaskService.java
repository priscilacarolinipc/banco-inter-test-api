package com.prova.provaapp.service;

import com.prova.provaapp.model.Task;
import com.prova.provaapp.service.exception.JobTaskException;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    /**
     * Retorna uma task dado um id.
     *
     * @param id
     * @return Optional<Task>
     */
    Optional<Task> findById(Integer id);

    /**
     * Retorna uma task dado um nome.
     *
     * @param name
     * @return Optional<Task>
     */
    Task findByName(String name);


    /**
     * Salva um task.
     *
     * @param task
     * @return Optional<Task>
     */
    Task adicionar(Task task) throws JobTaskException;

    /**
     * Remove um task.
     *
     * @param id
     */
    void remover(Integer id);

    /**
     * Retorna umas lista de tasks.
     *
     * @return List<Task>
     */
    List<Task> listar();
}
