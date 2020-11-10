import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Occurencies oc = FileTool.buildOccurencies("C:\\Users\\sacha\\test.save");
        String data = FileTool.readFile("C:\\Users\\sacha\\test1.txt");
        //oc.print();

    }

}
