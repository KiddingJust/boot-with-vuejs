package org.kidding.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.kidding.domain.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

//레파지토리<레파지토리가 표현해야하는 모델, 타입>
public interface ToDoItemRepository extends MongoRepository<ToDoItem, String> {

	List<ToDoItem> findByTodoDateBetween(LocalDateTime start, LocalDateTime end);
	void removeById(String Id);
}
