import org.jpl7.Query;

public class Main { 
	
	
	public static void main (String [] args) {
		
		try{
			
			String conexion="consult('4enlinea.pl')";
			Query con = new Query(conexion);
			System.out.println(conexion+" "+ (con.hasMoreSolutions() ? "ACEPTADO" : "FALSO"));
			
			
			
			
			
			
		} catch( Exception e ){
			
		}
	}
	
	public void jugada(int fila, int columna) {
		
	}

}


