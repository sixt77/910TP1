
public class WordParser {
    private String word;
    private Probabilities probabilities;
    static final long occurrences = 95675200;

    public WordParser(String word, Probabilities proba){
        this.word=word;
        this.probabilities=proba;
    }

    public double getProba(){
        int i=0;
        double res = 0;
        System.out.println(this.word);
        while (i<=word.length()-4){
            String subword = word.substring(i, i+4);
            try {
                res += probabilities.getProba(subword);
            }
            catch(Exception e) {
                res += Math.log(0.01/occurrences);
            }
            i++;
        }
        return res;
    }
}
