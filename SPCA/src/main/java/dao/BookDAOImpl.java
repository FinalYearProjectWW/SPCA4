package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Book;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BookDAOImpl extends CommonDAOImpl<Book, Integer> implements BookDAO{
	
	public BookDAOImpl() {
	    super(Book.class);
	}

	@Override
	public List<Book> searchByTitle(String title) {
		// TODO Auto-generated method stub
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
		query.setParameter("title", title);
		return query.getResultList();
	}
}