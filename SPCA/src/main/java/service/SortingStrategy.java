package service;

import java.util.List;
import entities.Book;

public interface SortingStrategy {	
	void sort(List<Book> books, boolean ascending);
}
