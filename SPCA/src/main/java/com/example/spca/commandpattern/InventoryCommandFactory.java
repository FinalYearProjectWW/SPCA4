package com.example.spca.commandpattern;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class InventoryCommandFactory {
	
	private final Map<String, InventoryCommand> commands;
	
	public InventoryCommandFactory(Map<String, InventoryCommand> commands) {
		this.commands = commands;
	}
	
	public InventoryCommand get(String name) {
		return commands.get(name);
	}
}