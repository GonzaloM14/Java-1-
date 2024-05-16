package lab.bancos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cuenta {
    private String iban;
    private String dni;
    private LocalDateTime fechaCreacion;
    private Double saldo;
    private Cuenta(String iban, String dni, LocalDateTime fechaCreacion2, Double saldo) {
        this.iban = iban;
        this.dni = dni;
        this.fechaCreacion = fechaCreacion2;
        this.saldo = saldo;
    }

    public static Cuenta of(String iban, String dni, LocalDateTime fechaCreacion2, Double saldo) {
        return new Cuenta(iban, dni, fechaCreacion2, saldo);
    }

    public static Cuenta parse(String cuentaString) {
        String[] partes = cuentaString.split(",");
        if (partes.length != 4) {
            throw new IllegalArgumentException("Formato de cadena incorrecto para la cuenta");
        }

        String iban = partes[0].trim();
        String dni = partes[1].trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaCreacion = LocalDateTime.parse(partes[2].trim(), formatter);

        Double saldo = Double.parseDouble(partes[3].trim());

        return of(iban, dni, fechaCreacion, saldo);
    }

    public void ingresar(Double cantidad) {
        this.saldo += cantidad;
    }

    public void retirar(Double cantidad) {
        if (cantidad > this.saldo) {
            throw new IllegalArgumentException("No se puede retirar más de lo que hay en la cuenta");
        }
        this.saldo -= cantidad;
    }

    public String getIban() {
        return iban;
    }

    public String getDni() {
        return dni;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public Double getSaldo() {
        return saldo;
    }

    @Override
    public String toString() {
        return iban + ", " + saldo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cuenta cuenta = (Cuenta) obj;
        return iban.equals(cuenta.iban) &&
                dni.equals(cuenta.dni) &&
                fechaCreacion.equals(cuenta.fechaCreacion) &&
                saldo.equals(cuenta.saldo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(iban, dni, fechaCreacion, saldo);
    }
}
