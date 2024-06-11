import java.math.BigDecimal;
import java.time.LocalDate;

public class SoutezniPar {
    private String jmeno;
    private int startNum;
    private BigDecimal startovne;
    private LocalDate datumPrihlaseni;
    private boolean wildCard;

    public SoutezniPar(String jmeno, int startNum, BigDecimal startovne, LocalDate datumPrihlaseni, boolean wildCard) {
        this.jmeno = jmeno;
        this.startNum = startNum;
        this.startovne = startovne;
        this.datumPrihlaseni = datumPrihlaseni;
        this.wildCard = wildCard;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }

    public BigDecimal getStartovne() {
        return startovne;
    }

    public void setStartovne(BigDecimal startovne) {
        this.startovne = startovne;
    }

    public LocalDate getDatumPrihlaseni() {
        return datumPrihlaseni;
    }

    public void setDatumPrihlaseni(LocalDate datumPrihlaseni) {
        this.datumPrihlaseni = datumPrihlaseni;
    }

    public boolean isWildCard() {
        return wildCard;
    }

    public void setWildCard(boolean wildCard) {
        this.wildCard = wildCard;
    }
}
