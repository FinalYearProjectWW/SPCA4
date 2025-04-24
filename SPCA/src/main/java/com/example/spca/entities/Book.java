package com.example.spca.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;

@Entity
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String title;
	private String author;
	private String publisher;
	private Double price;
	private String category;
	private String isbn;
	
	@Transient
	private Double averageRating;

	@Lob
    @Column(name = "image", columnDefinition = "MEDIUMBLOB")
	private byte[] image;
	
	@Column(name = "stock_level")
	private Integer stockLevel;
	
	public Book() {}
	
	  public Book(String t, String a, String pub, Double price, String c, String isbn, byte[] i, Integer sl) {
	        this.title = t;
	        this.author = a;
	        this.publisher = pub;
	        this.price = price;
	        this.category = c;
	        this.isbn = isbn;
	        this.image = i;
	        this.stockLevel = sl;
	    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] i) {
		this.image = i;
	}

	public Integer getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(Integer stockLevel) {
		this.stockLevel = stockLevel;
	}
	
	public Double getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(Double averageRating) {
		this.averageRating = averageRating;
	}
}