import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Occurencies oc = FileTool.buildOccurencies("C:\\Users\\sacha\\test.save");
        String data = FileTool.readFile("C:\\Users\\sacha\\test1.txt");
        double sum = 0;
        for(int i = 0; i < data.length()-3; i++){
            sum = sum + oc.getProba(data.substring(i, i+4));
        }
        System.out.println(sum);
    }
}
