package dmg.converter.entity;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "quotes", uniqueConstraints = {@UniqueConstraint(columnNames = {"currency_id", "date"}, name = "quotes_unique_currency_date_idx")})
public class Quotation extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "currency_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Currency currency;

    private double rubValue;

    private LocalDate date;

    public Quotation() {
    }

    public Quotation(Currency currency, double rubValue, LocalDate date) {
        this(null, currency, rubValue, date);
    }

    public Quotation(Integer id, Currency currency, double rubValue, LocalDate date) {
        super(id);
        this.currency = currency;
        this.rubValue = rubValue;
        this.date = date;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public double getRubValue() {
        return rubValue;
    }

    public void setRubValue(double rubValue) {
        this.rubValue = rubValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
