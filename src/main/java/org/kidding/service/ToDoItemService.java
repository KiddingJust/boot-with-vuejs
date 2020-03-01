package org.kidding.service;

import java.util.List;

import org.kidding.domain.ToDoItem;
import org.kidding.repository.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//서비스는 컨트롤러와 리포지토리 사이에서 비즈니스 로직을 수행
//컨트롤러는 리퀘스트를 받아서 서비스에 넘겨줌. 그리고 서비스가 넘겨준 리스판스를 다시 리턴 
//리포지토리는 데이터베이스로 쿼리를 날리는 역할 
@Service
public class ToDoItemService {

	
	@Autowired
	private ToDoItemRepository toDoItemRepository;
	
	public ToDoItem get(final String id) {
		// do id validation
		return toDoItemRepository.findById(id).orElse(null);
	}
	

	public ToDoItem create(final ToDoItem toDoItem) {
		if(toDoItem == null) {
			throw new NullPointerException("To Do Item cannot be null.");
		}
		return toDoItemRepository.insert(toDoItem);
	}

	public List<ToDoItem> getAll() {
		return toDoItemRepository.findAll();
	}

	
	
}
