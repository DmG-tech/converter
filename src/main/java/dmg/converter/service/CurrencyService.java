package dmg.converter.service;

import dmg.converter.entity.Currency;
import dmg.converter.entity.Quotation;
import dmg.converter.repository.CurrencyRepository;
import dmg.converter.repository.QuotationRepository;
import dmg.converter.to.CurrencyTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private QuotationRepository quotationRepository;

    @Transactional
    public void save(CurrencyTo currencyTo) {
        Currency currency = currencyRepository.getByCharCode(currencyTo.getCharCode());
        if (currency != null) {
            Quotation quotation = quotationRepository.getByCurrencyCharCodeAndDate(currencyTo.getCharCode(), currencyTo.getDate());
            if (quotation == null) {
                quotationRepository.save(new Quotation(currency, currencyTo.getRubValue(), currencyTo.getDate()), currency.getId());
            }
        }
        else {
            currency = currencyRepository.save(new Currency(currencyTo.getNumCode(), currencyTo.getCharCode(), currencyTo.getNominal(), currencyTo.getName()));
            quotationRepository.save(new Quotation(currency, currencyTo.getRubValue(), currencyTo.getDate()), currency.getId());
        }
    }

    public List<Currency> getAll() {
        return currencyRepository.getAll();
    }

    public Currency getByCharCode(String charCode) {
        return currencyRepository.getByCharCode(charCode);
    }
}
