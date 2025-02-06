package com.teste_iniflex.teste_iniflex.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class FormatadorUtil {

    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DecimalFormat FORMATO_MOEDA;

    static {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(new Locale("pt", "BR"));
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');
        FORMATO_MOEDA = new DecimalFormat("#,##0.00", symbols);
    }

    public static String formatarData(LocalDate data) {
        return data.format(FORMATO_DATA);
    }

    public static String formatarMoeda(BigDecimal valor) {
        return FORMATO_MOEDA.format(valor);
    }
}