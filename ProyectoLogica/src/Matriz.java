import org.jpl7.Query;
import org.jpl7.Term;
/**
 * Clase matriz utilizada para representar la configuración del tablero.
 * @author Tomás Perotti - Christian Bancalá
 *
 */
public class Matriz {

	public String [][] matriz;
	/**
	 * Constructor que inicializa la matriz de String con vacio.
	 */
	public Matriz () {
		
		matriz = new String[7][8];
	
		for (int i=1; i < 7 ; i++){
			
				for(int j=1; j < 8; j++) {
			
					matriz[i][j] ="vacio";
						
				}
		}
	
	}
	/**
	 * Método que retorna true si la posicion del tablero esta disponible, false en caso contrario.
	 * @param fila
	 * @param columna
	 * @return true si la posicion del tablero esta disponible, false en caso contrario.
	 */
	public boolean estaDisponible (int f, int c) {
		String disponible="disponible(["+f+","+c+"],"+getConfig()+")";
		Query q1 = new Query(disponible);
		return q1.hasSolution();
	}
	/**
	 * Método que incorpora una ficha de color a una posición ingresada por parámetro si esta disponible.
	 * @param fila
	 * @param columna
	 * @param color
	 * @return true si fue ingresada correctamente, false en caso contrario.
	 */
	public boolean setColor (int f, int c, String s ) {
		
		//PARA UNA MAYOR CONEXION CON PROLOG SE DEBERIA: String colocar_ficha="colocar_ficha("+s+",["+f+","+c+"],"+getConfig()+"Res)
		
		boolean estaDisponible = estaDisponible(f,c);
		if (estaDisponible) {
			matriz[f][c] =s;
		}
		return estaDisponible;
	}
	/**
	 * Método que retorna true si hay cuatro en línea, false en caso contrario.
	 * @param color
	 * @return true si hay cuatro en línea, false en caso contrario.
	 */
	public boolean cuatroEnLinea(String color) {
		
		String cuatro ="cuatro("+color+","+getConfig()+",Res)";
		Query q1 = new Query(cuatro);
		boolean hayCuatro = q1.hasSolution();
		return hayCuatro;
	}
	/**
	 * Método que retorna el color que contiene una determinada posición.
	 * @param fila
	 * @param columna
	 * @return color.
	 */
	public String getColor (int f, int c) {
		
		String en ="en(["+f+","+c+"],"+getConfig()+",Res)";
		Query q1 = new Query(en);
		System.out.println(""+en+ (q1.hasSolution() ? "TIENE" : "NO TIENE"));
		System.out.println();
		String solucion = q1.oneSolution().get("Res").toString();
		System.out.println(solucion);
		System.out.println("HOLA");
		if (solucion == "azul") System.out.println("He leido un azul");
		else System.out.println("No es azul.");
		
		return solucion;
	}
	
	public String getConfig () {
		
		String salida="";
		
		for (int i=1; i < 7; i++) {
			
			for (int j= 1; j < 8 ; j++ ) {
				
				
				if (j==7 && i==6)
					salida=salida+"p("+i+","+j+","+matriz[i][j]+")]]";
				else
					if (j==7) 
						salida=salida+"p("+i+","+j+","+matriz[i][j]+")]";
				else 
					if(j==1 && i==1)
						salida = salida+"[[p("+i+","+j+","+matriz[i][j]+"),";
				else 
					if (j==1)
						salida = salida+",[p("+i+","+j+","+matriz[i][j]+"),";
					else
						salida=salida+"p("+i+","+j+","+matriz[i][j]+"),";
			}
				
		}
		
		return salida;	
	}
	
	public void imprimir() {
		System.out.println(getConfig());
	}

}
