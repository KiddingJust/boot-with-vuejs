package org.kidding.response;

import java.util.List;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


//http response에 포함되어 브라우저로 전달 될 모델

@Getter @Setter
@RequiredArgsConstructor
public abstract class ApiResponse<T> {

	@NonNull private T data;		//어떤 모델이든 들어갈 수 있음
	private List<String> errors;	//각 리스폰스는 항상 errors 리스트를 가지므로 리스폰스마다 추가할 필요 없음. 

	
}
