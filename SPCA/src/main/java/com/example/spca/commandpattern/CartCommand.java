package com.example.spca.commandpattern;

public interface CartCommand {
	public void execute();
	public void undo();
}