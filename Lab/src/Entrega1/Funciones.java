package Entrega1;
import java.util.ArrayList;
import java.util.List;
public class Funciones {
// 1  Dado un número entero, determinar si es un número primo o no
    public static String esPrimo(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return n + " no es primo";
            }
        }
       return n + " es primo";
    }
 // 2  Dados los números enteros n,k con n ≥ k diseñar una función que calcule el número combinatorio (n k)
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
    	int combinatorio = factorial(n) / (factorial(k) * factorial(n - k));
        return combinatorio;
        
    }
 //3 Dados los números enteros n,k con n ≥ k diseñar una función que calcule el número S(n,k)
    public static double numerosS(int n, int k) {
    	if (k>n) {
            System.out.println("N debe ser mayor que K") ;
        }
    	 int sumatorio = 0;

    	    for (int i = 0; i < k + 1; i++) {
    	        sumatorio += Math.pow(-1, i) * numeroCombinatorio(k, i) * Math.pow(k - i, n);
    	    }

    	    int resultado = (int) ((1.0 / factorial(k)) * sumatorio);
    	    return resultado;

    }
   // 4 Dada una lista con números enteros, diseñar una función que devuelva otra lista con las diferencias entre cada valor y el anterior 
    public static List<Integer> difLista(List<Integer> lista) {
        List<Integer> lista2 = new ArrayList<>();
        
        for (int i = 1; i < lista.size(); i++) {
            int diferencia = lista.get(i) - lista.get(i - 1);
            lista2.add(diferencia);
        }
        return lista2;
    }
    //5 Dada una lista de cadenas de caracteres, diseñar una función que devuelva la cadena más larga
    
    public static String cadenaMasLarga(List<String> lista) {
        String maximo = "";

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).length() > maximo.length()) {
                maximo = lista.get(i);
            }
        }
        return maximo;
    }
    
        
 //Defensa
    
    public static double P2(int n, int k) {
		if(n<k) {
			System.out.println("N debe ser mayor que K");
			return 0;
		}
		
    	int multiplicador = 1;
		
	    for (int i = 0; i < k-1; i++) {
	        multiplicador *= (n-i);
	        if(i>k+1) {
	        	return 0; // esto hará que si no se valida que i<k+1 se detenga el bucle y de 0
	        }
	        }
	    int resultado = (int) (multiplicador);
	    return resultado;
		}
    public static int C2(int n, int k) {
    	if (k>n) {
            System.out.println("N debe ser mayor que K") ;
            return 0;
        }
    	int combinatorio = factorial(n) / (factorial(k+1) * factorial(n - (k+1)));
        return combinatorio;
    }
    public static double S2(int n, int k) {
    	if (k>n) {
            System.out.println("N debe ser menor que K");
            return 0;
        }
    	 int sumatorio = 0;

    	    for (int i = 0; i < k + 1; i++) {
    	        sumatorio += Math.pow(-1, i) * numeroCombinatorio(k, i) * Math.pow(k - i, n);
    	    }

    	    double resultado = ((double) factorial(k) / factorial(k + 2)) * sumatorio;
    	    return resultado;}
    
    
    
    public static void main(String[] args) {
        int numero = 77;
        int n = 8;
        int k = 5;
        System.out.println(esPrimo(numero));
        System.out.println(numeroCombinatorio(n, k));
        System.out.println(numerosS(n,k));
        List<Integer> lista = List.of(1, 5, 7, 12, 8, 3);
        List<Integer> resultado = difLista(lista);
        System.out.println("Lista original: " + lista);
        System.out.println("Diferencias: " + resultado);
        List<String> lista2 = List.of("hola", "mundo", "mi","nombre", "es", "Gonzalo");
        String resultado2 = cadenaMasLarga(lista2);
        System.out.println("La cadena más larga es: " + resultado2);
        
        //Test Defensa
        System.out.println("-----A----");
        int n1=8;
        int k1=5;
        
        int n2=3;
        int k2=5;
        //A
        System.out.println(P2(n1, k1));
        System.out.println(P2(n2,k2));
        System.out.println("--B--");
        //B
        System.out.println(C2(n1, k1));
        System.out.println(C2(n2, k2));
        System.out.println("--C--");
        //C
        System.out.println(S2(n1, k1));
        System.out.println(S2(n2, k2));
    }
    }

	