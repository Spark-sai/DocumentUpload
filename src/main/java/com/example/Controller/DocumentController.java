package com.example.Controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Model.Document;
import com.example.Service.IDocumentService;

@Controller
@RequestMapping("/Document")
public class DocumentController {
	@Autowired
	private IDocumentService service;

	@GetMapping("/reg")
	public String SaveDou(Model model) 
	{

	List<Object[]> list=service.getDocumentIdAndName();
	model.addAttribute("list", list);
		return "Document";
	}
	
	@PostMapping("/save")
	public String SaveDoc(
			@RequestParam Integer docId,
			@RequestParam MultipartFile docName,Model model)
	{
		if (docName!=null)
		{
			//Create Document class Object
			try {
			Document doc= new Document();
			doc.setDocId(docId);
			doc.setDocName(docName.getOriginalFilename());
			doc.setDocData(docName.getBytes());
			
			service.SaveDocument(doc);
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		
		return "redirect:reg";
		
	}
	@GetMapping("/download")
	public void DownloadDocument(@RequestParam Integer id,
			HttpServletResponse resp)
	{
		Optional<Document> doc=service.GetOneDocument(id);
		
		if (doc.isPresent()) 
		{
			Document d=doc.get();
			
			resp.addHeader("content-Disposition", "attachment;filename="+d.getDocName());
			
			//copy data 
			try {
				FileCopyUtils.copy(d.getDocData(), resp.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}

	
