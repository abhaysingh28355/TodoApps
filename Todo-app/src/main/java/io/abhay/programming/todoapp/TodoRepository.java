package io.abhay.programming.todoapp;

import org.springframework.data.jpa.repository.JpaRepository;

import io.abhay.programming.todoapp.entity.Todo;
import io.abhay.programming.todoapp.entity.User;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<User,Long>{

	void save(Todo todo);
	
	//void save(Todo todo);

}
