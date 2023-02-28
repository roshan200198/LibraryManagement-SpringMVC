package com.app.Controller;

import com.app.DAO.BookDao;
import com.app.ExcelReport.ExcelReportView;
import com.app.ExcelReport.ExcelReportViewForAvailableBooks;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
//import com.app.ExcelReport.ExcelReport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.app.DAO.BookDao;
//import com.app.ExcelReport.ExcelReport;
import com.app.Pojo.Admin;
import com.app.Pojo.LibraryBean;
import com.app.Pojo.UserSignup;
import com.app.Pojo.UserTable;
import com.app.Pojo.Users;

@Controller
public class HomeController {

	// private static final Object LibraryBean = null;
	@Autowired
	BookDao bookdao;
	@Autowired
	LibraryBean obj;
	@Autowired
	Admin admin;
	@Autowired
	UserSignup usersignup;
	@Autowired
	UserTable userTable;
	/*
	 * @Autowired ExcelReport excelreport;
	 */

	String mapping;

	@RequestMapping("/")
	public String home() {
		System.out.println(("In addForm.."));

		return "index";
	}
	
	@RequestMapping("/login_success")
	public String login_success() {
		System.out.println(("login_success"));

		return "login_success";
	}
	
	/*
	 * @RequestMapping("/login_process") public String
	 * login_process(@RequestParam("username") String username) { String view;
	 * String admin ="ADMIN"; String user = "USER"; Users users =
	 * bookdao.login_process(username);
	 * 
	 * if(users.getRole().equals(admin)) { view = "adminIndex"; } else { view =
	 * "userSigninPage"; }
	 * 
	 * System.out.println(("login_success"));
	 * 
	 * return "view"; }
	 */
	
	 @RequestMapping("/login_custom") 
	  public String login_custom() {
	  
	  return "login"; 
	  }
	 
	 @RequestMapping("/Access_Denied") 
	  public String Access_Denied() {
	  
	  return "Access_Denied"; 
	  }
	 


	@RequestMapping("/admin/addForm")
	public String addBook() {
	
		return "form";
	}

	@RequestMapping(value ="/admin/addBook", method = RequestMethod.POST)
	public String addBook(@ModelAttribute LibraryBean obj, Model model) throws Exception {
		System.out.println("In addBook controller..");

		System.out.println("BookId is:: " + obj.getBookId());
		System.out.println("Book Name is:: " + obj.getBookName());

		if(obj.getBookNumbers() > 0) {
			if (bookdao.addBook(obj)) {
				model.addAttribute("id", obj.getBookId());
				model.addAttribute("name", obj.getBookName());
				model.addAttribute("status", "Book added Successfully!");
			} else
				model.addAttribute("fail_status", "Book not added!");
			System.out.println("In addBook last");
		} else {
			model.addAttribute("status", "Book added Successfully!");
		}
		

		return "form";
	}

	@RequestMapping("/admin/issueForm")
	public String issueBook1() {

		return "issueForm";
	}
	
	@RequestMapping("/user/issueForm")
	public String issueBook2() {

		return "issueFormUser";
	}

	@RequestMapping(path = "/admin/issueBook", method = RequestMethod.POST)
	public String issueBookAdmin(@ModelAttribute LibraryBean obj, Model model, @RequestParam("bookCount") int count) throws Exception {
		System.out.println("objjjj--" + obj.getBookId());
		String bookId = obj.getBookId();
		String bookName = obj.getBookName();
		int userId = obj.getUserId();
		int bookNumbers = obj.getBookNumbers();
		System.out.println("bookId......." + bookId);
		System.out.println("Book Name....." + bookName);
		
		if(bookdao.getBookNumbers(bookId) != 0) {
			if(bookdao.getBookNumbers(bookId) >= obj.getBookNumbers()) {
				
				if(bookdao.issueBook(obj,count)) {
					model.addAttribute("userid", obj.getUserId());
					model.addAttribute("bookid", obj.getBookId());
					model.addAttribute("bookname", obj.getBookName());
					model.addAttribute("status", "Isuue Data updated successfully!");
				} else {
					model.addAttribute("fail_status", "Error occurs while updating Issue Data!!");
				}
				
			} else {
				model.addAttribute("status", "You can issue max" + bookdao.getBookNumbers(bookId)+ "books of Id: " + obj.getBookId());
			}
		} else {
			model.addAttribute("status", "Book is Not available!");
		}
		
		return "issueForm";
	}
	
