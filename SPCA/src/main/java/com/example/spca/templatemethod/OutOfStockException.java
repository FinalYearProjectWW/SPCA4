package com.example.spca.templatemethod;

@SuppressWarnings("serial")
public class OutOfStockException extends RuntimeException {
    public OutOfStockException(int bookId, int requested) {
        super("Book " + bookId + " out of stock for qty=" + requested);
    }
}