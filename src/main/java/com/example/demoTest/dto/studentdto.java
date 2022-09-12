package com.example.demoTest.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Service;

@Entity

@Table(name="test")
public class studentdto {
@Id
@Column(name="id")
	private int id;
@Column(name="name")
	private String name;
@Column(name="address")
	private String address;
@Column(name="NRC")
	private String NRC;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNRC() {
		return NRC;
	}
	public void setNRC(String nRC) {
		NRC = nRC;
	}
	@Override
	public String toString() {
		return "studentdto [id=" + id + ", name=" + name + ", address=" + address + ", NRC=" + NRC + "]";
	}
	
}
