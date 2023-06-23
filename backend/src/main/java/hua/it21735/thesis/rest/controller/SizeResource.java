package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.model.Size;
import hua.it21735.thesis.persistence.model.SizeEnum;
import hua.it21735.thesis.persistence.model.TargetConsumer;
import hua.it21735.thesis.persistence.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path = "/size",
//        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SizeResource {

    @Autowired
    SizeService sizeService;

    @GetMapping
    @RequestMapping(path = "/bySize")
    public ResponseEntity<List<Size>> getSize(@RequestParam("size") SizeEnum size) {

        List<Size> sizes = sizeService.findBySize(size);
        return sizes != null ? ResponseEntity.ok(sizes) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/bySizeAndGender")
    public ResponseEntity<Size> getSizeByGender(@RequestParam("size") SizeEnum size, @RequestParam("gender") TargetConsumer gender) {

        Size sizeFound = sizeService.findBySizeAndGender(size, gender);
        return sizeFound != null ? ResponseEntity.ok(sizeFound) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/byGender")
    public ResponseEntity<List<Size>> getSizesByGender(TargetConsumer gender) {

        List<Size> sizes = sizeService.findByGender(gender);
        return sizes != null ? ResponseEntity.ok(sizes) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/all")
    public ResponseEntity<List<Size>> getAllSizes() {
        return ResponseEntity.ok(sizeService.findAll());
    }

    @PostMapping
    @RequestMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Size> saveSize(@RequestBody Size size) {

        Size size1 = sizeService.save(size);
        return size1 != null ? ResponseEntity.ok(size1) : ResponseEntity.badRequest().build();
    }

}
