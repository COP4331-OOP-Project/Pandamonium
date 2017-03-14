package game.gameboard.areaEffects;

public class AreaEffectAlreadyPresentException extends Exception {

    public AreaEffectAlreadyPresentException(String message) {
        super(message);
    }

    public AreaEffectAlreadyPresentException() {}
}
