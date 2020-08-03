package dmg.converter.util;

import dmg.converter.entity.Conversion;
import dmg.converter.entity.Quotation;
import dmg.converter.to.ConversionTo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class ConverterUtil {

    private ConverterUtil() { }

    private static ConversionTo createTo(Conversion conversion){
        return new ConversionTo(conversion.getFromCurrency().getCharCode(), conversion.getToCurrency().getCharCode(), conversion.getInputValue(), conversion.getResultValue(), conversion.getDate());
    }

    public static List<ConversionTo> getTos(List<Conversion> conversions) {
        List<ConversionTo> conversionTos = new LinkedList<>();
        conversions.forEach(conversion -> conversionTos.add(createTo(conversion)));
        return conversionTos;
    }

    public static double calculate(Quotation from, Quotation to, double inputValue) {
        double result =  (from.getRubValue() / from.getCurrency().getNominal()) / (to.getRubValue() / to.getCurrency().getNominal()) * inputValue;
        return new BigDecimal(result).setScale(2, RoundingMode.CEILING).doubleValue();
    }
}
