package view.game;

import game.commands.SubCommandEnum;
import javafx.scene.control.Button;

public class SubCommandButton extends Button {

    private SubCommandEnum command; // Command
    private String description;     // Command description

    // Constructor
    public SubCommandButton(SubCommandEnum command) {
        super(command.toString());
        this.command = command;
        this.description = command.toString();
    }

    public SubCommandEnum getCommand() { return command; }

    public String getDescription() { return description; }


}
