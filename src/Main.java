public class Main {

    static final long occurrences = 95675200;
    static Probabilities oc;

    public static void main(String[] args) {
        oc = FileReader.buildOccurences(args[0]);


        for (int i = 0; i<1; i++) {
            Playfair pf = new Playfair("microphone");
            String message = pf.encode("UNMICROPHONESOUVENTAPPELEMICROPARAPOCOPEESTUNTRANSDUCTEURELECTROACOUSTIQUECESTADIREUNAPPAREILCAPABLEDECONVERTIRUNSIGNALACOUSTIQUEENSIGNALELECTRIQUE");
            System.out.println("disturbed key : "+pf.disturbKey());
            System.out.println("disturbed key : "+pf.disturbKey());

            String result = pf.crack(message);
            System.out.println("result : "+result);
            System.out.println();
        }

        Playfair pf1 = new Playfair("");
        String message = pf1.encode("BGUDOMGRCYLUQRTBIUMAUCMEQNMDNDOLMTBAIMQBPUDMXLQLPQAIHZUDVELENDQIPUMILTMUESQIUSZDNDORGUZDABDISPMEMZEQUBLETLMZEQQBEOIUAMSVQMPDDIOBIUOLGDOLPDQMPDWMPTNZDMABDQQCKUSPMORWEM");
        pf1.upgradedDisturbKey();
        pf1.upgradedDisturbKey();

        String result = pf1.upgradedCrack(message, 21, 0.2, 0, 50000);
        System.out.println("result : "+result);
    }
}