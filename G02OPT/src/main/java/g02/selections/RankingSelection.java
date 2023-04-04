package g02.selections;

import java.util.ArrayList;
import g02.individuals.Individuo;

public class RankingSelection<T> extends Selection<T> {

  double _beta;

  public RankingSelection(int s, ArrayList<Individuo<T>> pob) {
    super(s, pob);
    _beta = 2;
  }

  @Override
  public ArrayList<Individuo<T>> run() {
    if (this.pob.get(0).isMax()) {
      this.pob.sort((o1, o2) -> (o1.compareTo(o2)));
    } else {
      this.pob.sort((o2, o1) -> (o1.compareTo(o2)));
    }

    double media = 0;

    for (Individuo<T> p : pob) {
      media += p.fitness();
    }

    media /= pob.size();

    double ps = Math.min(1 + pob.get(0).fitness() / media, 2);

    double[] fitnessRanking = new double[pob.size()];

    for (int i = 0; i < pob.size(); i++) {
      fitnessRanking[i] = (1.0)/pob.size() * (_beta - 2*(_beta-1)*((double) (i-1)/(pob.size()-1))) ;
    }


    ArrayList<Individuo<T>> finalPob =
        new RouletteSelection<T>(pob.size(), pob).runRanked(fitnessRanking);

    return finalPob;
  }

}
