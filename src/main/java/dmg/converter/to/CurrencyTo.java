package dmg.converter.to;

import java.time.LocalDate;

public class CurrencyTo {

    private final String numCode;

    private final String charCode;

    private final int nominal;

    private final String name;

    private final double rubValue;

    private final LocalDate date;

    public CurrencyTo(String numCode, String charCode, int nominal, String name, double rubValue, LocalDate date) {
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
        this.rubValue = rubValue;
        this.date = date;
    }

    public String getNumCode() {
        return numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public String getName() {
        return name;
    }

    public double getRubValue() {
        return rubValue;
    }

    public LocalDate getDate() {
        return date;
    }
}
