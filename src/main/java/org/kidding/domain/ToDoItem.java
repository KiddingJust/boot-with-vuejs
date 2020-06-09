package org.kidding.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "todoitem")
public class ToDoItem {
	
	@Id
	private String id;
	private String title;
	
	@CreatedDate
	private LocalDateTime todoDate;
	private boolean done;
}
