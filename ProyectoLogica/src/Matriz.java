import java.util.List;
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
			
					matriz[i][j] ="v";
						
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
			matriz[f][c]=s;
		}
		return estaDisponible;
	}
	
	/**
	 * Método que retorna true si hay cuatro en línea, false en caso contrario.
	 * @param color
	 * @return true si hay cuatro en línea, false en caso contrario.
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
	 * Método que retorna el color que contiene una determinada posición.
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
	 * Método que retorna la configuración del tablero.
	 * @return la configuración del tablero.
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
	 * Método que imprime por consola la configuración del tablero.
	 */
	public void imprimir() {
		System.out.println(getConfig());
	}
	
	public p jugadaMaquina(String color) {
		p pos = new p(0,0,color.charAt(0));
		String maq ="jugada_maquina("+color+",[[p(6,1,a),p(6,2,a),p(6,3,v),p(6,4,a),p(6,5,v),p(6,6,v),p(6,7,v)],[p(5,1,v),p(5,2,a),p(5,3,a),p(5,4,v),p(5,5,v),p(5,6,v),p(5,7,v)],[p(4,1,a),p(4,2,a),p(4,3,v),p(4,4,v),p(4,5,v),p(4,6,v),p(4,7,v)],[p(3,1,a),p(3,2,a),p(3,3,r),p(3,4,r),p(3,5,a),p(3,6,v),p(3,7,v)],[p(2,1,a),p(2,2,v),p(2,3,a),p(2,4,r),p(2,5,v),p(2,6,v),p(2,7,v)],[p(1,1,v),p(1,2,v),p(1,3,a),p(1,4,a),p(1,5,v),p(1,6,v),p(1,7,v)]],Ranura)";
		//String maq ="jugada_maquina("+color+","+this.getConfig()+",Ranura)";
		Query q1 = new Query(maq);
		if (q1.hasSolution()){
			String sol = q1.oneSolution().get("Ranura").toString();
			
			boolean encontro = obtenerRanura(sol,pos);
			if (encontro)
				this.setColor(pos.getX(), pos.getY(), pos.getC()+"");
		} 
		return pos;
	}
	
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
