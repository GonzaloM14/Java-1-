package lab.bancos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;
import us.lsi.tools.Preconditions;


public class ExamenEntrega3 {

	//1
	public static List<Empleado> empleadosEntreDiaMes(Banco banco, String ini, String fin) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        LocalDate iniDate = LocalDate.parse(ini, formatter).withYear(2000);
        LocalDate finDate = LocalDate.parse(fin, formatter).withYear(2000);

        return banco.empleados().todos().stream()
                .filter(empleado -> {
                    LocalDate fechaContrato = empleado.fechaDeContrado().withYear(2000);
                    return (fechaContrato.isEqual(iniDate) || fechaContrato.isAfter(iniDate))
                            && (fechaContrato.isEqual(finDate) || fechaContrato.isBefore(finDate));
                })
                .collect(Collectors.toList());
    }

    //2
	public static Map<Character, List<Empleado>> empleadosConLetraDNI(Banco banco, Set<Character> letras) {
        return banco.empleados().todos().stream()
                .filter(empleado -> letras.contains(empleado.dni().charAt(0)))
                .collect(Collectors.groupingBy(empleado -> empleado.dni().charAt(0)));
    }
	//3
	 public static Double clientesEdadMedia(Banco banco, Integer m) {
	        Preconditions.checkNotNull(m, "El valor de m no puede ser nulo");
	        Preconditions.checkArgument(m > 0, "El valor de m debe ser positivo");
	        
	        return banco.personas().todos().stream()
	                .filter(persona -> persona.edad() >= m)
	                .mapToInt(Persona::edad)
	                .average()
	                .orElse(Double.NaN);
	    }
	
	//4
	 public static Set<String> clientesConMenosPrestamos(Banco banco, int n) {
	        return banco.prestamos().todos().stream()
	                .collect(Collectors.groupingBy(Prestamo::dniCliente, Collectors.counting()))
	                .entrySet().stream()
	                .sorted(Comparator.comparingLong(Map.Entry::getValue))
	                .limit(n)
	                .map(Map.Entry::getKey)
	                .collect(Collectors.toSet());
	    }

	
	    public static void main(String[] args) {
	        
	    	Banco banco = Banco.of();

	        
	        // Test de empleadosConLetraDNI
	        
	        System.out.println("\nTest de empleadosConLetraDNI:");
	        Set<Character> letrasDNI = new HashSet<>();
	        letrasDNI.add('5');
	        letrasDNI.add('2');
	        letrasDNI.add('7');
	        Map<Character, List<Empleado>> empleadosPorLetra = ExamenEntrega3.empleadosConLetraDNI(banco,letrasDNI);
	        for (Character letra : empleadosPorLetra.keySet()) {
	            System.out.println("Empleados con DNI que comienza por '" + letra + "':");
	            System.out.println(empleadosPorLetra.get(letra));
	        }
	        System.out.println("-------------");
	     // Test de clientesEdadMedia
	        Integer m = 18;
	        
	        Double edadMedia = clientesEdadMedia(banco, m);
	        System.out.println("\nTest de clientesEdadMedia:");
	        System.out.println("Edad media de los clientes mayores o iguales a " + m + ": " + edadMedia);
	     
	        //Test ClientesConMenosPrestamos
	        System.out.println("-------------");
	        int n = 5; 
	        
	        Set<String> clientes = clientesConMenosPrestamos(banco, n);
	        System.out.println("Clientes con menos préstamos:");
	        clientes.forEach(System.out::println);
	        
	    
	 
    }

}     
	    
	    