import java.util.ArrayList;

public class Occurencies {
    ArrayList <String> word;
    ArrayList <Integer> count;
    public Occurencies(){
        this.word = new ArrayList<>();
        this.count = new ArrayList<>();
    }
    public void add(String word, String count){
        this.word.add(word);
        Integer intCount = Integer.parseInt(count);
        this.count.add(intCount);
    }
    public void print(){
        for(int i = 0; i < this.word.size(); i++){
            System.out.println(this.word.get(i)+" : "+this.count.get(i));
        }
    }
    public float getProba(String word){
        if(this.word.indexOf(word) != -1){
            return (float) this.count.get(this.word.indexOf(word))/(float) this.count.size();
        }else{
            return 0;
        }

    }
}
