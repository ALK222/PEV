package g02.individuals;

import java.io.File; // Import the File class
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Individuo de calibrado.
 */
public class IndividuoNonograma extends Individuo<Boolean[]> {

  private int numColumnas;
  private int numFilas;
  private ArrayList<ArrayList<Integer>> restriccionesColumnas;
  private ArrayList<ArrayList<Integer>> restriccionesFilas;
  private String file;

  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param filename archivo del que se sacan las restricciones
   */
  public IndividuoNonograma(String filename) {

    this.restriccionesFilas = new ArrayList<ArrayList<Integer>>();
    this.restriccionesColumnas = new ArrayList<ArrayList<Integer>>();

    try {
      file = filename;
      File dataFile = new File(filename);
      Scanner fileReader = new Scanner(dataFile);

      this.numFilas = Integer.valueOf(fileReader.nextLine());

      for (int i = 0; i < numFilas; ++i) {
        String[] res = fileReader.nextLine().split(" ");
        ArrayList<Integer> lineRestriction = new ArrayList<Integer>();
        for (String r : res) {
          lineRestriction.add(Integer.valueOf(r));
        }
        restriccionesFilas.add(lineRestriction);
      }

      this.numColumnas = Integer.valueOf(fileReader.nextLine());

      for (int i = 0; i < numColumnas; ++i) {
        String[] res = fileReader.nextLine().split(" ");
        ArrayList<Integer> lineRestriction = new ArrayList<Integer>();
        for (String r : res) {
          lineRestriction.add(Integer.valueOf(r));
        }
        restriccionesColumnas.add(lineRestriction);
      }
      fileReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }


    this.chromosome = new Boolean[numFilas][numColumnas];

    for (int i = 0; i < this.numFilas; i++) {
      for (int j = 0; j < this.numColumnas; ++j) {
        chromosome[i][j] = ThreadLocalRandom.current().nextBoolean();
      }
    }

  }

  /**
   * Instantiates a new individuo nonograma.
   *
   * @param chromosome cromosoma dado
   * @param numFilas numero de filas del nonograma
   * @param numColumas numero de columnas del nonograma
   * @param restricciones restricciones de las filas y columnas del nonograma
   */
  public IndividuoNonograma(Boolean[][] chromosome, int numFilas, int numColumnas,
      ArrayList<ArrayList<Integer>> restriccionesFilas,
      ArrayList<ArrayList<Integer>> restriccionesColumnas) {
    this.numColumnas = numColumnas;
    this.numFilas = numFilas;

    this.restriccionesFilas = restriccionesFilas;
    this.restriccionesColumnas = restriccionesColumnas;

    this.chromosome = new Boolean[numFilas][numColumnas];
    for (int i = 0; i < numFilas; i++) {
      for (int j = 0; j < numColumnas; ++j)
        this.chromosome[i][j] = chromosome[i][j];
    }
  }


  /**
   * Calcula el fitness del individuo.
   *
   * @return fitness del individuo
   */
  public double fitness() {
    return this.getValor();
  }

  /**
   * Muta un individuo.
   *
   * @param individuo Individuo a mutar
   * @param prob probabilidad de mutacion
   * @return individuo mutado
   */
  @Override
  public Individuo<Boolean[]> mutar(Individuo<Boolean[]> individuo, double prob, int mut) {
    if (ThreadLocalRandom.current().nextDouble() < prob) {
      switch (mut) {
        case 0:
          this.mutaIntercambio(individuo);
          break;
        case 1:
          this.mutaInsercion(individuo);
          break;
        case 2:
          this.mutaInversion(individuo);
          break;
        case 3:
          this.mutaHeuristica(individuo);
          break;
        case 4:
          this.mutaRotacionHeu(individuo);
          break;
        default:
          for (int i = 0; i < this.numFilas; ++i) {
            for (int j = 0; j < this.numColumnas; ++j) {
              if (ThreadLocalRandom.current().nextDouble() > 0.5) {
                this.chromosome[i][j] = !this.chromosome[i][j];
              }
            }
          }
      }
    }
    return this.copyIndividuo();
  }

  private IndividuoNonograma mutaRotacionHeu(Individuo<Boolean[]> individuo) {


    return this;
  }

  private IndividuoNonograma mutaHeuristica(Individuo<Boolean[]> individuo) {

    double puntuacionInicial = this.getValor();

    for (int i = 0; i < this.numFilas; ++i) {
      for (int j = 0; j < this.numColumnas; ++j) {
        // if (ThreadLocalRandom.current().nextDouble() > 0.5) {
        this.chromosome[i][j] = !this.chromosome[i][j];
        if (this.getValor() < puntuacionInicial) {
          this.chromosome[i][j] = !this.chromosome[i][j];
        }
        // }
      }
    }

    return this;
  }

