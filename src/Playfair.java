import java.util.Random;
import java.util.Scanner;

public class Playfair {

    public String key;
    private String[][] ciperTable;

    public Playfair(String keyword) {
        String keyword1 = parseString(keyword);
        generateKey(keyword1);

    }

    public static String parseString(String parse) {
        parse = parse.toUpperCase();
        parse = parse.replaceAll("[^A-Z]", "");
        parse = parse.replace("J", "I");
        return parse;
    }

    /**
     * Question 2
     * Generate a key with a keyword
     *
     * @param key keyword or key
     */
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

    /**
     * Question 2
     * Print the cipher table
     */
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

    /**
     * Question 3
     * Get a point in the cipher table
     *
     * @param c character to get
     * @return the "coordinates" of the character
     */
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

    /**
     * Question 3
     * Encode a pair of letter with the Cipher Table
     *
     * @param di digramm to decode
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
        }
        // case 2: letters in digraph are of same column, shift rows down
        else if (c1 == c2) {
            r1 = (r1 + 1) % 5;
            r2 = (r2 + 1) % 5;
        }
        // case 3: letters in digraph form rectangle, swap first column # with second column #
        else {
            int temp = c1;
            c1 = c2;
            c2 = temp;
        }
        //performs the table look-up and puts those values into the encoded array
        enc = ciperTable[r1][c1] + "" + ciperTable[r2][c2];
        return enc;
    }

    /**
     * Question 3
     * Encode a message
     *
     * @param message message to encode
     * @return encrypted message
     */
    public String encode(String message) {
        int length = message.length() / 2 + message.length() % 2;

        // insert x between double-letter digraphs & redefines "length"
        for (int i = 0; i < (length - 1); i++) {
            if (message.charAt(2 * i) == message.charAt(2 * i + 1)) {
                message = new StringBuffer(message).insert(2 * i + 1, 'X').toString();
                length = message.length() / 2 + message.length() % 2;
            }
        }
        // adds an x to the last digraph, if necessary
        String[] digraph = new String[length];
        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && message.length() / 2 == (length - 1))
                message = message + "X";
            digraph[j] = message.charAt(2 * j) + "" + message.charAt(2 * j + 1);
        }
        // encodes the digraphs and returns the output
        StringBuilder out = new StringBuilder();
        for (int k = 0; k < length; k++) {
            out.append(encodePair(digraph[k]));//encDigraphs[k];
        }
        return out.toString();
    }

    /**
     * Question 3
     * Decode an encrypted message
     *
     * @param message Encrypted message to decode
     * @return decrypted message
     */
    public String decode(String message) {
        StringBuilder decoded = new StringBuilder();
        for (int i = 0; i < message.length() / 2; i++) {
            char a = message.charAt(2 * i);
            char b = message.charAt(2 * i + 1);
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

    /**
     * Parse the X letters from a decoded message
     *
     * @param decoded decoded message
     */
    private void parseX(StringBuilder decoded) {
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
    }

    /**
     * Question 4
     * Disturb Key function
     */
    public void disturbKey() {
        StringBuilder key = new StringBuilder(this.key);
        Random random = new Random();
        int a = random.nextInt(key.length());
        random = new Random();
        int b = random.nextInt(key.length());
        while (b == a)
            b = random.nextInt(key.length());

        char temp = key.charAt(a);
        key.setCharAt(a, key.charAt(b));
        key.setCharAt(b, temp);
        generateKey(key.toString());
    }

    /**
     * Question 5
     * Crack function
     *
     * @param message encrypted message
     * @return decrypted message
     */
    public String crack(String message) {
        String result = decode(message);
        WordParser wp = new WordParser(Main.oc);
        double score = wp.getProba(result);
        String key = this.key;

        for (int i = 0; i < 50000; i++) {

            disturbKey();
            String decoded = decode(message);
            double currentScore = wp.getProba(decoded);
            if (currentScore > score) {
                score = currentScore;
                result = decoded;
                key = this.key;
            }
            generateKey(key);
        }
        System.out.println("key : " + key + " score : " + score);
        System.out.println();
        return result;
    }

    /**
     * Question 7
     * Upgraded disturb key function
     */
    public void upgradedDisturbKey() {
        Random random = new Random();
        int rand = random.nextInt(100);
        if (rand < 90) {
            disturbKey();
        } else if (rand < 95)
            swapRowCol(0);
        else
            swapRowCol(1);
    }

    /**
     * Return a couple of random number between 0 and 4
     *
     * @return the pair of numbers
     */
    private int[] randomRowCol() {
        Random random = new Random();
        int first = random.nextInt(5);
        random = new Random();
        int second = random.nextInt(5);
        while (first == second)
            second = random.nextInt(5);
        return new int[]{first, second};
    }

    /**
     * Swap two rows or to columns depending on the mode value
     *
     * @param mode tell if it's a row or column swap
     */
    public void swapRowCol(int mode) {
        int[] random = randomRowCol();
        int first = random[0];
        int second = random[1];
        String temp;
        if (mode == 0) {
            for (int i = 0; i < 5; i++) {
                temp = this.ciperTable[first][i];
                this.ciperTable[first][i] = this.ciperTable[second][i];
                this.ciperTable[second][i] = temp;
            }

        } else {
            for (int i = 0; i < 5; i++) {
                temp = this.ciperTable[i][first];
                this.ciperTable[i][first] = this.ciperTable[i][second];
                this.ciperTable[i][second] = temp;
            }
        }

        StringBuilder key = new StringBuilder();
        for (String[] strings : this.ciperTable) {
            for (int j = 0; j < this.ciperTable[0].length; j++) {
                key.append(strings[j]);
            }
        }
        generateKey(key.toString());
    }

    /**
     * Question 8
     */
    public String upgradedCrack(String message, double tempInit, double tempStep, double tempFinale, int nbCycle) {
        String result = decode(message);
        WordParser wp = new WordParser(Main.oc);
        double score = wp.getProba(result);
        double delta;
        String key = this.key ;
        System.out.println(this.key);
        int bestKey = 0;

        while (tempInit > tempFinale && bestKey < 5) {
            System.out.println("Température : " + tempInit);
            System.out.println("clear = " + result + " with score : " + score);

            double lastscore = score;
            for (int i = 0; i < nbCycle; i++) {
                generateKey(key);
                upgradedDisturbKey();
                String decoded = decode(message);
                double currentScore = wp.getProba(decoded);
                delta = wp.getProba(result) - currentScore;
                if ((delta < 0) || (Math.random() < Math.exp((-delta) / tempInit))) {
                    key = this.key;
                    result = decoded;
                    score = currentScore;
                }
                generateKey(key);
            }
            if (lastscore == score)
                bestKey++;
            else bestKey = 0;
            System.out.println("bestkey :" + bestKey);
            tempInit -= tempStep;
        }
        System.out.println("key : " + key + " score : " + score);
        System.out.println();

        return result;

    }
}
