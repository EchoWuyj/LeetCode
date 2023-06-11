package alg_01_ds_wyj._03_high_level._02_set;

import sun.util.locale.provider.LocaleNameProviderImpl;
import sun.util.resources.cldr.ti.CalendarData_ti_ER;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-17 13:11
 * @Version 1.0
 */
public class FileReaderTest {
    public static List<String> readFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                if (trimmedLine.isEmpty()) {
                    continue;
                }
                String[] lineWords = trimmedLine.split(" ");
                for (String word : lineWords) {
                    words.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return words;
    }
}
