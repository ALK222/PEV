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
import g02.cruces.CruceMonopunto;
import g02.cruces.CruceOX;
import g02.cruces.CruceUniforme;
import g02.cruces.Cruces;
import g02.individuals.Individuo;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import org.math.plot.Plot2DPanel;

public class ventana extends JFrame {

  private static final long serialVersionUID = 1L;
  private JPanel contentPane;
  private JTextField numGeneraciones;
  private JTextField tamPob;
  private JTextField pProbTorneo;
  private JTextField precision;
  private JTextField pMutacion;
  private JTextField pCruce;
  private JTextField pElitismo;
  private JTextField nDims;

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

    tamPob = new JTextField();
    tamPob.setText("100");
    tamPob.setColumns(10);
    tamPob.setBounds(131, 82, 86, 20);
    contentPane.add(tamPob);

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

    pMutacion = new JTextField();
    pMutacion.setText("0.05");
    pMutacion.setColumns(10);
    pMutacion.setBounds(131, 172, 86, 20);
    contentPane.add(pMutacion);

    JLabel lblPMutacion = new JLabel("P. Mutacion");
    lblPMutacion.setBounds(10, 175, 111, 14);
    contentPane.add(lblPMutacion);

    JLabel lblPCruce = new JLabel("P. Cruce");
    lblPCruce.setBounds(10, 206, 111, 14);
    contentPane.add(lblPCruce);

    pCruce = new JTextField();
    pCruce.setText("0.6");
    pCruce.setColumns(10);
    pCruce.setBounds(131, 203, 86, 20);
    contentPane.add(pCruce);

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
        new String[] {"monopunto", "uniforme", "aritmético", "BLX-alpha", "OX"}));
    mCruce.setBounds(131, 267, 86, 22);
    contentPane.add(mCruce);

    JLabel lblMMutacion = new JLabel("M. Mutacion");
    lblMMutacion.setBounds(10, 300, 111, 14);
    contentPane.add(lblMMutacion);

    JComboBox mMutacion = new JComboBox();
    mMutacion.setModel(new DefaultComboBoxModel(new String[] {"básica"}));
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
    resultsPane.setBounds(10, 451, 207, 73);
    contentPane.add(resultsPane);

    JButton btnNewButton = new JButton("Iniciar");
    btnNewButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        int tamPoblacion = Integer.parseInt(tamPob.getText());
        int nGeneraciones = Integer.parseInt(numGeneraciones.getText());
        double probC = Double.parseDouble(pCruce.getText());
        double probM = Double.parseDouble(pMutacion.getText());
        double prec = Double.parseDouble(precision.getText());
        double elitismo = Double.parseDouble(pElitismo.getText());
        double probTorneo = Double.parseDouble(pProbTorneo.getText());
        int dimensiones = Integer.parseInt(nDims.getText());

        int individuo = individuox.getSelectedIndex();


        if (individuo != 4 && (mCruce.getSelectedIndex() == 2 || mCruce.getSelectedIndex() == 3)) {
          JOptionPane.showMessageDialog(new JFrame(),
              "Cruce aritmético y BLX-alpha solo disponible en Individuo 4B", "Dialog",
              JOptionPane.ERROR_MESSAGE);
        } else {
          boolean isMax = false;
          if (individuo == 0) {
            isMax = false;
          }
          Selection<?> mSel;
          Cruces<?> mCru;

          AlgoritmoGenetico<?> alg;

          if (individuo == 5) {
            switch (mSeleccion.getSelectedIndex()) {
              case 0:
                mSel = new RouletteSelection<Double>(tamPoblacion, null);
                break;
              case 1:
                mSel = new TournamentDeterministicSelection<Double>(tamPoblacion, null, isMax);
                break;
              case 2:
                mSel = new TournamentProbabilisticSelection<Double>(tamPoblacion, null, probTorneo,
                    isMax);
                break;
              case 3:
                mSel = new StochasticSelection<Double>(tamPoblacion, null);
                break;
              case 4:
                mSel = new TruncateSelection<Double>(tamPoblacion, null, 0.5);
                break;
              case 5:
                mSel = new RestosSelection<Double>(5, null);
                break;
              default:
                mSel = new RouletteSelection<Double>(tamPoblacion, null);
                break;
            }



            switch (mCruce.getSelectedIndex()) {

              case 1:
                mCru = new CruceUniforme<Double>();
                break;
              case 2:
                mCru = new CruceAritmetico();
                break;
              case 3:
                mCru = new CruceBLXAlpha();
                break;
              case 4:
                mCru = new CruceOX();
                break;
              default:
                mCru = new CruceMonopunto<Double>();
                break;
            }
            Individuo<Double> mejor;
            alg = new AlgoritmoGenetico(tamPoblacion, nGeneraciones, probC, probM, prec, mSel, mCru,
                elitismo);
            try {
              mejor = (Individuo<Double>) alg.run(individuo, dimensiones);
              System.out.println(mejor.fitness());
              resultsPane.setText(mejor.toString());
            } catch (Exception ex) {
              ex.printStackTrace();
            }

          } else { // Individuos 1-4A
            switch (mSeleccion.getSelectedIndex()) {
              case 0:
                mSel = new RouletteSelection<Boolean>(tamPoblacion, null);
                break;
              case 1:
                mSel = new TournamentDeterministicSelection<Boolean>(tamPoblacion, null, isMax);
                break;
              case 2:
                mSel = new TournamentProbabilisticSelection<Boolean>(tamPoblacion, null, probTorneo,
                    isMax);
                break;
              case 3:
                mSel = new StochasticSelection<Boolean>(tamPoblacion, null);
                break;
              case 4:
                mSel = new TruncateSelection<Boolean>(tamPoblacion, null, 0.5);
                break;
              case 5:
                mSel = new RestosSelection<Boolean>(5, null);
                break;
              default:
                mSel = new RouletteSelection<Boolean>(tamPoblacion, null);
                break;
            }



            switch (mCruce.getSelectedIndex()) {

              case 1:
                mCru = new CruceUniforme<Boolean>();
                break;
              case 4:
                mCru = new CruceOX();
                break;
              default:
                mCru = new CruceMonopunto<Boolean>();
                break;
            }
            Individuo<Boolean> mejor;
            alg = new AlgoritmoGenetico(tamPoblacion, nGeneraciones, probC, probM, prec, mSel, mCru,
                elitismo);
            try {
              mejor = (Individuo<Boolean>) alg.run(individuo, dimensiones);
              System.out.println(mejor.fitness());
              resultsPane.setText(mejor.toString());
            } catch (Exception ex) {
              ex.printStackTrace();
            }

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
      }



    });
    btnNewButton.setBounds(61, 389, 89, 23);
    contentPane.add(btnNewButton);

    JLabel lblResultados = new JLabel("Resultados");
    lblResultados.setBounds(10, 426, 111, 14);
    contentPane.add(lblResultados);



  }
}
