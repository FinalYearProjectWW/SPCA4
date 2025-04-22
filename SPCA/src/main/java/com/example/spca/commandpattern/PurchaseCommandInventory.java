package com.example.spca.commandpattern;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.spca.templatemethod.AbstractInventoryOperation;

@Component("purhcaseCmd")
public class PurchaseCommandInventory implements InventoryCommand{

	private final AbstractInventoryOperation op;
	
	public PurchaseCommandInventory(@Qualifier("purchaseOp") AbstractInventoryOperation aio) {
		this.op = aio;
	}
	
	@Override
	public void execute(int bookId, int qty) {
		// TODO Auto-generated method stub
		op.perform(bookId, qty);
	}
}