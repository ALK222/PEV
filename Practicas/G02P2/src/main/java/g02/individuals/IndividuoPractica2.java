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
	
	private final static int[][] _DIST = {
			{},// Albacete
			{171}, // Alicante
			{369,	294},  //
			{366,	537,	633},
			{525,	696,	604,	318},
			{540,	515,	809,	717,	1022},
			{646,	817,	958,	401,	694,	620},
			{488,	659,	800,	243,	536,	583,	158},
			{504,	675,	651,	229,	89,	918,	605,	447},
			{617,	688,	484,	618,	342,	1284,	1058,	900,	369},
			{256,	231,	525,	532,	805,	284,	607,	524,	701,	873},
			{207,	378,	407,	256,	318,	811,	585,	427,	324,	464,	463},
			{354,	525,	332,	457,	272,	908,	795,	637,	319,	263,	610,	201},
			{860,	1031,	1172,	538,	772,	1118,	644,	535,	683,	1072,	1026,	799,	995},
			{142,	313,	511,	282,	555,	562,	562,	404,	451,	708,	305,	244,	445,	776},
			{640,	615,	909,	817,	1122,	100,	720,	683,	1018,	1384,	384,	911,	1008,	1218,	662},
			{363,	353,	166,	534,	438,	868,	829,	671,	485,	335,	584,	278,	166,	1043,	479,	968},
			{309,	480,	621,	173,	459,	563,	396,	238,	355,	721,	396,	248,	458,	667,	486,	663,	492},
			{506,	703,	516,	552,	251,	1140,	939,	781,	323,	219,	856,	433,	232,	1006,	677,	1240,	350,	690},
			{495,	570,	830,	490,	798,	274,	322,	359,	694,	1060,	355,	587,	797,	905,	406,	374,	831,	339,	1029},
			{264,	415,	228,	435,	376,	804,	730,	572,	423,	367,	520,	179,	104,	944,	380,	904,	99,	393,	336,	732},
			{584,	855,	896,	255,	496,	784,	359,	201,	407,	796,	725,	511,	733,	334,	500,	884,	761,	391,	730,	560,	668},
			{515,	490,	802,	558,	866,	156,	464,	427,	762,	1128,	259,	655,	865,	973,	472,	256,	861,	407,	1097,	118,	779,	628},
			{578,	653,	899,	358,	676,	468,	152,	115,	595,	999,	455,	526,	736,	650,	464,	568,	770,	278,	968,	244,	671,	316,	312},
			{762,	933,	1074,	440,	674,	1020,	546,	437,	585,	974,	928,	696,	897,	98,	678,	1120,	945,	569,	908,	807,	846,	236,	875,	352},
			{251,	422,	563,	115,	401,	621,	395,	237,	297,	663,	417,	190,	400,	609,	167,	721,	434,	58,	632,	397,	335,	333,	465,	336,	551},
			{473,	482,	219,	644,	436,	997,	939,	781,	506,	265,	713,	388,	187,	1153,	615,	1097,	129,	602,	313,	941,	209,	877,	1009,	880,	1055,	544},
			{150,	75,	219,	516,	675,	590,	796,	638,	654,	613,	306,	357,	444,	1010,	292,	690,	278,	459,	628,	611,	340,	734,	583,	694,	912,	401,	407}
			};


  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param precision precision de la codificacion
   */
  public IndividuoPractica2(double precision) {
	  this.precision = precision;
	  this.tamTotal = _DIST.length;
	  this.numFenotipos = _DIST.length;
	  
	  this.chromosome = new Integer[this.tamTotal];
	  ArrayList<Integer> ciudades = new ArrayList<Integer>(Arrays.asList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,26,27));
	  Collections.shuffle(ciudades);
	  this.chromosome[0] = 25;
	    for (int i = 1; i < this.tamTotal - 1; i++) {
	    	this.chromosome[i] = ciudades.get(i);
	    }
	    this.chromosome[this.tamTotal - 1] = this.chromosome[0];

  }

  /**
   * Instantiates a new individuo funcion 1.
   *
   * @param chromosome cromosoma dado
   * @param precision precision de la codificacion
   */
  public IndividuoPractica2(Integer[] _chromosome, double precision) {
	this.precision = precision;
	this.tamTotal = _DIST.length;
	this.numFenotipos = _DIST.length;

    this.chromosome = new Integer[tamTotal];
    for (int i = 0; i < tamTotal; i++) {
      this.chromosome[i] = _chromosome[i];
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
  public Individuo<Integer> mutar(Individuo<Integer> individuo, double prob) {
	  for(int i = 0; i < this.tamTotal; i++) {
		  if (ThreadLocalRandom.current().nextDouble() < prob) {

		      //chromosome[i] = !chromosome[i];
	  }
    }

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
    for(int i = 0; i < this.tamTotal - 1; i++) {
    	int ciu1 = (int)this.getFenotipo(i);
    	int ciu2 = (int)this.getFenotipo(i+1);
    	if(ciu1 < ciu2)
    		aux += _DIST[ciu2][ciu1];
    	else
    		aux += _DIST[ciu1][ciu2];
    	
    }
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


}
