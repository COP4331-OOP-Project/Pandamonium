package game.mode;

public enum Submode {
	RALLY_POINT (Mode.RALLY_POINT, "Rally Point"), 
	CAPITOL (Mode.STRUCTURE, "Capitol"), 
	FARM (Mode.STRUCTURE, "Farm"), 
	FORT (Mode.STRUCTURE, "Fort"), 
	MINE (Mode.STRUCTURE, "Mine"), 
	OBSERVE (Mode.STRUCTURE, "Observation Tower"),
	PLANT (Mode.STRUCTURE, "Power Plant"), 
	UNIVERSITY (Mode.STRUCTURE, "University"), 
	EXPLORER (Mode.UNIT, "Explorer"), 
	COLONIST (Mode.UNIT, "Colonist"), 
	MELEE (Mode.UNIT, "Melee"), 
	RANGED (Mode.UNIT, "Ranged"), 
	ENTIRE_ARMY (Mode.ARMY, "Entire Army"), 
	BATTLE_GROUP (Mode.ARMY, "Battle Group"), 
	REINFORCEMENTS (Mode.ARMY, "Reinforcements");
	
	private final Mode associatedMode;
	private final String text;
	
	private Submode (Mode associatedMode, String text) {
		this.associatedMode = associatedMode;
		this.text = text;
	}
	
    private Submode next() {
        return this.ordinal() < Submode.values().length - 1
                ? Submode.values()[this.ordinal() + 1]
                : Submode.values()[0];
    }

    private Submode previous() {
        return this.ordinal() > 0
                ? Submode.values()[this.ordinal() - 1]
                : Submode.values()[Submode.values().length - 1];
    }
	
	public Submode getNext(Mode currentMode) {
		Submode next = next();
		if (currentMode == next.associatedMode) {
			return next;
		} else {
			return getNext(currentMode, next);
		}
	}
	
	public Submode getPrevious(Mode currentMode) {
		Submode previous = previous();
		if (currentMode == previous.associatedMode) {
			return previous;
		} else {
			return getPrevious(currentMode, previous);
		}
	}
	
	private Submode getNext(Mode currentMode, Submode submode) {
		Submode next = submode.next();
		if (currentMode == next.associatedMode) {
			return next;
		} else {
			return getNext(currentMode, next);
		}
	}
	
	private Submode getPrevious(Mode currentMode, Submode submode) {
		Submode previous = submode.previous();
		if (currentMode == previous.associatedMode) {
			return previous;
		} else {
			return getPrevious(currentMode, previous);
		}
	}
	
	public String getText() {
		return text;
	}
}
