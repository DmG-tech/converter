package dmg.converter.repository.datajpa;

import dmg.converter.entity.Quotation;
import dmg.converter.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaQuotationRepository implements QuotationRepository {

    @Autowired
    private CrudQuotationRepository crudQuotationRepository;

    @Autowired
    private CrudCurrencyRepository crudCurrencyRepository;

    @Override
    public Quotation save(Quotation quotation, int currencyId) {
        if (!quotation.isNew() && get(quotation.getId(), currencyId) == null) {
            return null;
        }
        quotation.setCurrency(crudCurrencyRepository.getOne(currencyId));
        return crudQuotationRepository.save(quotation);
    }

    @Override
    public boolean delete(int id, int currencyId) {
        return crudQuotationRepository.delete(id, currencyId) != 0;
    }

    @Override
    public Quotation get(int id, int currencyId) {
        return crudQuotationRepository.findById(id)
                .filter(quotation -> quotation.getCurrency().getId() == currencyId)
                .orElse(null);
    }

    @Override
    public Quotation getByCurrencyAndDate(int currencyId, LocalDate date) {
        return crudQuotationRepository.getByCurrencyAndDate(currencyId, date);
    }

    @Override
    public Quotation getByCurrencyCharCodeAndDate(String charCode, LocalDate date) {
        return crudQuotationRepository.getByCurrencyCharCodeAndDate(charCode, date);
    }

    @Override
    public List<Quotation> getAllByDate(LocalDate date) {
        return crudQuotationRepository.getAllByDate(date);
    }

}
