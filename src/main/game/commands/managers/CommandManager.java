package game.commands.managers;

import game.Player;
import game.entities.EntityId;

public class CommandManager {
	private EntityId selectedEntity;
	private Player currentPlayer;
	
	public void updateCommandList() {
		
	}
	
	public void cycleForward() {
	
	}

	public void cycleBackward() {
	
	}

	public void addMoveToList(int degrees) {
		
	}
	
	public void execute() {
		
	}
	
	public void setPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public void updateSelectedEntity(EntityId entityId) {
		selectedEntity = entityId;
	}

}
