import java.awt.EventQueue;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;

import org.jpl7.Query;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * Clase utilizada para representar la Interfaz del juego.
 * @author Tomás Perotti - Christian Bancalá
 *
 */
public class Interfaz {
	
	private String colorSeleccionado ="";
	private JLabel turnoColor = new JLabel();
	private Matriz matriz = new Matriz ();
	private JFrame frame;
	private JLabel [][] etiquetas = new JLabel [7][8];
	private JLabel [] flechas = new JLabel [8];

	/**
	 * Ejecuta la aplicación.
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
	 * Crea la aplicación.
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
		frame.getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		frame.getContentPane().setLayout(null);
		
		//Panel General

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 675, 442);
		frame.add(panel);
		
		//Panel superior
		JPanel panelSuperior = new JPanel();
		panelSuperior.setBounds(0, 0, 713, 100);
		panel.add(panelSuperior);
		panelSuperior.setLayout(null);
		 
		turnoColor.setBounds(605, 10, 50, 50);
		panelSuperior.add(turnoColor);
		
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
				colorSeleccionado ="azul";
				p pos = matriz.jugadaMaquina("r");
				cambiarColor(pos.getX(),pos.getY(),pos.getC());
				panelBotonesInicio.setVisible(false);
				
				for (int i=1; i < 8 ; i++)
					flechas[i].setVisible(true);	
			}
		});
	
		panelBotonesInicio.add(botonAzul);
		
		
		botonRojo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.gif")));
				turnoColor.setName("TURNO ROJO");
				colorSeleccionado ="rojo";
				p pos = matriz.jugadaMaquina("a");
				cambiarColor(pos.getX(),pos.getY(),pos.getC());
				panelBotonesInicio.setVisible(false);
			
				for (int i=1; i < 8 ; i++)
					flechas[i].setVisible(true);
			}
		});
		
		botonRojo.setBackground(new Color(255, 0, 0));
		botonRojo.setForeground(new Color(255, 255, 255));
		panelBotonesInicio.add(botonRojo);
		panelSuperior.add(panelBotonesInicio);
		
		
		//Titulo juego
		JLabel lblCuatroEnLinea = new JLabel();
		lblCuatroEnLinea.setIcon(new ImageIcon(Interfaz.class.getResource("/images/cel.png")));
		lblCuatroEnLinea.setSize(400,60);
		lblCuatroEnLinea.setBounds(140,0, 400, 60);
		panelSuperior.add(lblCuatroEnLinea);
		
		//Panel fichas y flechas
		JPanel panelInferior = new JPanel();
		panelInferior.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		panelInferior.setBounds(0, 66, 713, 337);
		panel.add(panelInferior);
		panelInferior.setLayout(new GridLayout(7, 7, 0, 0));
		
		String t1 = "consult('4enlinea.pl')";
		Query q1 = new Query(t1);
		System.out.println( t1 + " " + (q1.hasSolution() ? "succeeded" : "failed") );
		
		MouseAdapter OyenteFlecha = new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				JLabel flecha = (JLabel) arg0.getSource();
				String numFlecha = flecha.getName();
				Integer col = Integer.parseInt(numFlecha);
				List<p> resultado = new LinkedList<p>();
				
					
					for (int i=6; i>0; i--) {
							if(colorSeleccionado.equals("azul")){
								if(matriz.setColor(i, col, "a")){
									etiquetas[i][col].setIcon(new ImageIcon(Interfaz.class.getResource("/images/"+colorSeleccionado+".png")));
									turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.gif")));
									turnoColor.setName("TURNO ROJO");
									if( matriz.cuatroEnLinea("a", resultado)) 
										gameOver(resultado,"azul");
									else {
										p pos = matriz.jugadaMaquina("r");
										cambiarColor(pos.getX(),pos.getY(),pos.getC());
										if( matriz.cuatroEnLinea("r", resultado)) 
											gameOver(resultado,"rojo");									}
									i=0;
								}
								
							} else {
								if(matriz.setColor(i, col, "r")){
									etiquetas[i][col].setIcon(new ImageIcon(Interfaz.class.getResource("/images/"+colorSeleccionado+".png")));
									turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.gif")));
									turnoColor.setName("TURNO AZUL");
									if( matriz.cuatroEnLinea("r", resultado)) 
										gameOver(resultado,"rojo");
									else {
										p pos = matriz.jugadaMaquina("a");
										cambiarColor(pos.getX(),pos.getY(),pos.getC());
										if( matriz.cuatroEnLinea("a", resultado)) 
											gameOver(resultado,"azul");
									}
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
			
			panelInferior.add(flechas[i]);
		}
		
		int f = 1;
		int c = 1;
		
		for (int i=1; i < 7 ; i++){
			for(int j=1; j < 8; j++) {
				etiquetas[i][j] = new JLabel();
				etiquetas[i][j].setName("("+c+","+f+")");
				etiquetas[i][j].setIcon(new ImageIcon(Interfaz.class.getResource("/images/negro.png")));
				panelInferior.add(etiquetas[i][j]);
			
				f++;
				if ( f == 8) {
					f=1;
					c++;
				}
			}
		}
	}
	/**
	 * Realiza el cambio de color cuando la maquina realiza su jugada en las coordenadas mencionadas 
	 * @param x coordenada
	 * @param y coordenada
	 * @param c color
	 */
	private void cambiarColor (int x, int y, Character c){
		if(c == 'r'){
			etiquetas[x][y].setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.png")));
			turnoColor.setName("TURNO AZUL");
			turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.gif")));
		} else if (c == 'a'){
			etiquetas[x][y].setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.png")));
			turnoColor.setName("TURNO ROJO");
			turnoColor.setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.gif")));
		}

	}
	
	/**
	 * Método que termina la ejecución del juego.
	 */
	public void gameOver (List<p> resultado, String color) {
		
		for (p pos : resultado){
			etiquetas[pos.getX()][pos.getY()].setIcon(new ImageIcon(Interfaz.class.getResource("/images/color.png")));
		}
		JOptionPane.showMessageDialog (null, "¡CUATRO EN LINEA!\nGanador: jugador "+color+"", "Final del juego", JOptionPane.INFORMATION_MESSAGE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
	}
}
