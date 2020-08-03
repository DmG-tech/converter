package dmg.converter.util;

import javax.validation.constraints.NotNull;

public class MyDoubleParser {

    private MyDoubleParser() {
    }

    public static double parse(@NotNull String s) {
        String value = s.replace(",", ".");
        try {
            return Double.parseDouble(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
