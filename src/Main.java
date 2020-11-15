import java.util.Scanner;

public class Main {

    static final long occurrences = 95675200;
    static Probabilities oc;

    public void traiterChoix(int choix){
        //procedure de traitement du choix de l'user

        switch(choix){
            case 1: Playfair pf = createPlayfair();
                break;
            case 2: score();
                break;
//            case 3: encode();
//                break;
//            case 4: decode();
//                break;
//            case 5: crack();
//                break;
            case 9: System.out.println("À plus");
                break;
            default : System.out.println("Mauvais choix");
                break;
        }
    }

    public static Playfair createPlayfair(){
        Scanner sc = new Scanner(System.in);
        // prompts user for message to be encoded
        System.out.println("Input the key");
        Playfair pf = new Playfair(sc.nextLine());
        System.out.println();
        return pf;
    }

    public static double score(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the message to be scored");
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        String message = input.toString().split(" ")[0];
        String path = input.toString().split(" ")[1];

        oc = FileReader.buildOccurences(path);
        System.out.println("message : "+message+" path : "+path);
        WordParser wp = new WordParser(oc);
        return wp.getProba(message);
    }

    public static void encode(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the message to be encoded and the key");
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        String message = input.toString().split(" ")[0];
        String key = input.toString().split(" ")[1];

        Playfair pf = new Playfair(key);
        pf.encode(message);
    }
    public static void decode(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the message to be decoded and the key");
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        String message = input.toString().split(" ")[0];
        String key = input.toString().split(" ")[1];

        Playfair pf = new Playfair(key);
        pf.decode(message);
    }

    public static void crack(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Input the message to be decoded and the key");
        StringBuilder input = new StringBuilder(sc.nextLine());
        while (input.toString().equals(""))
            input.append(sc.nextLine());
        String[] line = input.toString().split(" ");
        double tempMin = 0;
        double tempMax;
        double step = 0.5;
        int number = 50000;
        int i;
        for (i = 0; i< line.length-3; i++){
            if (line[i].equals("-t"))
                tempMin = Double.parseDouble(line[i+1]);
            if (line[i].equals("-T"))
                tempMin = Double.parseDouble(line[i+1]);
            if (line[i].equals("-s"))
                step = Double.parseDouble(line[i+1]);
            if (line[i].equals("-n"))
                number = Integer.parseInt(line[i+1]);
        }
        String file = line[i++];
        String message = line[i++];
    }

    public static void main(String[] args) {


        score();


        oc = FileReader.buildOccurences(args[0]);

        for (int i = 0; i<0; i++) {
            Playfair pf = new Playfair("microphone");
            String message = pf.encode("UNMICROPHONESOUVENTAPPELEMICROPARAPOCOPEESTUNTRANSDUCTEURELECTROACOUSTIQUECESTADIREUNAPPAREILCAPABLEDECONVERTIRUNSIGNALACOUSTIQUEENSIGNALELECTRIQUE");


            String result = pf.crack(message);
            System.out.println("result : "+result);
            System.out.println();
        }

        Playfair pf1 = new Playfair("");
        String message = "AMUNOFIEMSMHSECNMTSGCZIERCSNRPCZCSEGAZAOQAMOMTNGICMIFAUOHOOSNRSYZAUKOUUKTAMAELIYATWMKRIESPZACRAOQANZMTOSOURPOHMARUCACINAFTNZMEPRUOHOSCDRASURZCSNLRKHUOLNOMHQNZMERPACZCMICSSGELIYATGSDRSAUZIFOAIYRTTFICOFDNAMSGCHISHSTEFIMOCSIYASUOTEMOMTEMOGPEAHPWPKAOASOSSAEMUKOUTAAKISAZZIZMIVAMNSTINSMISYPALITAATTEPEECUDAZIAOHMOIEHRANTEUKOUCRRSHOTELNKZCSEMOSAHIWISZMCHRDFOAHTONRTIUOATNXOASGMTEIXCHSNSNTAORMINEISAACANTEUKTAAPEIIVRSHOTEMECSESIWISZMAMTMLNKZCSEMOSAHKHSECNMTOQPAOMOMRCISQRCZIERCSNHRTIENQETAUOARNTFRNSNTRGPEEIXCAOUIIDCSEMOMTEDTKZOATMRTEIMIAOMECSEMTOELRPURHTATAGHZHCTIHOHMHZLIOHMADRABTAMELITAHLXFWREMUKSAIULIMECSESFRISHTDWNUUHICNTUIDWMEDRSNNZMTOSNZOHKQRDOGMOCIATRPUOMECSEQIFAZTMDENVOMHZHTGSOAIURFNZMERUNMRPCRNSGPPAAQCWNAPWURSNABSUZAONKZMOMTOGPECZSINZMEAOUNESSNABSUZARDLDOHKQRPNENZ";
        pf1.upgradedDisturbKey();

        int temp = message.length() /8;
        double step;
        if (message.length() > 400) step = 1 ;
        else step = 0.2;
        String result = pf1.upgradedCrack(message, temp, step, 0, 50000);
        System.out.println("result : "+result);
    }
}