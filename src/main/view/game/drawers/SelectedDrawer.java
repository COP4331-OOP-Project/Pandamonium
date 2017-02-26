package view.game.drawers;

import view.game.GamePanel;

public class SelectedDrawer {
    GamePanel gamePanel;

    public SelectedDrawer(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
/*
    public void drawSelectedItemOutline() {
    	Point p = new Point(game.getCenterCoordinates().getX(), game.getCenterCoordinates().getY());
        if (game.getCurrentMode() == ModeEnum.RALLY_POINT) {
            drawSelectedRallyPointOutline(p);
        } else if (game.getCurrentMode() == ModeEnum.STRUCTURE) {
            drawSelectedStructureOutline(p);
        } else if (game.getCurrentMode() == ModeEnum.UNIT) {
            drawSelectedUnitOutline(p);
        } else if (game.getCurrentMode() == ModeEnum.ARMY) {
            drawSelectedArmyOutline(p);
        }
    }

    private void drawSelectedRallyPointOutline(Point p) {
        if (game.getCurrentPlayer().getArmyRallyPoint().size() > 0)
            gamePanel.drawStaticTileElement(p, "RALLY_POINT_SELECTED");
    }

    private void drawSelectedStructureOutline(Point p) {
        if (game.getCurrentPlayer().getBases().size() > 0)
            gamePanel.drawStaticTileElement(p, "BASE_SELECTED");
    }

    private void drawSelectedArmyOutline(Point p) {
        if (game.getGameBoard().getTiles()[p.x][p.y].containsArmy) {
            gamePanel.drawStaticTileElement(p, "ARMY_SELECTED");
        }
    }

    private void drawSelectedUnitOutline(Point p) {
        if (game.getGameBoard().getTiles()[p.x][p.y].containsArmy) {
            gamePanel.drawStaticTileElement(p, "ARMY_SELECTED");
        } else if (game.getCurrentPlayer().getAllUnit().size() > 0) {
            gamePanel.drawStaticTileElement(p, "UNIT_SELECTED");
        }
    }

*/
}
