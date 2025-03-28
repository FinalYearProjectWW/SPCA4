package SPCA.SPCA;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "stock_level")
	private Integer stockLevel;
	
	public Book() {}
	
	  public Book(String t, String a, String pub, Double price, String c, String isbn, String i, Integer sl) {
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

	public String getImage() {
		return image;
	}

	public void setImage(String i) {
		this.image = i;
	}

	public Integer getStockLevel() {
		return stockLevel;
	}

	public void setStockLevel(Integer stockLevel) {
		this.stockLevel = stockLevel;
	}
}