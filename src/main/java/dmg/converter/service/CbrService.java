package dmg.converter.service;

import dmg.converter.to.CurrencyTo;
import dmg.converter.util.MyDoubleParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class CbrService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final String URL_CBR = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=%s";

    private final String URL_CBR_DEFAULT = "http://www.cbr.ru/scripts/XML_daily.asp?";

    @Autowired
    private CurrencyService currencyService;

    private Document getDocOfQuotes(LocalDate date) throws ParserConfigurationException, IOException, SAXException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String url = String.format(URL_CBR, date.format(formatter));

        log.info("try get data for input date {}", date.format(formatter));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(url);
        if (document == null) {

            log.debug("failed to get data for input date");
            document = builder.parse(URL_CBR_DEFAULT);
        }
        return document;
    }

    public LocalDate getQuotes(LocalDate date) {
        Document document = null;

        try {
            document = getDocOfQuotes(date);
        } catch (Exception ignored) {}

        if (document != null) {
            String stringDate = document.getElementsByTagName("ValCurs").item(0).getAttributes().getNamedItem("Date").getTextContent();
            LocalDate currentDate = LocalDate.parse(stringDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));

            log.info("get current date {}", stringDate);

            NodeList nodeList = document.getElementsByTagName("Valute");

            for (int i = 0; i < nodeList.getLength(); i++) {

                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    parseQuote(element, currentDate);
                }
            }

            return currentDate;
        }

        return null;
    }

    private void parseQuote(Element element, LocalDate date) {

        String numCode = element.getElementsByTagName("NumCode").item(0).getTextContent();
        String charCode = element.getElementsByTagName("CharCode").item(0).getTextContent();
        int nominal = Integer.parseInt(element.getElementsByTagName("Nominal").item(0).getTextContent());
        String name = element.getElementsByTagName("Name").item(0).getTextContent();
        double rubValue = MyDoubleParser.parse(element.getElementsByTagName("Value").item(0).getTextContent());

        currencyService.save(new CurrencyTo(numCode, charCode, nominal, name, rubValue, date));
    }

}
