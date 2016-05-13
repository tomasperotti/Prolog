import java.util.HashMap;

import org.jpl7.Query;
import org.jpl7.Term;

public class Main { 
	
	
	public static void main (String [] args) {
		
		try{
			
			String conexion="consult('4enlinea.pl')";
			Query con = new Query(conexion);
			System.out.println(conexion+" "+ (con.hasMoreSolutions() ? "ACEkPTADO" : "FALSO"));
			
			String con1 ="pasar([1,2,3,4],Zs)";
			Query q2 = new Query(con1);
			
			System.out.println("La lista es: "+q2.oneSolution().get("Zs"));
			
			/*HashMap h = (HashMap) q2.oneSolution();
			Term sol = (Term) h.get("Zs");
			Term[] solutionTerms = sol.toTermArray();
			System.out.println(solutionTerms.toString());
			*/
			
			
	            /*HashMap<Term, Term> h = (HashMap) q2.oneSolution();
	            Term sol = (Term) h.get("Zs");
	            Term[] solutionTerms = sol.toTermArray();
	            for (Term term : solutionTerms) {
	                System.out.println(term.arg(1).intValue());
	            }
				*/
		
			
			System.out.println(conexion+" "+ (q2.hasSolution() ? "ACEPTADO" : "FALSO"));
			
			
			
		} catch( Exception e ){
			
		}
	}
	
	public void jugada(int fila, int columna) {
		
	}

}


