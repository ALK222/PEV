package g02.Selections;

import g02.individuals.Individuo;
import java.util.ArrayList;


// TODO: Auto-generated Javadoc
/**
 * Abstract class to extend all types of selection.
 *
 * @param <T> the generic type
 */
public abstract class Selection<T> {

  /** The pob. */
  protected ArrayList<Individuo<T>> pob;
  
  /** The probs. */
  protected double[] probs;
  
  /** The seleccionar. */
  protected int seleccionar;
  
  /** The fitness total. */
  protected double fitnessTotal;

  /**
   * Instantiates a new selection.
   *
   * @param s numero de individuos a seleccionar
   * @param pob poblacion a seleccionar
   */
  public Selection(int s, ArrayList<Individuo<T>> pob) {
    seleccionar = s;
    this.pob = pob;
  }

  /**
   * Selecciona los individuos.
   *
   * @return nueva poblacion
   */
  public abstract ArrayList<Individuo<T>> run();

  /**
   * Sets the pob.
   *
   * @param pob poblacion a seleccionar
   */
  public void setPob(ArrayList<Individuo<T>> pob) {
    this.pob = pob;
  }

  /**
   * Corrige minimizar.
   *
   * @param max nuevo maximo del algoritmo
   */
  public void corrigeMinimizar(double max) {
    for (int i = 0; i < probs.length; i++) {
      probs[i] = (1.05 * max) - probs[i];
      this.fitnessTotal += probs[i];
    }
  }

  /**
   * Corrige maximizar.
   *
   * @param min nuevo minimo del algoritmo
   */
  public void corrigeMaximizar(double min) {
    for (int i = 0; i < probs.length; i++) {
      probs[i] = probs[i] + Math.abs(min);
      this.fitnessTotal += probs[i];
    }
  }

}
