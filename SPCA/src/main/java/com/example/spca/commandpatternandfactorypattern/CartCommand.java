package com.example.spca.commandpatternandfactorypattern;

public interface CartCommand {
	public void execute();
	public void undo();
}