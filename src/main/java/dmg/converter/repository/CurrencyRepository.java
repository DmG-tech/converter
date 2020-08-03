package dmg.converter.repository;


import dmg.converter.entity.Currency;

import java.util.List;

public interface CurrencyRepository {
    // null if not found, when updated
    Currency save(Currency currency);

    // false if not found
    boolean delete(int id);

    // null if not found
    Currency get(int id);

    // null if not found
    Currency getByCharCode(String charCode);

    List<Currency> getAll();
}
