package com.prova.provaapp.repository;

import com.prova.provaapp.model.Job;
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
public class JobRepositoryTest {

    @Autowired
    private JobRepository jobRepository;

    private static final String NOME = "Job_teste";

    @Before
    public void setUp(){
        Job job = new Job();
        job.setName("Job_teste");
        job.setActive(true);
        this.jobRepository.save(job);
    }

    @After
    public final void tearDown() {
        this.jobRepository.deleteAll();
    }


    @Test
    public void testBuscarPorNome() {
        Job job = this.jobRepository.findByName(NOME);
        assertEquals(NOME, job.getName());
    }


}