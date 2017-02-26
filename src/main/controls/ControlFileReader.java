package controls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import javafx.scene.input.KeyCode;


public class ControlFileReader {
	private static final String CONFIG_FILE = "assets/controls/controls.cfg";
	File controlsFile = new File(CONFIG_FILE);
	private HashMap<String, KeyCode> controls;
	
	public ControlFileReader() {
		controls = new HashMap<String, KeyCode>();
		loadControls();
	}
	
	public void loadControls() {
		try {
			FileReader fileReader = new FileReader(controlsFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String currentLine;
			while ((currentLine = bufferedReader.readLine()) != null) {
				String[] line = currentLine.split("\\s+");
				if(line.length == 2) {
					controls.put(line[0], KeyCode.valueOf(line[1]));
				}
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public KeyCode getControl(String string) {
		return controls.get(string);
	}
	
	
}
