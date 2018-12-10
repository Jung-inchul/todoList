/**
 * 
 */
package com.homework.todolist.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.homework.todolist.dao.MapTodoRepository;
import com.homework.todolist.dao.TodoRepository;
import com.homework.todolist.model.MapTodo;
import com.homework.todolist.model.Todo;
import com.homework.todolist.model.pojo.GetTodoParameter;
import com.homework.todolist.model.pojo.GetTodoResponse;
import com.homework.todolist.model.pojo.SaveTodo;
import com.querydsl.core.QueryResults;


/**
 * @author Incheol Jung
 */
@Service
public class TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private MapTodoRepository mapTodoRepository;
	
	/**
	 * 
	 * Get TodoList
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param parameter
	 * @return
	 */
	public GetTodoResponse getTodos(GetTodoParameter parameter) {
		GetTodoResponse response = new GetTodoResponse();
		QueryResults<Todo> result = todoRepository.findTodos(parameter);
		response.setItems(result.getResults());
		response.setTotalCount(result.getTotal());
		return response;
	}
	
	/**
	 * 
	 * save Todo
	 * 
	 * @since 2018. 12. 5.
	 * @author Incheol Jung
	 * @param todo
	 * @return
	 * @throws Exception 
	 */
	@Transactional
	public Todo saveTodo(Integer todoId, SaveTodo parameter) throws Exception {
		Todo todo = new Todo();
		if(todoId != null) {
			todo = todoRepository.findOneByTodoId(todoId);
		}
		
		boolean check = CollectionUtils.isEmpty(parameter.getReferenceIds()) ? 
							true : this.checkExistIds(parameter.getReferenceIds());
		
		if(check) {
			if(parameter.getIsDone()) {
				boolean referenceIsDone = todoRepository.checkisDonewithReferenceIds(parameter.getReferenceIds());
				if(referenceIsDone != true) {
					throw new Exception("Sorry, Reference tasks are done yet");
				}
			}
			
			todo.setCreatedDate(parameter.getCreatedDate());
			todo.setUpdatedDate(parameter.getUpdatedDate());
			todo.setTask(parameter.getTask());
			todo.setIsDone(parameter.getIsDone());
			todoRepository.save(todo);
			
			if(todoId != null) mapTodoRepository.deleteByTodoId(todo.getTodoId());
			
			if(!CollectionUtils.isEmpty(parameter.getReferenceIds())) {
				List<MapTodo> list = new ArrayList<MapTodo>();
				for(Integer referenceId: parameter.getReferenceIds()) {
					MapTodo mapTodo = new MapTodo();
					mapTodo.setTodoId(todo.getTodoId());
					mapTodo.setReferenceId(referenceId);
					list.add(mapTodo);
				}
				mapTodoRepository.saveAll(list);
				todo.setMapTodos(list);
			}
			
			return todo;
		}else {
			throw new Exception("Check Reference Ids");
		}
	}
	
	/**
	 * 
	 * @param todoIds
	 * @return
	 */
	private boolean checkExistIds(List<Integer> todoIds) {
		Integer count = todoRepository.countByTodoIdIn(todoIds);
		return (todoIds.size() == count);
	}
	
	
	/**
	 * 
	 * delete Todo
	 * 
	 * @since 2018. 12. 9.
	 * @author Incheol Jung
	 * @param todoId
	 * @return
	 */
	@Transactional
	public String deleteTodo(Integer todoId) {
		todoRepository.deleteById(todoId);
		return "success";
	}
}