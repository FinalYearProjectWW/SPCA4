package service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.Book;

public class SortByPriceStrategy implements SortingStrategy{

	@Override
	public void sort(List<Book> books, boolean ascending) {
		// TODO Auto-generated method stub
		Comparator<Book> comparator = Comparator.comparingDouble(Book::getPrice);
		if(!ascending) {
			comparator = comparator.reversed();
		}
		Collections.sort(books, comparator);
	}
}