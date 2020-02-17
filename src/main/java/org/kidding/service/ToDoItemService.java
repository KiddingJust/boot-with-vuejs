package org.kidding.service;

import org.kidding.domain.ToDoItem;
import org.springframework.stereotype.Service;


//서비스는 컨트롤러와 리포지토리 사이에서 비즈니스 로직을 수행
//컨트롤러는 리퀘스트를 받아서 서비스에 넘겨줌. 그리고 서비스가 넘겨준 리스판스를 다시 리턴 
//리포지토리는 데이터베이스로 쿼리를 날리는 역할 
@Service
public class ToDoItemService {

	public ToDoItem get(final String id) {
		return ToDoItem.builder()
				.title("Add an id validation")
				.build();
	}
}
