package alg_01_ds_dm._03_high_level._02_set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Wuyj
 * @DateTime 2023-03-16 23:40
 * @Version 1.0
 */
public class FileReaderTest {
    public static List<String> readFile(String fileName) {
        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                // 该行为空，直接跳过，读取下一行
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
