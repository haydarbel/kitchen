package be.vdab.keuken.domain;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "artikels")
public class Artikel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String naam;
    private BigDecimal aankoopprijs;
    private BigDecimal verkoopprijs;

    public Artikel(String naam, BigDecimal aankoopprijs, BigDecimal verkoopprijs) {
        this.naam = naam;
        this.aankoopprijs = aankoopprijs;
        this.verkoopprijs = verkoopprijs;
    }

    protected Artikel() {
    }

    public String getNaam() {
        return naam;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getAankoopprijs() {
        return aankoopprijs;
    }

    public BigDecimal getVerkoopprijs() {
        return verkoopprijs;
    }
}
