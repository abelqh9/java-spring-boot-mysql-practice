package com.codingdojo.administrador_de_tareas.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.codingdojo.administrador_de_tareas.models.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long>{
	
	@Query(value="Select * from tasks order by FIELD(priority, 'high', 'medium', 'low')", nativeQuery=true)
	List<Task> findAll_DESC();
	
	@Query(value="Select * from tasks order by FIELD(priority, 'low', 'medium', 'high')", nativeQuery=true)
	List<Task> findAll_ASC();
	
	List<Task> findAll();
	
	Optional<Task> findById(Long id);

}
