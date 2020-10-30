package com.example.Service;

import java.util.List;
import java.util.Optional;

import com.example.Model.Document;

public interface IDocumentService 
{
	void SaveDocument(Document doc);
	List<Object[]>getDocumentIdAndName();
	Optional<Document>GetOneDocument(Integer id);

}
