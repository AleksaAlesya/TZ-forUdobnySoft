package by.aleksabrakor.tz_udobnySoft;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@Tag(name = "ReaderXlsxController", description = "Найти N-ное максимальное число из файла")
public class ReaderXlsxController {
    private final ReaderXlsxService readerXlsxService;
    private final QuickSelect quickSelect;
    private final MinHeapSelect minHeapSelect;


    @Autowired
    public ReaderXlsxController(ReaderXlsxService readerXlsxService, QuickSelect quickSelect, MinHeapSelect minHeapSelect) {
        this.readerXlsxService = readerXlsxService;
        this.quickSelect = quickSelect;
        this.minHeapSelect = minHeapSelect;
    }

    @Operation(summary = "Получить N-ное максимальное число из xmlx файла\" _(MinHeap)", description = " pathFile and N")
    @GetMapping("/find-nth-max")
    public ResponseEntity<Integer> findNthMaxNumberMinHeap(@RequestParam String pathFile, @RequestParam int n) {

        List<Integer> numbers = readerXlsxService.readNumbers(pathFile, n);
        return ResponseEntity.ok(minHeapSelect.findNthMaxWithMinHeap(numbers, n));
    }

    @GetMapping("/find-nth-max-quick")
    @Operation(summary = "Получить N-ное максимальное число из xmlx файла, _(QuickSelect)", description = " pathFile and N")
    public ResponseEntity<Integer> findNthMaxNumberQuickSelect(@RequestParam String pathFile, @RequestParam int n) {

        List<Integer> numbers = readerXlsxService.readNumbers(pathFile, n);
        return ResponseEntity.ok(quickSelect.findNthMaxWithQuickSelect(numbers, n));
    }
}
