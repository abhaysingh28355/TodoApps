package io.abhay.programming.todoapp;




import io.abhay.programming.todoapp.entity.Todo;
import io.abhay.programming.todoapp.entity.User;
//import io.abhay.programming.todoapp.repository.TodoRepository;
//import io.abhay.programming.todoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
		
	}
		
		@Override
		 public void run(String... args) throws Exception {

	        User user = new User();
	        user.setPassword("should be hashed");
	        user.setUsername("John");

	        Todo todo  = new Todo();
	        todo.setContent("Upload video to YT");

	        user.getTodoList().add(todo);

	        todoRepository.save(todo);
	        userRepository.save(user);
	    }
		
	

}
