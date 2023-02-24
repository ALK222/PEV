package g02;

import javax.swing.JFrame;
import org.math.plot.Plot2DPanel;
import g02.Selections.RouletteSelection;
import g02.algoritmogenetico.AlgoritmoGenetico;
import g02.individuals.Individuo;
import g02.individuals.IndividuoFuncion1;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {

    IndividuoFuncion1 pob[] = new IndividuoFuncion1[100];

    for (int i = 0; i < 100; i++) {
      pob[i] = new IndividuoFuncion1(0.001);
    }

    int gens = 100;

    RouletteSelection rs = new RouletteSelection(50, pob);

    for (int i = 0; i < gens; ++i) {
      pob = rs.run();
    }

    AlgoritmoGenetico alg = new AlgoritmoGenetico(10, 2, 0.1, 0.1, 2, 0.001);
    alg.run();


    double[] generaciones = {1, 2, 3};
    double[] fitness = alg.getMejores();
    double[] media = alg.getMedias();
    // create your PlotPanel (you can use it as a JPanel)
    Plot2DPanel plot = new Plot2DPanel();
    // define the legend position
    plot.addLegend("SOUTH");
    // add a line plot to the PlotPanel
    plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
    plot.addLinePlot("MEDIAS", generaciones, media);
    // put the PlotPanel in a JFrame like a JPanel
    JFrame frame = new JFrame("a plot panel");
    frame.setSize(600, 600);
    frame.setContentPane(plot);
    frame.setVisible(true);

  }

  private static void PruebaPlot() {
    double[] generaciones = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    double[] fitness = {12, 25, 32, 45, 65, 67, 70, 72, 73, 76};
    // create your PlotPanel (you can use it as a JPanel)
    Plot2DPanel plot = new Plot2DPanel();
    // define the legend position
    plot.addLegend("SOUTH");
    // add a line plot to the PlotPanel
    plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
    // put the PlotPanel in a JFrame like a JPanel
    JFrame frame = new JFrame("a plot panel");
    frame.setSize(600, 600);
    frame.setContentPane(plot);
    frame.setVisible(true);
  }

}
