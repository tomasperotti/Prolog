import java.util.List;
import org.jpl7.Query;
import org.jpl7.Term;
/**
 * Clase matriz utilizada para representar la configuraci�n del tablero.
 * @author Tom�s Perotti - Christian Bancal�
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
			
					matriz[i][j] ="v";
						
				}
		}
	
	}
	
	/**
	 * M�todo que retorna true si la posicion del tablero esta disponible, false en caso contrario.
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
	 * M�todo que incorpora una ficha de color a una posici�n ingresada por par�metro si esta disponible.
	 * @param fila
	 * @param columna
	 * @param color
	 * @return true si fue ingresada correctamente, false en caso contrario.
	 */
	public boolean setColor (int f, int c, String s ) {
		
		//PARA UNA MAYOR CONEXION CON PROLOG SE DEBERIA: String colocar_ficha="colocar_ficha("+s+",["+f+","+c+"],"+getConfig()+"Res)
		
		boolean estaDisponible = estaDisponible(f,c);
		if (estaDisponible) {
			matriz[f][c]=s;
		}
		return estaDisponible;
	}
	
	/**
	 * M�todo que retorna true si hay cuatro en l�nea, false en caso contrario.
	 * @param color
	 * @return true si hay cuatro en l�nea, false en caso contrario.
	 */
	public boolean cuatroEnLinea(String color, List<p> resultado) {
		
		String cuatro ="cuatro("+color+","+getConfig()+",Res)";
		Query q1 = new Query(cuatro);
		boolean hayCuatro = q1.hasSolution();
		q1.open();
		if (hayCuatro){
			String sol = q1.getSolution().get("Res").toString();
			listaPosiciones(sol,resultado);
		}
		return hayCuatro;
	}
	
	/**
	 * M�todo que retorna el color que contiene una determinada posici�n.
	 * @param fila
	 * @param columna
	 * @return color.
	 */
	public String getColor (int f, int c) {
		
		String en ="en(["+f+","+c+"],"+getConfig()+",Res)";
		Query q1 = new Query(en);
		String solucion = q1.oneSolution().get("Res").toString();
				
		return solucion;
	}
	
	/**
	 * M�todo que retorna la configuraci�n del tablero.
	 * @return la configuraci�n del tablero.
	 */
	public String getConfig () {
		
		String salida="[";
		
		for (int i=6; i > 0; i--) {
			salida+= "[";
			for (int j= 1; j < 8 ; j++ ) {
		
					if (j!=1)
						salida+=",p("+i+","+j+","+matriz[i][j]+")";
					else
						salida+="p("+i+","+j+","+matriz[i][j]+")";
			}
			salida +="]";
			if(i != 1)
				salida+=",";
		}
		salida +="]";
		return salida;	
	}
	
	/**
	 * M�todo que imprime por consola la configuraci�n del tablero.
	 */
	public void imprimir() {
		System.out.println(getConfig());
	}
	
	/**
	 * Jugada de la maquina luego del turno del usuario
	 * @param color Color correspondiente a la maquina
	 * @return
	 */
	public p jugadaMaquina(String color) {
		p pos = new p(0,0,color.charAt(0));
		String maq ="jugada_maquina("+color+","+this.getConfig()+",Ranura)";
		Query q1 = new Query(maq);
		System.out.println(this.getConfig());
		if (q1.hasSolution()){
			String sol = q1.oneSolution().get("Ranura").toString();
			
			boolean encontro = obtenerRanura(sol,pos);
			System.out.println(pos);
			if (encontro)
				this.setColor(pos.getX(), pos.getY(), pos.getC()+"");
		} else {
			System.out.println("zapatoto no");
		}
		return pos;
	}
	
	/**
	 * Analiza el resultado que brinda Prolog al momento de invocar jugadaMaquina
	 * @param sol Resultado a analizar
	 * @param pos Posicion resultante representada por la clase p (Debe setearse su color previamente desde el m�todo que lo invoca)
	 * @return Retorna true en caso de encontrar una posicion en el String y false en caso contrario
	 */
	private boolean obtenerRanura (String sol, p pos){
		int l = sol.length();
		boolean x = false;
		boolean y = false;
		for (int i = 0; i < l; i++){
			Character c = sol.charAt(i);
			if (c.isDigit(c)){
				if (!x){
					pos.setX(Integer.parseInt(c+""));
					x = true;
				} else if (!y){
					pos.setY(Integer.parseInt(c+""));
					y = true;
					break;
				}
			}
		}
		return x && y;
		
	}
	
	/**
	 * Analiza una soluci�n brindada por Prolog para hallar todas las posiciones que contiene
	 * @param sol Listado de posiciones a analizar
	 * @param resultado Lista con las posiciones encontradas, representadas mediante la clase p
	 */
	private void listaPosiciones(String sol, List<p> resultado){
		int l = sol.length();
    	int j = 0;
    	String aux;
    	for (int i = 0; i < l; i++){
    		if(sol.charAt(i) == 'p'){
    			j = i;
    		}else if (j != 0 && sol.charAt(i) == ')'){
    			aux = sol.substring(j, i+1);
    			procesarSubString (aux,resultado);
    			j = 0;
    		}
    	 }
		
	}
	
	/**
	 * Agrega a una lista pasada por par�metro la posici�n encontrada en el String aux
	 * @param aux String a analizar en busca de una posici�n
	 * @param list Lista donde se insertar� la posici�n encontrada
	 * @return verdadero en caso de que haya encontrado una posici�n, false en caso contrario
	 */
	private boolean procesarSubString (String aux, List<p> list){
		int x, y;
		x = 0;
		y = 0;
		Character c =' ';
		boolean encontroX = false;
		boolean encontroY = false;
		boolean encontroC = false;    	
		int l = aux.length();
		
		for (int i = 0; i < l; i++){
			Character car = aux.charAt(i);
			if (!encontroX && Character.isDigit(car)){
				x = Integer.parseInt(Character.toString(car));
				encontroX = true;
			} else if (encontroX && !encontroY && Character.isDigit(car)){
				y = Integer.parseInt(Character.toString(car));
				encontroY = true;
			} else if (encontroX && encontroY && !encontroC && Character.isAlphabetic(car)){
				c= car;
				encontroC = true;
				break;
			}
		}
		
		if (encontroX && encontroY && encontroC){
			p toInsert = new p(x,y,c);
			list.add(toInsert);
			return true;
		}
		
		return false;
		
		
	}
}
