package service;

public interface CartCommand {
	public void execute();
	public void undo();
}