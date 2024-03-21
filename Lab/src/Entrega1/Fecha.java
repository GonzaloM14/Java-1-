package Entrega1;
import java.time.temporal.ChronoUnit;

import java.util.List;
import java.util.Arrays;
import java.time.LocalDate;
public class Fecha {
	Integer año;
    Integer mes;
    Integer dia;

    public Fecha(Integer año, Integer mes, Integer dia) {
        this.año = año;
        this.mes = mes;
        this.dia = dia;
    }
    public static Fecha of(Integer año, Integer mes, Integer dia) {
        return new Fecha(año, mes, dia);
    }
    public static Fecha parse(String fechaStr) {
        String[] partes = fechaStr.split("-");
        int año = Integer.parseInt(partes[0]);
        int mes = Integer.parseInt(partes[1]);
        int dia = Integer.parseInt(partes[2]);
        return new Fecha(año, mes, dia);
    }
    public Integer getAño() {
        return año;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getDia() {
        return dia;
    }

    public String getNombreMes() {
        List<String> nombresMeses = Arrays.asList(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
        );
        return nombresMeses.get(mes - 1);
    }

    public String getDiaSemana() {
        int zeller = calcularZeller(año, mes, dia);
        List<String> nombresDiasSemana = Arrays.asList(
                "Sábado", "Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"
        );
        return nombresDiasSemana.get(zeller);
    }

    public Fecha sumarDias(int dias) {
        int[] diasEnMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int nuevoDia = dia + dias;
        int nuevoMes = mes;
        int nuevoAño = año;

        while (nuevoDia > diasEnMes[nuevoMes]) {
            nuevoDia -= diasEnMes[nuevoMes];
            nuevoMes++;

            if (nuevoMes > 12) {
                nuevoMes = 1;
                nuevoAño++;
            }
        }
        return new Fecha(nuevoAño, nuevoMes, nuevoDia);
    }

    public Fecha restarDias(int dias) {
        int[] diasEnMes = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int nuevoDia = dia - dias;
        int nuevoMes = mes;
        int nuevoAño = año;

        while (nuevoDia <= 0) {
            nuevoMes--;

            if (nuevoMes <= 0) {
                nuevoMes = 12;
                nuevoAño--;
            }

            nuevoDia += diasEnMes[nuevoMes];
        }
        return new Fecha(nuevoAño, nuevoMes, nuevoDia);
    }

    public Integer diferenciaEnDias(Fecha otraFecha) {
        LocalDate fechaActual = LocalDate.of(this.año, this.mes, this.dia);
        LocalDate otraFechaLocalDate = LocalDate.of(otraFecha.getAño(), otraFecha.getMes(), otraFecha.getDia());
        long diferenciaEnDias = ChronoUnit.DAYS.between(fechaActual, otraFechaLocalDate);
        return Math.toIntExact(diferenciaEnDias);

    }

    public int calcularZeller(int año, int mes, int dia) {
        if (mes < 3) {
            mes += 12;
            año--;
        }
        int k = año % 100;
        int j = año / 100;

        int h = (dia + 13 * (mes + 1) / 5 + k + k / 4 + j / 4 + 5 * j) % 7;

        return (h + 5) % 7;
    }

    public boolean esBisiesto(int año) {
        return año % 4 == 0 && (año % 100 != 0 || año % 400 == 0);
    }
    public static int díasEnMes(Integer año, Integer mes) {
        return LocalDate.of(año, mes, 1).lengthOfMonth();
    }

   
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", año, mes, dia);
    }

    public static void main(String[] args) {
        Fecha fecha = new Fecha(2024, 3, 10);
        String cadenaFecha = "2023-09-20";

        System.out.println(Fecha.parse(cadenaFecha));
        System.out.println("Fecha: " + fecha);
        System.out.println("Nombre del mes: " + fecha.getNombreMes());
        System.out.println("Día de la semana: " + fecha.getDiaSemana());

        Fecha fechaSumada = fecha.sumarDias(7);
        System.out.println("Fecha sumada 7 días: " + fechaSumada);

        Fecha fechaRestada = fecha.restarDias(3);
        System.out.println("Fecha restada 3 días: " + fechaRestada);

        Fecha otraFecha = new Fecha(2024, 3, 15);
        System.out.println("Diferencia en días: " + fecha.diferenciaEnDias(otraFecha));
        System.out.println(fecha.esBisiesto(2024));
    }
}
