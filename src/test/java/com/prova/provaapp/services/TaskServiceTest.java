package com.prova.provaapp.services;

import com.prova.provaapp.model.Task;
import com.prova.provaapp.repository.TaskRepository;
import com.prova.provaapp.service.TaskService;
import com.prova.provaapp.service.exception.JobTaskException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TaskServiceTest {

	@MockBean
	private TaskRepository taskRepository;

	@Autowired
	private TaskService taskService;

	private static final Integer ID = 1;
	private static final String NOME = "Task12";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.taskRepository.findByName(Mockito.anyString())).willReturn(new Task());
		BDDMockito.given(this.taskRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Task()));
		BDDMockito.given(this.taskRepository.save(Mockito.any(Task.class))).willReturn(new Task());
	}

	@Test
	public void testBuscarTaskPorId() {
		Optional<Task> task = this.taskService.findById(ID);
		assertTrue(task.isPresent());
	}

	@Test
	public void testBuscarTaskPorNome() {
		Optional<Task> task = Optional.ofNullable(this.taskService.findByName(NOME));
		assertTrue(task.isPresent());
	}

	@Test
	public void testPersistirTask() throws JobTaskException {
		Task task = this.taskService.adicionar(new Task());
		assertNotNull(task);
	}

	@Test
	public void testRemoverTask() throws JobTaskException {
		Optional<Task> task_aux = this.taskService.findById(ID);
		this.taskService.remover(task_aux.get().getId());
	}

}
