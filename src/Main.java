public class Main {

    static final long occurrences = 95675200;

    public static void main(String[] args) {
        Probabilities oc = FileReader.buildOccurences(args[0]);
        WordParser wp = new WordParser("QIRSOPBBMF", oc);
        System.out.print(wp.getProba());
        System.out.println();
        System.out.println();

        Playfair pf = new Playfair("microphone");
        pf.printTable();
    }
}