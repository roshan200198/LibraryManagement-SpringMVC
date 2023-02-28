package com.app.Controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.app.DAO.BookDao;
import com.app.Pojo.LibraryBean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	BookDao bookdao;
	
	@Autowired
	LibraryBean obj;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@RequestMapping(path = "/user/issueMultipleBooks", method = RequestMethod.POST)
	public String issueBookFromDropdown( @RequestBody String requestBody,
									Model model, HttpSession session) throws Exception {
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		
//  	  int userId = bookdao.getUserIdByUserName(obj.getUserName());
//  	  int userId = (Integer) session.getAttribute("userId");
//  	  obj.setUserId(userId);
//  	  System.out.println("userId without parse"+ (obj.getUserId() - 2) + " "+ obj);
//  	  obj.setUserId(userId);
		
		try {
		      JsonNode jsonNode = objectMapper.readTree(requestBody);
		      String[] checkBooks = objectMapper.convertValue(jsonNode.get("checkBooks"), String[].class);
		      String[] checkCounts = objectMapper.convertValue(jsonNode.get("checkCounts"), String[].class);
		      System.out.println("in restController " + checkBooks);
		      System.out.println("in restController " + checkCounts);
		      
		      obj.setUserName((String) session.getAttribute("username"));
		      obj.setUserId((Integer) session.getAttribute("userId"));
		      
		      for(int i = 0; i < checkBooks.length; i++)  {
		    	  String book = checkBooks[i];
		    	  for(int j = i; j == i; j++) {
		    		  int count = Integer.parseInt(checkCounts[j]);
			    	  System.out.println("  "+book+" "+count);

// ======================= Logic for Issue Book =========================================================//
			    	  obj.setBookName(book);
			    	 if(count>0) {
			    		  if(bookdao.getBookNumbersByBookname(book) != 0) {
					  			if(bookdao.getBookNumbersByBookname(book) >= count) {
						  				
						  				if(bookdao.issueBookForUserDropdown(obj,count)) {
						  					model.addAttribute("bookname", obj.getBookName());
						  					model.addAttribute("status", "Isuue Data updated successfully!");
						  				} else {
						  					model.addAttribute("fail_status", "Error occurs while updating Issue Data!!");
						  				}
						  				
						  			} else {
						  				model.addAttribute("fail_status", "You can issue max " +"  "+ bookdao.getBookNumbers(book)+"  "+ " books of Id: " + obj.getBookId());
						  			}
						  		} else {
						  			model.addAttribute("fail_status", "Book is Not available!");
						  		}
			    	 } else {
			    		 model.addAttribute("fail_status", "insert");
			    	 }
			  		
			  	}
		    	  
	}
		      
		      // Handle the request here
		      // You can access the two JSON arrays using checkBooks and checkCounts
		    } catch (JsonProcessingException e) {
		      // Handle the exception here
		    }
		
		return "issueFormUser";
	}
		
		
	
}
