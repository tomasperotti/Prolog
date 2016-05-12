import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.UIManager;

import org.jpl7.Query;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Interfaz {

	private JFrame frame;

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
		
		
		JLabel [][] etiquetas = new JLabel [7][8];
		JLabel [] flechas = new JLabel [8];
		/*class OyenteFlecha () implements MouseListener  {
			
			
		}*/
		Matriz matriz = new Matriz ();
		
		String t1 = "consult('4enlinea.pl')";
		Query q1 = new Query(t1);

		System.out.println( t1 + " " + (q1.hasSolution() ? "succeeded" : "failed") );
		//String config = Matriz.getConfig();
		
		//String cuatro ="en([1,1],"+config, Res)";
		
		MouseAdapter OyenteFlecha = new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				
				JLabel flecha = (JLabel) arg0.getSource();
				String numFlecha = flecha.getText();
				Integer leed = Integer.parseInt(numFlecha);
				
				if(turnoColor.getText()=="TURNO AZUL") {
					
					for (int i=6; i>0; i--) {
						if (matriz.getColor(i, leed) == "negro") {
							etiquetas[i][leed].setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.png")));
							matriz.setColor(i, leed, "azul");
							turnoColor.setText("TURNO ROJO");
							i=0;
						}	
					}

				} else {
					for (int i=6; i>0; i--) {
						if (matriz.getColor(i, leed) == "negro") {
							etiquetas[i][leed].setIcon(new ImageIcon(Interfaz.class.getResource("/images/rojo.png")));
							matriz.setColor(i, leed, "rojo");
							turnoColor.setText("TURNO AZUL");
							i=0;
						}	
					}
				}
				
				
						
				/*if (matriz.getColor(l,leed)=="negro") {
					etiquetas[leed][leed].setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.png")));
					matriz.setColor(leed,leed,"azul");
				} else {
					etiquetas[leed][leed].setIcon(new ImageIcon(Interfaz.class.getResource("/images/negro.png")));
					matriz.setColor(leed,leed,"negro");
				}
				*/
			}
		
		};
		for (int i=1; i < 8 ; i++){
			
			flechas[i] = new JLabel (""+i);
			flechas[i].setIcon(new ImageIcon(Interfaz.class.getResource("/images/flecha.png")) );
			flechas[i].addMouseListener (OyenteFlecha);
			
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
		
		
		
		/*JLabel posicion11 = new JLabel("(1,1)");
		posicion11.setBackground(UIManager.getColor("Button.focus"));
		posicion11.setHorizontalAlignment(SwingConstants.CENTER);
		posicion11.setIcon(new ImageIcon(Interfaz.class.getResource("/images/azul.png")));
		panel_2.add(posicion11);
		
		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_6);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_2);
		
		JLabel lblNewLabel_24 = new JLabel("New label");
		lblNewLabel_24.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_24);
		
		JLabel lblNewLabel_22 = new JLabel("New label");
		lblNewLabel_22.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_22);
		
		JLabel lblNewLabel_30 = new JLabel("New label");
		lblNewLabel_30.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_30);
		
		JLabel lblNewLabel_26 = new JLabel("New label");
		lblNewLabel_26.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_26);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblNewLabel_25 = new JLabel("New label");
		lblNewLabel_25.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_25);
		
		JLabel lblNewLabel_20 = new JLabel("New label");
		lblNewLabel_20.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_20);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_4);
		
		JLabel lblNewLabel_29 = new JLabel("New label");
		lblNewLabel_29.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_29);
		
		JLabel lblNewLabel_27 = new JLabel("New label");
		lblNewLabel_27.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_27);
		
		JLabel lblNewLabel_28 = new JLabel("New label");
		lblNewLabel_28.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_28);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_5);
		
		JLabel lblNewLabel_23 = new JLabel("New label");
		lblNewLabel_23.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_23);
		
		JLabel lblNewLabel_21 = new JLabel("New label");
		lblNewLabel_21.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_21);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel);
		
		JLabel lblNewLabel_31 = new JLabel("New label");
		lblNewLabel_31.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_31);
		
		JLabel lblNewLabel_10 = new JLabel("New label");
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_10);
		
		JLabel lblNewLabel_19 = new JLabel("New label");
		lblNewLabel_19.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_19);
		
		JLabel lblNewLabel_9 = new JLabel("New label");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_9);
		
		JLabel lblNewLabel_36 = new JLabel("New label");
		lblNewLabel_36.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_36);
		
		JLabel lblNewLabel_12 = new JLabel("New label");
		lblNewLabel_12.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_12);
		
		JLabel lblNewLabel_15 = new JLabel("New label");
		lblNewLabel_15.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_15);
		
		JLabel lblNewLabel_11 = new JLabel("New label");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_11);
		
		JLabel lblNewLabel_13 = new JLabel("New label");
		lblNewLabel_13.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("New label");
		lblNewLabel_14.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_14);
		
		JLabel lblNewLabel_16 = new JLabel("New label");
		lblNewLabel_16.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_16);
		
		JLabel lblNewLabel_38 = new JLabel("New label");
		lblNewLabel_38.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_38);
		
		JLabel lblNewLabel_18 = new JLabel("New label");
		lblNewLabel_18.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_18);
		
		JLabel lblNewLabel_17 = new JLabel("New label");
		lblNewLabel_17.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_17);
		
		JLabel lblNewLabel_32 = new JLabel("New label");
		lblNewLabel_32.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_32);
		
		JLabel lblNewLabel_33 = new JLabel("New label");
		lblNewLabel_33.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_33);
		
		JLabel lblNewLabel_34 = new JLabel("New label");
		lblNewLabel_34.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_34);
		
		JLabel lblNewLabel_35 = new JLabel("New label");
		lblNewLabel_35.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_35);
		
		JLabel lblNewLabel_37 = new JLabel("New label");
		lblNewLabel_37.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_37);
		
		JLabel lblNewLabel_39 = new JLabel("New label");
		lblNewLabel_39.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_39);
		
		JLabel lblNewLabel_40 = new JLabel("New label");
		lblNewLabel_40.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(lblNewLabel_40);
		
		JLabel label = new JLabel("New label");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label);
		*/
	}
}
