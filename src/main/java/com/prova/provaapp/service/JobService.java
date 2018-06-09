package com.prova.provaapp.service;

import com.prova.provaapp.model.Job;
import com.prova.provaapp.repository.JobRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class JobService {

    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }


    @GetMapping(produces="application/json")
    public @ResponseBody
    List<Job> listar() {
        return jobRepository.findAll();
    }

    @PostMapping
    public Job adicionar(@Valid @RequestBody Job job) {
        return jobRepository.save(job);
    }
}
