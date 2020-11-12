public class Main {

    static final long occurrences = 95675200;
    static Probabilities oc;

    public static void main(String[] args) {
        oc = FileReader.buildOccurences(args[0]);
        WordParser wp = new WordParser("QIRSOPBBMF", oc);
        System.out.print(wp.getProba());
        System.out.println();
        System.out.println();

        Playfair pf1 = new Playfair("MICROPHNEABDFGKLQSTUVWXYZ");
        String message1 = pf1.encode("UNMICROPHONESOUVENTAPPELEMICROPARAPOCOPEESTUNTRANSDUCTEURELECTROACOUSTIQUECESTADIREUNAPPAREILCAPABLEDECONVERTIRUNSIGNALACOUSTIQUEENSIGNALELECTRIQUE");
        pf1.generateKey("LIDROPHNEABCFGKMQSTUVWYXZ");
        String message2 = pf1.decode(message1);
        String message3 = pf1.encode(message2);
        pf1.generateKey("ZYXWVUTSQLKGFDBAENHPORCIM");
        System.out.println(pf1.decode(message3));

//        for (int i = 0; i<1; i++) {
//            Playfair pf = new Playfair("microphone");
//            String message = pf.encode("UNMICROPHONESOUVENTAPPELEMICROPARAPOCOPEESTUNTRANSDUCTEURELECTROACOUSTIQUECESTADIREUNAPPAREILCAPABLEDECONVERTIRUNSIGNALACOUSTIQUEENSIGNALELECTRIQUE");
//            System.out.println(pf.decode(message));
//            System.out.println("key : "+pf.disturbKey());
//
//            String result = pf.crack(message);
//            System.out.println("result : "+result);
//            System.out.println();
//        }
    }
}