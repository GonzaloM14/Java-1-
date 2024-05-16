package lab.bancos;

import java.util.*;
import java.util.stream.Collectors;

import us.lsi.tools.File2;

public class Cuentas {
    private static Cuentas gestorDeCuentas = null;
    private Set<Cuenta> cuentas;
    private Map<String, Cuenta> cuentasIban;

    // Constructor privado
    private Cuentas(Set<Cuenta> cuentas) {
    	super();
        this.cuentas = cuentas;
        this.cuentasIban = this.cuentas.stream().collect(Collectors.toMap(e->e.getIban(),e->e));
    }

    // Método factoría
    public static Cuentas of() {
        if(Cuentas.gestorDeCuentas == null)
            Cuentas.gestorDeCuentas = Cuentas.parse("ficheros/cuentas.txt");
        return Cuentas.gestorDeCuentas;
    }          
    
    public static Cuentas parse(String fichero) {
    	Set<Cuenta> cuentas = File2.streamDeFichero(fichero,"UTF-8")
    			.map(ln->Cuenta.parse(ln))
    			.collect(Collectors.toSet());
        Cuentas.gestorDeCuentas = new Cuentas(cuentas);
        return Cuentas.gestorDeCuentas;
    }
    // Getters

    public Set<Cuenta> todas() {
        return cuentas;
    }

    public Optional<Cuenta> cuentaIban(String iban) {
        return Optional.ofNullable(cuentasIban.get(iban));
    }

    public Integer size() {
        return cuentas.size();
    }

    public Cuenta cuentaIndex(int n) {
        List<Cuenta> cuentaList = new ArrayList<>(cuentas);
        if (n < 0 || n >= cuentaList.size()) {
            throw new IllegalArgumentException("El índice está fuera del rango de cuentas");
        }
        return cuentaList.get(n);
    }

    // Representación como cadena
    @Override
    public String toString() {
        return cuentas.stream()
                .map(Cuenta::toString)
                .collect(Collectors.joining("\n"));
    }
}
