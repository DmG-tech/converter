package dmg.converter.controller;

import dmg.converter.entity.Currency;
import dmg.converter.service.CbrService;
import dmg.converter.service.ConverterService;
import dmg.converter.service.CurrencyService;
import dmg.converter.to.ConversionTo;
import dmg.converter.util.MyDoubleParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping(value = {"/", "/index"})
public class ConverterController {

    @Autowired
    private CbrService cbrService;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public String convert(Model model) {
        cbrService.getQuotes(LocalDate.now());
        model.addAttribute("currencies", currencyService.getAll());

        return "/index";
    }

    @PostMapping
    public String convert(HttpServletRequest request, Model model) {
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        double inputValue = MyDoubleParser.parse(request.getParameter("inputValue"));

        List<Currency> currencies = currencyService.getAll();
        model.addAttribute("currencies", currencies);

        ConversionTo conversionTo = converterService.convert(new ConversionTo(from, to, inputValue, LocalDate.now()));

        conversionTo.setFromCurrencyCharCode(conversionTo.getFromCurrencyCharCode() + " (" + currencyService.getByCharCode(conversionTo.getFromCurrencyCharCode()).getName() + ")");
        conversionTo.setToCurrencyCharCode(conversionTo.getToCurrencyCharCode() + " (" + currencyService.getByCharCode(conversionTo.getToCurrencyCharCode()).getName() + ")");

        model.addAttribute("conversion", conversionTo);
        return "/index";
    }
}
