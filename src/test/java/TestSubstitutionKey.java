import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the class SubstitutionKey.
 */
public class TestSubstitutionKey {
  // Validation tests
  @Test
  public void nullPathTest() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(null));
  }

  @Test
  public void wrongPathTest() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(
                    "wow/this is invalido3"));
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(
                    "chiphers/bobbobccc..!!"));
    Assertions.assertDoesNotThrow(
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/key.txt"));
  }

  @Test
  public void rightLines() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/emptyKey.txt"));
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/oneLineKey.txt"));
    Assertions.assertDoesNotThrow(
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/moreLineKey.txt"));
  }

  @Test
  public void duplicateBaseCharacterTest() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/duplicateBaseLine.txt"));
    Assertions.assertDoesNotThrow(
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/duplicateSubsitutionLine.txt"));
  }

  @Test
  public void mismatchLineLength() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubstitutionKey(
                    "src/test/testKeys/validationTestKeys/keyMismatch.txt"));
  }

  // Decipher tests
  @Test
  public void nullExampleTest() {
    SubstitutionKey key1 = new SubstitutionKey(
            "src/test/testKeys/dechipherTestKeys/validKey.txt");
    SubstitutionKey key2 = new SubstitutionKey(
            "src/test/testKeys/dechipherTestKeys/emptyTwoLineKey.txt");
    SubstitutionKey key3 = new SubstitutionKey(
            "src/test/testKeys/dechipherTestKeys/mixedKey.txt");
    Assertions.assertEquals(key1.decipher(null),"");
    Assertions.assertEquals(key2.decipher(null),"");
    Assertions.assertEquals(key3.decipher(null),"");
  }

  @Test
  public void validExampleTest() {
    SubstitutionKey key = new SubstitutionKey(
            "src/test/testKeys/dechipherTestKeys/validKey.txt");
    Assertions.assertEquals(key.decipher(
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
            "bcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890a");
    Assertions.assertEquals(key.decipher(
                    "KowaBunga"),
            "LpxbCvohb");
  }

  @Test
  public void keepSameCharTest() {
    SubstitutionKey key = new SubstitutionKey(
            "src/test/testKeys/dechipherTestKeys/emptyTwoLineKey.txt");
    Assertions.assertEquals(key.decipher(
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
            "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
    Assertions.assertEquals(key.decipher(
                    "KowaBunga"),
            "KowaBunga");
  }

  @Test
  public void mixedTest() {
    SubstitutionKey key = new SubstitutionKey(
            "src/test/testKeys/dechipherTestKeys/mixedKey.txt");
    Assertions.assertEquals(key.decipher(
                    "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"),
            "hijklmnhijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890");
    Assertions.assertEquals(key.decipher(
                    "KowaBunga"),
            "KowhBunnh");
  }
}
