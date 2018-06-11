package com.prova.provaapp.service.impl;

import com.prova.provaapp.model.Task;
import com.prova.provaapp.repository.TaskRepository;
import com.prova.provaapp.service.TaskService;
import com.prova.provaapp.service.exception.JobTaskException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);

    @Autowired
    private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    @Override
    public Optional<Task> findById(Integer id) {
        log.info("Buscando uma task pelo o id {}", id);
        return taskRepository.findById(id);
    }

    @Override
    public Task findByName(String name) {
        log.info("Buscando uma task pelo o nome {}", name);
        return taskRepository.findByName(name);
    }

     public Task adicionar(Task task) throws JobTaskException {
        log.info("Adicionando task: {}", task);
        Optional<Task> optional = Optional.ofNullable(taskRepository.findByName(task.getName()));

        if( optional.isPresent() && task.getId() != optional.get().getId() ) {
            throw new JobTaskException("Já existe uma task cadastrado com o Nome '"+task.getName()+"'");
        }

        return this.taskRepository.save(task);
    }

    @Override
    public void remover(Integer id) {
        log.info("Removendo task com id: {}", id);
        this.taskRepository.deleteById(id);
    }

    public List<Task > listar() {
        return this.taskRepository.findAll();
    }


}
