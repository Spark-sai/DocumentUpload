package com.example.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "doc_tab")
public class Document 
{
	
	@Id
	@GeneratedValue
	@Column(name = "doc_Id_Col")
	private Integer docId;
	@Column(name = "doc_Name_Col")
	private String docName;
	@Column(name = "doc_Data_Col")
	@Lob
	private byte[] docData;

}
