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

    public Integer findNthMaxNumber(String pathFile, int n) throws IOException {
        File file = new File(pathFile);
        if (!file.exists()) {
            throw new IllegalArgumentException("Файл не найден: " + pathFile);
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            List<Integer> numbers = extractNumbersFromSheet(sheet);

            if (numbers.size() < n) {
                throw new IllegalArgumentException("В файле недостаточно чисел");
            }
            // Используем Min-Heap для нахождения N-го максимального числа
            return findNthMaxWithMinHeap(numbers, n);
        }
    }

    private List<Integer> extractNumbersFromSheet(Sheet sheet) {
        List<Integer> numbers = new ArrayList<>();
        // Читаем числа из ячеек и добавляем в лист
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                numbers.add((int) cell.getNumericCellValue());
            }
        }
        return numbers;
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
}

