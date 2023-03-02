package g02.individuals;

public class IndividuoFuncion4b extends Individuo<Double> {

  public IndividuoFuncion4b(double precision) {
    this.tamGenes = new int[2];
    this.min = new double[2];
    this.max = new double[2];
    this.min[0] = -3.000;
    this.min[1] = 4.100;
    this.max[0] = 12.100;
    this.max[1] = 5.800;
    this.precision = precision;
    this.tamGenes[0] = this.tamGen(precision, min[0], max[0]);
    this.tamGenes[1] = this.tamGen(precision, min[1], max[1]);
    int tamTotal = tamGenes[0] + tamGenes[1];


  }

  @Override
  public double fitness() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Individuo<Double> mutar(Individuo<Double> individuo, double prob) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Double[] getCromosoma() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getFenotipo(int index) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public double getValor() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Individuo<Double> copyIndividuo() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public boolean isMax() {
    // TODO Auto-generated method stub
    return false;
  }

}
