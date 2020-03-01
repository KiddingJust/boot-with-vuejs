package org.kidding.adapter;

import java.util.List;

import org.kidding.domain.ToDoItem;
import org.kidding.reqresp.ToDoItemRequest;
import org.kidding.reqresp.ToDoItemResponse;

//ToDoItem을 ToDoItemResponse로 바꾸어주는 역할
public class ToDoItemAdapter {
	
    public static ToDoItemResponse toDoItemResponse(final ToDoItem toDoItem, final List<String> errors) {
        return ToDoItemResponse.builder().toDoItem(toDoItem).errors(errors).build();
    }
    
    public static ToDoItem toDoItem(final ToDoItemRequest toDoItemRequest) {
    	if(toDoItemRequest == null) {
    		return null;
    	}
    	return ToDoItem.builder()
    		.title(toDoItemRequest.getTitle())
    		.done(toDoItemRequest.isDone())
    		.build();
    }
}
