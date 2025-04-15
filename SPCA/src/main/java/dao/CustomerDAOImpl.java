package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import entities.Customer;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerDAOImpl extends CommonDAOImpl<Customer, Integer> implements CustomerDAO{
	
	public CustomerDAOImpl() {
	    super(Customer.class);
	}

	@Override
	public List<Customer> findByUsername(String username) {
		// TODO Auto-generated method stub
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username", Customer.class);
		query.setParameter("username", username);
		return query.getResultList();
	}

	@Override
	public Customer findByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password", Customer.class);
		query.setParameter("email", email);
		query.setParameter("password", password);
		return query.getSingleResult();
	}
}
