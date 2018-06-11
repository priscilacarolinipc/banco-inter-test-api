package com.prova.provaapp.services;

import com.prova.provaapp.model.Job;
import com.prova.provaapp.model.Task;
import com.prova.provaapp.repository.JobRepository;
import com.prova.provaapp.service.JobService;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class JobServiceTest {

	@MockBean
	private JobRepository jobRepository;

	@Autowired
	private JobService jobService;
	@Autowired
	private TaskService taskService;

	private static final Integer ID = 1;
	private static final String NOME = "Job1";

	@Before
	public void setUp() throws Exception {
		BDDMockito.given(this.jobRepository.findByName(Mockito.anyString())).willReturn(new Job());
		BDDMockito.given(this.jobRepository.findById(Mockito.anyInt())).willReturn(Optional.of(new Job()));
        BDDMockito.given(this.jobRepository.save(Mockito.any(Job.class))).willReturn(new Job());
	}

	@Test
	public void testBuscarJobPorID() {
		Optional<Job> job = this.jobService.findById(ID);
		assertTrue(job.isPresent());
	}

	@Test
	public void testBuscarJobPorNome() {
        Optional<Job> job = Optional.ofNullable(this.jobService.findByName(NOME));
		assertTrue(job.isPresent());
	}

	@Test
	public void testRemoverJob() throws JobTaskException {
		Optional<Job> job_aux = this.jobService.findById(ID);
		this.jobService.remover(job_aux.get().getId());
	}


    @Test
    public void testPersistirJob() throws JobTaskException {
	    //cria job
        Optional<Job> job =  Optional.ofNullable(this.jobService.findByName(NOME));
        Job aux_job = null;
        if(!job.isPresent()){
            aux_job =  new Job();
            aux_job.setName("Job_1");
            aux_job.setActive(true);
        }else{
            aux_job =  job.get();
        }


        //cria task
        Task task = new Task();
        task.setName("Task_01");
        task.setWeight(5);
        task.setCompleted(true);

        List<Task> tasks = new ArrayList<>() ;

        task.setJob(aux_job);
        tasks.add(task);
        aux_job.setTasks(tasks);

        aux_job = this.jobService.adicionar(aux_job);
        assertNotNull(aux_job);
    }



}
