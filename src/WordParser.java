
public class WordParser {
    private final String word;
    private final Probabilities probabilities;
    static final long occurrences = 95675200;

    public WordParser(String word, Probabilities proba){
        this.word=word;
        this.probabilities=proba;
    }

    public double getProba(){
        double res = 0;
        for(int i = 0; i < word.length()-3; i++){
            String subword = word.substring(i, i+4);
            try {
                res += probabilities.getProba(subword);
            }
            catch(Exception e) {
                res += Math.log(0.01/occurrences);
            }
        }
        return res;
    }
}
