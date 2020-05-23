package org.kidding.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.kidding.adapter.ToDoItemAdapter;
import org.kidding.domain.ToDoItem;
import org.kidding.reqresp.ToDoItemRequest;
import org.kidding.reqresp.ToDoItemResponse;
import org.kidding.service.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//url 리퀘스트 받는 역할 
@RestController	//RESTful API를 위한 컨트롤러임을 명시 
@RequestMapping("/todo")
public class ToDoController {

	@Autowired
	private ToDoItemService toDoItemService;
	
	@RequestMapping(method = RequestMethod.GET, value="/{searchDate}")
	public @ResponseBody List<ToDoItemResponse> getTodoList(@PathVariable(value="searchDate") String dateTime){
		
		List<String> errors = new ArrayList<>();
		List<ToDoItem> toDoItems = toDoItemService.getTodoList(dateTime);
		List<ToDoItemResponse> toDoItemResponses = new ArrayList<>();
		toDoItems.stream().forEach(toDoItem -> {
			toDoItemResponses.add(ToDoItemAdapter.toDoItemResponse(toDoItem,  errors));
		});
		
		return toDoItemResponses;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody ToDoItemResponse create(@RequestBody final ToDoItemRequest toDoItemRequest) {
		List<String> errors = new ArrayList<>();
		ToDoItem toDoItem = ToDoItemAdapter.toDoItem(toDoItemRequest);
		System.out.println(">>>>>>>>>>>>>>>>> 변경 됐따 <<<<<<<<<<<<<<<<<<<<<");
		try {
			toDoItem = toDoItemService.create(toDoItem);
		}catch(final Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
		}
		return ToDoItemAdapter.toDoItemResponse(toDoItem, errors);
	}

	@RequestMapping(value = "/remove", method = RequestMethod.POST)
	public @ResponseBody ToDoItemResponse remove(@RequestBody final ToDoItemRequest toDoItemRequest) {
		List<String> errors = new ArrayList<>();
		ToDoItem toDoItem = ToDoItemAdapter.toDoItem(toDoItemRequest);
		try {
			toDoItemService.get(toDoItem.getId());
			toDoItemService.removeTodoList(toDoItem);	
		}catch(final Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
		}
		return ToDoItemAdapter.toDoItemResponse(toDoItem, errors);
	}
	
	@RequestMapping(value = "clearAll", method = RequestMethod.POST)
	public List<String> clearAll(String myDate) {
		List<String> errors = new ArrayList<>();
		try {
			toDoItemService.clearAll(myDate);
		}catch(final Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
		}
		return errors;
	}
	
	//PUT이 아닌 POST의 경우 데이터가 있으면 update, 없으면 create 하게도 가능. 
	@RequestMapping(method = RequestMethod.PUT)
	public @ResponseBody ToDoItemResponse update(@RequestBody final ToDoItemRequest toDoItemRequest) {
		List<String> errors = new ArrayList<>();
		
		//todoitemadapter를 통해 todoitemrequest를 todoitem으로 변
		//update 시에는 id가 반드시 필요하므로 id를 제대로 adapt하는지 확인
		//ToDoItemAdapter도 수정
		ToDoItem toDoItem = ToDoItemAdapter.toDoItem(toDoItemRequest); 
		try {
			toDoItem = toDoItemService.update(toDoItem);
		} catch (final Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
		}
		return ToDoItemAdapter.toDoItemResponse(toDoItem, errors);
	}
	
	@RequestMapping(value="/toNextDay", method = RequestMethod.POST)
	public List<String> toNextDay(@RequestBody final List<ToDoItemRequest> toDoItemRequests){
		List<String> errors = new ArrayList<>();
		
		List<ToDoItem> toDoItems = new ArrayList<ToDoItem>();
		for(int i=0; i < toDoItemRequests.size(); i++) {
			toDoItems.add(i, ToDoItemAdapter.toDoItem(toDoItemRequests.get(i)));
		}
		try {
			toDoItemService.toNextDay(toDoItems);
		} catch (final Exception e) {
			errors.add(e.getMessage());
			e.printStackTrace();
		}
		
		return errors;
	}
}


////어떤 메서드를 사용하며 어떤 경로인지 설정.
////PathVariable의 경우 이름 지정 가능 
//@RequestMapping(method = RequestMethod.GET, value="/{id}")
////@ResponseBody는 HTTP Response Body 를 JSON 형태로 리턴 받을 것임을 명시 
//public @ResponseBody ToDoItemResponse get(@PathVariable(value="id") String id) {
//
//	List<String> errors = new ArrayList<>();
//	ToDoItem toDoItem = null;
//	try {
//		toDoItem = toDoItemService.get(id);
//	} catch (final Exception e) {
//		errors.add(e.getMessage());
//	}
//	
//	//todo/{id}로 리퀘스트 보내면 service로 가서 get(id)를 수행. builder()로 빌드하고 id, title, done에는 정해진 정보를 넣음.
//	//TodoItemAdapter에서 toDoItemResponse 형태로 바꿔주겠지. 
//	return ToDoItemAdapter.toDoItemResponse(toDoItem, errors);
//}
