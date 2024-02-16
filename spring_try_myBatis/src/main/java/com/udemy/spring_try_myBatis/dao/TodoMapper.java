package com.udemy.spring_try_myBatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.udemy.spring_try_myBatis.entity.Todo;

@Mapper
public interface TodoMapper {
	
	List<Todo> getAllTodos();
	
	Todo getTodo(Long id);
	
	void addTodo(Todo todo);
	
	void updateTodo(Long id, Todo todo);
	
	void deleteTodo(Long id);
	
	List<Todo> getTodoByTitle(String title);
	
	List<Todo> getTodoByStatus(String status);
	
	List<Todo> getTodosortById();
	
	List<Todo> getTodosortByStatus();
}
