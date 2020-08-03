package dmg.converter.controller;

import dmg.converter.entity.Conversion;
import dmg.converter.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping
    public String getHistory(Model model) {
        List<Conversion> conversions = historyService.getAll();
        model.addAttribute("history", conversions);
        checkEmpty(conversions, model);
        return "history";
    }

    @PostMapping
    public String getHistory(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("date") LocalDate date, Model model) {
        List<Conversion> conversions;
        if (date == null) {
            conversions = historyService.getAll();
        } else {
            conversions = historyService.getByDate(date);
        }
        model.addAttribute("history", conversions);
        checkEmpty(conversions, model);
        return "history";
    }

    private void checkEmpty(List<Conversion> conversions, Model model) {
        if (conversions.isEmpty()) {
            conversions.add(new Conversion());
            model.addAttribute("isEmpty", "1");
        }
        else model.addAttribute("isEmpty", "0");
    }
}
