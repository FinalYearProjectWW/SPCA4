package dao;

import org.springframework.stereotype.Repository;
import entities.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class OrderDAOImpl extends CommonDAOImpl<Order, Integer> implements OrderDAO{
	
	@PersistenceContext
	private EntityManager em;

	public OrderDAOImpl() {
		super(Order.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Order findOrderByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("SELECT o FROM Order o WHERE o.customerId = :customerId", Order.class)
					.setParameter("customerId", customerId)
					.getSingleResult();
		}catch(jakarta.persistence.NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveOrUpdate(Order order) {
		// TODO Auto-generated method stub
		if(order.getId() == 0) {
			em.persist(order);
		}else {
			em.merge(order);
		}
	}

	@Override
	public Order findCartByCustomerId(int customerId) {
		// TODO Auto-generated method stub
		try {
			return em.createQuery("SELECT o FROM Order o WHERE o.customer.id = :customerId AND o.status = 'IN_CART'", Order.class)
					.setParameter("customerId", customerId)
					.getSingleResult();		
		}catch (jakarta.persistence.NoResultException e) {
			return null;
		}
	}
}