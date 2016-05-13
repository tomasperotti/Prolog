import org.jpl7.Query;

public class Matriz {

	public String [][] matriz;
	
	public Matriz () {
		
	matriz = new String[7][8];
	
	for (int i=1; i < 7 ; i++){
			
			for(int j=1; j < 8; j++) {
			
				matriz[i][j] ="vacio";
						
			}
		}
	
	}
	
	public void setColor (int f, int c, String s ) {
		
		String disponible="disponible(["+f+","+c+"],"+getConfig()+")";
		//NO SE DEBERIA NECESITAR String colocar_ficha="colocar_ficha("+s+",["+f+","+c+"],"+getConfig()+"Res)";
		Query q1 = new Query(disponible);
		boolean estaDisponible = q1.hasSolution();
		System.out.println((estaDisponible ? "TRUE" : "FALSE"));
		if (estaDisponible) {
			matriz[f][c] =s;
		}
	}
	
	public String getColor (int f, int c) {
		return matriz[f][c];
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
