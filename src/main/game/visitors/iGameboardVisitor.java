package game.visitors;

import game.gameboard.Gameboard;

public interface iGameboardVisitor {
    void visitGameboard(Gameboard gb);
}
