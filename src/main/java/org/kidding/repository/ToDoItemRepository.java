package org.kidding.repository;

import org.kidding.domain.ToDoItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoItemRepository extends MongoRepository<ToDoItem, String> {

}