	@RequestMapping(path = "/user/issueBook", method = RequestMethod.POST)
	public String issueBookUser(@ModelAttribute LibraryBean obj,@RequestParam("bookCount") int count,HttpSession session, Model model) throws Exception {
		
		obj.setUserName((String) session.getAttribute("username"));
		obj.setUserId((Integer) session.getAttribute("userId"));
		System.out.println("objjjj--" + obj.getBookId());
		String bookId = obj.getBookId();
		String bookName = obj.getBookName();
		obj.setBookNumbers(bookdao.getBookNumbers(bookId));
		int bookNumbers = obj.getBookNumbers();
		System.out.println("bookId......." + bookId);
		System.out.println("Book Name....." + bookName);
		
		if(obj.getBookNumbers() != 0) {
			if(obj.getBookNumbers() >= count) {
				
				if(bookdao.issueBook(obj,count)) {
					model.addAttribute("userid", obj.getUserId());
					model.addAttribute("bookid", obj.getBookId());
					model.addAttribute("bookname", obj.getBookName());
					model.addAttribute("status", "Isuue Data updated successfully!");
				} else {
					model.addAttribute("fail_status", "Error occurs while updating Issue Data!!");
				}
				
			} else {
				model.addAttribute("fail_status", "You can issue max " +"  "+ bookdao.getBookNumbers(bookId)+"  "+ " books of Id: " + obj.getBookId());
			}
		} else {
			model.addAttribute("status", "Book is Not available!");
		}
		
		return "issueFormUser";
	}
	
	
	@RequestMapping("/admin/returnForm")
	public String returnForm() {

		return "returnForm";
	}
	
	@RequestMapping("/user/returnFormForUser")
	public String returnFormForUser() {

		return "returnFormForUser";
	}
	
	@RequestMapping("/user/returnForm{book}{bookId}{count}")
	public String returnFormForUser(@RequestParam("book") String book, @RequestParam("bookId") String bookId,
			@RequestParam("count") int count, Model model) {
		
		model.addAttribute("bookNumbers", count);
		model.addAttribute("book", book);
		model.addAttribute("bookId", bookId);
		return "returnFormForUser";
	}

	
	@RequestMapping("/admin/returnBook")
	public String returnBook(@ModelAttribute LibraryBean obj,@RequestParam("bookCount") int count, Model model) throws Exception {

		if(count >0) {
			if(bookdao.userExistInUsertable(obj.getUserName(), obj.getBookName())) {
				if(count <= bookdao.getIssueBookNumbers(obj.getUserName(), obj.getBookId())) {
					if(bookdao.returnBookForUser(obj, count)) {
						model.addAttribute("status", "You have return"+count+" " +obj.getBookName()+" books");
					}else {
						model.addAttribute("fail_status", "Return book not updated!");
					}
				}else {
					model.addAttribute("fail_status", "You can return max "+bookdao.getIssueBookNumbers(obj.getUserName(), obj.getBookId())+" book!");
				}
				
			} else {
				model.addAttribute("fail_status", "You haven't issue thise book!");
			}
			
		} else {
			model.addAttribute("fail_status", "Return Book number should be greater than 0");
		}
		System.out.println(obj);

		return "returnForm";
	}
	
