package com.app.ExcelReport;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.app.DAO.BookDao;
import com.app.Pojo.LibraryBean;

@Component
public class ExcelReportViewForAvailableBooks extends AbstractXlsView{

	@Autowired
	BookDao bookdao;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			response.setHeader("Content-Disposition", "attachment; filename=\"ExcelAvailableBook.xls\"");
			
			List<LibraryBean> list = (List<LibraryBean>) model.get("excelAvailableBook");
			
			Sheet sheet = workbook.createSheet("Book Details");
			Row header = sheet.createRow(0);
			
			header.createCell(0).setCellValue("Book ID");
			header.createCell(1).setCellValue("Book Name");
			header.createCell(2).setCellValue("Book Status");
			header.createCell(3).setCellValue("No of Books");
			
			int row_count = 1;
			for (LibraryBean records : list) {
			System.out.println("records are: Book ID : \n" + records.getBookId() + "Book Name:"+ records.getBookName() + 
								"Book Status: " + records.getBookStatus() + "Book Numbers:"+ records.getBookNumbers());
			
				Row datarow = sheet.createRow(row_count++);
				datarow.createCell(0).setCellValue(records.getBookId());
				datarow.createCell(1).setCellValue(records.getBookName());
				datarow.createCell(2).setCellValue(records.getBookStatus());
				datarow.createCell(3).setCellValue(records.getBookNumbers());
			}
			
		}catch(Exception e) {
			System.out.println(e);
			e.printStackTrace();
			e.getMessage();
			
		}
		
	}

}
