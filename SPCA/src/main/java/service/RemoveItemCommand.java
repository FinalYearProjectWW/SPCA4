package service;

import entities.Book;
import entities.OrderItem;

public class RemoveItemCommand implements CartCommand{
	
	private ShoppingCartService scs;
	private int customerId;
    private OrderItem oi;

	
	public RemoveItemCommand(ShoppingCartService scs, int customerId, OrderItem oi) {
		this.scs = scs;
		this.customerId = customerId;
		this.oi = oi;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		scs.internalRemoveItem(customerId, oi);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		scs.internalAddItem(customerId, oi);
	}
}
