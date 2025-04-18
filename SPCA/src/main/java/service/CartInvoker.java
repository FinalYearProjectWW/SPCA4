package service;

import java.util.ArrayDeque;
import java.util.Deque;

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