package dmg.converter.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "currencies")
public class Currency extends AbstractBaseEntity {

    private String numCode;

    private String charCode;

    private int nominal;

    private String name;

    public Currency() {
    }

    public Currency(String numCode, String charCode, int nominal, String name) {
        this(null, numCode, charCode, nominal, name);
    }

    public Currency(Integer id, String numCode, String charCode, int nominal, String name) {
        super(id);
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public int getNominal() {
        return nominal;
    }

    public void setNominal(int nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
