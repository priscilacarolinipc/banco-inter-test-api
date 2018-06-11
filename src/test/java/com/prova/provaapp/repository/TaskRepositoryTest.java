package com.prova.provaapp.repository;

import com.prova.provaapp.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    private static final Integer ID = 1;
    private static final String NOME = "Task1";

    @Before
    public void setUp() {
        Task task = new Task();
        task.setName("Task1");
        task.setWeight(5);
        task.setCompleted(true);
        this.taskRepository.save(task);
    }


    @After
    public final void tearDown() {
        this.taskRepository.deleteAll();
    }


    @Test
    public void testBuscarPorNome () {
        Task task = this.taskRepository.findByName(NOME);
        assertEquals(NOME, task.getName());
    }



}