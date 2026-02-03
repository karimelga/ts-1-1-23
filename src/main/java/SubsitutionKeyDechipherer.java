import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Takes in file and dechipers text based on
 * subsitution key.
 */
public class SubsitutionKeyDechipherer {
  // Key is the base char and value is the char that subsitutes that char in
  // decrypted message.
  private HashMap<Character, Character> subsitutionKey;

  /**
   * Verifies that given subsitution key file exists and is in the below format.
   * -base character line-
   * -subsituted character line-
   *
   * @param keyPath File path to key that contains subsitution chiper
   * @exception IllegalArgumentException If file path led to an invalid file or led to
   *                                     invalid subsitution key.
   */
  public SubsitutionKeyDechipherer(final String keyPath) throws IllegalArgumentException {
    // Verify file exists at all and begins scanning
    File keyFile;
    String baseLine;
    String subsituteLine;
    try {
      keyFile = new File(keyPath);
      Scanner keyScanner = new Scanner(keyFile);
      if (!keyScanner.hasNextLine()) {
        throw new IllegalArgumentException("Key file doesn't have any lines.");
      }
      baseLine = keyScanner.nextLine();
      if (!keyScanner.hasNextLine()) {
        throw new IllegalArgumentException("Key file has only one line.");
      }
      subsituteLine = keyScanner.nextLine();
      keyScanner.close();
    } catch (FileNotFoundException exception) {
      throw new IllegalArgumentException("Key path does not lead to a valid txt file.");
    }

    // Verify that lines extracted from file will work as a subsitution key
    if (subsituteLine.length() != baseLine.length()) {
      throw new IllegalArgumentException(
              "Base character line and subsituted letter line don't have the same length.");
    }
    HashSet<Character> baseSet = new HashSet<>();
    for (int lineIter = 0 ; lineIter < baseLine.length() ; lineIter++) {
      if (baseSet.contains(baseLine.charAt(lineIter))) {
        throw new IllegalArgumentException(
                "Duplicate chareter in base line causing ambiguous subsitution key.");
      }
    }

    // Actually sets up hash map
    subsitutionKey = new HashMap<>();
    for (int lineIter = 0 ; lineIter < baseLine.length() ; lineIter++) {
      subsitutionKey.put(baseLine.charAt(lineIter), subsituteLine.charAt(lineIter));
    }
  }

  /**
   * Runs a string through a subsitution key and return the result.
   *
   * @param encryptedString String encrypted with given subsitution key.
   * @return Decrypted string.
   */
  public String Dechiper(final String encryptedString) {
    StringBuilder decryptedOutputStringBuilder = new StringBuilder(encryptedString.length());
    for (int lineIter = 0 ; lineIter < encryptedString.length() ; lineIter++) {
      char correspondingChar = subsitutionKey.get(encryptedString.charAt(lineIter));
      decryptedOutputStringBuilder.setCharAt(lineIter, correspondingChar);
    }
    return decryptedOutputStringBuilder.toString();
  }
}
