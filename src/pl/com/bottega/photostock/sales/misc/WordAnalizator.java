package pl.com.bottega.photostock.sales.misc;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class WordAnalizator {
    private HashMap<String, Integer> wordMap = new HashMap<>();
    private String inputFile;
    private String outputFile;

    public WordAnalizator(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public void analize() {
        try (InputStream inputStream = new FileInputStream(inputFile)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                addWordsToMap(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie ma takiego pliku.");
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addWordsToMap(String line) {
        String[] words = line.trim().split("\\s+");
        for (String word: words) {
            word = word.toLowerCase();
            Integer amount = wordMap.get(word);
            if (amount == null) {
                amount = 0;
            }
            wordMap.put(word, ++amount);
        }
    }

    public void saveResult() {
        try (OutputStream outputStream = new FileOutputStream(outputFile); PrintStream ps = new PrintStream(outputStream)) {
            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                ps.println(String.format("%s %d", entry.getKey(), entry.getValue()));
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
