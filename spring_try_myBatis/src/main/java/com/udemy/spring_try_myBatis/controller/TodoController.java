package com.udemy.spring_try_myBatis.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udemy.spring_try_myBatis.entity.Todo;
import com.udemy.spring_try_myBatis.service.TodoService;

@RestController
public class TodoController {
	
	private static final Logger logger = LoggerFactory.getLogger(TodoController.class);
	
	@Autowired
	private TodoService todoServise;
	
	
	@GetMapping("/todos")
	public ResponseEntity<List<Todo>> getAllTodos(){
		try {
			List<Todo> todos = todoServise.getAllTodos();
			if(!todos.isEmpty()) {
				logger.info("TODOのリスト");
				return new ResponseEntity<>(todos, HttpStatus.OK);
			}else {
				logger.info("TODOのリストが見つかりません");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch (Exception e) {
			logger.error("エラーが発生しました", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/todos/{id}")
	public ResponseEntity<Todo> getTodo(@PathVariable("id") Long id) {
		try {
			Todo todo = todoServise.getTodo(id);
			if(todo != null) {
				logger.info("特定のTODOの詳細");
				return new ResponseEntity<>(todo, HttpStatus.OK);
			}else {
				logger.info("TODOが見つかりません");
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {
			logger.error("エラーが発生しました", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PostMapping("/todos")
	public ResponseEntity<Void> addTodo(@RequestBody Todo todo) {
		try {
			if(isValidateStatus(todo.getStatus())) {
				todoServise.addTodo(todo);
				logger.info("TODOが作成されました");
				return new ResponseEntity<>(HttpStatus.CREATED);
			}else {
				logger.info("無効なステータスが指定されました");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
				
			}
		}catch(Exception e) {
			logger.error("TODOが作成できません", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/todos/{id}")
	public ResponseEntity<Void> updateTodo(
			@RequestBody Todo todo,
			@PathVariable("id") Long id) {
		try {
			if(isValidateStatus(todo.getStatus())) {
				todoServise.updateTodo(id, todo);
				logger.info("TODOが更新されました");
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				logger.info("無効なステータスが指定されました");
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			logger.error("TODOが見つかりません", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/todos/{id}")
	public ResponseEntity<Void> delteTodo(@PathVariable("id") Long id) {
		try {
			todoServise.deleteTodo(id);
			logger.info("TODOが削除されました");
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			logger.error("TODOが見つかりません", e);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	//フィルタリング
	/*titleでフィルタリング*/
	@GetMapping("/todos/title/{title}")
	public ResponseEntity<List<Todo>> getTodoByTitle(@PathVariable("title") String title){
		try {
			List<Todo> todosByTitle = todoServise.getTodoByTitle(URLDecoder.decode(title, StandardCharsets.UTF_8.toString()));
				logger.info(title + "でフィルタリング");
				return new ResponseEntity<>(todosByTitle, HttpStatus.OK);
		} catch (UnsupportedEncodingException e) {
			logger.warn("デコードに失敗しました", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	logger.error("エラーが発生しました", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	/*statusでフィルタリング*/
	@GetMapping("/todos/status/{status}")
	public ResponseEntity<List<Todo>> getTodoByStatus(@PathVariable("status") String status){
		try {
			List<Todo> todosByStatus = todoServise.getTodoByStatus(URLDecoder.decode(status, StandardCharsets.UTF_8.toString()));
			logger.info(status + "でフィルタリング");
			return new ResponseEntity<>(todosByStatus, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
        	logger.warn("デコードに失敗しました", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
        	logger.error("エラーが発生しました", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	}
	
	//ソート
	/*idでソート*/
	@GetMapping("/todos/sortById")
	public ResponseEntity<List<Todo>> getTodosortById(){
		try {
			List<Todo> todosSortById = todoServise.getTodosortById();
			logger.info("idでフィルタリング");
			return new ResponseEntity<>(todosSortById, HttpStatus.OK);
		}catch (Exception e) {
			logger.error("エラーが発生しました", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/*statusでソート*/
	@GetMapping("/todos/sortByStatus")
	public ResponseEntity<List<Todo>> getTodosortByStatus(){
		try {
			List<Todo> todosSortByStatus= todoServise.getTodosortByStatus();
			logger.info("idでフィルタリング");
			return new ResponseEntity<>(todosSortByStatus, HttpStatus.OK);
		}catch (Exception e) {
			logger.error("エラーが発生しました", e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	//バリデーションメソッド
	public boolean isValidateStatus(String status) {
		List<String> validateStatusList = Arrays.asList("未着手","着手","完了");
		return validateStatusList.contains(status);
	}
}

