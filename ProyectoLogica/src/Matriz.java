

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

}
