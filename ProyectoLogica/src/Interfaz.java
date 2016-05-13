import java.awt.EventQueue;
import java.awt.*;

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

public class Interfaz {
	
	
	private JFrame frame;
	private JLabel [][] etiquetas = new JLabel [7][8];
	private JLabel [] flechas = new JLabel [8];

	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 442);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		JPanel panel = new JPanel();
		panel.setForeground(UIManager.getColor("Button.foreground"));
		panel.setBackground(UIManager.getColor("Button.darkShadow"));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 713, 67);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel turnoColor = new JLabel("TURNO AZUL");
		turnoColor.setHorizontalAlignment(SwingConstants.CENTER);
		turnoColor.setBounds(586, 25, 89, 19);
		panel_3.add(turnoColor);
		
		JButton botonRojo = new JButton("ROJO");
		JButton botonAzul = new JButton("AZUL");
		
		botonAzul.setBackground(new Color(0, 0, 255));
		botonAzul.setForeground(new Color(255, 255, 255));
		botonAzul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				turnoColor.setText("TURNO AZUL");
				botonAzul.setVisible(false);
				botonRojo.setVisible(false);
				
				for (int i=1; i < 8 ; i++){
					
					flechas[i].setVisible(true);
					
				}
				
			}
		});
	
		botonAzul.setBounds(21, 21, 89, 23);
		panel_3.add(botonAzul);
		
		JLabel lblCuatroEnLinea = new JLabel("CUATRO EN LINEA ");
		lblCuatroEnLinea.setBounds(269, 5, 269, 38);
		panel_3.add(lblCuatroEnLinea);
		lblCuatroEnLinea.setFont(new Font("Tahoma", Font.PLAIN, 31));
		
		
		botonRojo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				turnoColor.setText("TURNO ROJO");
				botonRojo.setVisible(false);
				botonAzul.setVisible(false);
				
				for (int i=1; i < 8 ; i++){
					
					flechas[i].setVisible(true);
					
				}
			}
		});
		botonRojo.setBackground(new Color(255, 0, 0));
		botonRojo.setForeground(new Color(255, 255, 255));
		botonRojo.setBounds(131, 21, 89, 23);
		panel_3.add(botonRojo);
		
		
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		panel_2.setBounds(0, 66, 713, 337);
		panel.add(panel_2);
		panel_2.setLayout(new GridLayout(7, 7, 0, 0));
		
		
		
		/*class OyenteFlecha () implements MouseListener  {
			
			
		}*/
		Matriz matriz = new Matriz ();
		
		//PRUEBO SI FUNCIONA
		String t1 = "consult('4enlinea.pl')";
		Query q1 = new Query(t1);
		System.out.println( t1 + " " + (q1.hasSolution() ? "succeeded" : "failed") );
		
		//OBTENGO EL STRING DE CONFIGURACION
		
		
		
		MouseAdapter OyenteFlecha = new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				JLabel flecha = (JLabel) arg0.getSource();
				String numFlecha = flecha.getText();
				Integer col = Integer.parseInt(numFlecha);
				
				if(turnoColor.getText()=="TURNO AZUL") {
					
					for (int i=6; i>0; i--) {
						
						if (matriz.setColor(i, col, "azul")) {
							etiquetas[i][col].setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.png")));
							turnoColor.setText("TURNO ROJO");
							matriz.imprimir();		
							if( matriz.cuatroEnLinea("azul")) gameOver();
							if( (matriz.getColor(6, 1)=="azul") ) System.out.println("AGUANTA DOGO");
							i=0;

						}
		
					}

				} else {
					for (int i=6; i>0; i--) {
						if (matriz.setColor(i, col, "rojo")) {
							etiquetas[i][col].setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.png")));
							turnoColor.setText("TURNO AZUL");
							matriz.imprimir();		
							if( matriz.cuatroEnLinea("rojo")) gameOver();
							i=0;

						}
					}
				}
			}
		
		};
		
		for (int i=1; i < 8 ; i++){
			
			flechas[i] = new JLabel (""+i);
			flechas[i].setIcon(new ImageIcon(Interfaz.class.getResource("/images/flecha.png")) );
			flechas[i].addMouseListener (OyenteFlecha);
			flechas[i].setVisible(false);
			
			panel_2.add(flechas[i]);
			
		}
		
		int f = 1;
		int c = 1;
		
		for (int i=1; i < 7 ; i++){
			
			for(int j=1; j < 8; j++) {
			
				etiquetas[i][j] = new JLabel ("("+c+","+f+")");
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
	
	public void gameOver () {
		
		//SE PODRIAN PINTAR LOS 4 ELEMENTOS DE LA LISTA QUE DEVUELVE PROLOG 
		
		JOptionPane.showMessageDialog (null, "CUATRO EN LINEA !!! ", "Ganaste", JOptionPane.INFORMATION_MESSAGE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
	}
}
