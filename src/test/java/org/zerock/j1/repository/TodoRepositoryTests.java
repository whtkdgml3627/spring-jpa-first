package org.zerock.j1.repository;

import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Todo;
import org.zerock.j1.dto.TodoDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private TodoRepository todoRepository;

  @Test
  public void testInsert(){

    IntStream.rangeClosed(1, 100).forEach(i -> {
      Todo todo = Todo.builder().title("Title" + i).build();

      //save의 return타입은 Todo
      Todo result = todoRepository.save(todo);
      log.info(result);
    });

  }

  @Test
  public void testRead(){

    Long tno = 100L;

    Optional<Todo> result = todoRepository.findById(tno);
    
    Todo entity = result.orElseThrow();

    log.info("ENTITY-----------------------------");
    log.info(entity);

    //map(소스, 타입)
    //modelMapper 라이브러리로 Entity -> DTO로 변환
    TodoDTO dto = modelMapper.map(entity, TodoDTO.class);
    log.info(dto);

  }

  @Test
  public void testPaging(){

    Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending());

    Page<Todo> result = todoRepository.findAll(pageable);

    log.info(result);
  }

  
}
