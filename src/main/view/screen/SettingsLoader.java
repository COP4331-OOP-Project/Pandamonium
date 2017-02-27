package view.screen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public final class SettingsLoader {
	private static final int NUM_OF_CONTROLS = 16;
	public static String[][] getControls(File controlsFile) {
		String[][] controlsArray = new String[NUM_OF_CONTROLS][];
		try {
			FileReader fileReader = new FileReader(controlsFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String currentLine;
			int lineNumber = 0;
			while ((currentLine = bufferedReader.readLine()) != null) { //Read a whole line from the file
				String[] line = currentLine.split("\\s+"); //Split the line up by whitespace
				controlsArray[lineNumber] = line;
				lineNumber++;
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return controlsArray;
	}

}