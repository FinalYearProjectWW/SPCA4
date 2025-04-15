package entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	
	private int rating;
	@Column(length = 2000)
	private String comment;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date reviewDate;
	
	public Review() {}
	
	public Review(Customer c, Book b, int r, String com, Date rd) {
		this.customer = c;
		this.book = b;
		this.rating = r;
		this.comment = com;
		this.reviewDate = rd;
	}
}
