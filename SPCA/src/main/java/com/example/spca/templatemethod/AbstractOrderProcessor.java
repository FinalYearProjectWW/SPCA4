package com.example.spca.templatemethod;

import java.util.Date;
import com.example.spca.entities.Order;
import com.example.spca.entities.OrderItem;


public abstract class AbstractOrderProcessor {
	
	public final void processOrder(Order order) {
		validateOrder(order);
		boolean paymentSuccessful = processPayment(order);
		if(!paymentSuccessful) {
			handlePaymentFailure(order);
			return;
		}
		updateStock(order);
		recalculateTotal(order);
		finaliseOrder(order);
	}
	
	protected abstract void validateOrder(Order order);
	
	protected abstract boolean processPayment(Order order);
	
	protected abstract void updateStock(Order order);
	
	
	protected void handlePaymentFailure(Order order) {
		throw new RuntimeException("Payment failed for order " + order.getId());
	}
	
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