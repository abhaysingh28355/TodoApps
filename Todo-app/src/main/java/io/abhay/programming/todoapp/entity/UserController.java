package io.abhay.programming.todoapp.entity;

import java.util.NoSuchElementException;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import io.abhay.programming.todoapp.TodoRepository;
import io.abhay.programming.todoapp.UserRepository;


@RestController
@RequestMapping("/users")
public class UserController {
	
	 private UserRepository userRepository;
	   private TodoRepository todoRepository;

	    public UserController(UserRepository userRepository, TodoRepository todoRepository) {
	        this.userRepository = userRepository;
	        this.todoRepository = todoRepository;
	    }

	    @GetMapping("/{userId}")
	    public User getUserById(@PathVariable Long userId){
	        return todoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	    }

	    @PostMapping
	    public User addUser(@RequestBody AddUserRequest userRequest){
	        User user = new User();
	        user.setUsername( userRequest.getUsername());
	        user.setPassword(userRequest.getPassword());
	        return userRepository.save(user);
	    }

	    @PostMapping("/{userId}/todos")
	    public void addTodo(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest){
	        User user = todoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        Todo todo = new Todo();
	        todo.setContent(todoRequest.getContent());
	        user.getTodoList().add(todo);
	        userRepository.save(user);
	    }

	    @PostMapping("/todos/{todoId}")
	    public void toggleTodoCompleted( @PathVariable Long todoId){
	        Todo todo = userRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
	        todo.setCompleted(!todo.getCompleted());
	        todoRepository.save(todo);
	    }


	    @DeleteMapping("{userId}/todos/{todoId}")
	    public void deleteTodo(@PathVariable Long userId,@PathVariable Long todoId){
	        User user = todoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        Todo todo = userRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
	        user.getTodoList().remove(todo);
	        userRepository.delete(todo);
	    }

	    @DeleteMapping("/{userId}")
	    public void deleteUser(@PathVariable Long userId){
	        User user = todoRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
	        todoRepository.delete(user);
	    }



}
