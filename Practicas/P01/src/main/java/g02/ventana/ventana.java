package g02.ventana;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.math.plot.Plot2DPanel;

import g02.Selections.RouletteSelection;
import g02.Selections.Selection;
import g02.Selections.StochasticSelection;
import g02.Selections.TournamentDeterministicSelection;
import g02.Selections.TournamentProbabilisticSelection;
import g02.Selections.TruncateSelection;
import g02.algoritmogenetico.AlgoritmoGenetico;
import g02.cruces.CruceMonopunto;
import g02.cruces.CruceUniforme;
import g02.cruces.Cruces;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JInternalFrame;

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
    numGeneraciones.setText("2");
    numGeneraciones.setBounds(131, 31, 86, 20);
    contentPane.add(numGeneraciones);
    numGeneraciones.setColumns(10);

    JLabel lblNewLabel = new JLabel("Num. Generaciones");
    lblNewLabel.setBounds(10, 34, 111, 14);
    contentPane.add(lblNewLabel);

    tamPob = new JTextField();
    tamPob.setText("10");
    tamPob.setColumns(10);
    tamPob.setBounds(131, 62, 86, 20);
    contentPane.add(tamPob);

    JLabel lblNumGenes = new JLabel("Tam. Población");
    lblNumGenes.setBounds(10, 65, 111, 14);
    contentPane.add(lblNumGenes);

    JLabel lblNumPasos = new JLabel("Prob Torneo");
    lblNumPasos.setBounds(10, 96, 111, 14);
    contentPane.add(lblNumPasos);

    pProbTorneo = new JTextField();
    pProbTorneo.setText("2");
    pProbTorneo.setColumns(10);
    pProbTorneo.setBounds(131, 93, 86, 20);
    contentPane.add(pProbTorneo);

    precision = new JTextField();
    precision.setText("0.001");
    precision.setColumns(10);
    precision.setBounds(131, 121, 86, 20);
    contentPane.add(precision);

    JLabel lblProfMaxInicial = new JLabel("Precision");
    lblProfMaxInicial.setBounds(10, 124, 111, 14);
    contentPane.add(lblProfMaxInicial);

    pMutacion = new JTextField();
    pMutacion.setText("0.1");
    pMutacion.setColumns(10);
    pMutacion.setBounds(131, 152, 86, 20);
    contentPane.add(pMutacion);

    JLabel lblPMutacion = new JLabel("P. Mutacion");
    lblPMutacion.setBounds(10, 155, 111, 14);
    contentPane.add(lblPMutacion);

    JLabel lblPCruce = new JLabel("P. Cruce");
    lblPCruce.setBounds(10, 186, 111, 14);
    contentPane.add(lblPCruce);

    pCruce = new JTextField();
    pCruce.setText("0.1");
    pCruce.setColumns(10);
    pCruce.setBounds(131, 183, 86, 20);
    contentPane.add(pCruce);

    JComboBox mSeleccion = new JComboBox();
    mSeleccion.setModel(new DefaultComboBoxModel(
        new String[] {"ruleta", "torneo 1", "torneo 2", "estocástico", "truncamiento", "restos"}));
    mSeleccion.setBounds(131, 214, 86, 22);
    contentPane.add(mSeleccion);

    JLabel lblMSeleccion = new JLabel("M. Seleccion");
    lblMSeleccion.setBounds(10, 218, 111, 14);
    contentPane.add(lblMSeleccion);

    JLabel lblMCruce = new JLabel("M. Cruce");
    lblMCruce.setBounds(10, 251, 111, 14);
    contentPane.add(lblMCruce);

    JComboBox mCruce = new JComboBox();
    mCruce.setModel(new DefaultComboBoxModel(new String[] {"monopunto", "uniforme"}));
    mCruce.setBounds(131, 247, 86, 22);
    contentPane.add(mCruce);

    JLabel lblMMutacion = new JLabel("M. Mutacion");
    lblMMutacion.setBounds(10, 280, 111, 14);
    contentPane.add(lblMMutacion);

    JComboBox mMutacion = new JComboBox();
    mMutacion.setModel(new DefaultComboBoxModel(new String[] {"básica"}));
    mMutacion.setBounds(131, 276, 86, 22);
    contentPane.add(mMutacion);

    pElitismo = new JTextField();
    pElitismo.setText("0.1");
    pElitismo.setColumns(10);
    pElitismo.setBounds(131, 305, 86, 20);
    contentPane.add(pElitismo);

    JLabel lblElitismo = new JLabel("Elitismo");
    lblElitismo.setBounds(10, 308, 111, 14);
    contentPane.add(lblElitismo);

    JInternalFrame internalFrame = new JInternalFrame("Resultado");
    internalFrame.setBounds(227, 31, 631, 624);
    contentPane.add(internalFrame);
    internalFrame.setVisible(true);

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

        Selection<Boolean> mSel;

        switch (mSeleccion.getSelectedIndex()) {
          case 0:
            mSel = new RouletteSelection<Boolean>(tamPoblacion, null);
            break;
          case 1:
            mSel = new TournamentDeterministicSelection<Boolean>(tamPoblacion, null);
            break;
          case 2: 
            mSel = new TournamentProbabilisticSelection<>(tamPoblacion, null, probTorneo);
          case 3:
            mSel = new StochasticSelection<Boolean>(tamPoblacion, null);
            break;
          case 4:
            mSel = new TruncateSelection<>(tamPoblacion, null, 0.5);
            break;
          default:
            mSel = new RouletteSelection<Boolean>(tamPoblacion, null);
            break;
        }

        Cruces<Boolean> mCru;

        switch (mCruce.getSelectedIndex()) {
          default:
            mCru = new CruceMonopunto<Boolean>();
            break;

          case 1:
            mCru = new CruceUniforme<Boolean>();
        }

        AlgoritmoGenetico alg =
            new AlgoritmoGenetico(tamPoblacion, nGeneraciones, probC, probM, 2, prec, mSel, mCru, elitismo);

        try {
          System.out.println(alg.run().fitness());
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
        plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
        plot.addLinePlot("MEDIAS", generaciones, media);
        plot.addLinePlot("MEJOR ABSOLUTO", generaciones, best);
        // put the PlotPanel in a JFrame like a JPanel
        internalFrame.setContentPane(plot);
      }
    });
    btnNewButton.setBounds(62, 336, 89, 23);
    contentPane.add(btnNewButton);



  }
}
