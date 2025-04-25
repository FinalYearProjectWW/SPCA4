package com.example.spca.commandpatternandfactorypattern;

public interface InventoryCommand {
	void execute(int bookId, int qty);
}