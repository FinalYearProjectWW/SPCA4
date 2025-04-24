package com.example.spca.templatemethod;

import java.util.Date;
import com.example.spca.entities.Order;
import com.example.spca.entities.OrderItem;


public abstract class AbstractOrderProcessor {
	
	public final void processOrder(Order order) {
		validateOrder(order);
		updateStock(order);
		recalculateTotal(order);
		finaliseOrder(order);
	}
	
	protected abstract void validateOrder(Order order);
		
	protected abstract void updateStock(Order order);
	
	protected void recalculateTotal(Order order) {
		long total = 0;
		for(OrderItem item: order.getOrderItems()) {
			total += item.getPrice() * item.getQuantity();
		}
		order.setTotalAmount(total);
	}
	
	protected void finaliseOrder(Order order) {
		order.setOrderDate(new Date());
		order.setStatus("Complete");
	}
}