package com.example.spca.dao;

import java.util.List;
import com.example.spca.entities.Book;


public interface BookDAO extends CommonDAO<Book, Integer> {
	List<Book> searchBookByTitle(String title);
}