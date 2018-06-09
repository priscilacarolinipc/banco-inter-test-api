package com.prova.provaapp.resource;

import com.prova.provaapp.model.Job;
import com.prova.provaapp.service.JobService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
public class JobResource {

	private final JobService jobService;

	public JobResource(JobService jobService) {
		this.jobService = jobService;
	}

	@GetMapping
	public ResponseEntity<Iterable<Job>> findAll() {
		return new ResponseEntity<>(this.jobService.listar(), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<Iterable<Job>> findAll() {
		return new ResponseEntity<>(this.jobService.listar(), HttpStatus.OK);
	}




}











