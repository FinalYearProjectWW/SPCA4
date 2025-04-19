package com.example.spca.dao;

import java.io.Serializable;
import java.util.List;

public interface CommonDAO<T, ID extends Serializable> {
	T findByID(int id);
	List<T> findAll();
	void save(T object);
	T update(T object);
	void delete(T object);
}