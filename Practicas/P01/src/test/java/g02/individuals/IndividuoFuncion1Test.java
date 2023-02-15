package g02.individuals;

import junit.framework.TestCase;

public class IndividuoFuncion1Test extends TestCase {
  
  IndividuoFuncion1 _testCase;

  protected void setUp() throws Exception {
    Boolean cromosomaTest[] = {false, true, true, false, true, false, true, true, true, true, false, true, true, false, true};
    _testCase = new IndividuoFuncion1(cromosomaTest, 0001);
  }

  public void testMutarIndividuoOfBooleanDouble() {
    fail("Not yet implemented");
  }

  public void testCruzarIndividuoOfBooleanIndividuoOfBooleanDouble() {
    fail("Not yet implemented");
  }

  public void testGetFenotipo() {
    assertEquals(_testCase.getFenotipo(0),11.625);
  }

  public void testGetValor() {
    fail("Not yet implemented");
  }
  
}
