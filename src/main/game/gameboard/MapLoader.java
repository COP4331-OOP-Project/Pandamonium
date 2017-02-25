package game.gameboard;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class MapLoader {
	public static int[][] getMap(int boardSize, File mapFile) {
		int[][] map = new int[boardSize][boardSize];
		try {
			FileReader fileReader = new FileReader(mapFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String currentLine;
			int i = 0;
			int invisibleLeft = boardSize/2;
			while ((currentLine = bufferedReader.readLine()) != null) { //Read a whole line from the file
				String[] line = currentLine.split("\\s+"); //Split the line up by whitespace
				int j = 0;
				if (i % 2 == 0 && i != 0) { //The invisible tiles are staggered by 2
					invisibleLeft--;
				}
				for (int left = 0; left < invisibleLeft; left++) {
					map[i][j] = -1;
					j++;
				}
				for (int k = 0; k < line.length; k++) {
					map[i][j] = Integer.parseInt(line[k]);
					j++;
				}
				while (j < boardSize) {
					map[i][j] = -1;
					j++;
				}
				i++;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
