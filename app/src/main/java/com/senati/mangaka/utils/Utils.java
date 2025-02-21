package com.senati.mangaka.utils;

import java.time.ZonedDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
public class Utils {

    public static String formatearFecha(String fechaOriginal) {

        ZonedDateTime fecha = ZonedDateTime.parse(fechaOriginal);

        DateTimeFormatter formatoPersonalizado = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        String fechaFormateada = fecha.format(formatoPersonalizado);

        return "xd";
    }
}
