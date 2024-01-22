package io.abhay.programming.todoapp;

import org.springframework.data.jpa.repository.JpaRepository;

import io.abhay.programming.todoapp.entity.Todo;
import io.abhay.programming.todoapp.entity.User;

public interface UserRepository extends JpaRepository<Todo,Long>{

	User save(User user);
	
	

}
