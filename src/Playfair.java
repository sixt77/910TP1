public class Playfair {

    private String keyword;
    private String[][] ciperTable;

    public Playfair (String keyword){
        this.keyword = keyword.toUpperCase();
        ciperTable = cipherTable(this.keyword);
    }


    private String[][] cipherTable(String key){
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";

        // fill string array with empty string
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                playfairTable[i][j] = "";

        for(int k = 0; k < keyString.length(); k++){
            boolean repeat = false;
            boolean used = false;
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(playfairTable[i][j].equals("" + keyString.charAt(k))){
                        repeat = true;
                    }else if(playfairTable[i][j].equals("") && !repeat && !used){
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }

    public void printTable(){
        System.out.println("This is the cipher table from " + this.keyword);
        System.out.println();

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                System.out.print(ciperTable[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
