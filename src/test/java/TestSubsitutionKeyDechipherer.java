import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestSubsitutionKeyDechipherer {
  // Validation tests
  @Test
  public void wrongPath() {
    Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> new SubsitutionKeyDechipherer("wow/this is invalido3"));
  }


}