	@RequestMapping("/user/returnBook")
	public String returnBookForUser(@ModelAttribute LibraryBean obj,@RequestParam("bookCount") int count, Model model, HttpSession session) throws Exception {

//		String username = (String) session.getAttribute("username");
		obj.setUserName((String) session.getAttribute("username"));
		if(count >0) {
			if(bookdao.userExistInUsertable(obj.getUserName(), obj.getBookName())) {
				if(count <= bookdao.getIssueBookNumbers(obj.getUserName(), obj.getBookId())) {
					if(bookdao.returnBookForUser(obj, count)) {
						model.addAttribute("status", "You have return"+count+" " +obj.getBookName()+" books");
					}else {
						model.addAttribute("fail_status", "Return book not updated!");
					}
				}else {
					model.addAttribute("fail_status", "You can return max "+bookdao.getIssueBookNumbers(obj.getUserName(), obj.getBookId())+" book!");
				}
				
			} else {
				model.addAttribute("fail_status", "You haven't issue thise book!");
			}
			
		} else {
			model.addAttribute("fail_status", "Return Book number should be greater than 0");
		}
		System.out.println(obj);
		
		return "returnFormForUser";
	}
	
	
	@RequestMapping("/user/returnBookEdit")
	public String returnBookForUserEdit(@ModelAttribute LibraryBean obj, Model model, HttpSession session) throws Exception {

//		String username = (String) session.getAttribute("username");
		obj.setUserName((String) session.getAttribute("username"));
		
		if (bookdao.returnBook(obj)) {
			model.addAttribute("status", "Return Book updated Successfully!!!!");
		} else {
			model.addAttribute("fail_status", "Return Book not updated!!");
		}

		return "returnFormForUser";
	}

	@RequestMapping("/signupForm")
	public String signupForm() {

		return "signup_css";
	}
	
	
	  @RequestMapping("/signup") 
	  public String signup(@ModelAttribute Users users, @RequestParam("cpassword") String cpswd, 
			  				@RequestParam("photo") MultipartFile photo, Model model) throws Exception {
	  
		  byte[] imageBytes = photo.getBytes();
		  System.out.println(photo.getOriginalFilename());
		  
		  
			if (users.getPassword().equals(cpswd)) {
				if (!bookdao.mailExist(users.getUsername())) {
					if (bookdao.signup(users, imageBytes)) {
						model.addAttribute("status", "Signup data is save Successfully!");
					} else {
						model.addAttribute("status", "Signup data is not save Successfully!");
					}
				} else
					model.addAttribute("status", "Admin exist. Please signin..");

			} else
				model.addAttribute("status", "Password doesn't match!!");

			return "userSignupView";
	  
	  }
	 
	
	@RequestMapping("/admin/signin")
	public String signinForm() {
		//session.setAttribute("username", session.getAttribute("username"));
		return "signinPage";
	}

	/*
	 * @RequestMapping("/adminSignin") public String
	 * adminSignin(@RequestParam("mailId") String mailId, @RequestParam("password")
	 * String password, Model model) throws Exception {
	 * 
	 * String view = "adminSigninView";
	 * System.out.println("admin signin data test::  " + mailId + "..." + password);
	 * 
	 * if (bookdao.checkSigninMail(mailId)) { if (bookdao.signin(mailId, password))
	 * { model.addAttribute("status", "signin successfull..");
	 * model.addAttribute("mailId", mailId);
	 * 
	 * view = "signinPage"; System.out.println("We are in::  " + view); return
	 * "signinPage";
	 * 
	 * } else model.addAttribute("status", "Incorrect password!!");
	 * System.out.println("We are in::  " + view);
	 * 
	 * return "adminSigninView"; } else { model.addAttribute("status",
	 * "User not found.. please Sign up"); view = "adminSigninView";
	 * System.out.println("We are in::  " + view); return "adminSigninView"; }
	 * 
	 * }
	 */

	@RequestMapping("/viewIssueUser")
	public String viewIssueUser(Model model) {
		// List<UserTable> obj = bookdao.viewIssueUser();
		// System.out.println(obj);

		model.addAttribute("obj", obj);

		return "viewUser";
	}

	@RequestMapping("/admin/viewBookTableForAdmin")
	public String viewBookTable(Model model) {
		System.out.println("We are in viewBookTable");
		// List obj = ArrayList<LibraryBean>();;

		List<LibraryBean> obj = bookdao.viewBookTable();
		for (LibraryBean record : obj) {
			System.out.print("ID : " + record.getBookId());
			System.out.print("user Name : " + record.getUserName());
			System.out.print(", Name : " + record.getBookName());
			System.out.println(", User ID : " + record.getUserId());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj);
		model.addAttribute("obj", obj);
		System.out.println("viewBookTable:: " + obj);

		return "viewBookTableForAdmin";
	}
	
	
	@RequestMapping("/user/viewBookTableForUser")
	public String viewBookTableForUser(Model model) {
		System.out.println("We are in viewBookTable");
		// List obj = ArrayList<LibraryBean>();;

		List<LibraryBean> obj = bookdao.viewBookTable();
		for (LibraryBean record : obj) {
			System.out.print("ID : " + record.getBookId());
			System.out.print(", Name : " + record.getBookName());
			System.out.println(", User ID : " + record.getUserId());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj);
		model.addAttribute("obj", obj);
		System.out.println("viewBookTable:: " + obj);

		return "viewBookTableForUser";
	}
	
	
	@RequestMapping("/user/userSigninPage")
	public String userSigninPage(Model model, HttpSession session) {
		// List<UserTable> obj = bookdao.viewIssueUser();
		// System.out.println(obj);
		String username = (String) session.getAttribute("username");
		System.out.println("session username is: ---->"+ username);
		
		
		model.addAttribute("obj", obj);
		

		return "userSigninPage";
	}
	
