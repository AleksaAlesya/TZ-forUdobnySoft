package by.aleksabrakor.tz_udobnySoft;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController()
@Tag(name = "ReaderXlsxController", description = "Найти N-ное максимальное число из файла")
public class ReaderXlsxController {
    private final ReaderXlsxService readerXlsxService;

    @Autowired
    public ReaderXlsxController(ReaderXlsxService readerXlsxService) {
        this.readerXlsxService = readerXlsxService;
    }


    @Operation(summary = "Получить N-ное максимальное число из xmlx файла\"")
    @GetMapping("/number-max")
    public Integer getNumberMax(@RequestParam String pathFile, @RequestParam int n) throws IOException {
        return readerXlsxService.findNthMaxNumber(pathFile, n);
    }
}
