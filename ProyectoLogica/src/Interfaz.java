import java.awt.EventQueue;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.UIManager;

import org.jpl7.Query;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Clase utilizada para representar la Interfaz del juego.
 * @author Tom�s Perotti - Christian Bancal�
 *
 */
public class Interfaz {
	
	private Matriz matriz = new Matriz ();
	private JFrame frame;
	private JLabel [][] etiquetas = new JLabel [7][8];
	private JLabel [] flechas = new JLabel [8];

	/**
	 * Ejecuta la aplicaci�n.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la aplicaci�n.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Inicializa los contenidos del frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(50, 50, 675, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximumSize(new Dimension(675,442));
		frame.setMinimumSize(new Dimension(675,442));
		frame.setResizable(false);
		frame.setTitle("Cuatro en linea");
		
	    frame.setIconImage(new ImageIcon(Interfaz.class.getResource("/images/logo.png")).getImage());
		
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(UIManager.getColor("Button.foreground"));
		panel.setBackground(UIManager.getColor("Button.darkShadow"));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 713, 100);
		panel.add(panel_3);
		panel_3.setLayout(null);
		 
		JLabel turnoColor = new JLabel();
		turnoColor.setBounds(605, 25, 50, 50);
		panel_3.add(turnoColor);
		
		//Botones comienzo de juego
		JPanel panelBotonesInicio = new JPanel();
		panelBotonesInicio.setBounds(0, 0, 100, 70);
		panelBotonesInicio.setLayout(new GridLayout(2,1,0,0));
		JButton botonRojo = new JButton("ROJO");
		JButton botonAzul = new JButton("AZUL");
		
		botonAzul.setBackground(new Color(0, 0, 255));
		botonAzul.setForeground(new Color(255, 255, 255));
		
		botonAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.gif")));
				turnoColor.setName("TURNO AZUL");
				botonAzul.setVisible(false);
				botonRojo.setVisible(false);
				
				for (int i=1; i < 8 ; i++){
					
					flechas[i].setVisible(true);
					
				}
				
			}
		});
	
		panelBotonesInicio.add(botonAzul);
		
		
		botonRojo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.gif")));
				turnoColor.setName("TURNO ROJO");
				botonRojo.setVisible(false);
				botonAzul.setVisible(false);
				
				for (int i=1; i < 8 ; i++){
					
					flechas[i].setVisible(true);
					
				}
			}
		});
		botonRojo.setBackground(new Color(255, 0, 0));
		botonRojo.setForeground(new Color(255, 255, 255));
		panelBotonesInicio.add(botonRojo);
		panel_3.add(panelBotonesInicio);
		
		JLabel lblCuatroEnLinea = new JLabel();
		lblCuatroEnLinea.setIcon(new ImageIcon(Interfaz.class.getResource("/images/cel.png")));
		lblCuatroEnLinea.setSize(400,60);
		lblCuatroEnLinea.setBounds(153,0, 400, 60);
		panel_3.add(lblCuatroEnLinea);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		panel_2.setBounds(0, 66, 713, 337);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(7, 7, 0, 0));
		

		
		//PRUEBO SI FUNCIONA
		String t1 = "consult('4enlinea.pl')";
		Query q1 = new Query(t1);
		System.out.println( t1 + " " + (q1.hasSolution() ? "succeeded" : "failed") );
		
		MouseAdapter OyenteFlecha = new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				JLabel flecha = (JLabel) arg0.getSource();
				String numFlecha = flecha.getName();
				Integer col = Integer.parseInt(numFlecha);
				List<p> resultado = new LinkedList<p>();
				if(turnoColor.getName().equals("TURNO AZUL")) {
					
					for (int i=6; i>0; i--) {
						
						if (matriz.setColor(i, col, "a")) {
							etiquetas[i][col].setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.png")));
							turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.gif")));
							turnoColor.setName("TURNO ROJO");
							matriz.imprimir();		
							if( matriz.cuatroEnLinea("a", resultado)) gameOver(resultado);
							i=0;

						}
		
					}

				} else {
					for (int i=6; i>0; i--) {
						if (matriz.setColor(i, col, "r")) {
							etiquetas[i][col].setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.png")));
							turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.gif")));
							turnoColor.setName("TURNO AZUL");
							matriz.imprimir();		
							if( matriz.cuatroEnLinea("r", resultado)) 
								gameOver(resultado);
							i=0;

						}
					}
				}
			}
		
		};
		
		for (int i=1; i < 8 ; i++){
			
			flechas[i] = new JLabel();
			flechas[i].setName(""+i);
			flechas[i].setIcon(new ImageIcon(Interfaz.class.getResource("/images/flecha.png")) );
			flechas[i].addMouseListener (OyenteFlecha);
			flechas[i].setVisible(false);
			
			panel_2.add(flechas[i]);
			
		}
		
		int f = 1;
		int c = 1;
		
		for (int i=1; i < 7 ; i++){
			
			for(int j=1; j < 8; j++) {
			
				etiquetas[i][j] = new JLabel();
				etiquetas[i][j].setName("("+c+","+f+")");
				etiquetas[i][j].setIcon(new ImageIcon(Interfaz.class.getResource("/images/negro.png")));
				panel_2.add(etiquetas[i][j]);
			
				f++;
				if ( f == 8) {
					f=1;
					c++;
				}
			
			}
		}
		
	}
	/**
	 * M�todo que termina la ejecuci�n del juego.
	 */
	public void gameOver (List<p> resultado) {
		
		//SE PODRIAN PINTAR LOS 4 ELEMENTOS DE LA LISTA QUE DEVUELVE PROLOG 
		for (p pos : resultado){
			etiquetas[pos.getX()][pos.getY()].setIcon(new ImageIcon(Interfaz.class.getResource("/images/color.png")));
		}
		JOptionPane.showMessageDialog (null, "CUATRO EN LINEA !!! ", "Ganaste", JOptionPane.INFORMATION_MESSAGE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
	}
}
