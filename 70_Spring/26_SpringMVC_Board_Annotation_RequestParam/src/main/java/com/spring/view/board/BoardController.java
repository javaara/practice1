package com.spring.view.board;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.biz.board.BoardVO;
import com.spring.biz.board.impl.BoardDAO;

@Controller
public class BoardController {
	
	//리턴타입 ModelAndView -> String 변경해서 리턴타입 통일
	//데이터 저장타입 ModelAndView -> Model
	@RequestMapping("/getBoardList.do")
	public String getBoardList(
			@RequestParam(value="searchCondition", defaultValue="TITLE", required=false) String condition, 
			@RequestParam(value="searchKeyword", defaultValue="", required=false) String keyword, 			
			BoardDAO boardDAO, 
			Model model) {
		System.out.println(">>> 글 전체 목록 조회 처리-getBoardList()");
		
		List<BoardVO> boardList = boardDAO.getBoardList();
		
		model.addAttribute("boardList", boardList);
		
		return "getBoardList.jsp";
	}
	
	@RequestMapping("/getBoard.do")
	public String getBoard(BoardVO vo, BoardDAO boardDAO, Model model) {
		System.out.println(">>> 글 상세조회 처리-getBoard()");
		
		BoardVO board = boardDAO.getBoard(vo);
		//model.addAttribute(board); //속성명 부여x -> boardVO
		model.addAttribute("board", board); //데이터 저장(속성명 부여 - board)
	    
	    return "getBoard.jsp";
	}
	
//	@RequestMapping("/getBoardList.do")
//	public ModelAndView getBoardList(BoardDAO boardDAO, ModelAndView mav) {
//		System.out.println(">>> 글 전체 목록 조회 처리-getBoardList()");
//		
//		List<BoardVO> boardList = boardDAO.getBoardList();
//		
//		mav.addObject("boardList", boardList);
//		mav.setViewName("getBoardList.jsp");
//		
//		return mav;
//	}
//	
//	@RequestMapping("/getBoard.do")
//	public ModelAndView getBoard(BoardVO vo, BoardDAO boardDAO, ModelAndView mav) {
//		System.out.println(">>> 글 상세조회 처리-getBoard()");
//		
//		BoardVO board = boardDAO.getBoard(vo);
//		
//		mav.addObject("board", board); //데이터 저장
//	    mav.setViewName("getBoard.jsp");
//	    
//	    return mav;
//	}
	
	@RequestMapping("/insertBoard.do")
	public String insertBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 글 등록 처리 - insertBoard()");
		
		boardDAO.insertBoard(vo);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/updateBoard.do")
	public String updateBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 글 수정 처리-updateBoard()");
		
		boardDAO.updateBoard(vo);
		
		return "getBoardList.do";
	}
	
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo, BoardDAO boardDAO) {
		System.out.println(">>> 글 삭제 처리-deleteBoard()");
		
		boardDAO.deleteBoard(vo);
		
		return "getBoardList.do";
	}
	
}
