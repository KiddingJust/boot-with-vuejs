package org.kidding.reqresp;

import java.util.List;

import org.kidding.domain.ToDoItem;

import lombok.Builder;

public class ToDoItemResponse extends ApiResponse<ToDoItem> {

	//생성
	@Builder
	public ToDoItemResponse(final ToDoItem toDoItem, final List<String> errors) {
		super(toDoItem);		//부모클래스인 ApiResponse의 data를 toDoItem으로 초기화
		this.setErrors(errors);
	}
	
	

}
