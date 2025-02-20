package by.aleksabrakor.tz_udobnySoft;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@Tag(name = "ReaderXlsxController", description = "Найти N-ное максимальное число из файла")
public class ReaderXlsxController {
    private final ReaderXlsxService readerXlsxService;

    @Autowired
    public ReaderXlsxController(ReaderXlsxService readerXlsxService) {
        this.readerXlsxService = readerXlsxService;
    }


    @Operation(summary = "Получить N-ное максимальное число из xmlx файла\" _(MinHeap)", description = " pathFile and N")
    @GetMapping("/find-nth-max")
    public ResponseEntity<Integer> findNthMaxNumberMinHeap(@RequestParam String pathFile, @RequestParam int n) {
        return ResponseEntity.ok(readerXlsxService.findNthMaxNumber(pathFile, n));
    }

    @GetMapping("/find-nth-max")
    @Operation(summary = "Получить N-ное максимальное число из xmlx файла, _(QuickSelect)", description = " pathFile and N")
    public ResponseEntity<Integer> findNthMaxNumberQuickSelect(@RequestParam String pathFile, @RequestParam int n) {
        return ResponseEntity.ok(readerXlsxService.findNthMaxNumberQuickSelect(pathFile, n));
    }
}
