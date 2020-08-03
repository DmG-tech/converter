package dmg.converter.repository.datajpa;

import dmg.converter.entity.Currency;
import dmg.converter.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DataJpaCurrencyRepository implements CurrencyRepository {

    private static final Sort SORT_CHAR_CODE = Sort.by(Sort.Direction.ASC, "charCode");

    @Autowired
    private  CrudCurrencyRepository crudRepository;

    @Override
    public Currency save(Currency currency) {
        return crudRepository.save(currency);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Currency get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Currency> getAll() {
        return crudRepository.findAll(SORT_CHAR_CODE);
    }

    @Override
    public Currency getByCharCode(String charCode) {
        return crudRepository.getByCharCode(charCode);
    }
}