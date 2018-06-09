package com.prova.provaapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.List;


@Entity
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String name;
	
	@NotNull
	private Boolean  active;

	List<Task> ltask;

    public List<Task> getLtask() {
        return ltask;
    }

    public void setLtask(List<Task> ltask) {
        this.ltask = ltask;
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

	public List<Task> sortByweight(){
		ltask.sort((Task s1, Task s2) -> s1.getWeight() - s2.getWeight());
		return ltask;
    }
}
