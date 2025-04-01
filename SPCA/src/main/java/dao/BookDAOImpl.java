package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import SPCA.SPCA.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class BookDAOImpl implements BookDAO{
	
	private EntityManager em;

	@Override
	public Book findByID(int id) {
		// TODO Auto-generated method stub
		return em.find(Book.class, id);
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b", Book.class);
		return query.getResultList();
	}

	@Override
	public void save(Book book) {
		// TODO Auto-generated method stub
		em.persist(book);
	}

	@Override
	public void update(Book book) {
		// TODO Auto-generated method stub
		em.merge(book);
	}

	@Override
	public void delete(Book book) {
		// TODO Auto-generated method stub
        em.remove(em.contains(book) ? book : em.merge(book));
	}

	@Override
	public List<Book> searchByTitle(String title) {
		// TODO Auto-generated method stub
		TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
		return query.getResultList();
	}
}