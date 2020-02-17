package org.kidding.adapter;

import java.util.List;

import org.kidding.domain.ToDoItem;
import org.kidding.response.ToDoItemResponse;

//ToDoItem을 ToDoItemResponse로 바꾸어주는 역할
public class ToDoItemAdapter {
	
    public static ToDoItemResponse toDoItemResponse(final ToDoItem toDoItem, final List<String> errors) {
        return ToDoItemResponse.builder().toDoItem(toDoItem).errors(errors).build();
    }

}
