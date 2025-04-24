package com.example.spca.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.spca.entities.Customer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class CustomerDAOImpl extends CommonDAOImpl<Customer, Integer> implements CustomerDAO{
	
	@PersistenceContext
	private EntityManager em;
	
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
	public Customer findByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c WHERE c.username = :username AND c.password = :password", Customer.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getSingleResult();
	}
}
