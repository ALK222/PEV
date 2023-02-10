package g02.individuals;

import java.util.Arrays;
import g02.utils.Utils;

public class CalibrationIndividual extends Individuo<Boolean> {


  public CalibrationIndividual(Boolean chromosome[]) {
    _chromosome = chromosome;
  }

  @Override
  public double fitness() {

    int x1 = Utils.decimalToBinary(Arrays.copyOfRange(_chromosome, 0, 3));
    int x2 = Utils.decimalToBinary(Arrays.copyOfRange(_chromosome, 4, _chromosome.length));


    double res = 21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2);
    return res;
  }

  @Override
  public Individuo<Boolean> mutar(Individuo<Boolean> individuo, double prob) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Individuo<Boolean>[] cruzar(Individuo<Boolean> i1, Individuo<Boolean> i2, double prob) {
    // TODO Auto-generated method stub
    return null;
  }



}
