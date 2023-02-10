package g02.individuals;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CalibrationIndividualTest extends TestCase {

  CalibrationIndividual _testIndividual;

  /**
   * Create the test case
   *
   * @param testName name of the test case
   */
  public CalibrationIndividualTest(String testName) {
    super(testName);
  }

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    int[] ch = {0, 0, 0, 1, 0, 0, 0, 0, 1};
    _testIndividual = new CalibrationIndividual(ch);
  }

  /**
   * @return the suite of tests being tested
   */
  public static Test suite() {
    return new TestSuite(CalibrationIndividualTest.class);
  }

  public void testFitness() {
    assertEquals(_testIndividual.fitness(), 3);
  }
}
