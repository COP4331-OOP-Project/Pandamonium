package game.mode;

public enum Mode {
    RALLY_POINT ("Rally Point"), 
    STRUCTURE ("Structure"), 
    UNIT ("Unit"), 
    ARMY ("Army");

	private final String text;
	
	private Mode (String text) {
		this.text = text;
	}
	
    public Mode getNext() {
        return this.ordinal() < Mode.values().length - 1
                ? Mode.values()[this.ordinal() + 1]
                : Mode.values()[0];
    }

    public Mode getPrevious() {
        return this.ordinal() > 0
                ? Mode.values()[this.ordinal() - 1]
                : Mode.values()[Mode.values().length - 1];
    }
    
    public String getText() {
    	return text;
    }
}
