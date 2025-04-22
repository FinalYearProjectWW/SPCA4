package com.example.spca.commandpattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.spca.templatemethod.AbstractInventoryOperation;

@Component("restockCmd")
public class RestockCommandInventory implements InventoryCommand{

	private final AbstractInventoryOperation aio;
	
	public RestockCommandInventory(@Qualifier("restockOp") AbstractInventoryOperation aio) {
		this.aio = aio;
   }
	
	@Override
	public void execute(int bookId, int qty) {
		// TODO Auto-generated method stub
		aio.perform(bookId, qty);
	}

}
