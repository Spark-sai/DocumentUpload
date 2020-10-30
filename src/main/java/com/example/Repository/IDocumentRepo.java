package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.Model.Document;

public interface IDocumentRepo extends JpaRepository<Document, Integer>
{
		@Query("SELECT docId,docName FROM Document")
		List<Object[]>getDocumentIdAndName();
	

}
