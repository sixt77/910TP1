import java.util.Scanner;

public class Playfair {

    private final String keyword;
    private final String[][] ciperTable;

    public Playfair(String keyword) {
        this.keyword = keyword.toUpperCase();
        ciperTable = generateKey(this.keyword);

        Scanner sc = new Scanner(System.in);
        // prompts user for message to be encoded
        System.out.println("Please input the message to be encoded");
        System.out.println("using the previously given keyword");
        String input = parseString(sc);
        while (input.equals(""))
            input = parseString(sc);
        System.out.println();

        String output = encode(input);
        decode(output);

    }

    private String parseString(Scanner s) {
        String parse = s.nextLine();
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }

    private String[][] generateKey(String key) {
        String[][] playfairTable = new String[5][5];
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
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }

    private int[] getPoint(char c) {
        int[] pt = new int[2];
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (c == ciperTable[i][j].charAt(0)) {
                    pt[0] = i;
                    pt[1] = j;
                }
        //pt = new Point(i,j);
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
        System.out.println("out : " + out);
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

    private String decode(String out){
        StringBuilder decoded = new StringBuilder();
        for(int i = 0; i < out.length() / 2; i++){
            char a = out.charAt(2*i);
            char b = out.charAt(2*i+1);
            int r1 = getPoint(a)[0];
            int r2 = getPoint(b)[0];
            int c1 = getPoint(a)[1];
            int c2 = getPoint(b)[1];
            if(r1 == r2){
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            }else if(c1 == c2){
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            }else{
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded.append(ciperTable[r1][c1]).append(ciperTable[r2][c2]);
        }
        decoded = parseX(decoded);
        System.out.println("decoded : "+decoded);
        return decoded.toString();
    }

    private StringBuilder parseX(StringBuilder decoded) {
        if(decoded.charAt(decoded.length()-1) == 'X'){
            decoded.deleteCharAt(decoded.length()-1);
        }
        int i = 1;
        while (i < decoded.length()-2){
            if (decoded.charAt(i) == 'X' && decoded.charAt(i-1) == decoded.charAt(i+1)){
                decoded.deleteCharAt(i);
            }
            i++;
        }
        return decoded;
    }

    public void printTable() {
        System.out.println("This is the cipher table from " + this.keyword);
        System.out.println();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(ciperTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
