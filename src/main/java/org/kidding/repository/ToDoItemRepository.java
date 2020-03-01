package org.kidding.repository;

import org.kidding.domain.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

//레파지토리<레파지토리가 표현해야하는 모델, 타입>
public interface ToDoItemRepository extends MongoRepository<ToDoItem, String> {

}
