package com.prova.provaapp.controller;

import com.prova.provaapp.model.Job;
import com.prova.provaapp.model.Task;
import com.prova.provaapp.service.JobService;
import com.prova.provaapp.service.exception.JobTaskException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/jobs")
@CrossOrigin(origins = "*")
public class JobController {

	private static final Logger log = LoggerFactory.getLogger(JobController.class);

	@Autowired
	private JobService jobService;

	public JobController() {
	}


	@GetMapping(produces="application/json")
	public @ResponseBody List<Job> listaJobs(@RequestParam(value = "sortByWeight", required=false) String sortByWeight){
		List<Job> listJobs = jobService.listar();
	    if(sortByWeight != null){
            listJobs.forEach((j) -> Collections.sort(j.getTasks(),(Task t1, Task t2) ->
                t2.getWeight() - t1.getWeight()));
        }
		return listJobs;
	}

	/**
	 * Adiciona um novo job.
	 *
	 * @param job
	 * @return ResponseEntity<Response<Task>>
	 */
	@PostMapping
	public ResponseEntity<Job> adicionar(@Valid @RequestBody Job job) throws ParseException, JobTaskException {
		log.info("Adicionando Task: {}", job.getName());
		job = this.jobService.adicionar(job);
		return ResponseEntity.ok(job);
	}

	/**
	 * Retorna um job dado o id.
	 * 
	 * @param id
	 * @return ResponseEntity<Optional<Job>>
	 */
	@GetMapping(value = "/{id}",  produces="application/json")
	public ResponseEntity<Optional<Job>> buscarPorId(@PathVariable("id") Integer id) {
		log.info("Buscando job por id: {}", id);
		Optional<Job> job = jobService.findById(id);

		if (!job.isPresent()) {
			log.info("Job não encontrada para o id: {}", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(job);
	}

	@PutMapping(value = "/{id}",  produces="application/json")
	public ResponseEntity<Job> atualizar(@PathVariable("id") Integer id, @Valid @RequestBody Job job) throws ParseException, JobTaskException {
		log.info("Atualizando job: {}", job.getName());
		job.setId(id);
		job = this.jobService.adicionar(job);
		return ResponseEntity.ok(job);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		jobService.remover(id);
		return ResponseEntity.ok().build();
	}

}
