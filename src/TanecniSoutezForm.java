import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TanecniSoutezForm extends JFrame {
    private JPanel panel;
    private JTable tabulka;
    private JTextField txtNumOfPairs;
    private JCheckBox chckIsFinished;
    public List<SoutezniPar> seznamParu = new ArrayList<>();
    private File selectedFile;

    public TanecniSoutezForm(){
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Taneční soutěšž Příjmení");
        setSize(500,500);
        initMenu();
        seznamParu.add(new SoutezniPar("Jana Nováková a Šimon Vystrčil", 1, new BigDecimal("200"), LocalDate.of(2021, 5, 1), false));
        seznamParu.add(new SoutezniPar("Petr Novák a Tereza Kostková", 3, new BigDecimal("200"), LocalDate.of(2021, 5, 1), false));
        seznamParu.add(new SoutezniPar("Mgjagag Uhgaiugha a Nhfauigha IJoijhg", 6, new BigDecimal("200"), LocalDate.of(2021, 5, 1), false));
        renderTable();
        display();
    }

    public void initMenu(){
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);

        JMenu soutez = new JMenu("Soutěž");
        mbar.add(soutez);

        JMenuItem load = new JMenuItem("Načti");
        soutez.add(load);
        load.addActionListener(_-> loadFile());

        JMenuItem end = new JMenuItem("Ukonči");
        soutez.add(end);
        end.addActionListener(_-> ukonciSoutez());

        JMenuItem stats = new JMenuItem("Statistika");
        soutez.add(stats);
        stats.addActionListener(_-> showStats());
    }

    public void showStats(){
        Soutez s = new Soutez("", false, null, seznamParu);
        JOptionPane.showMessageDialog(this,"Startovné: " + s.startovneCelkem() + "\n" + "Počet divokých karet: " + s.pocetDivokychKaret());
    }

    public void ukonciSoutez(){
        chckIsFinished.setSelected(true);
    }

    public void loadFile(){
        JFileChooser fc = new JFileChooser(".");
        fc.setFileFilter(new FileNameExtensionFilter("text", "txt"));
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION){
            selectedFile = fc.getSelectedFile();
            readFile(selectedFile);
            renderTable();
            display();
        }
    }

    public void readFile(File selectedFile){
        try(Scanner sc = new Scanner(new BufferedReader(new FileReader(selectedFile)))){
            while (sc.hasNextLine()){
                String line = sc.nextLine();
                String[] bloky = line.split(":");
                int startNum = Integer.parseInt(bloky[0]);
                String nazev = bloky[1];
                BigDecimal startovne = new BigDecimal(bloky[2]);
                LocalDate datumPrihlaseni = LocalDate.parse(bloky[3]);
                boolean wildCard = bloky[4].equals("x");
                seznamParu.add(new SoutezniPar(nazev, startNum, startovne, datumPrihlaseni, wildCard));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void display(){
        Soutez s = new Soutez("", false, null, seznamParu);
        txtNumOfPairs.setText(String.valueOf(seznamParu.size()));
        chckIsFinished.setSelected(s.isDokoncena());
    }

    public void renderTable(){
        tabulka.setModel(new TabulkaModel());
    }

    public class TabulkaModel extends AbstractTableModel{

        @Override
        public int getRowCount() {
            return seznamParu.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            SoutezniPar s = seznamParu.get(rowIndex);
            switch (columnIndex){
                case 0: return s.getStartNum();
                case 1: return s.getJmeno();
                case 2: return s.getDatumPrihlaseni();
                case 3: return s.isWildCard() ? "ano" : "ne";
            }
            return null;
        }

        @Override
        public String getColumnName(int columnIndex){
            switch (columnIndex){
                case 0:
                    return "Startovní číslo";
                case 1:
                    return "Jméno";
                case 2:
                    return "Datum přihlášení";
                case 3:
                    return "Divoká karta";
            }
            return super.getColumnName(columnIndex);
        }
    }
}
