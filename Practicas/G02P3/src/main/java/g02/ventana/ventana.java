package g02.ventana;

import g02.Selections.RankingSelection;
import g02.Selections.RestosSelection;
import g02.Selections.RouletteSelection;
import g02.Selections.Selection;
import g02.Selections.StochasticSelection;
import g02.Selections.TournamentDeterministicSelection;
import g02.Selections.TournamentProbabilisticSelection;
import g02.Selections.TruncateSelection;
import g02.algoritmogenetico.AlgoritmoGenetico;
import g02.cruces.CruceP3;
import g02.cruces.Cruces;
import g02.individuals.Cromosoma;
import g02.individuals.Individuo;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import org.math.plot.Plot2DPanel;
import java.awt.Font;
import javax.swing.JCheckBox;

public class ventana extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField numGeneraciones;
  private JTextField tamPobMin;
  private JTextField pProbTorneo;
  private JTextField precision;
  private JTextField pMutacionMin;
  private JTextField pCruceMin;
  private JTextField pElitismo;
  private JTextField nDims;
  private JTextField tamPobMax;
  private JTextField pMutacionMax;
  private JTextField pCruceMax;
  private JTextField nEjecuciones;
  private JTextField ncruces;

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          ventana frame = new ventana();
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public ventana() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 1520, 725);
    contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);

    numGeneraciones = new JTextField();
    numGeneraciones.setText("100");
    numGeneraciones.setBounds(131, 51, 86, 20);
    contentPane.add(numGeneraciones);
    numGeneraciones.setColumns(10);

    JLabel lblNewLabel = new JLabel("Num. Generaciones");
    lblNewLabel.setBounds(10, 54, 111, 14);
    contentPane.add(lblNewLabel);

    tamPobMin = new JTextField();
    tamPobMin.setText("100");
    tamPobMin.setColumns(10);
    tamPobMin.setBounds(104, 82, 39, 20);
    contentPane.add(tamPobMin);

    JLabel lblNumGenes = new JLabel("Tam. Población");
    lblNumGenes.setBounds(10, 85, 111, 14);
    contentPane.add(lblNumGenes);

    JLabel lblNumPasos = new JLabel("Prob Torneo");
    lblNumPasos.setBounds(10, 116, 111, 14);
    contentPane.add(lblNumPasos);

    pProbTorneo = new JTextField();
    pProbTorneo.setText("0.5");
    pProbTorneo.setColumns(10);
    pProbTorneo.setBounds(131, 113, 86, 20);
    contentPane.add(pProbTorneo);

    /*
     * precision = new JTextField(); precision.setText("0.001"); precision.setColumns(10);
     * precision.setBounds(131, 141, 86, 20); contentPane.add(precision);
     * 
     * JLabel lblProfMaxInicial = new JLabel("Precision"); lblProfMaxInicial.setBounds(10, 144, 111,
     * 14); contentPane.add(lblProfMaxInicial);
     */

    pMutacionMin = new JTextField();
    pMutacionMin.setText("0.02");
    pMutacionMin.setColumns(10);
    pMutacionMin.setBounds(104, 172, 39, 20);
    contentPane.add(pMutacionMin);

    JLabel lblPMutacion = new JLabel("P. Mutacion");
    lblPMutacion.setBounds(10, 175, 111, 14);
    contentPane.add(lblPMutacion);

    JLabel lblPCruce = new JLabel("P. Cruce");
    lblPCruce.setBounds(10, 206, 111, 14);
    contentPane.add(lblPCruce);

    pCruceMin = new JTextField();
    pCruceMin.setText("0.4");
    pCruceMin.setColumns(10);
    pCruceMin.setBounds(104, 203, 39, 20);
    contentPane.add(pCruceMin);

    JComboBox mSeleccion = new JComboBox();
    mSeleccion.setModel(new DefaultComboBoxModel(new String[] {"ruleta", "torneo 1", "torneo 2",
        "estocástico", "truncamiento", "restos", "Ranking"}));
    mSeleccion.setBounds(131, 234, 86, 22);
    contentPane.add(mSeleccion);

    JLabel lblMSeleccion = new JLabel("M. Seleccion");
    lblMSeleccion.setBounds(10, 238, 111, 14);
    contentPane.add(lblMSeleccion);

    JLabel lblMMutacion = new JLabel("M. Mutacion");
    lblMMutacion.setBounds(10, 271, 111, 14);
    contentPane.add(lblMMutacion);

    JComboBox mMutacion = new JComboBox();
    mMutacion.setModel(new DefaultComboBoxModel(
        new String[] {"Terminal", "Funcional", "SubArbol", "Permutacion", "Expansion"}));
    mMutacion.setBounds(131, 267, 86, 22);
    contentPane.add(mMutacion);

    pElitismo = new JTextField();
    pElitismo.setText("0.01");
    pElitismo.setColumns(10);
    pElitismo.setBounds(131, 296, 86, 20);
    contentPane.add(pElitismo);

    JLabel lblElitismo = new JLabel("Elitismo");
    lblElitismo.setBounds(10, 299, 111, 14);
    contentPane.add(lblElitismo);

    JInternalFrame internalFrame = new JInternalFrame("Resultado");
    internalFrame.setBounds(227, 51, 631, 624);
    contentPane.add(internalFrame);
    internalFrame.setVisible(true);

    JComboBox individuox = new JComboBox();
    individuox.setModel(new DefaultComboBoxModel(new String[] {"Inicializacion Completa", "Inicializacion Creciente", "Inicializacion Ramped & Half"}));
    individuox.setBounds(250, 11, 353, 22);
    contentPane.add(individuox);

    nDims = new JTextField();
    nDims.setText("2");
    nDims.setColumns(10);
    nDims.setBounds(131, 353, 86, 20);
    //contentPane.add(nDims);

    JLabel lblDimensiones = new JLabel("Dimensiones");
    lblDimensiones.setBounds(10, 356, 111, 14);
    //contentPane.add(lblDimensiones);

    JTextPane resultsPane = new JTextPane();
    resultsPane.setBounds(12, 11, 207, 118);
    contentPane.add(resultsPane);

    JScrollPane scrollPane = new JScrollPane(resultsPane);
    scrollPane.setBounds(10, 453, 207, 118);
    contentPane.add(scrollPane);

    JLabel lblResultados = new JLabel("Resultados");
    lblResultados.setBounds(10, 439, 111, 14);
    contentPane.add(lblResultados);

    tamPobMax = new JTextField();
    tamPobMax.setText("300");
    tamPobMax.setColumns(10);
    tamPobMax.setBounds(166, 82, 39, 20);
    contentPane.add(tamPobMax);

    JLabel lblMin = new JLabel("Min");
    lblMin.setFont(new Font("Tahoma", Font.PLAIN, 8));
    lblMin.setBounds(146, 85, 39, 14);
    contentPane.add(lblMin);

    JLabel lblMax = new JLabel("Max");
    lblMax.setFont(new Font("Tahoma", Font.PLAIN, 8));
    lblMax.setBounds(208, 85, 39, 14);
    contentPane.add(lblMax);

    pMutacionMax = new JTextField();
    pMutacionMax.setText("0.08");
    pMutacionMax.setColumns(10);
    pMutacionMax.setBounds(166, 172, 39, 20);
    contentPane.add(pMutacionMax);

    JLabel lblMin_1 = new JLabel("Min");
    lblMin_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
    lblMin_1.setBounds(146, 175, 39, 14);
    contentPane.add(lblMin_1);

    JLabel lblMax_1 = new JLabel("Max");
    lblMax_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
    lblMax_1.setBounds(208, 175, 39, 14);
    contentPane.add(lblMax_1);

    pCruceMax = new JTextField();
    pCruceMax.setText("0.8");
    pCruceMax.setColumns(10);
    pCruceMax.setBounds(166, 203, 39, 20);
    contentPane.add(pCruceMax);

    JLabel lblMin_1_1 = new JLabel("Min");
    lblMin_1_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
    lblMin_1_1.setBounds(146, 206, 39, 14);
    contentPane.add(lblMin_1_1);

    JLabel lblMax_1_1 = new JLabel("Max");
    lblMax_1_1.setFont(new Font("Tahoma", Font.PLAIN, 8));
    lblMax_1_1.setBounds(208, 206, 39, 14);
    contentPane.add(lblMax_1_1);

    nEjecuciones = new JTextField();
    nEjecuciones.setText("1");
    nEjecuciones.setColumns(10);
    nEjecuciones.setBounds(131, 382, 86, 20);
    contentPane.add(nEjecuciones);
    
    JInternalFrame resultsp3 = new JInternalFrame("Funciones");
    resultsp3.setBounds(868, 51, 607, 624);
    contentPane.add(resultsp3);
    resultsp3.setVisible(true);


    JLabel lblNumEjecuciones = new JLabel("Num. Ejecuciones");
    lblNumEjecuciones.setBounds(10, 385, 111, 14);
    contentPane.add(lblNumEjecuciones);
    
    JLabel lblbloating = new JLabel("Control Bloating");
    lblbloating.setBounds(12, 358, 109, 15);
    contentPane.add(lblbloating);
    
    JCheckBox chckbxBloating = new JCheckBox("");
    chckbxBloating.setBounds(131, 353, 86, 23);
    contentPane.add(chckbxBloating);
    
    JLabel lblRegeneracinPob = new JLabel("Regeneración Pob.");
    lblRegeneracinPob.setBounds(12, 328, 109, 15);
    contentPane.add(lblRegeneracinPob);
    
    JCheckBox regenbool = new JCheckBox("");
    regenbool.setBounds(131, 323, 86, 23);
    contentPane.add(regenbool);
    
    JLabel lblNCruces = new JLabel("N. Cruces");
    lblNCruces.setBounds(10, 144, 111, 14);
    contentPane.add(lblNCruces);
    
    ncruces = new JTextField();
    ncruces.setText("3");
    ncruces.setColumns(10);
    ncruces.setBounds(131, 141, 86, 20);
    contentPane.add(ncruces);

    JButton btnNewButton = new JButton("Iniciar");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int tamPoblacionMin = Integer.parseInt(tamPobMin.getText());
        int tamPoblacionMax = Integer.parseInt(tamPobMax.getText());
        int nGeneraciones = Integer.parseInt(numGeneraciones.getText());
        double probCMin = Double.parseDouble(pCruceMin.getText());
        double probCMax = Double.parseDouble(pCruceMax.getText());
        double probMMin = Double.parseDouble(pMutacionMin.getText());
        double probMMax = Double.parseDouble(pMutacionMax.getText());
        double prec = 0;
        double elitismo = Double.parseDouble(pElitismo.getText());
        double probTorneo = Double.parseDouble(pProbTorneo.getText());
        int dimensiones = Integer.parseInt(nDims.getText());
        int ejecuciones = Integer.parseInt(nEjecuciones.getText());

        int individuo = individuox.getSelectedIndex();

        

        boolean isMax = false;
        if (individuo == 0) {
          isMax = false;
        }
        Selection<?> mSel;
        Cruces<?> mCru;
        mCru = new CruceP3(Integer.parseInt(ncruces.getText()));
        int mutacion = mMutacion.getSelectedIndex();
        AlgoritmoGenetico<?> alg;

        if (ejecuciones == 1) {
          switch (mSeleccion.getSelectedIndex()) {
            case 0:
              mSel = new RouletteSelection<Cromosoma>(tamPoblacionMin, null);
              break;
            case 1:
              mSel = new TournamentDeterministicSelection<Cromosoma>(tamPoblacionMin, null, isMax);
              break;
            case 2:
              mSel = new TournamentProbabilisticSelection<Cromosoma>(tamPoblacionMin, null,
                  probTorneo, isMax);
              break;
            case 3:
              mSel = new StochasticSelection<Cromosoma>(tamPoblacionMin, null);
              break;
            case 4:
              mSel = new TruncateSelection<Cromosoma>(tamPoblacionMin, null, 0.5);
              break;
            case 5:
              mSel = new RestosSelection<Cromosoma>(5, null);
              break;
            case 6:
              mSel = new RankingSelection<Cromosoma>(tamPoblacionMin, null);
              break;
            default:
              mSel = new RouletteSelection<Cromosoma>(tamPoblacionMin, null);
              break;
          }
          Individuo<Cromosoma> mejor;
          alg = new AlgoritmoGenetico(tamPoblacionMin, nGeneraciones, probCMin, probMMin, prec,
              mSel, mCru, elitismo, mutacion, chckbxBloating.isSelected(), regenbool.isSelected());
          try {
            mejor = (Individuo<Cromosoma>) alg.run(individuo, dimensiones);
            System.out.println(mejor.fitness());
            resultsPane.setText(mejor.toString());
            
            double[] valorX = new double[101];
            double[] valor1 = new double[101];
            double[] valor2 = new double[101];
            
            // Resultados esperados de la funcion
            for(int i = 0; i < 101; ++i) {
            	valorX[i] = (-1.0) + ((2.0/100.0) * i);
            	valor1[i] = Math.pow(valorX[i], 4) + Math.pow(valorX[i], 3) + Math.pow(valorX[i], 2) + valorX[i] + 1;
            	valor2[i] = mejor.getCromosoma().updateFitness(valorX[i], mejor.getCromosoma().getArbol());
            }
            
            Plot2DPanel resultadosp3 = new Plot2DPanel();
            resultadosp3.addLegend("SOUTH");
            resultadosp3.addLinePlot("VALORES ESPERADOS", valorX, valor1);
            resultadosp3.addLinePlot("VALORES OBTENIDOS", valorX, valor2);
            resultsp3.setContentPane(resultadosp3);
            
          } catch (Exception ex) {
            ex.printStackTrace();
          }

          double[] generaciones = new double[nGeneraciones];
          for (int i = 0; i < nGeneraciones; i++) {
            generaciones[i] = i;
          }
          double[] fitness = alg.getMejores();
          double[] media = alg.getMedias();
          double[] best = alg.getMejorSiempre();

          // create your PlotPanel (you can use it as a JPanel)
          Plot2DPanel plot = new Plot2DPanel();
          // define the legend position
          plot.addLegend("SOUTH");
          // add a line plot to the PlotPanel
          plot.addLinePlot("MEJOR ABSOLUTO", generaciones, best);
          plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
          plot.addLinePlot("MEDIAS", generaciones, media);
          // put the PlotPanel in a JFrame like a JPanel
          internalFrame.setContentPane(plot);
          
          
          
        } else {
          Integer[] tams;
          if (tamPoblacionMax != tamPoblacionMin) {
            tams = new Integer[ejecuciones];
            int auxTams = (tamPoblacionMax - tamPoblacionMin) / (ejecuciones - 1);
            for (int j = 0; j < ejecuciones; ++j) {
              tams[j] = tamPoblacionMin + (auxTams * j);
            }
          } else {
            tams = new Integer[1];
            tams[0] = tamPoblacionMin;
          }

          Double[] mutaciones;
          if (probMMax != probMMin) {
            mutaciones = new Double[ejecuciones];
            double auxMut = (probMMax - probMMin) / (double) (ejecuciones - 1);
            for (int j = 0; j < ejecuciones; ++j) {
              mutaciones[j] = probMMin + (auxMut * j);
            }
          } else {
            mutaciones = new Double[1];
            mutaciones[0] = probMMin;
          }

          Double[] cruces;
          if (probCMax != probCMin) {
            cruces = new Double[ejecuciones];
            double auxCru = (probCMax - probCMin) / (double) (ejecuciones - 1);
            for (int j = 0; j < ejecuciones; ++j) {
              cruces[j] = probCMin + (auxCru * j);
            }
          } else {
            cruces = new Double[1];
            cruces[0] = probCMin;
          }

          // ArrayList<Plot2DPanel> plots = new ArrayList<Plot2DPanel>();
          ArrayList<Individuo<Cromosoma>> mejores = new ArrayList<Individuo<Cromosoma>>();

          Individuo<Cromosoma> mejorSiempre = null;
          double bestFit = Double.MAX_VALUE;
          int bestT = 0;
          int bestM = 0;
          int bestC = 0;

          for (int t = 0; t < tams.length; ++t) {
            for (int m = 0; m < mutaciones.length; ++m) {
              for (int c = 0; c < cruces.length; ++c) {
                switch (mSeleccion.getSelectedIndex()) {
                  case 0:
                    mSel = new RouletteSelection<Cromosoma>(tams[t], null);
                    break;
                  case 1:
                    mSel = new TournamentDeterministicSelection<Cromosoma>(tams[t], null, isMax);
                    break;
                  case 2:
                    mSel = new TournamentProbabilisticSelection<Cromosoma>(tams[t], null, probTorneo,
                        isMax);
                    break;
                  case 3:
                    mSel = new StochasticSelection<Cromosoma>(tams[t], null);
                    break;
                  case 4:
                    mSel = new TruncateSelection<Cromosoma>(tams[t], null, 0.5);
                    break;
                  case 5:
                    mSel = new RestosSelection<Cromosoma>(5, null);
                    break;
                  case 6:
                    mSel = new RankingSelection<Cromosoma>(tams[t], null);
                    break;
                  default:
                    mSel = new RouletteSelection<Cromosoma>(tams[t], null);
                    break;
                }

                Individuo<Cromosoma> mejor;
                alg = new AlgoritmoGenetico(tams[t], nGeneraciones, cruces[c], mutaciones[m], prec,
                    mSel, mCru, elitismo, mutacion, chckbxBloating.isSelected(), regenbool.isSelected());
                try {
                  mejor = (Individuo<Cromosoma>) alg.run(individuo, dimensiones);
                  System.out.println(mejor.fitness());

                  if (mejor.fitness() < bestFit) {
                    bestFit = mejor.fitness();
                    mejorSiempre = mejor;
                    bestT = t;
                    bestM = m;
                    bestC = c;
                  }

                  mejores.add(mejor);
                } catch (Exception ex) {
                  ex.printStackTrace();
                }
              }
            }
          }
          double[] valorX = new double[101];
          double[] valor1 = new double[101];
          double[] valor2 = new double[101];
          
          // Resultados esperados de la funcion
          for(int i = 0; i < 101; ++i) {
          	valorX[i] = (-1.0) + ((2.0/100.0) * i);
          	valor1[i] = Math.pow(valorX[i], 4) + Math.pow(valorX[i], 3) + Math.pow(valorX[i], 2) + valorX[i] + 1;
          	valor2[i] = mejorSiempre.getCromosoma().updateFitness(valorX[i], mejorSiempre.getCromosoma().getArbol());
          }
          
          Plot2DPanel resultadosp3 = new Plot2DPanel();
          resultadosp3.addLegend("SOUTH");
          resultadosp3.addLinePlot("VALORES ESPERADOS", valorX, valor1);
          resultadosp3.addLinePlot("VALORES OBTENIDOS", valorX, valor2);
          resultsp3.setContentPane(resultadosp3);

          String txt = "Mejor ejecución \nPoblación: " + tams[bestT] + "\nMutación: "
              + mutaciones[bestM] + "\nCruce: " + cruces[bestC] + "\nFitness: " + bestFit;
          resultsPane.setText(txt);

          double[] excs = new double[(int) (tams.length * mutaciones.length * cruces.length)];
          double[] fits = new double[(int) (tams.length * mutaciones.length * cruces.length)];
          for (int i = 0; i < (int) (tams.length * mutaciones.length * cruces.length); i++) {
            excs[i] = i;
            fits[i] = mejores.get(i).fitness();
          }
          Plot2DPanel plot = new Plot2DPanel();
          // define the legend position
          plot.addLegend("SOUTH");
          // add a line plot to the PlotPanel
          plot.addLinePlot("MEJOR DE LA EJECUCIÓN", excs, fits);
          internalFrame.setContentPane(plot);
        }
      }
    });
    btnNewButton.setBounds(45, 413, 140, 23);
    contentPane.add(btnNewButton);
    
    
    
    
    
    
    
    


  }
}
