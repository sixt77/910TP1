import java.util.Scanner;

/**
 * Menu class
 */
public class Menu {
    
    public void displayMenu() {
        System.out.println();
        System.out.println("1--Generate a key");
        System.out.println("2--Compute score with a n-gramm file");
        System.out.println("3--Encipher a message");
        System.out.println("4--Decipher a message");
        System.out.println("5--Decrypt a message");
        System.out.println("6--Help");
        System.out.println("7--Exit");
        System.out.println();
    }

    public void processChoice(int choice) {
        switch (choice) {
            case 1:
                createPlayfair();
                break;
            case 2:
                score();
                break;
            case 3:
                encode();
                break;
            case 4:
                decode();
                break;
            case 5:
                crack();
                break;
            case 6:
                help();
                break;
            case 7:
                System.out.println("See you");
                break;
            default:
                System.out.println("Wrong choice");
                break;
        }
    }

    public void createPlayfair() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the key");
        Playfair pf = new Playfair(sc.nextLine());
        pf.printTable();
    }

    public void score() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the n-gramme filepath then the message to be scored");
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        String message = input.toString().split(" ")[0];
        String path = input.toString().split(" ")[1];

        Main.oc = FileReader.buildOccurences(path);
        WordParser wp = new WordParser(Main.oc);
        System.out.println("message : " + message + "score : " + wp.getProba(Playfair.parseString(message)));
    }

    private String[] getLine() {
        Scanner sc = new Scanner(System.in);
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        return input.toString().split(" ");
    }

    public void encode() {
        System.out.println("Input the key and the message to be encoded");

        String[] line = getLine();
        Playfair pf = new Playfair(Playfair.parseString(line[0]));
        String encoded = pf.encode(Playfair.parseString(line[1]));
        System.out.println(encoded);
    }

    public void decode() {
        System.out.println("Input the key and the message to be decoded");

        String[] line = getLine();
        Playfair pf = new Playfair(Playfair.parseString(line[0]));
        String decoded = pf.decode(Playfair.parseString(line[1]));
        System.out.println(decoded);
    }

    public void crack() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the options, the n-gramme file then the encrypted message");
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        String[] line = input.toString().split(" ");
        double tempMin = 0;
        double tempMax = (line[line.length - 1].length()) / 8.0;
        double step = 0.5;
        int number = 50000;
        String file = "";
        String message = "";
        int i;
        for (i = 0; i < line.length; i++) {
            if (line[i].equals("-t"))
                tempMin = Double.parseDouble(line[i + 1]);
            if (line[i].equals("-T"))
                tempMin = Double.parseDouble(line[i + 1]);
            if (line[i].equals("-s"))
                step = Double.parseDouble(line[i + 1]);
            if (line[i].equals("-n"))
                number = Integer.parseInt(line[i + 1]);
            if (i == line.length - 2)
                file = line[i];
            if (i == line.length - 1)
                message = line[i];
        }

        Playfair pf = new Playfair("");
        Main.oc = FileReader.buildOccurences(file);
        pf.upgradedCrack(message, tempMax, step, tempMin, number);
    }

    public void help() {
        System.out.println("1. generate key -> <KEY>");
        System.out.println();
        System.out.println("2. score -> <NGRAM_FILE> <MESSAGE(as String)>");
        System.out.println();
        System.out.println("3/4. encode/decode -> <KEY> <MESSAGE(as String)>");
        System.out.println();
        System.out.println("5. crack -> [OPTIONS] <NGRAM_FILE> <MESSAGE(as String)> ");
        System.out.println("crack options : ");
        System.out.println(" -t : minimal temperature ");
        System.out.println(" -T : maximal temperature ");
        System.out.println(" -s : step");
        System.out.println(" -n : number of iterations");
        System.out.println();
    }
}
