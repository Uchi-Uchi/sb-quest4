package com.udemy.spring_try_myBatis.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udemy.spring_try_myBatis.dao.TodoMapper;
import com.udemy.spring_try_myBatis.entity.Todo;

@Service
public class TodoService {
	
	@Autowired
	private TodoMapper todoMapper;
		
	public List<Todo> getAllTodos(){
		return todoMapper.getAllTodos();
	}
	
	
	public Todo getTodo(Long id) {
		return todoMapper.getTodo(id);
	} 
	
	public void addTodo(Todo todo) {
		todoMapper.addTodo(todo);
	}
	
	public void updateTodo(Long id, Todo todo) {
		todoMapper.updateTodo(id, todo);
	}
	
	public void deleteTodo(Long id) {
		todoMapper.deleteTodo(id);
	}
	
	public List<Todo> getTodoByTitle(String title){
		return todoMapper.getTodoByTitle(title);
	}
	
	public List<Todo> getTodoByStatus(String status){
		return todoMapper.getTodoByStatus(status);
	}
	
	public List<Todo> getTodosortById(){
		return todoMapper.getTodosortById();
	}
	
	public List<Todo> getTodosortByStatus(){
		return todoMapper.getTodosortByStatus();
	}
}
