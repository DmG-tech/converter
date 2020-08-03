package dmg.converter.repository.datajpa;

import dmg.converter.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface CrudCurrencyRepository extends JpaRepository<Currency, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Currency c WHERE c.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT c FROM Currency c WHERE c.charCode=:charCode")
    Currency getByCharCode(@Param("charCode") String charCode);
}
