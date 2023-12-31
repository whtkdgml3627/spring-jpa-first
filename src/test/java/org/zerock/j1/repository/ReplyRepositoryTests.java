package org.zerock.j1.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.j1.domain.Board;
import org.zerock.j1.domain.Reply;
import org.zerock.j1.dto.ReplyPageRequestDTO;
import org.zerock.j1.service.ReplyService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class ReplyRepositoryTests {
  
  @Autowired
  private ReplyRepository replyRepository;

  @Autowired
  private ReplyService replyService;

  //댓글 1개 추가
  // @Test
  // public void insertOne(){
  //   Long bno = 100L;

  //   Board board = Board.builder().bno(bno).build();

  //   Reply reply = Reply.builder()
  //     .replyText("Reply....1")
  //     .replyer("replyer00")
  //     .board(board)
  //     .build();
    
  //   replyRepository.save(reply);
  // }

  //여러개 게시글에 댓글 5개씩 추가
  // @Test
  // public void testInsertDummies(){
  //   Long[] bnoArr = {99L, 96L, 92L, 84L, 81L};

  //   for (Long bno : bnoArr) {
      
  //     Board board = Board.builder().bno(bno).build();

  //     for (int i = 0; i < 50; i++) {
  //       Reply reply = Reply.builder()
  //         .replyText("Reply..." + bno + "--" + i)
  //         .replyer("Replyer" + i)
  //         .board(board)
  //         .build();

  //       replyRepository.save(reply);
  //     }//end for

  //   }//end for
  // }

  //댓글 조회
  //Board 테이블도 조회함
  //no Session 추가적인 DB연결이 필요한대 없어서 뜨는 에러
  //뜨면 @Transactional이 필요하다!!!!!!
  @Test
  public void testListBoard(){
    Long bno = 99L;

    Pageable pageable = PageRequest.of(0, 10, Sort.by("rno").ascending());

    Page<Reply> result = replyRepository.listBoard(bno, pageable);

    result.get().forEach(r -> log.info(r));

  }

  //댓글의 Count 조회
  @Test
  public void testCount(){
    Long bno = 99L;

    long count = replyRepository.getCountBoard(bno);

    log.info("count: " + count);
  }

  //댓글의 마지막페이지 조회
  @Test
  public void testListLast(){

    ReplyPageRequestDTO requestDTO = ReplyPageRequestDTO.builder()
      .bno(99L)
      .last(true)
      .build();

    log.info(replyService.list(requestDTO));

  }

}
