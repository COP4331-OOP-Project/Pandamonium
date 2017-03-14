package game.gameboard.oneShotItem;

public class OneShotItemAlreadyPresentException extends Exception {

    public OneShotItemAlreadyPresentException(String message) {
        super(message);
    }

    public OneShotItemAlreadyPresentException() {
        super();
    }
}
