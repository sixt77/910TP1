import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class FileTool {
    public static Occurencies buildOccurencies() {
        Occurencies oc = new Occurencies();
        try {
            File myObj = new File("C:\\Users\\sacha\\test.save");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                oc.add(data.substring(0,4), data.substring(5,data.length()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return oc;
    }

}