  private IndividuoNonograma mutaInversion(Individuo<Boolean[]> individuo) {
    /*
     * int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length); int
     * punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
     * 
     * while (punto2 == punto1) punto2 = ThreadLocalRandom.current().nextInt(1,
     * individuo.getCromosoma().length);
     * 
     * 
     * int ini = Math.min(punto1, punto2); int fin = Math.max(punto1, punto2);
     * 
     * Integer[] aux = new Integer[fin - ini]; int itAux = 0;
     * 
     * for (int i = fin; i > ini; --i) { aux[itAux] = individuo.getCromosoma()[i]; ++itAux; }
     * 
     * itAux = 0; for (int i = ini + 1; i <= fin; ++i) { individuo.getCromosoma()[i] = aux[itAux];
     * ++itAux; }
     */


    return this;
  }

  private IndividuoNonograma mutaInsercion(Individuo<Boolean[]> individuo) {
    /*
     * for (int i = 0; i < nDesplazamientos; ++i) { int punto1 =
     * ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1); int punto2 =
     * ThreadLocalRandom.current().nextInt(0, punto1); Integer aux =
     * Integer.valueOf(individuo.getCromosoma()[punto1]); for (int j = punto1; j > punto2; --j) {
     * individuo.getCromosoma()[j] = individuo.getCromosoma()[j - 1]; }
     * individuo.getCromosoma()[punto2] = Integer.valueOf(aux); }
     */

    return this;
  }

  private Individuo<Boolean[]> mutaIntercambio(Individuo<Boolean[]> individuo) {
    /*
     * int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1); int
     * punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
     * 
     * Integer aux = Integer.valueOf(individuo.getCromosoma()[punto1]);
     * individuo.getCromosoma()[punto1] = Integer.valueOf(individuo.getCromosoma()[punto2]);
     * individuo.getCromosoma()[punto2] = Integer.valueOf(aux);
     * 
     */return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return el cromosoma
   */
  @Override
  public Boolean[][] getCromosoma() {
    return chromosome;
  }

  /**
   * Gets the fenotipo of a given chromosome.
   *
   * @param index the index of the fenotype
   * @return the fenotype
   */
  // Necesario para calcular el fitness
  @Override
  public double getFenotipo(int index) {
    return 0;
  }

  /**
   * Valor del individuo en su funcion.
   *
   * @return el valor
   */
  @Override
  public double getValor() {
    double aux = 0;

    // filas
    for (int i = 0; i < numFilas; ++i) {
      aux += fitnessArray(restriccionesFilas.get(i), this.chromosome[i], numFilas);
    }

    // columnas
    for (int i = 0; i < numColumnas; ++i) {
      Boolean[] columna = new Boolean[numFilas];
      for(int j = 0; j < numFilas; ++j) {
        columna[j] = chromosome[i][j];
      }
      aux += fitnessArray(restriccionesFilas.get(i), columna, numColumnas);
    }

    return aux;
  }

  private int fitnessArray(ArrayList<Integer> restricciones, Boolean[] fila, int tam) {
    int fitness = 0;
    int conjuntos = 0;
    int puestas = 0;
    int seguidas = 0;
    int[] conjunto = new int[tam];

    for (int i = 0; i < tam; ++i) {
      if (seguidas > 0 && !fila[i]) {
        conjunto[conjuntos] = seguidas;
        conjuntos++;
        seguidas = 0;
      }
      if (fila[i]) {
        puestas++;
        seguidas++;
      }
    }
    
    if(seguidas > 0) {
      conjunto[conjuntos] = seguidas;
      conjuntos++;
    }
    
    fitness += puestas;

    if (conjuntos == restricciones.size()) {
      fitness += conjuntos * 2;
      for(int i = 0; i < restricciones.size(); ++i) {
        if(restricciones.get(i) == conjunto[i]) {
          fitness += tam*3;
        }
      }
    }else {
      fitness = 0;
    }

    return fitness;
  }

  /**
   * Copy individuo.
   *
   * @return the individuo
   */
  @Override
  public Individuo<Boolean[]> copyIndividuo() {
    return new IndividuoNonograma(this.chromosome, this.numFilas, this.numColumnas,
        this.restriccionesFilas, this.restriccionesColumnas);
  }

  /**
   * Comprueba si es una funcion de maximizacion.
   *
   * @return true
   */
  @Override
  public boolean isMax() {
    return true;
  }

  @Override
  public void corregir() {
    this.chromosome[this.numFenotipos - 1] = this.chromosome[0];
  }

  @Override
  public String toString() {
    String aux = "Cromosoma: ";

    try {
      File myObj = new File(file + "_solution");
      if (myObj.createNewFile()) {
        System.out.println("File created: " + myObj.getName());
      } else {
        System.out.println("File already exists.");
      }

      FileWriter writer = new FileWriter(file + "_solution");

      for (int i = 0; i < numFilas; ++i) {
        for (int j = 0; j < numColumnas; ++j) {
          if (!chromosome[i][j]) {
            writer.write('·');
          } else {
            writer.write('X');
          }
        }
        writer.write("\n");
      }
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

    return aux;
  }


}
