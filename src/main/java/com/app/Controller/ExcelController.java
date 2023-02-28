package com.app.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;

import com.app.DAO.ImportExcelService;

//import com.app.CsvImport.XSSFWorkbook;



@Controller
public class ExcelController implements ServletContextAware{

	private ServletContext servletContext;
	
	@Autowired
	ImportExcelService importdao;

	
	@PostMapping("/admin/import_excel")
	public String import_excel(@RequestParam("file") MultipartFile file,HttpSession session, Model model) throws Exception {
		System.out.println(file.getSize());
		
		String path = session.getServletContext().getRealPath("/");
		String fileName = file.getOriginalFilename();
		String newpath = path+fileName;
		System.out.println(path +fileName);
		
//String path = servletContext.getRealPath("/WEB-INF/uploads/" + file.getOriginalFilename());
//
//		
//		System.out.println("path is--------.>: "+  path);
		String finalpath = newpath.replace("\\", "\\\\");
		System.out.println("again new path is: "+ finalpath);
		
        	try {
        		long start = System.currentTimeMillis();
        		
        	   // FileInputStream fileStream = new FileInputStream(new File(finalpath));
//        	    HSSFWorkbook workbook = new HSSFWorkbook(fileStream);
        	    Workbook workbook = WorkbookFactory.create(file.getInputStream());
        	    Sheet sheet = workbook.getSheetAt(0);
        	    
   String query1 = "update book set BookId=?, BookName=?, BookStatus = ?, BookNumbers = ? where BookId= ?;";
   String query2 = "insert into book (BookId, BookName, BookStatus, BookNumbers) values (?, ?, ?, ?);";
        	    
        	    // To ignore 1st row header, iterate row from i = 1;
        	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
        	        Row row = sheet.getRow(i);
        	        // Iterate over the cells in the row and process the data
        	        int userId = 0, bookNumber = 0;
        	        String bookId = "", bookName = "", bookStatus = "";
        	        
        	        for (int j = 0; j < row.getLastCellNum(); j++) {  
        	            Cell cell = row.getCell(j);
        	            System.out.println(cell + " ");
        	            
        	           if(j == 0) {
        	            	bookId =  cell.getStringCellValue();
        	            	System.out.println(bookId);
        	            } else if(j == 1) {
        	            	bookName = cell.getStringCellValue();
        	            	System.out.println(bookName);
        	            } else if(j == 2) {
        	            	bookStatus = cell.getStringCellValue();
        	            	System.out.println(bookStatus);
        	            }else if(j == 3) {
        	            	bookNumber = (int) cell.getNumericCellValue();
        	            	System.out.println(bookNumber);
        	            }
        	            
        	            // Process the cell data as needed
        	            //System.out.print(cell.getStringCellValue() + "\t");
        	        }
        	        if(importdao.bookIdExistInBooktable(bookId)) {
        	        	if(importdao.updateBook(query1, bookId,bookName, bookStatus, bookNumber)) {
        	        		System.out.println("update excel book done..!");
        	        	} else {
        	        		model.addAttribute("fail_status", "Update Excel book import fail..!");
        	        	}
        	        
        	        System.out.println();
        	    }
        	   
        	    long end = System.currentTimeMillis();
        	    System.out.printf("\n Import done in %d ms\n", (end - start));
        	    model.addAttribute("status","Excel file is saved in database..");
        	    
        	    }  

        	} catch (IOException e) {
        	    e.printStackTrace();
        	    model.addAttribute("fail_status","excel file isn't imported.. ");
        	    
        	}
        	
        	return "signinPage";
	
	}

	
	

	public String uploadExcelFIle(MultipartFile multipartFile)  {
		 
		try {
			byte[] bytes = multipartFile.getBytes();
			Path path = Paths.get(servletContext.getRealPath("/Resources/Excel") + multipartFile.getOriginalFilename());
			Files.write(path, bytes);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return multipartFile.getOriginalFilename();
		
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
		
	}
	
}
	

