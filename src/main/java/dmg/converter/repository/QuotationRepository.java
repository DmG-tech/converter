package dmg.converter.repository;

import dmg.converter.entity.Quotation;

import java.time.LocalDate;
import java.util.List;

public interface QuotationRepository {
    // null if not found, when updated
    Quotation save(Quotation quotation, int currencyId);

    // false if not found
    boolean delete(int id, int currencyId);

    // null if not found
    Quotation get(int id, int currencyId);

    // null if not found
    Quotation getByCurrencyAndDate(int currencyId, LocalDate date);

    // null if not found
    Quotation getByCurrencyCharCodeAndDate(String charCode, LocalDate date);

    List<Quotation> getAllByDate(LocalDate date);
}
