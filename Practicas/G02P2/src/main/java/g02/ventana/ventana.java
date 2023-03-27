package g02.ventana;

import g02.Selections.RestosSelection;
import g02.Selections.RouletteSelection;
import g02.Selections.Selection;
import g02.Selections.StochasticSelection;
import g02.Selections.TournamentDeterministicSelection;
import g02.Selections.TournamentProbabilisticSelection;
import g02.Selections.TruncateSelection;
import g02.algoritmogenetico.AlgoritmoGenetico;
import g02.cruces.CruceAritmetico;
import g02.cruces.CruceBLXAlpha;
import g02.cruces.CruceCX;
import g02.cruces.CruceERX;
import g02.cruces.CruceMonopunto;
import g02.cruces.CruceOX;
import g02.cruces.CruceOXPP;
import g02.cruces.CrucePMX;
import g02.cruces.CruceUniforme;
import g02.cruces.Cruces;
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
    setBounds(100, 100, 884, 725);
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

    precision = new JTextField();
    precision.setText("0.001");
    precision.setColumns(10);
    precision.setBounds(131, 141, 86, 20);
    contentPane.add(precision);

    JLabel lblProfMaxInicial = new JLabel("Precision");
    lblProfMaxInicial.setBounds(10, 144, 111, 14);
    contentPane.add(lblProfMaxInicial);

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
    mSeleccion.setModel(new DefaultComboBoxModel(
        new String[] {"ruleta", "torneo 1", "torneo 2", "estocástico", "truncamiento", "restos"}));
    mSeleccion.setBounds(131, 234, 86, 22);
    contentPane.add(mSeleccion);

    JLabel lblMSeleccion = new JLabel("M. Seleccion");
    lblMSeleccion.setBounds(10, 238, 111, 14);
    contentPane.add(lblMSeleccion);

    JLabel lblMCruce = new JLabel("M. Cruce");
    lblMCruce.setBounds(10, 271, 111, 14);
    contentPane.add(lblMCruce);

    JComboBox mCruce = new JComboBox();
    mCruce.setModel(new DefaultComboBoxModel(
        new String[] {"OX","OXPP", "PMX", "CX", "ERX"}));
    mCruce.setSelectedIndex(4);
    mCruce.setBounds(131, 267, 86, 22);
    contentPane.add(mCruce);

    JLabel lblMMutacion = new JLabel("M. Mutacion");
    lblMMutacion.setBounds(10, 300, 111, 14);
    contentPane.add(lblMMutacion);

    JComboBox mMutacion = new JComboBox();
    mMutacion.setModel(new DefaultComboBoxModel(new String[] {"Intercambio", "Inserción", "Inversion", "Heurística"}));
    mMutacion.setBounds(131, 296, 86, 22);
    contentPane.add(mMutacion);

    pElitismo = new JTextField();
    pElitismo.setText("0.1");
    pElitismo.setColumns(10);
    pElitismo.setBounds(131, 325, 86, 20);
    contentPane.add(pElitismo);

    JLabel lblElitismo = new JLabel("Elitismo");
    lblElitismo.setBounds(10, 328, 111, 14);
    contentPane.add(lblElitismo);

    JInternalFrame internalFrame = new JInternalFrame("Resultado");
    internalFrame.setBounds(227, 51, 631, 624);
    contentPane.add(internalFrame);
    internalFrame.setVisible(true);

    JComboBox individuox = new JComboBox();
    individuox.setModel(new DefaultComboBoxModel(new String[] {"Individuo Practica 2"}));
    individuox.setBounds(250, 11, 353, 22);
    contentPane.add(individuox);

    nDims = new JTextField();
    nDims.setText("2");
    nDims.setColumns(10);
    nDims.setBounds(131, 353, 86, 20);
    contentPane.add(nDims);

    JLabel lblDimensiones = new JLabel("Dimensiones");
    lblDimensiones.setBounds(10, 356, 111, 14);
    contentPane.add(lblDimensiones);

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
    
    JLabel lblNumEjecuciones = new JLabel("Num. Ejecuciones");
    lblNumEjecuciones.setBounds(10, 385, 111, 14);
    contentPane.add(lblNumEjecuciones);

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
        double prec = Double.parseDouble(precision.getText());
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
          int mutacion = mMutacion.getSelectedIndex();
          AlgoritmoGenetico<?> alg;

            switch (mCruce.getSelectedIndex()) {
              case 0:
                mCru = new CruceOX();
                break;
              case 1:
                  mCru = new CruceOXPP();
                  break;
              case 2:
                mCru = new CrucePMX();
                break;
              case 3:
                  mCru = new CruceCX();
                  break;
              case 4:
                  mCru = new CruceERX();
                  break;
              default:
                mCru = new CruceOX();
                break;
            }
            
            if(ejecuciones == 1) {
            	switch (mSeleccion.getSelectedIndex()) {
                case 0:
                  mSel = new RouletteSelection<Boolean>(tamPoblacionMin, null);
                  break;
                case 1:
                  mSel = new TournamentDeterministicSelection<Boolean>(tamPoblacionMin, null, isMax);
                  break;
                case 2:
                  mSel = new TournamentProbabilisticSelection<Boolean>(tamPoblacionMin, null, probTorneo,
                      isMax);
                  break;
                case 3:
                  mSel = new StochasticSelection<Boolean>(tamPoblacionMin, null);
                  break;
                case 4:
                  mSel = new TruncateSelection<Boolean>(tamPoblacionMin, null, 0.5);
                  break;
                case 5:
                  mSel = new RestosSelection<Boolean>(5, null);
                  break;
                default:
                  mSel = new RouletteSelection<Boolean>(tamPoblacionMin, null);
                  break;
              }
            	Individuo<Boolean> mejor;
                alg = new AlgoritmoGenetico(tamPoblacionMin, nGeneraciones, probCMin, probMMin, prec, mSel, mCru,
                    elitismo, mutacion);
                try {
                  mejor = (Individuo<Boolean>) alg.run(individuo, dimensiones);
                  System.out.println(mejor.fitness());
                  resultsPane.setText(mejor.toString());
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
            }
            else {
            	Integer[] tams = new Integer[ejecuciones];
                int auxTams = (tamPoblacionMax - tamPoblacionMin) / (ejecuciones - 1);
                Double[] mutaciones = new Double[ejecuciones];
                double auxMut = (probMMax - probMMin) / (double)(ejecuciones - 1);
                Double[] cruces = new Double[ejecuciones];
                double auxCru = (probCMax - probCMin) / (double)(ejecuciones - 1);
                //ArrayList<Plot2DPanel> plots = new ArrayList<Plot2DPanel>();
                ArrayList<Individuo<Boolean>> mejores = new ArrayList<Individuo<Boolean>>();
                
                for(int j = 0; j < ejecuciones; ++j) {
                	tams[j] = tamPoblacionMin + (auxTams * j);
                	cruces[j] = probCMin + (auxCru * j);
                	mutaciones[j] = probMMin + (auxMut * j);
                }
                
                double bestFit = Double.MAX_VALUE;
                int bestT = 0;
                int bestM = 0;
                int bestC = 0;
                
                for(int t = 0; t < ejecuciones; ++t) {
                	for(int m = 0; m < ejecuciones; ++m) {
                		for(int c = 0; c < ejecuciones; ++c) {
                			switch (mSeleccion.getSelectedIndex()) {
                            case 0:
                              mSel = new RouletteSelection<Boolean>(tams[t], null);
                              break;
                            case 1:
                              mSel = new TournamentDeterministicSelection<Boolean>(tams[t], null, isMax);
                              break;
                            case 2:
                              mSel = new TournamentProbabilisticSelection<Boolean>(tams[t], null, probTorneo,
                                  isMax);
                              break;
                            case 3:
                              mSel = new StochasticSelection<Boolean>(tams[t], null);
                              break;
                            case 4:
                              mSel = new TruncateSelection<Boolean>(tams[t], null, 0.5);
                              break;
                            case 5:
                              mSel = new RestosSelection<Boolean>(5, null);
                              break;
                            default:
                              mSel = new RouletteSelection<Boolean>(tams[t], null);
                              break;
                          }
                        	
                        	Individuo<Boolean> mejor;
                            alg = new AlgoritmoGenetico(tams[t], nGeneraciones, cruces[c], mutaciones[m], prec, mSel, mCru,
                                elitismo, mutacion);
                            try {
                              mejor = (Individuo<Boolean>) alg.run(individuo, dimensiones);
                              System.out.println(mejor.fitness());
                              
                              if(mejor.fitness() < bestFit) {
                            	  bestFit = mejor.fitness();
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
                
                String txt = "Mejor ejecución \nPoblación: " + tams[bestT] +
                		"\nMutación: " + mutaciones[bestM] + "\nCruce: " + cruces[bestC]+ "\nFitness: " + bestFit;
                resultsPane.setText(txt);
                
                double[] excs = new double[(int)Math.pow(ejecuciones, 3)];
                double[] fits = new double[(int)Math.pow(ejecuciones, 3)];
                for (int i = 0; i < (int)Math.pow(ejecuciones, 3); i++) {
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
