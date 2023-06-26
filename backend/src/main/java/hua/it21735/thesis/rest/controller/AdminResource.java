package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.service.StatisticsService;
import hua.it21735.thesis.persistence.service.XlsxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path = "/admin",
//        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdminResource {

    @Autowired
    private StatisticsService service;
    @Autowired
    private XlsxService xlsxService;


    @GetMapping
    @RequestMapping(path = "/download")
    public ResponseEntity<?> getTryOnsPerGarment() {

        xlsxService.mainMethod();

        File file = new File(System.getProperty("user.dir") + "/garmentStatistics.xlsx");

        InputStreamResource resource = null;
        try {
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=garmentStatistics.xlsx");

        MediaType mediaType = MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(mediaType)
                .body(resource);

    }


}
