import java.util.Random;
import java.util.Scanner;

public class Playfair {

    private final String keyword;
    public String key;
    private String[][] ciperTable;

    public Playfair(String keyword) {
        this.keyword = parseString(keyword);
        generateKey(this.keyword);

    }

    public void inputMessage(){
        Scanner sc = new Scanner(System.in);
        // prompts user for message to be encoded
        System.out.println("Input the message to be encoded");
        StringBuilder input = new StringBuilder(parseString(sc.nextLine()));
        while (input.toString().equals(""))
            input.append(parseString(sc.nextLine()));
        System.out.println();

        String output = encode(input.toString());
        generateKey("ZYXWVUTSQLKGFDBAENHPORCIM");
        System.out.println(decode(output));
        System.out.println(this.key);
        printTable();
        System.out.println(output);
        System.out.println(decode(output));
        WordParser wp = new WordParser(decode(output), Main.oc);
        System.out.print(wp.getProba());
    }

    private String parseString(String parse) {

        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }

    public void generateKey(String key) {
        String[][] playfairTable = new String[5][5];
        StringBuilder keybuild = new StringBuilder();
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        // fill string array with empty string
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                playfairTable[i][j] = "";

        for (int k = 0; k < keyString.length(); k++) {
            boolean repeat = false;
            boolean used = false;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
                        repeat = true;
                    } else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        keybuild.append(keyString.charAt(k));
                        used = true;
                    }
                }
            }
        }
        this.key = keybuild.toString();
        this.ciperTable = playfairTable;
    }

    private int[] getPoint(char c) {
        int[] pt = new int[2];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (c == ciperTable[i][j].charAt(0)) {
                    pt[0] = i;
                    pt[1] = j;
                }
        return pt;
    }

    public String encode(String in) {
        int length = (int) in.length() / 2 + in.length() % 2;

        // insert x between double-letter digraphs & redefines "length"
        for (int i = 0; i < (length - 1); i++) {
            if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }

        // adds an x to the last digraph, if necessary
        String[] digraph = new String[length];
        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && in.length() / 2 == (length - 1))
                in = in + "X";
            digraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
        }

        // encodes the digraphs and returns the output
        StringBuilder out = new StringBuilder();
//        String[] encDigraphs = new String[length];
//        encDigraphs = encodePair(digraph);
        for (int k = 0; k < length; k++) {
            out.append(encodePair(digraph[k]));//encDigraphs[k];
        }
        return out.toString();
    }

    /**
     * Encode a pair of letter with the Cipher Table
     *
     * @param di
     * @return the encoded pair
     */
    public String encodePair(String di) {
        String enc;
        char a = di.charAt(0);
        char b = di.charAt(1);
        int r1 = getPoint(a)[0];
        int r2 = getPoint(b)[0];
        int c1 = getPoint(a)[1];
        int c2 = getPoint(b)[1];

        // case 1: letters in digraph are of same row, shift columns to right
        if (r1 == r2) {
            c1 = (c1 + 1) % 5;
            c2 = (c2 + 1) % 5;

            // case 2: letters in digraph are of same column, shift rows down
        } else if (c1 == c2) {
            r1 = (r1 + 1) % 5;
            r2 = (r2 + 1) % 5;

            // case 3: letters in digraph form rectangle, swap first column # with second column #
        } else {
            int temp = c1;
            c1 = c2;
            c2 = temp;
        }

        //performs the table look-up and puts those values into the encoded array
        enc = ciperTable[r1][c1] + "" + ciperTable[r2][c2];

        return enc;
    }

    public String decode(String out) {
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = getPoint(a)[0];
            int r2 = getPoint(b)[0];
            int c1 = getPoint(a)[1];
            int c2 = getPoint(b)[1];
            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded.append(ciperTable[r1][c1]).append(ciperTable[r2][c2]);
        }
        parseX(decoded);
        return decoded.toString();
    }

    private StringBuilder parseX(StringBuilder decoded) {
        if (decoded.charAt(decoded.length() - 1) == 'X') {
            decoded.deleteCharAt(decoded.length() - 1);
        }
        int i = 1;
        while (i < decoded.length() - 2) {
            if (decoded.charAt(i) == 'X' && decoded.charAt(i - 1) == decoded.charAt(i + 1)) {
                decoded.deleteCharAt(i);
            }
            i++;
        }
        return decoded;
    }

    public void printTable() {
        System.out.println("This is the cipher table from " + this.key);
        System.out.println();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(ciperTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public String disturbKey() {
        StringBuilder key = new StringBuilder(this.key);
        Random random = new Random();
        int a = random.nextInt(key.length());
        random = new Random();
        int b = random.nextInt(key.length());
        while (b == a){
            b = random.nextInt(key.length());
        }
        char temp = key.charAt(a);
        key.setCharAt(a, key.charAt(b));
        key.setCharAt(b, temp);
        generateKey(key.toString());
        return key.toString();
    }

    public String crack(String message){
        System.out.println("message 0 : "+message);
        String result = decode(message);
        WordParser wp = new WordParser(result, Main.oc);
        double score = wp.getProba();
        String key = this.key;
        System.out.println("message 1 : "+result+" proba : "+score);
        System.out.println("key 1 : "+key);

        for (int i = 0; i < 50000; i++){
            disturbKey();
            String decoded = decode(message);
            wp = new WordParser(decoded, Main.oc);
            double currentScore = wp.getProba();
            if (currentScore > score){
                score = currentScore;
                result = decoded;
                key = this.key;
                System.out.println("message result : "+decode(message)+" proba : "+score);
            }
        }
        System.out.println("key : " + key + " score : " + score);
        System.out.println();
        return result;
    }
}
