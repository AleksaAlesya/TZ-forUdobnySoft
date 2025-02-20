package by.aleksabrakor.tz_udobnySoft;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ReaderXlsxService {

    public Integer findNthMaxNumber(String pathFile, int n) {
        File file = validateFileExists(pathFile);

        List<Integer> numbers = readNumbersFromXlsx(file);
        if (numbers.size() < n) {
            throw new IllegalArgumentException("В файле недостаточно чисел");
        }
        return findNthMaxWithMinHeap(numbers, n);
    }


    private Integer findNthMaxWithMinHeap(List<Integer> numbers, int n) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n + 1);

        for (Integer number : numbers) {
            minHeap.offer(number);
            if (minHeap.size() > n) {
                minHeap.poll(); // Удаляем наименьший элемент, если размер кучи больше N
            }
        }
        return minHeap.peek();
    }

    public int findNthMaxNumberQuickSelect(String pathFile, int n) {
        File file = validateFileExists(pathFile);

        List<Integer> numbers = readNumbersFromXlsx(file);
        if (numbers.size() < n) {
            throw new IllegalArgumentException("В файле недостаточно чисел");
        }
        return QuickSelect.findNthLargest(numbers, n);
    }

    private File validateFileExists(String pathFile) {
        File file = new File(pathFile);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + pathFile);
        }
        return file;
    }

    private List<Integer> readNumbersFromXlsx(File file) {
        List<Integer> numbers = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                Cell cell = row.getCell(0);
                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    numbers.add((int) cell.getNumericCellValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbers;
    }
}

