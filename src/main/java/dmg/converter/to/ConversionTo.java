package dmg.converter.to;

import java.time.LocalDate;

public class ConversionTo {

    private String fromCurrencyCharCode;

    private String toCurrencyCharCode;

    private double inputValue;

    private double resultValue;

    private LocalDate date;

    public ConversionTo(String fromCurrencyCharCode, String toCurrencyCharCode, double inputValue, LocalDate date) {
        this.fromCurrencyCharCode = fromCurrencyCharCode;
        this.toCurrencyCharCode = toCurrencyCharCode;
        this.inputValue = inputValue;
        this.date = date;
    }

    public ConversionTo(String fromCurrencyCharCode, String toCurrencyCharCode, double inputValue, double resultValue, LocalDate date) {
        this.fromCurrencyCharCode = fromCurrencyCharCode;
        this.toCurrencyCharCode = toCurrencyCharCode;
        this.inputValue = inputValue;
        this.resultValue = resultValue;
        this.date = date;
    }

    public String getFromCurrencyCharCode() {
        return fromCurrencyCharCode;
    }

    public void setFromCurrencyCharCode(String fromCurrencyCharCode) {
        this.fromCurrencyCharCode = fromCurrencyCharCode;
    }

    public String getToCurrencyCharCode() {
        return toCurrencyCharCode;
    }

    public void setToCurrencyCharCode(String toCurrencyCharCode) {
        this.toCurrencyCharCode = toCurrencyCharCode;
    }

    public double getInputValue() {
        return inputValue;
    }

    public void setInputValue(double inputValue) {
        this.inputValue = inputValue;
    }

    public double getResultValue() {
        return resultValue;
    }

    public void setResultValue(double resultValue) {
        this.resultValue = resultValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
