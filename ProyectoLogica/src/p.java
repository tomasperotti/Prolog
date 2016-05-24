/**
 * Clase utilizada para representar la posición de una ficha y su color
 * @author Tomás Perotti - Christian Bancalá
 *
 */
public class p {
	private int x,y;
	private Character c;
	/**
	 * Constructor que inicializa la posición con las coordenadas y el color ingresado por parámetro.
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
	 * Retorna la posición X.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	/**
	 * Setea la posición X.
	 * @param x
	 */
	public void setX(int x) {
		this.x = x;
	}
	/**
	 * Retorna la posición Y.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setea la posición Y.
	 * @param y
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Retorna el color de la posición.
	 * @return color
	 */
	public Character getC() {
		return c;
	}
	/**
	 * Setea el color de la posición.
	 * @param color
	 */
	public void setC(Character c) {
		this.c = c;
	}
	/**
	 * Retorna un string que contiene la posición.
	 * @return string posición
	 */
	public String toString (){
		return "p("+x+","+y+","+c+")";
	}
}
