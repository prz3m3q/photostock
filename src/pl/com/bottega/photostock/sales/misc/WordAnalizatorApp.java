package pl.com.bottega.photostock.sales.misc;

public class WordAnalizatorApp {

    private final static String inputFile = "/home/przemek/test.txt";
    private final static String outputFile = "/home/przemek/output.txt";

    public static void main(String[] args) {
        WordAnalizator wordAnalizator = new WordAnalizator(inputFile, outputFile);
        wordAnalizator.analize();
        wordAnalizator.saveResult();
    }
}
