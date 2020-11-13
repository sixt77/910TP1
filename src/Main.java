public class Main {

    static final long occurrences = 95675200;
    static Probabilities oc;

    public static void main(String[] args) {
        oc = FileReader.buildOccurences(args[0]);
        WordParser wp = new WordParser("ATILU", oc);
        System.out.println(wp.getProba());
        System.out.println();
        System.out.println();


        for (int i = 0; i<10; i++) {
            Playfair pf = new Playfair("microphone");
            String message = pf.encode("UNMICROPHONESOUVENTAPPELEMICROPARAPOCOPEESTUNTRANSDUCTEURELECTROACOUSTIQUECESTADIREUNAPPAREILCAPABLEDECONVERTIRUNSIGNALACOUSTIQUEENSIGNALELECTRIQUE");
            System.out.println(pf.decode(message));
            System.out.println("disturbed key : "+pf.disturbKey());
            System.out.println("disturbed key : "+pf.disturbKey());

            String result = pf.crack(message);
            System.out.println("result : "+result);
            System.out.println();
        }
    }
}