# Java Regular Expression (REGEX) checker.
A GUI Regex checker created with Java SE 8 with JavaFX using FXML.

---------------------------------------------------------------------

Purpose:

Checks text for matching Regex search queries. Returns matching elements in the lower text area.
Either search manually with a regex query or press the "OPTIONS" button and select the relevant options.
Text can be loaded from a file or entered into the upper text area.

Instructions: 

1. Enter or load text from a plain text file (.txt) or a rich text file (.rtf). Or simply enter text in the upper text area.
2. Enter a regex search query or select appropriate options.
3. Press 'CHECK' once to search for matching elements.
4. If matching elements have been found, they will appear in the lower text area.

Regex Search queries:

- ([a-z]) : Returns any lower case characters found in the text area.
- ([a-zA-Z]) : Returns any lower and upper case characters found in the text area.
- ([a-zA-Z0-9]) : Returns any lower and upper case characters and numbers found in the text area.
- ([a-zA-Z0-9_ ]) : Returns any lower and upper case characters, numbers and spaces found in the text area.

![Alt text](/screenshots/manual.png?raw=true "Optional Title")

Option buttons:

- 'az': Returns any lower case characters found in the text area.
- 'AZ': Returns any upper case characters found in the text area.
- '09': Returns any numerical characters found in the text area.
- 'spaces': Returns any spaces found in the text area.

![Alt text](/screenshots/options.png?raw=true "Optional Title")

OS specific issues:

- OSX: No known issues.
- Linux: No known issues.
- Windows: No known issues.
