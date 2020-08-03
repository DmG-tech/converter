package dmg.converter.service;

import dmg.converter.entity.Conversion;
import dmg.converter.entity.Currency;
import dmg.converter.entity.Quotation;
import dmg.converter.repository.ConversionRepository;
import dmg.converter.repository.QuotationRepository;
import dmg.converter.to.ConversionTo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static dmg.converter.util.ConverterUtil.calculate;
import static dmg.converter.util.ConverterUtil.getTos;
import static dmg.converter.util.SecurityUtil.getCurrentUserId;

@Service
public class ConverterService {

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ConversionRepository conversionRepository;

    @Autowired
    private CbrService cbrService;

    @Transactional
    public Conversion save(ConversionTo conversionTo) {
        Currency from = currencyService.getByCharCode(conversionTo.getFromCurrencyCharCode());
        Currency to = currencyService.getByCharCode(conversionTo.getToCurrencyCharCode());

        return conversionRepository.save(new Conversion(from, to, conversionTo.getInputValue(), conversionTo.getResultValue(), conversionTo.getDate()), getCurrentUserId());
    }

    @Transactional
    public ConversionTo convert(ConversionTo conversionTo) {
        Quotation from = quotationRepository.getByCurrencyCharCodeAndDate(conversionTo.getFromCurrencyCharCode(), conversionTo.getDate());
        Quotation to = quotationRepository.getByCurrencyCharCodeAndDate(conversionTo.getToCurrencyCharCode(), conversionTo.getDate());

        if (from == null || to == null) {
            LocalDate lastAvailableDate = cbrService.getQuotes(LocalDate.now());

            if (lastAvailableDate == null) {
                return conversionTo;
            }

            conversionTo.setDate(lastAvailableDate);

            from = quotationRepository.getByCurrencyCharCodeAndDate(conversionTo.getFromCurrencyCharCode(), lastAvailableDate);
            to = quotationRepository.getByCurrencyCharCodeAndDate(conversionTo.getToCurrencyCharCode(), lastAvailableDate);
        }

        conversionTo.setResultValue(calculate(from, to, conversionTo.getInputValue()));
        save(conversionTo);

        return conversionTo;
    }

    public List<ConversionTo> getAll() {
        return getTos(conversionRepository.getAll(getCurrentUserId()));
    }
}
