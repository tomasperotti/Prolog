/**
 * Representa la posición de una ficha y su color
 * @author Christian
 *
 */
public class p {
	private int x,y;
	private Character c;
	/**
	 * 
	 * @param x coordenada 
	 * @param y coordenada
	 * @param c color
	 */
	public p(int x, int y,Character c){
		this.x = x;
		this.y = y;
		this.c = c;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Character getC() {
		return c;
	}

	public void setC(Character c) {
		this.c = c;
	}

	public String toString (){
		return "p("+x+","+y+","+c+")";
	}
}
