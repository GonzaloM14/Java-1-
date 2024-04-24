package Lab;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class funciones2 {
	
	//A
	public static double Multiplicador(int n, int k) {
		int multiplicador = 1;

	    for (int i = 1; i < k; i++) {
	        multiplicador *= Math.pow(n-i, 2);}
	    int resultado = (int) (multiplicador);
	    return resultado;
		
		
	}
	
	//B
	public static int factorial(int num) {
        if (num == 0) {
            return 1;
        }
        int res = 1;
        for (int i = 1; i <= num; i++) {
            res *= i;
        }
        return res;
    }
    public static int numeroCombinatorio(int n, int k) {
    	if (k>n) {
            System.out.println("N debe ser menor que K") ;
        }
    	int combinatorio = factorial(n+1) / (factorial(k) * factorial((n+1) - k));
        return combinatorio;
}
    //C
    public static double numerosS(int n, int k) {
    	if (k>n) {
            System.out.println("N debe ser menor que K") ;
        }
    	 int sumatorio = 0;

    	    for (int i = 0; i < k + 1; i++) {
    	        sumatorio += Math.pow(-1, i) * numeroCombinatorio(k, i) * Math.pow(k - i, n);
    	    }

    	    int resultado = (int) ((factorial(k) / Math.sqrt(k)) * sumatorio);
    	    return resultado;}
    
    //public static Fecha parse2(String fecha_str) {
    // Eliminar los corchetes y dividir la cadena en partes utilizando el delimitador "/"
    //String[] partes = fecha_str.substring(1, fecha_str.length() - 1).split("/");

    // Convertir las partes a enteros
    //int dia = Integer.parseInt(partes[0]);
    //int mes = Integer.parseInt(partes[1]);
    //int año = Integer.parseInt(partes[2]);
    record Asignatura(String nombre, Integer numAlumnos) {

        public static Asignatura of(String nombre, Integer numAlumnos) {

               return new Asignatura(nombre,numAlumnos);

        }

   }

   Asignatura a1 = Asignatura.of("A",35);

   Asignatura a2

   = a1;

   Asignatura a3

   = Asignatura.of("A",35);

   Asignatura a4 = Asignatura.of("a",35);
    public static void main(String[] args) {
    	Set<Integer> a = Set.of(1, 2, 3, 4, 5, 6), Set<Integer> b = Set.of(4, 5, 6, 7, 8) y Set<Integer> c = new HashSet<>(a);
        int n = 8;
        int k = 5;
        System.out.println(funciones2.numeroCombinatorio(n, k));
        System.out.println(funciones2.Multiplicador(n, k));
        System.out.println(funciones2.numerosS(n, k));
        System.out.println(a1.equals(a3));
}
}