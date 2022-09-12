package com.example.demoTest.controller;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demoTest.dto.studentdto;
import com.example.demoTest.model.student;
import com.example.demoTest.repository.StudentRepository;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Controller
@RequestMapping("/Home")
public class HomeController {
	@Autowired
	private StudentRepository repo;
	@GetMapping("/popup")
	public ModelAndView home () {
		return new ModelAndView("pop","student",new student());
	}
	@GetMapping("/link")
	public ModelAndView link() {
		ModelMap map=new ModelMap();
		//map.addAttribute("student", new student());
		
		return new ModelAndView("hello","student",new student());
	}
	@GetMapping("/search")
	public String search (@ModelAttribute("student")student student,ModelMap map,HttpSession session) {
		studentdto dto=new studentdto();
		
		System.out.println(student.getName());
		dto.setName(student.getName());
		dto.setAddress(student.getAddress());
		dto.setNRC(student.getNRC());
		dto.setId(student.getId());
		dto.setId(student.getId());
		List<studentdto> stulist=repo.findByName(null);
		System.out.println(student.getName());
		if(stulist.size()==0) {
			map.addAttribute("err","Data Not Found!!");
		}
		
			
			map.addAttribute("stulist", stulist);
		
		return "pop";
	}
	
	@GetMapping("/searchname")
	@ResponseBody
	public List<studentdto> searchname(@RequestParam(value="term",required = false,defaultValue = "")String term) {
	List<studentdto>searchstudent=	repo.findByName(term);
	List<studentdto> allList=new ArrayList();
	studentdto stu1=new studentdto();
	List<String> list=new ArrayList();
	for(studentdto student:searchstudent) {
		  allList.add(student);
	
	}
	return allList;
		
	
	}
	
	@GetMapping("/InvoiceReport")
	 public ResponseEntity<byte[]> studentReport() throws Exception, JRException {
	 	//List<InvoiceReportForm> list=new ArrayList<InvoiceReportForm>();
	 	//list=invoiceService.findall();
	 	//RoomDTO booking_room=list.get(0).getBooking().getBookingRoom();
	 	JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(repo.findAll(),false);
	 	//System.out.println(invoiceService.findall().size());
	 		JasperReport compileReport = JasperCompileManager
	 			.compileReport(new FileInputStream("src/main/resources/reports/Blank_A4.jrxml"));

	 	Map<String, Object> map = new HashMap<>();
		
		
	 	JasperPrint report = JasperFillManager.fillReport(compileReport, map, dataSource);
	 	byte[] data = JasperExportManager.exportReportToPdf(report);

	 	HttpHeaders headers = new HttpHeaders();
	 	headers.set(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=InvoiceReport.pdf");

	 	return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(data);
	 }
//	@GetMapping("/popup")
//	public ModelAndView popup(ModelAndView map) {
//		return new ModelAndView("popup","student",new student());
//	}
}
