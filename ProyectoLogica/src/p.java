/**
 * Clase utilizada para representar la posici�n de una ficha y su color
 * @author Tom�s Perotti - Christian Bancal�
 *
 */
public class p {
	private int x,y;
	private Character c;
	/**
	 * Constructor que inicializa la posici�n con las coordenadas y el color ingresado por par�metro.
	 * @param x coordenada 
	 * @param y coordenada
	 * @param c color
	 */
	public p(int x, int y,Character c){
		this.x = x;
		this.y = y;
		this.c = c;
	}
	/**
	 * Retorna la posici�n X.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setea la posici�n X.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Retorna la posici�n Y.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setea la posici�n Y.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Retorna el color de la posici�n.
	 * @return color
	 */
	public Character getC() {
		return c;
	}
	/**
	 * Setea el color de la posici�n.
	 * @param color
	 */
	public void setC(Character c) {
		this.c = c;
	}
	/**
	 * Retorna un string que contiene la posici�n.
	 * @return string posici�n
	 */
	public String toString (){
		return "p("+x+","+y+","+c+")";
	}
}
