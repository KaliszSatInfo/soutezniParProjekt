import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Soutez {
    private String nazev;
    private boolean dokoncena;
    private SoutezniPar vitez;
    private List<SoutezniPar> seznamParu = new ArrayList<>();

    public Soutez(String nazev, boolean dokoncena, SoutezniPar vitez, List<SoutezniPar> seznamParu) {
        this.nazev = nazev;
        this.dokoncena = equals("ne");
        this.vitez = null;
        this.seznamParu = seznamParu;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public boolean isDokoncena() {
        return dokoncena;
    }

    public void setDokoncena(boolean dokoncena) {
        this.dokoncena = dokoncena;
    }

    public SoutezniPar getVitez() {
        return vitez;
    }

    public void setVitez(SoutezniPar vitez) {
        this.vitez = vitez;
    }

    public List<SoutezniPar> getSeznamParu() {
        return seznamParu;
    }

    public void setSeznamParu(List<SoutezniPar> seznamParu) {
        this.seznamParu = seznamParu;
    }

    public BigDecimal startovneCelkem(){
        BigDecimal startovneCelkem = BigDecimal.ZERO;
        for (SoutezniPar s : seznamParu){
            startovneCelkem = startovneCelkem.add(s.getStartovne());
        }
        return startovneCelkem;
    }

    public int pocetDivokychKaret(){
        int pocetKaret = 0;
        for (SoutezniPar s : seznamParu){
            if (s.isWildCard()){
                pocetKaret++;
            }
        }
        return pocetKaret;
    }
}
