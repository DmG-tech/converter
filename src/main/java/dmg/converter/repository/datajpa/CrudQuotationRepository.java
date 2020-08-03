package dmg.converter.repository.datajpa;

import dmg.converter.entity.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudQuotationRepository extends JpaRepository<Quotation, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Quotation q WHERE q.id=:id AND q.currency.id=:currencyId")
    int delete(@Param("id") int id, @Param("currencyId") int currencyId);

    @Query("SELECT q FROM Quotation q WHERE q.currency.id=:currencyId AND q.date=:date")
    Quotation getByCurrencyAndDate(@Param("currencyId") int currencyId, @Param("date") LocalDate date);

    @Query("SELECT q FROM Quotation q WHERE q.date=:date ORDER BY q.date")
    List<Quotation> getAllByDate(@Param("date") LocalDate date);

    @Query("SELECT q FROM Quotation q WHERE q.currency.charCode=:charCode AND q.date=:date")
    Quotation getByCurrencyCharCodeAndDate(@Param("charCode") String charCode, @Param("date") LocalDate date);
}
