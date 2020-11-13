
public class WordParser {
    private String word;
    private final Probabilities probabilities;

    public WordParser(String word, Probabilities proba){
        this.word=word;
        this.probabilities=proba;
    }

    public WordParser(Probabilities proba){
        this.probabilities=proba;
    }

    public double getProba(){
        double res = 0;
        for(int i = 0; i < word.length()-3; i++){
            String subword = word.substring(i, i+4);
            res += probabilities.getProba(subword);
        }
        return res;
    }
    public double getProba(String word){
        double res = 0;
        for(int i = 0; i < word.length()-3; i++){
            String subword = word.substring(i, i+4);
            res += probabilities.getProba(subword);
        }
        return res;
    }
}
