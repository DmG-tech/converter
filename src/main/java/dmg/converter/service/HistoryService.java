package dmg.converter.service;

import dmg.converter.entity.Conversion;
import dmg.converter.repository.ConversionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static dmg.converter.util.SecurityUtil.getCurrentUserId;

@Service
public class HistoryService {

    @Autowired
    private ConversionRepository repository;

    public List<Conversion> getByDate(LocalDate date) {
        return rounding(repository.getByDate(date, getCurrentUserId()));
    }

    public List<Conversion> getAll() {
        return rounding(repository.getAll(getCurrentUserId()));
    }

    private List<Conversion> rounding(List<Conversion> conversions) {
            conversions.forEach(conversion -> conversion.setResultValue(new BigDecimal(conversion.getResultValue()).setScale(2, RoundingMode.CEILING).doubleValue()));
        return conversions;
    }
}
