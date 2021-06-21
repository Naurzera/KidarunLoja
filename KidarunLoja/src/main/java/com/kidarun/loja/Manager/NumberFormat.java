package com.kidarun.loja.Manager;

import java.util.Locale;

public class NumberFormat {

    private static String formatNumber(final double d) {
        final java.text.NumberFormat format = java.text.NumberFormat.getInstance(Locale.ENGLISH);
        format.setMaximumFractionDigits(2);
        format.setMinimumFractionDigits(0);
        return format.format(d);
    }
    public static String humanBalance(double balance) {
        if (balance < 1000) {
            return formatNumber(balance);
        }
        if (balance < 1000000) {
            return formatNumber(balance / 1000.0) + "K";
        }
        if (balance < 1E9) {
            return formatNumber(balance / 1000000.0) + "M";
        }
        if (balance < 1E12) {
            return formatNumber(balance / 1.0E9) + "B";
        }
        if (balance < 1E15) {
            return formatNumber(balance / 1.0E12) + "T";
        }
        if (balance < 1E18) {
            return formatNumber(balance / 1.0E15) + "Q";
        }
        if (balance < 1E21) {
            return formatNumber(balance / 1.0E18) + "QQ";
        }
        if (balance < 1E24) {
            return formatNumber(balance / 1.0E21) + "S";
        }
        if (balance < 1E27) {
            return formatNumber(balance / 1.0E24) + "SS";
        }
        if (balance < 1E30) {
            return formatNumber(balance / 1.0E27) + "OC";
        }
        if (balance < 1E33) {
            return formatNumber(balance / 1.0E30) + "N";
        }
        if (balance < 1E36) {
            return formatNumber(balance / 1.0E33) + "D";
        }
        if (balance < 1E39) {
            return formatNumber(balance / 1.0E36) + "uD";
        }
        if (balance < 1E42) {
            return formatNumber(balance / 1.0E39) + "dD";
        }
        if (balance < 1E45) {
            return formatNumber(balance / 1.0E42) + "tD";
        }
        if (balance < 1E48) {
            return formatNumber(balance / 1.0E45) + "qD";
        }
        if (balance < 1E51) {
            return formatNumber(balance / 1.0E48) + "QD";
        }
        if (balance < 1E54) {
            return formatNumber(balance / 1.0E51) + "sD";
        }
        if (balance < 1E57) {
            return formatNumber(balance / 1.0E54) + "SD";
        }
        if (balance < 1E60) {
            return formatNumber(balance / 1.0E57) + "OD";
        }
        if (balance < 1E63) {
            return formatNumber(balance / 1.0E60) + "ND";
        }
        if (balance < 1E66) {
            return formatNumber(balance / 1.0E63) + "tT";
        }
        if (balance < 1E69) {
            return formatNumber(balance / 1.0E66) + "DT";
        }
        if (balance < 1E72) {
            return formatNumber(balance / 1.0E69) + "TT";
        }
        if (balance < 1E75) {
            return formatNumber(balance / 1.0E72) + "qT";
        }
        if (balance < 1E78) {
            return formatNumber(balance / 1.0E75) + "QT";
        }
        if (balance < 1E303) {
            return formatNumber(balance / 1.0E75) + "QT";
        }
        return formatNumber(balance / 1.0E303) + "C";
    }
}
