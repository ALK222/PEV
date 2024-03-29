package g02.individuals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Individuo de calibrado.
 */
public class IndividuoPractica2 extends Individuo<Integer> {

  private final static int[][] _DIST = {{}, // Albacete
      {171}, // Alicante
      {369, 294}, //
      {366, 537, 633}, {525, 696, 604, 318}, {540, 515, 809, 717, 1022},
      {646, 817, 958, 401, 694, 620}, {488, 659, 800, 243, 536, 583, 158},
      {504, 675, 651, 229, 89, 918, 605, 447}, {617, 688, 484, 618, 342, 1284, 1058, 900, 369},
      {256, 231, 525, 532, 805, 284, 607, 524, 701, 873},
      {207, 378, 407, 256, 318, 811, 585, 427, 324, 464, 463},
      {354, 525, 332, 457, 272, 908, 795, 637, 319, 263, 610, 201},
      {860, 1031, 1172, 538, 772, 1118, 644, 535, 683, 1072, 1026, 799, 995},
      {142, 313, 511, 282, 555, 562, 562, 404, 451, 708, 305, 244, 445, 776},
      {640, 615, 909, 817, 1122, 100, 720, 683, 1018, 1384, 384, 911, 1008, 1218, 662},
      {363, 353, 166, 534, 438, 868, 829, 671, 485, 335, 584, 278, 166, 1043, 479, 968},
      {309, 480, 621, 173, 459, 563, 396, 238, 355, 721, 396, 248, 458, 667, 136, 663, 492},
      {506, 703, 516, 552, 251, 1140, 939, 781, 323, 219, 856, 433, 232, 1006, 677, 1240, 350, 690},
      {495, 570, 830, 490, 798, 274, 322, 359, 694, 1060, 355, 587, 797, 905, 406, 374, 831, 339,
          1029},
      {264, 415, 228, 435, 376, 804, 730, 572, 423, 367, 520, 179, 104, 944, 380, 904, 99, 393, 336,
          732},
      {584, 855, 896, 255, 496, 784, 359, 201, 407, 796, 725, 511, 733, 334, 500, 884, 761, 391,
          730, 560, 668},
      {515, 490, 802, 558, 866, 156, 464, 427, 762, 1128, 259, 655, 865, 973, 472, 256, 861, 407,
          1097, 118, 779, 628},
      {578, 653, 899, 358, 676, 468, 152, 115, 595, 999, 455, 526, 736, 650, 464, 568, 770, 278,
          968, 244, 671, 316, 312},
      {762, 933, 1074, 440, 674, 1020, 546, 437, 585, 974, 928, 696, 897, 98, 678, 1120, 945, 569,
          908, 807, 846, 236, 875, 352},
      {251, 422, 563, 115, 401, 621, 395, 237, 297, 663, 417, 190, 400, 609, 167, 721, 434, 58, 632,
          397, 335, 333, 465, 336, 551},
      {473, 482, 219, 644, 436, 997, 939, 781, 506, 265, 713, 388, 187, 1153, 615, 1097, 129, 602,
          313, 941, 209, 877, 1009, 880, 1055, 544},
      {150, 75, 219, 516, 675, 590, 796, 638, 654, 613, 306, 357, 444, 1010, 292, 690, 278, 459,
          628, 611, 340, 734, 583, 694, 912, 401, 407}};
  
