package com.quiz.lesson06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.lesson06.bo.BookmarkBO;
import com.quiz.lesson06.domain.Bookmark;

@RequestMapping("/lesson06")
@Controller
public class Lesson06Controller {
	
	@Autowired
	private BookmarkBO bookmarkBO;
	
	// 즐겨찾기 추가하기 페이지 : http://localhost:8080/lesson06/add-bookmark-view
	@GetMapping("/add-bookmark-view")
	public String addBookmarkView() {
		return "lesson06/addBookmark";
	}
	
	// 즐겨찾기 추가하기 : Ajax 요청 -> ResponseBody로 return 해야함!!
	@ResponseBody
	@PostMapping("/add-bookmark")
	public Map<String, Object> addBookmark(
			@RequestParam("name") String name,
			@RequestParam("url") String url) {
		// insert db
		int rowCount = bookmarkBO.addBookmark(name, url);
		
		// 성공 JSON
		// {"code":200, "result":"성공"}
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("result", "성공");

		return result; // JSON String
	}
	
	// 즐겨찾기 목록 페이지
	@GetMapping("/bookmark-list-view")
	public String bookmarkListView(Model model) {
		// select로 bookmark 튜플들 가져오기
		List<Bookmark> bookmarkList = bookmarkBO.getBookmarkList();
		model.addAttribute("bookmarkList", bookmarkList);
		
		return "lesson06/bookmarkList";
	}
	
	// AJAX 요청 - url 중복확인
	@ResponseBody
	@GetMapping("/is-duplication-url")
	public Map<String, Object> isDuplicationUrl(
			@RequestParam("url") String url) {
		// DB에서 중복검사
		boolean isDuplication = bookmarkBO.isDupliationByUrl(url);
		
		// AJAX에 data 돌려주기
		// {"code":200, "is_duplication":true}
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("is_duplication", isDuplication);
		return result;
	}
	
	// AJAX 요청 - delete 북마크
	@ResponseBody
	@PostMapping("/delete-bookmark")
	public Map<String, Object> deleteBookmark(
			@RequestParam("id") int id) {
		// DB에서 id에 해당하는 행 삭제
		bookmarkBO.deleteBookmarkById(id);
		
		// AJAX에 return
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		return result; 
	}
}
