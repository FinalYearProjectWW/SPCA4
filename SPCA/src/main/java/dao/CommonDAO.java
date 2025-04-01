package dao;

import java.io.Serializable;
import java.util.List;

import SPCA.SPCA.Book;
import SPCA.SPCA.Customer;

public interface CommonDAO<T, ID extends Serializable> {
	
	T findByID(int id);
	List<T> findAll();
	void save(T object);
	T update(T object);
	void delete(T object);
}