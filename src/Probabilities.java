import java.util.ArrayList;
import java.util.HashMap;

public class Probabilities {

    ArrayList <String> word;
    ArrayList <Double> count;
    ArrayList <Double> log;
    private HashMap<String, Double> tab ;

    public Probabilities(){
        this.word = new ArrayList<>();
        this.count = new ArrayList<>();
        this.log = new ArrayList<>();
        this.tab = new HashMap<>();
    }

    public void add(String word, String count){
        this.word.add(word);
        double dcount = Double.parseDouble(count);
        this.count.add(dcount);
        double proba = Math.log(dcount/Main.occurrences);
        this.log.add(proba);
        tab.put(word,proba);
    }

    public void print(){
        System.out.println("print"+tab.get("ABBA"));
        System.out.println(word.size());
    }
    public double getProba(String word){
        if (tab.containsKey(word))
            return tab.get(word);
        else return Math.log(0.01/ Main.occurrences);
    }

}
