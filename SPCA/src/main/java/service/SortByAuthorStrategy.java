package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.Book;

public class SortByAuthorStrategy implements SortingStrategy{

	@Override
	public void sort(List<Book> books, boolean ascending) {
		// TODO Auto-generated method stub
		Comparator<Book> comparator = Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER);
		if(!ascending) {
			comparator = comparator.reversed();
		}
		Collections.sort(books, comparator);
	}
}