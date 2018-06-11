package com.prova.provaapp.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "job")
public class Job implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Boolean  active;

	@OneToMany(mappedBy = "job", cascade = CascadeType.ALL)
	private List<Task> tasks;

    @ManyToOne(cascade={CascadeType.ALL})
    @JoinColumn(name="job_id")
    private Job jobFather;

    @OneToMany(mappedBy="jobFather")
    private Set<Job> parentJob = new HashSet<Job>();


	public Job(){

	}

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

    public Job getJobFather() {
        return jobFather;
    }

    public void setJobFather(Job jobFather) {
        this.jobFather = jobFather;
    }

    public Set<Job> getParentJob() {
        return parentJob;
    }

    public void setParentJob(Set<Job> parentJob) {
        this.parentJob = parentJob;
    }
}
