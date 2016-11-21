package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.text.rtf.*;
import javax.swing.text.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.util.regex.*;


public class Controller {

	@FXML
	private TextArea textField;

	@FXML
	private Button fileButton;

	@FXML
	private TextField regexField;

	@FXML
	private TextArea matchingTextField;

	@FXML
	private Button clearButton;

	@FXML
	private Button checkButton;
	
	@FXML
	private Button optionsButton;
	
	@FXML
	private ToggleButton az;
	
	@FXML
	private ToggleButton AZ;
	
	@FXML
	private ToggleButton nums;
	
	@FXML
	private ToggleButton spaces;

	String currentText = "";
	String regex = "";
	String matched = "";
	Boolean options = false;
	File currentFile;

	public void clearFunc() {
		textField.clear();
		regexField.clear();
		matchingTextField.setText("");
		matched = "";
	}
	
	public void optionsFunc() {
		if(options == false) { // options
			options = true;
			optionsButton.setText("OPTIONS");
			regexField.setOpacity(0);
			regexField.setDisable(true);
			az.setDisable(false);
			AZ.setDisable(false);
			nums.setDisable(false);
		    spaces.setDisable(false);
			az.setOpacity(1);
			AZ.setOpacity(1);
			nums.setOpacity(1);
		    spaces.setOpacity(1);
		} else { // manual
			options = false;
			optionsButton.setText("MANUAL");
			regexField.setOpacity(1);
			regexField.setDisable(false);
			az.setDisable(true);
			AZ.setDisable(true);
			nums.setDisable(true);
		    spaces.setDisable(true);
			az.setOpacity(0);
			AZ.setOpacity(0);
			nums.setOpacity(0);
		    spaces.setOpacity(0);
		}
	}
	
	public String optionsRegex() {
		String tempString = "([";
		
		if(az.isSelected()) {
			tempString += "a-z";
		}
		
		if(AZ.isSelected()) {
			tempString += "A-Z";
		}

		if(nums.isSelected()) {
			tempString += "0-9";
		}

		if(spaces.isSelected()) {
			tempString += "_ ";
		}
		
		tempString += "])";
		
		System.out.println("temp: " + tempString);
		return tempString;
	}

	public void prepRegex() {
			currentText = textField.getText();
			regex = regexField.getText();
			matchingTextField.setText("");
			matched = "";
	}

	public void checkFunc() {
		prepRegex();
		StringBuilder sb = new StringBuilder();
		
		try {
		    Pattern regex;
			if(options) {
			String tempRegex = optionsRegex();
			regex = Pattern.compile(tempRegex);
			} else {
		    regex = Pattern.compile(this.regex);
			}
		    Matcher regexMatcher = regex.matcher(currentText);
		    while (regexMatcher.find()) {
		        for (int i = 1; i <= regexMatcher.groupCount(); i++) {
		        	sb.append(regexMatcher.group(i));
		            // match start: regexMatcher.start(i)
		            // match end: regexMatcher.end(i)
		        }
		    } 
		    
		    System.out.println("matched: " + sb.toString());
		    
		} catch (PatternSyntaxException ex) {
		    System.out.println("Syntax error");
		}
		
		matchingTextField.setText(sb.toString());
	}

	public void fileFunc() throws IOException, BadLocationException {
		openFile();
	}

	private String getFileExtension(File file) {
		String name = file.getName();
		try {
			return name.substring(name.lastIndexOf(".") + 1);
		} catch (Exception e) {
			return "";
		}
	}

	public void openFile() throws IOException, BadLocationException {
		textField.clear();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.txt", "*.rtf"));
		currentFile = fileChooser.showOpenDialog(textField.getScene().getWindow());

		if (getFileExtension(currentFile).equals("rtf")) {
			System.out.println("true");
			System.out.println("rtf here");
			RTFEditorKit rtf = new RTFEditorKit();
			Document doc = rtf.createDefaultDocument();

			FileInputStream fis = new FileInputStream(currentFile.getAbsolutePath());
			rtf.read(fis, doc, 0);
			textField.setText(doc.getText(0, doc.getLength()));

		} else {

			StringBuilder builder = new StringBuilder();
			BufferedReader in = new BufferedReader(
					new InputStreamReader(new FileInputStream(currentFile.getAbsolutePath()), "UTF-8"));
			String ls = System.getProperty("line.separator");

			try {
				String str = "";
				while ((str = in.readLine()) != null) {
					builder.append(str + ls);
				}

				builder.setLength(builder.length() - 1);
				textField.setText(builder.toString());

			} finally {
				in.close();
			}

		}

		int check = (int) textField.getText().charAt(0);
		if (check > 127) {
			textField.setEditable(false);
		}

	}

	public void menuOpenFunc() throws IOException, BadLocationException {
		openFile();
	}

}
