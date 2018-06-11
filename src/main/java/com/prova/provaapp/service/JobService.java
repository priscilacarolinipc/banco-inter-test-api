package com.prova.provaapp.service;

import com.prova.provaapp.model.Job;
import com.prova.provaapp.service.exception.JobTaskException;

import java.util.List;
import java.util.Optional;

public interface JobService{

     /**
     * Retorna um job dado um id.
     *
     * @param id
     * @return Job
     */
    Optional<Job> findById(Integer id);

    /**
     * Retorna um job dado um nome.
     *
     * @param name
     * @return Job
     */
    Job findByName(String name);

     /**
     * Salva um job.
     *
     * @param job
     * @return Job     */
    Job adicionar(Job job) throws JobTaskException;

    /**
     * Remove um job.
     *
     * @param id
     */
    void remover(Integer id);

    /**
     * Retorna umas lista de tasks.
     *
     * @return List<Job>
     */
    List<Job> listar();

}
