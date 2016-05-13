

public class Matriz {

	public String [][] matriz;
	
	public Matriz () {
		
	matriz = new String[7][8];
	
	for (int i=1; i < 7 ; i++){
			
			for(int j=1; j < 8; j++) {
			
				matriz[i][j] ="negro";
						
			}
		}
	
	}
	
	public void setColor (int f, int c, String s ) {
		matriz[f][c] =s;
	}
	
	public String getColor (int f, int c) {
		return matriz[f][c];
	}
	
	public String getConfig () {
		return "[[p(4,1,azul), p(4,2,azul), p(4,3,azul), p(4,4,azul)],[p(3,1,a), p(3,2,r), p(3,3,r), p(3,4,r)],[p(2,1,a), p(2,2,r), p(2,3,a), p(2,4,a)],[p(1,1,r), p(1,2,a), p(1,3,a), p(1,4,a)]]";
	}

}
