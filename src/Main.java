public class Main {

    static final long occurrences = 95675200;

    public static void main(String[] args) {
        Probabilities oc = FileReader.buildOccurences(args[0]);
        oc.print();
        WordParser wp = new WordParser("QIRSOPBBMF", oc);
        System.out.print(wp.getProba());
    }
}