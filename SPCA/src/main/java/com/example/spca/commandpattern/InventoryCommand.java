package com.example.spca.commandpattern;

public interface InventoryCommand {
	void execute(int bookId, int qty);
}