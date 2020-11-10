import java.util.ArrayList;
import java.util.HashMap;

public class Occurencies {
    ArrayList <String> word;
    ArrayList <Double> count;
    ArrayList <Double> log;
    private HashMap<String, Double> tab ;
    static final long occurrences = 95675200;

    public Occurencies(){
        this.word = new ArrayList<>();
        this.count = new ArrayList<>();
        this.log = new ArrayList<>();
        this.tab = new HashMap<>();
    }

    public void add(String word, String count){
        this.word.add(word);
        double dcount = Double.parseDouble(count);
        this.count.add(dcount);
        double proba = Math.log(dcount/occurrences);
        this.log.add(proba);
        tab.put(word,proba);
    }

    public void print(){

        System.out.println(tab.get("TION"));
        System.out.print(word.size());
    }
    public double getProba(String word){
        return tab.get(word);
    }
}