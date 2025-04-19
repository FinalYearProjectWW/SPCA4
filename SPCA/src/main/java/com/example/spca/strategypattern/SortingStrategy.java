package com.example.spca.strategypattern;

import java.util.List;
import com.example.spca.entities.Book;

public interface SortingStrategy {	
	void sort(List<Book> books, boolean ascending);
}
