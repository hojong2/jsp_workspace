package com.academy.ormsqlmapapp;

import com.academy.ormsqlmapapp.model.board.BoardDAO;
import com.academy.ormsqlmapapp.model.board.HibernateBoardDAO;
import com.academy.ormsqlmapapp.model.domain.Board;

public class MainApp {
	public static void main(String[] args) {
		BoardDAO boardDAO= new HibernateBoardDAO();
		//boardDAO.selectAll();
		Board board= new Board();
		board.setBoard_id(5);
		//board.setTitle("하이버네이트 제목 수정 테스트");
		//board.setWriter("하이버네이트 작성자 수정 테스트");
		//board.setContent("하이버네이트 내용 수정 테스트");
		//boardDAO.insert(board);
		//boardDAO.update(board);
		boardDAO.delete(board);
	}
}
