package com.prova.provaapp.service.impl;

import com.prova.provaapp.model.Job;
import com.prova.provaapp.model.Task;
import com.prova.provaapp.repository.JobRepository;
import com.prova.provaapp.service.JobService;
import com.prova.provaapp.service.exception.JobTaskException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class JobServiceImpl implements JobService {

    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private JobRepository jobRepository;

    @Override
    public Optional<Job> findById(Integer id) {
        log.info("Buscando uma job pelo id {}", id);
        return this.jobRepository.findById(id);
    }

    @Override
    public Job findByName(String name) {
        log.info("Buscando uma job pelo nome {}", name);
        return jobRepository.findByName(name);
    }

    public Job adicionar(Job job) throws JobTaskException {
        log.info("Adicionando job: {}", job);
        Optional<Job> optional = Optional.ofNullable(jobRepository.findByName(job.getName()));

        if( optional.isPresent() ) {
            throw new JobTaskException("Já existe um job cadastrado com o Nome '"+job.getName()+"'");
        }

        List<Task> tasks = new ArrayList<>() ;

        for(Task t : job.getTasks()) {
            t.setJob(job);
            tasks.add(t);
        }

        job.setTasks(tasks);

        return this.jobRepository.save(job);
    }

    public void remover(Integer id) {
        log.info("Removendo job com id: {}", id);
        this.jobRepository.deleteById(id);
    }

    @Override
    public List<Job> listar() {
        List<Job> jobs = this.jobRepository.findAll();
        return jobs;
    }

}
