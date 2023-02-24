package g02.ventana;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.math.plot.Plot2DPanel;

import g02.Selections.RouletteSelection;
import g02.Selections.Selection;
import g02.Selections.StochasticSelection;
import g02.algoritmogenetico.AlgoritmoGenetico;
import g02.cruces.CruceMonopunto;
import g02.cruces.CruceUniforme;
import g02.cruces.Cruces;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JInternalFrame;

public class ventana extends JFrame {

	private JPanel contentPane;
	private JTextField numGeneraciones;
	private JTextField tamPob;
	private JTextField numPasos;
	private JTextField precision;
	private JTextField pMutacion;
	private JTextField pCruce;
	private JTextField textField_6;

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
		
		JLabel lblNumPasos = new JLabel("Num. Pasos");
		lblNumPasos.setBounds(10, 96, 111, 14);
		contentPane.add(lblNumPasos);
		
		numPasos = new JTextField();
		numPasos.setText("2");
		numPasos.setColumns(10);
		numPasos.setBounds(131, 93, 86, 20);
		contentPane.add(numPasos);
		
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
		mSeleccion.setModel(new DefaultComboBoxModel(new String[] {"ruleta", "torneo 1", "torneo 2", "estocástico", "truncamiento", "restos"}));
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
		
		textField_6 = new JTextField();
		textField_6.setText("0.1");
		textField_6.setColumns(10);
		textField_6.setBounds(131, 305, 86, 20);
		contentPane.add(textField_6);
		
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
				
				Selection<Boolean> mSel;
			
				switch(mSeleccion.getSelectedIndex()) {
					default:
						mSel = new RouletteSelection<Boolean>(tamPoblacion,null);
						break;
				}
				
				Cruces<Boolean> mCru;
				
				switch(mCruce.getSelectedIndex()) {
					default:
						mCru = new CruceMonopunto<Boolean>(0);
						break;
						
					case 1:
						mCru = new CruceUniforme<Boolean>();
				}
				
				AlgoritmoGenetico alg = new AlgoritmoGenetico(tamPoblacion,nGeneraciones,probC,probM, 2, prec, mSel, mCru);
				
				try {
				      System.out.println(alg.run().fitness());
				    } catch (Exception ex) {
				      ex.printStackTrace();
				    }
		    	
		    	
		    	double[] generaciones = new double[nGeneraciones];
		    	for(int i = 0; i < nGeneraciones; i++) {
		    		generaciones[i] = i;
		    	}
		        double[] fitness = alg.getMejores();
		        double[] media = alg.getMedias();
		        // create your PlotPanel (you can use it as a JPanel)
		        Plot2DPanel plot = new Plot2DPanel();
		        // define the legend position
		        plot.addLegend("SOUTH");
		        // add a line plot to the PlotPanel
		        plot.addLinePlot("EVOLUCIÓN", generaciones, fitness);
		        plot.addLinePlot("MEDIAS",generaciones, media);
		        // put the PlotPanel in a JFrame like a JPanel
		        internalFrame.setContentPane(plot);
			}
		});
		btnNewButton.setBounds(62, 336, 89, 23);
		contentPane.add(btnNewButton);
		
		
		
		
	}
}
