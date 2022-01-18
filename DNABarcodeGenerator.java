import java.util.Arrays;
import java.util.Scanner;
//program generates random valid DNA sequences
public class DNABarcodeGenerator {
    //class variables
    public static int N = 1000;
    public static int L = 0;
    //main
    public static void main(String[] args) {

        generateBarcode();

    }
    public static void generateBarcode() {
        //prompt user
        Scanner input = new Scanner(System.in);
        System.out.println("how many sequences of DNA barcodes do you want to generate?: ");
        N = input.nextInt();
        System.out.println("what is the length of the DNA barcodes you would like to generate?: ");
        L = input.nextInt();
        //create array
        String[] returnArr = new String[N];
        //avoid issues with null values
        Arrays.fill(returnArr, "");
        //ASCII codes for A, T, G, and C
        int[] charCodes = {65, 84, 67, 71};
        //loop the amount of times that the user entered
        int y = 0;
        for (int z = 0; z < N; z++) {
            //loop for the length entered
            y++;
            for (int a = 0; a < L; a++) {
                returnArr[z] += (char) charCodes[(int)(Math.random() * 4)];
            }
            //check if the string is valid, if not reset the array position and start again at the same z value
            if (!isValid(returnArr[z])) {
                returnArr[z] = "";
                z -= 1;
            }
        }
        printArray(returnArr);
    }
    //consolidates all validation methods
    public static boolean isValid(String aBarcode) {
        return (((!isRestrictedList(aBarcode))) && (validateGCCount(aBarcode)) && (!isRedundantBarcode(aBarcode)));
    }
    //checks if the string is a restricted sequence
    public static boolean isRestrictedList(String aBarcode) {
        return ((aBarcode.equals("ACCGGT"))||(aBarcode.equals("GGCGCGCC"))||(aBarcode.equals("GGATCC"))||(aBarcode.equals("CCTGCAGG")));
    }
    //validates the count of each char (I hope idrk)
    public static boolean validateGCCount(String aBarcode) {
        double percent = ((countOf(aBarcode, 'G') + countOf(aBarcode, 'C'))/(countOf(aBarcode, 'A') + countOf(aBarcode, 'T') + countOf(aBarcode, 'G') + countOf(aBarcode, 'C')))*100;
        return percent > 40 && percent < 60;
    }
    //checks for triple subsequences
    public static boolean isRedundantBarcode(String aBarcode) {
        return (aBarcode.contains("AAA") || aBarcode.contains("TTT") || aBarcode.contains("CCC") ||  aBarcode.contains("GGG"));
    }
    //method used by validateCGCount to check the occurrences of each char
    public static double countOf(String aBarcode, char aChar) {
        double count = 0;
        for (int a = 0; a < aBarcode.length(); a++) {
            if (aBarcode.charAt(a) == aChar) {
                count += 1;
            }
        }
        return count;
    }
    //prints array in correct format
    public static void printArray(String[] arr) {
        for (int a = 0; a < arr.length; a++) {
            //print barcodes
            System.out.print("DNA barcode " + (a+1) + ": ");
            System.out.println(arr[a]);
        }
    }
    //default constructor
    public DNABarcodeGenerator() {

    }
}
