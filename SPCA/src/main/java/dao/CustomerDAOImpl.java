package dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import SPCA.SPCA.Customer;
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
}
