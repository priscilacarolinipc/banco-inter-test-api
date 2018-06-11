package com.prova.provaapp.controller;

import com.prova.provaapp.model.Task;
import com.prova.provaapp.service.TaskService;
import com.prova.provaapp.service.exception.JobTaskException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

	private static final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	private TaskService taskService;

	public TaskController() {
	}


	@GetMapping(produces="application/json")
	public @ResponseBody List<Task> listaTasks(@RequestParam(value = "createdAt", required=false) String createdAt) throws ParseException {

        List<Task> listTasks = taskService.listar();
	    if(createdAt != null){
            List<Task> tasks = listTasks.stream()
                    .filter(t -> t.getCreatedAt().toString().equals(createdAt))
                    .collect(Collectors.toList());
            listTasks =  tasks;
        }
		return listTasks;
	}

	/**
	 * Adiciona uma nova task.
	 *
	 * @param task
	 * @param result
	 * @return ResponseEntity<Response<Task>>
	 */
	@PostMapping
	public ResponseEntity<Task> adicionar(@Valid @RequestBody Task task,
															 BindingResult result) throws ParseException, JobTaskException {
		log.info("Adicionando Task: {}", task.getName());
		task = this.taskService.adicionar(task);
		return ResponseEntity.ok(task);
	}

	/**
	 * Retorna um job dado o id.
	 * 
	 * @param id
	 * @return ResponseEntity<Optional<Task>>
	 */
	@GetMapping(value = "/{id}",  produces="application/json")
	public ResponseEntity<Optional<Task>> buscarPorId(@PathVariable("id") Integer id) {
		log.info("Buscando job por id: {}", id);
		Optional<Task> task = taskService.findById(id);

		if (!task.isPresent()) {
			log.info("Job não encontrada para o id: {}", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}

	@PutMapping(value = "/{id}",  produces="application/json")
	public ResponseEntity<Task> atualizar(@PathVariable("id") Integer id,
												 @Valid @RequestBody Task task, BindingResult result) throws ParseException, JobTaskException {
        log.info("Buscando job por id: {}", id);
        Optional<Task> existente = taskService.findById(id);
	    log.info("Atualizando task: {}", existente.get().getName());
        if (!existente.isPresent()) {
            log.info("Job não encontrada para o id: {}", id);
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(task,existente.get(), "id");
		task = this.taskService.adicionar(existente.get());
		return ResponseEntity.ok(task);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Integer id) {
		taskService.remover(id);
		return ResponseEntity.ok().build();
	}

}
