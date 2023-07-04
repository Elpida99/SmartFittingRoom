package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.model.Material;
import hua.it21735.thesis.persistence.model.SizeEnum;
import hua.it21735.thesis.persistence.service.GarmentService;
import hua.it21735.thesis.rest.jpo.GarmentJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path = "/garment",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GarmentResource {

    @Autowired
    GarmentService garmentService;

    @GetMapping
    @RequestMapping(path = "/get/material")
    public ResponseEntity<List<Material>> getGarmentMaterialBlend(@RequestParam("garmentCode") String garmentCode) {
        System.out.println("GarmentResource.getGarmentMaterialBlend");
        System.out.println(garmentCode);
        return ResponseEntity.ok(garmentService.getGarmentMaterial(garmentCode.split("-")[1]));
    }

    @GetMapping
    @RequestMapping(path = "/get/colours")
    public ResponseEntity<List<String>> getGarmentColours(@RequestParam("garmentCode") String garmentCode) {

        return ResponseEntity.ok(garmentService.getGarmentColours(garmentCode.split("-")[1]));
    }

    @GetMapping
    @RequestMapping(path = "/get/sizes")
    public ResponseEntity<List<SizeEnum>> getGarmentSizes(@RequestParam("garmentCode") String garmentCode) {

        return ResponseEntity.ok(garmentService.getGarmentSizes(garmentCode.split("-")[1]));
    }

    @GetMapping
    @RequestMapping(path = "/get/barcode")
    public ResponseEntity<Garment> getGarmentByBarcode(@RequestParam("barcode") String barcode) {

        Garment garment = garmentService.findByBarcode(Long.valueOf(barcode));
        return garment != null ? ResponseEntity.ok(garment) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/get/name")
    public ResponseEntity<List<Garment>> getGarmentByName(@RequestParam("name") String name) {
        System.out.println("GarmentResource.getGarmentByName " + name);
        List<Garment> garments = garmentService.findByName(name);
        return garments != null ? ResponseEntity.ok(garments) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/get/sku")
    public ResponseEntity<List<Garment>> getGarmentBySku(@RequestParam("sku") String sku) {
        System.out.println("GarmentResource.getGarmentBySku " + sku);
        List<Garment> garments = garmentService.findBySku(sku);
        return garments != null ? ResponseEntity.ok(garments) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/get/code")
    public ResponseEntity<List<Garment>> getGarmentByCode(@RequestParam("code") String code) {
        System.out.println("GarmentResource.getGarmentByCode " + code);
        List<Garment> garments = garmentService.findByCode(code);
        return garments != null ? ResponseEntity.ok(garments) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/get/category")
    public ResponseEntity<List<Garment>> getGarmentByCategory(@RequestParam("category") String category) {
        System.out.println("GarmentResource.getGarmentByCategory " + category);
        List<Garment> garments = garmentService.findByCategory(category);
        return garments != null ? ResponseEntity.ok(garments) : ResponseEntity.badRequest().build();
    }

    @GetMapping
    @RequestMapping(path = "/get/all")
    public ResponseEntity<List<Garment>> getAllGarments() {
        List<Garment> garments = garmentService.findAll();
        System.out.println(garments);
        return ResponseEntity.ok(garments);
    }

    @PostMapping
    @RequestMapping(path = "/save")
    public ResponseEntity<GarmentJpo> saveGarment(@RequestBody GarmentJpo garment) {
        //TODO: admin will have to add the garment location as well!!!
        // store and storeinventory!!!!

        Long id = garmentService.save(garment);
        return id != null ? ResponseEntity.ok(garment) : ResponseEntity.badRequest().build();
    }

}
