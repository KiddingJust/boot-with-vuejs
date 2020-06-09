package org.kidding.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
		toDoItem.setTodoDate(LocalDateTime.now());
		return toDoItemRepository.insert(toDoItem);
	}

	public List<ToDoItem> getAll() {
		return toDoItemRepository.findAll();
	}
	
	public List<ToDoItem> getTodoList(String dateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate searchDate = LocalDate.parse(dateTime, formatter);

		LocalDateTime startDatetime = LocalDateTime.of(searchDate, LocalTime.of(00,00,00)); //오늘 23:59:59
		LocalDateTime endDatetime = LocalDateTime.of(searchDate, LocalTime.of(23,59,59)); //오늘 23:59:59
		
		return toDoItemRepository.findByTodoDateBetween(startDatetime, endDatetime);
	}
	
	public void clearAll(String myDate) { //delete All은 에러메세지 안뱉는다고 가정. 

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate clearDate = LocalDate.parse(myDate, formatter);
		
		LocalDateTime startDatetime = LocalDateTime.of(clearDate, LocalTime.of(00,00,00)); //오늘 23:59:59
		LocalDateTime endDatetime = LocalDateTime.of(clearDate, LocalTime.of(23,59,59)); //오늘 23:59:59

		List<ToDoItem> tempList	= toDoItemRepository.findByTodoDateBetween(startDatetime, endDatetime);
		for(int i=0; i<tempList.size(); i++) {
			toDoItemRepository.deleteById(tempList.get(i).getId());
		}
	}
	
	public void removeTodoList(final ToDoItem toDoItem) {
		if (toDoItem == null) {
			throw new NullPointerException("To Do Item Cannot be null!");
		}
		toDoItemRepository.removeById(toDoItem.getId());
	}

	public ToDoItem update(final ToDoItem toDoItem) {
		if (toDoItem == null) {
			throw new NullPointerException("To Do Item Cannot be null!");
		}
		
		final ToDoItem original = toDoItemRepository.findById(toDoItem.getId())
											.orElseThrow(() -> new RuntimeException("Entity Not Found!"));
		original.setTitle(toDoItem.getTitle());
		original.setDone(toDoItem.isDone());
		return toDoItemRepository.save(original);
	}
	
	public void toNextDay(final List<ToDoItem> toDoItems) {
		if (toDoItems == null) {
			throw new NullPointerException("To Do Item Cannot be null!");
		}
		List<ToDoItem> items = new ArrayList<>();
		ToDoItem item;
		for(int i=0; i<toDoItems.size(); i++) {
			item = toDoItemRepository.findById(toDoItems.get(i).getId()).orElse(null);
			item.setTodoDate(item.getTodoDate().plusDays(1));
			items.add(item); 
		}
		toDoItemRepository.saveAll(items);
	}
	
}