  private int nDesplazamientos = 3;


  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param precision precision de la codificacion
   */
  public IndividuoPractica2(double precision) {
    this.precision = precision;
    this.tamTotal = _DIST.length - 1;
    this.numFenotipos = _DIST.length - 1;

    this.chromosome = new Integer[this.tamTotal];
    ArrayList<Integer> ciudades = new ArrayList<Integer>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
        10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 26, 27));
    Collections.shuffle(ciudades);
    for (int i = 0; i < this.tamTotal; i++) {
      this.chromosome[i] = ciudades.get(i);
    }

  }

  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   */
  public IndividuoPractica2(Integer[] _chromosome, double precision) {
    this.precision = precision;
    this.tamTotal = _DIST.length - 1;
    this.numFenotipos = _DIST.length - 1;

    this.chromosome = new Integer[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = Integer.valueOf(_chromosome[i]);
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
  public Individuo<Integer> mutar(Individuo<Integer> individuo, double prob, int mut) {
      if (ThreadLocalRandom.current().nextDouble() < prob) {
        switch(mut) {
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
        }
      }
    return this;
  }
  
  private Individuo<Integer> mutaRotacionHeu(Individuo<Integer> individuo){
	  int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
      int punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
      
      while(punto2 == punto1)
    	  punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
      

      int ini = Math.min(punto1, punto2);
      int fin = Math.max(punto1, punto2);
      
      int rotaciones = this.tamTotal - (fin - ini);
      int bestP = 0;
	  double bestFit = Double.MAX_VALUE;
	  
	  for(int it = 0; it < rotaciones; ++it) {
		  int i = fin + 1;
		  int auxAnterior = individuo.getCromosoma()[ini - 1];
		  int auxNuevo;
		  while(i != ini) {
			  auxNuevo = individuo.getCromosoma()[i];
			  individuo.getCromosoma()[i] = auxAnterior;
			  
			  auxAnterior = auxNuevo;
			  ++i;
			  if(i==individuo.getCromosoma().length) i = 0;
		  }
		  if(individuo.fitness() < bestFit) {
			  bestFit = individuo.fitness();
			  bestP = it;
		  }
	  }
	  
	  for(int it = 0; it < bestP; ++it) {
		  int i = fin + 1;
		  int auxAnterior = individuo.getCromosoma()[ini - 1];
		  int auxNuevo;
		  while(i != ini) {
			  auxNuevo = individuo.getCromosoma()[i];
			  individuo.getCromosoma()[i] = auxAnterior;
			  
			  auxAnterior = auxNuevo;
			  ++i;
			  if(i==individuo.getCromosoma().length) i = 0;
		  }
	  }
	  
	  return this;
  }
  
  private Individuo<Integer> mutaHeuristica(Individuo<Integer> individuo){
	  ArrayList<Integer> puntos = new ArrayList<Integer>();
	  ArrayList<Integer> elementos = new ArrayList<Integer>();
	  int[] indexes = new int[nDesplazamientos];
	  
	  for(int i = 0; i < nDesplazamientos; ++i) {
		  indexes[i] = 0;
		  int aux = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
		  while(puntos.contains(aux)) {
			  aux = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
		  }
		  puntos.add(aux);
		  elementos.add(individuo.getCromosoma()[puntos.get(i)]);
	  }
	  
	  ArrayList<ArrayList<Integer>> permutaciones = new ArrayList<ArrayList<Integer>>();
	  permutaciones.add(new ArrayList<Integer>(elementos));
	  int i = 0;
	  while(i < nDesplazamientos) {
		  if(indexes[i] < i) {
			  Collections.swap(elementos, i % 2 == 0 ?  0: indexes[i], i);
			  permutaciones.add(new ArrayList<Integer>(elementos));
			  ++indexes[i];
			  i = 0;
		  }
		  else {
			  indexes[i] = 0;
			  ++i;
		  }
	  }
	  
	  int bestP = 0;
	  double bestFit = Double.MAX_VALUE;
	  
	  for(int j = 0; j < permutaciones.size(); ++j) {
		  for(int x = 0; x < permutaciones.get(j).size(); ++x) {
			  individuo.getCromosoma()[puntos.get(x)] = permutaciones.get(j).get(x);
		  }
		  if(individuo.fitness() < bestFit) {
			  bestFit = individuo.fitness();
			  bestP = j;
		  }
	  }
	  
	  for(int x = 0; x < permutaciones.get(bestP).size(); ++x) {
		  individuo.getCromosoma()[puntos.get(x)] = permutaciones.get(bestP).get(x);
	  }
	  
	  return this;
  }
  
  private Individuo<Integer> mutaInversion(Individuo<Integer> individuo){
	  int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
      int punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
      
      while(punto2 == punto1)
    	  punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length);
      

      int ini = Math.min(punto1, punto2);
      int fin = Math.max(punto1, punto2);
      
      Integer[] aux = new Integer[fin-ini];
      int itAux = 0;
      
      for(int i = fin; i > ini; --i) {
    	  aux[itAux] = individuo.getCromosoma()[i];
    	  ++itAux;
      }
      
      itAux = 0;
      for(int i = ini + 1; i <= fin; ++i) {
    	  individuo.getCromosoma()[i] = aux[itAux];
    	  ++itAux;
      }
      
	  
	  return this;
  }
  
  private Individuo<Integer> mutaInsercion(Individuo<Integer> individuo){
	  for(int i = 0; i < nDesplazamientos; ++i) {
		  int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
		  int punto2 = ThreadLocalRandom.current().nextInt(0, punto1);
		  Integer aux = Integer.valueOf(individuo.getCromosoma()[punto1]);
		  for(int j = punto1; j > punto2; --j) {
			  individuo.getCromosoma()[j] = individuo.getCromosoma()[j - 1]; 
		  }
		  individuo.getCromosoma()[punto2] = Integer.valueOf(aux);
	  }
	  
	  return this;
  }
  
  private Individuo<Integer> mutaIntercambio(Individuo<Integer> individuo){
	  int punto1 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
      int punto2 = ThreadLocalRandom.current().nextInt(1, individuo.getCromosoma().length - 1);
      
      Integer aux = Integer.valueOf(individuo.getCromosoma()[punto1]);
      individuo.getCromosoma()[punto1] = Integer.valueOf(individuo.getCromosoma()[punto2]);
      individuo.getCromosoma()[punto2] = Integer.valueOf(aux);
	  
  return this;
  }

  /**
   * Gets the chromosome.
   *
   * @return el cromosoma
   */
  @Override
  public Integer[] getCromosoma() {
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
    return this.chromosome[index];
  }

  /**
   * Valor del individuo en su funcion.
   *
   * @return el valor
   */
  @Override
  public double getValor() {
    double aux = 0;
    
    // Incluimos a Madrid para el cálculo del fitness
    if(25 <= this.getFenotipo(0))
    	aux += _DIST[(int)this.getFenotipo(0)][25];
    else
    	aux += _DIST[25][(int)this.getFenotipo(0)];
    
    for (int i = 0; i < this.tamTotal - 2; i++) {
      int ciu1 = (int) this.getFenotipo(i);
      int ciu2 = (int) this.getFenotipo(i + 1);
      if (ciu1 <= ciu2)
        aux += _DIST[ciu2][ciu1];
      else
        aux += _DIST[ciu1][ciu2];

    }
    
    // Madrid por último
    if(25 <= this.getFenotipo(this.tamTotal - 1))
    	aux += _DIST[(int)this.getFenotipo(this.tamTotal - 1)][25];
    else
    	aux += _DIST[25][(int)this.getFenotipo(this.tamTotal - 1)];
    
    return aux;
  }

  /**
   * Copy individuo.
   *
   * @return the individuo
   */
  @Override
  public Individuo<Integer> copyIndividuo() {
    return new IndividuoPractica2(this.chromosome, this.precision);
  }

  /**
   * Comprueba si es una funcion de maximizacion.
   *
   * @return true
   */
  @Override
  public boolean isMax() {
    return false;
  }

  @Override
  public void corregir() {
    this.chromosome[this.numFenotipos - 1] = this.chromosome[0];
  }
  
  @Override
  public String toString() {
    String aux = "Cromosoma: ";
    
    String ciudades[] = {"Albacete", "Alicante", "Almeria", "Avila", "Badajoz", "Barcelona", "Bilbao", "Burgos", "Caceres", "Cadiz", "Castellon", "Ciudad Real", "Cordoba", "A Coruña", "Cuenca", "Gerona", "Granada", "Guadalajara", "Huelva", "Huesca", "Jaen", "Leon", "Lerida", "Logroño", "Lugo", "Madrid", "Malaga", "Murcia"};
    
    aux += "Madrid";
    
    for(Integer i : chromosome) {
      aux += " " + ciudades[i] + " ";
    }
    aux += "Madrid\n";
    

    aux += "Fitness: " + this.fitness();
    
    return aux;
  }


}
