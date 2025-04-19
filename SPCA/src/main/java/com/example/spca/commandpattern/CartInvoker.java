package com.example.spca.commandpattern;

import java.util.ArrayDeque;
import java.util.Deque;
import org.springframework.stereotype.Component;

@Component
public class CartInvoker {
		
	private final Deque<CartCommand> list = new ArrayDeque<>();
	
	public void execute(CartCommand cc) {
		cc.execute();
		list.push(cc);
	}
	
	public void undoLast() {
		if(!list.isEmpty()) {
			list.pop().undo();
		}
	}
}