package service;

import entities.OrderItem;

public class AddItemCommand implements CartCommand{
	
	private ShoppingCartService scs;
	private int customerId;
	private OrderItem oi;
	
	public AddItemCommand(ShoppingCartService scs, int customerId, OrderItem oi) {
		this.scs = scs;
		this.customerId = customerId;
		this.oi = oi;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		scs.internalAddItem(customerId, oi);
		
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		scs.internalRemoveItem(customerId, oi.getBook());
	}

}
