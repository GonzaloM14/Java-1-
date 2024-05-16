package lab.bancos;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import us.lsi.ejemplos_b1_tipos.Persona;

public class Questions {

	//	Vencimiento de los prestamos de un cliente
	public static Set<LocalDate> vencimientoDePrestamosDeCliente(Banco banco, String dni) {
        return banco.prestamos().todos().stream()
                .filter(p -> p.dniCliente().equals(dni))
                .map(Prestamo::fechaVencimiento)
                .collect(Collectors.toSet());
    }
	//	Persona con más prestamos
	public static Persona clienteConMasPrestamos(Banco banco) {
        return banco.prestamos().todos().stream()
                .collect(Collectors.groupingBy(Prestamo::dniCliente, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .flatMap(banco.personas()::personaDni)
                .orElse(null);
    }
	//	Cantidad total de los crétditos gestionados por un empleado
	public static Double cantidadPrestamosEmpleado(Banco banco, String dni) {
        return banco.prestamos().todos().stream()
                .filter(p -> p.dniEmpleado().equals(dni))
                .mapToDouble(Prestamo::cantidad)
                .sum();
    }
	//	Empleado más longevo
	public static Persona empleadoMasLongevo(Banco banco) {
        return banco.empleados().todos().stream()
                .max(Comparator.comparing(Empleado::fechaDeContrado))
                .map(Empleado::persona)
                .orElse(null);
    }
	//	Interés mínimo, máximo y medio de los préstamos
	public static record Info(Double min, Double max) {}

    public static record Info2(Integer mes, Integer año) {}
	
	public static Info rangoDeIntereseDePrestamos(Banco banco) {
        Double minInteres = banco.prestamos().todos().stream()
                .mapToDouble(Prestamo::interes)
                .min().orElse(Double.NaN);
        Double maxInteres = banco.prestamos().todos().stream()
                .mapToDouble(Prestamo::interes)
                .max().orElse(Double.NaN);
        return new Info(minInteres, maxInteres);
    }
	

	//	Número de préstamos por mes y año
	
	public static Map<Object, Object> numPrestamosPorMesAño(Banco banco) {
        return banco.prestamos().todos().stream()
                .collect(Collectors.groupingBy(p -> new Info2(p.fechaComienzo().getMonthValue(), p.fechaComienzo().getYear()), Collectors.counting()))
                .entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
    }
}
