package dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import SPCA.SPCA.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerDAOImpl implements CustomerDAO{
	private EntityManager em;
	@Override
	public List<Customer> findByUsername(String username) {
		// TODO Auto-generated method stub
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username", Customer.class);
		return query.getResultList();
	}
}