	@RequestMapping("/user/viewAvailableBook")
	public String viewAvailableBook(Model model) {
		System.out.println("We are in viewBookTable");
		// List obj = ArrayList<LibraryBean>();;

		List<LibraryBean> obj = bookdao.viewAvailableBook();
		for (LibraryBean record : obj) {
			System.out.print("ID : " + record.getBookId());
			System.out.print(", Name : " + record.getBookName());
//			System.out.println(", User ID : " + record.getUserId());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj);
		model.addAttribute("obj", obj);
		System.out.println("viewBookTable:: " + obj);

		return "viewAvailableBook";
	}
	
	
	
	@RequestMapping("/admin/searchForAdmin")
	public String searchBookForAdmin(@RequestParam("txtbookname") String bookname, Model model) {

		/*
		 * List<LibraryBean> obj = bookdao.searchBook(bookname);
		 * model.addAttribute("obj", obj);
		 */

		System.out.println("We are in viewBookTable");
		// List obj = ArrayList<LibraryBean>();;

		List<LibraryBean> obj = bookdao.searchBook(bookname);
		for (LibraryBean record : obj) {
			System.out.print("ID : " + record.getBookId());
			System.out.print(", Name : " + record.getBookName());
			System.out.println(", User ID : " + record.getUserId());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj);
		model.addAttribute("obj", obj);
		System.out.println("viewBookTable:: " + obj);

		return "viewBookTableForAdmin";
	}
	
	
	@RequestMapping("/user/searchForUser")
	public String searchBookForUser(@RequestParam("txtbookname") String bookname, Model model) {

		/*
		 * List<LibraryBean> obj = bookdao.searchBook(bookname);
		 * model.addAttribute("obj", obj);
		 */

		System.out.println("We are in viewBookTable");
		// List obj = ArrayList<LibraryBean>();;

		List<LibraryBean> obj = bookdao.searchBook(bookname);
		for (LibraryBean record : obj) {
			System.out.print("ID : " + record.getBookId());
			System.out.print(", Name : " + record.getBookName());
			System.out.println(", User ID : " + record.getUserId());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj);
		model.addAttribute("obj", obj);
		System.out.println("viewBookTable:: " + obj);

		return "viewBookTableForUser";
	}
	
	
	@RequestMapping("/user/searchAvailableBook")
	public String searchAvailableBook(@RequestParam("txtbookname") String bookname, Model model) {

		/*
		 * List<LibraryBean> obj = bookdao.searchBook(bookname);
		 * model.addAttribute("obj", obj);
		 */

		System.out.println("We are in searchAvailableBook");
		// List obj = ArrayList<LibraryBean>();;

		List<LibraryBean> obj = bookdao.searchAvailableBook(bookname);
		for (LibraryBean record : obj) {
			System.out.print("ID : " + record.getBookId());
			System.out.print(", Name : " + record.getBookName());
//			System.out.println(", User ID : " + record.getUserId());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj);
		model.addAttribute("obj", obj);
		System.out.println("searchAvailableBook:: " + obj);

		return "viewAvailableBook";
	}

	// ===================================================================================================================

	@RequestMapping("/userSignupForm")
	public String userSignup() {

		return "userSignupForm";
	}

	@RequestMapping("/userSignup")
	public String userSignup(@ModelAttribute UserSignup usersignup, @RequestParam("cpassword") String cpswd,
			Model model) {

		model.addAttribute("mailId", usersignup.getMailId());

		if (usersignup.getPassword().equals(cpswd)) {
			if (bookdao.userSignup(usersignup)) {
				model.addAttribute("status", "Signup data is save Successfully!");
			} else {
				model.addAttribute("status", "Signup data is not save Successfully!");
			}
		} else
			model.addAttribute("status", "Password doesn't match!!");

		return "userSignupView";
	}

	@RequestMapping("/userSigninForm")
	public String userSignin() {

		return "userSigninForm";
	}
	
	
	 
	  
	  
		/*
		 * @RequestMapping("/logout") public String logout() {
		 * 
		 * return "logout"; }
		 */
	  
	  
	  @RequestMapping("/afterlogout") 
	  public String afterlogout() {
	  
	  return "afterlogout"; 
	  }
	  
	  @RequestMapping("/homepage") 
	  public String homepage() {
	  
	  return "homepage"; 
	  }
	 

	/*
	 * @RequestMapping("/user/signin") public String userSigninPage() {
	 * 
	 * return "userSigninPage"; }
	 */

	/*
	 * @RequestMapping("/userSignin") public String
	 * userSignin(@RequestParam("mailId") String mailId, @RequestParam("paswword")
	 * String password, Model model) {
	 * 
	 * String view = "userSigninView";
	 * System.out.println("user signin data test::  " + mailId + "..." + password);
	 * 
	 * if (bookdao.checkUserSigninMail(mailId)) { if (bookdao.usersignin(mailId,
	 * password)) { model.addAttribute("status", "User signin successfull..");
	 * model.addAttribute("mailId", mailId);
	 * 
	 * view = "userSigninPage"; System.out.println("We are in::  " + view); return
	 * "userSigninPage";
	 * 
	 * } else model.addAttribute("status", "Incorrect password!!");
	 * System.out.println("We are in::  " + view);
	 * 
	 * return "userSigninView"; } else { model.addAttribute("status",
	 * "User not found.. please Sign up"); view = "adminSigninView";
	 * System.out.println("We are in::  " + view); return "userSigninView"; } }
	 */

	
	  @RequestMapping("/signin{username}")
		public String signinByRole(@RequestParam("username") String username, RedirectAttributes redirectAttr) {

			String view = "adminSigninView";
			System.out.println("rolebased signin data test::  " + username + "..." );

			if (bookdao.signinByRole(username).equals("ADMIN")) {
				
//					redirectAttr.addFlashAttribute("status", "signin successfull..");
					redirectAttr.addFlashAttribute("username", username);

					System.out.println("We are in::  " + view);
					mapping = username + "_" + "admin";
					return "redirect:/SpringMvc3/admin/signin";

				}else 
					if(bookdao.signinByRole(username).equals("USER")) {

						redirectAttr.addFlashAttribute("username", username);

						System.out.println("We are in::  " + view);
						mapping = username + "_" + "user";
						return "redirect:userSigninPage";
				}else {
					return "redirect:login"; 
				}
			
				
		}
	  
	  
		/*
		 * @RequestMapping("/signin{username}") public String
		 * signin(@RequestParam("username") String mailId, @RequestParam("password")
		 * String password, RedirectAttributes redirectAttr) {
		 * 
		 * String view = "adminSigninView";
		 * System.out.println("admin signin data test::  " + mailId + "..." + password);
		 * 
		 * if (bookdao.signinByRole(mailId)== "ADMIN") { if (bookdao.signin(mailId,
		 * password)) { redirectAttr.addFlashAttribute("status",
		 * "signin successfull.."); redirectAttr.addFlashAttribute("mailId", mailId);
		 * 
		 * System.out.println("We are in::  " + view); mapping = mailId + "_" + "admin";
		 * return "redirect:adminSigninPage";
		 * 
		 * } else redirectAttr.addFlashAttribute("status",
		 * "Admin entered wrong password!"); return "adminSigninView"; } else { if
		 * (bookdao.checkUserSigninMail(mailId)) { if (bookdao.usersignin(mailId,
		 * password)) { redirectAttr.addFlashAttribute("status",
		 * "signin successfull.."); redirectAttr.addFlashAttribute("mailId", mailId);
		 * 
		 * view = "userSigninPage"; System.out.println("We are in::  " + view); return
		 * "redirect:userSigninPage"; } else redirectAttr.addFlashAttribute("status",
		 * "User entered wrong password!"); return "userSigninView"; } else
		 * redirectAttr.addFlashAttribute("status", "Not registered, Please signup..");
		 * return "commonSigninView"; } }
		 */
	 

	/*
	 * @RequestMapping("/adminSigninPage") public String signinPage() {
	 * 
	 * return "signinPage"; }
	 */

	@RequestMapping("/userSigninPage")
	public String signinPage2() {

		return "userSigninPage";
	}

	// ==========================
//				model.addAttribute("status", "Incorrect password!!");
//			System.out.println("We are in::  "+ view);
//			    
//			    return "adminSigninView";
//		} else {
//			model.addAttribute("status", "User not found.. please Sign up");
//			view = "adminSigninView";
//			System.out.println("We are in::  "+ view);
//			return "adminSigninView";
//		}
//		
//	}

	@RequestMapping("/admin/viewUser{page}")
	public String viewPagination(@RequestParam("page") String page, Model model) {
		int pageid;
		int start;
		int rowPerPage = 2;
		if (page == null) {
			pageid = Integer.parseInt(page);
			
			pageid = 1;
		} else {
			pageid = Integer.parseInt(page);
		}
		double totalRow = bookdao.viewUserPaginationForAdminTotalRow();
		System.out.println("totalRow is :: "+ totalRow);
		int noOfBtn = (int) Math.ceil(totalRow/rowPerPage);
		System.out.println("noofBtn is :: "+ noOfBtn);
		model.addAttribute("noOfBtn", noOfBtn);
		
		start = (pageid * rowPerPage) - rowPerPage;
		List<UserTable> list = bookdao.viewUserPaginationForAdmin(start, rowPerPage);
		model.addAttribute("list", list);
		System.out.println("Pagination final list:: " + list);
		return "viewUserForAdmin";
	}
	
	
	@RequestMapping("/user/viewUser{page}")
	public String viewUserPagination(@RequestParam("page") String page, Model model, HttpSession session) {
		int pageid;
		int start;
		int rowPerPage = 10;
		if (page == null) {
			pageid = Integer.parseInt(page);
			pageid = 1;
		} else {
			pageid = Integer.parseInt(page);
		}
		String username = (String) session.getAttribute("username");
		start = (pageid * rowPerPage) - rowPerPage;
		List<UserTable> list = bookdao.viewUserPagination(username,start, rowPerPage);
		model.addAttribute("list", list);
		System.out.println("Pagination final list:: " + list);
		return "viewUserForUser";
	}

	

//  ======================== export data to Excel Sheet ==================================================

	@RequestMapping(value = "/admin/downloadExcelSheet", method = RequestMethod.GET)
	public ModelAndView getReportForAdmin(HttpServletRequest request, HttpServletResponse response) {
		String reportType = request.getParameter("type");
//	  if(reportType != null && reportType.equals("excel")) { 
		List<LibraryBean> obj = bookdao.getExcel();

		for (LibraryBean record : obj) {

			System.out.println(", User ID : " + record.getUserId());
			System.out.print("Book ID : " + record.getBookId());
			System.out.print("Book Name : " + record.getBookName());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj); // model.addAttribute("obj", obj);
		System.out.println("viewBookTable excel:: " + obj);

		return new ModelAndView(new ExcelReportView(), "excelReport", obj);

//	  }

	}
	
	
	@RequestMapping(value = "/user/downloadExcelSheet", method = RequestMethod.GET)
	public ModelAndView getReportForUser(HttpServletRequest request, HttpServletResponse response) {
		String reportType = request.getParameter("type");
//	  if(reportType != null && reportType.equals("excel")) { 
		List<LibraryBean> obj = bookdao.getExcelOfAvailableBooks();

		for (LibraryBean record : obj) {

			System.out.print("Book ID : " + record.getBookId());
			System.out.print("Book Name : " + record.getBookName());
			System.out.println(", Book Status : " + record.getBookStatus());
		}
		System.out.println("Obj is:: " + obj); // model.addAttribute("obj", obj);
		System.out.println("viewBookTable excel:: " + obj);

		return new ModelAndView(new ExcelReportViewForAvailableBooks(), "excelAvailableBook", obj);

//	  }

	}

}
