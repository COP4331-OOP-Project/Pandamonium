package game;
public class GameFailedToStartException extends Exception {
    public GameFailedToStartException(){ super("Game failed to start."); }
}