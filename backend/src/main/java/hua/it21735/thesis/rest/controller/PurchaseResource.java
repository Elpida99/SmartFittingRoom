package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.model.PointOfSale;
import hua.it21735.thesis.persistence.model.StatusEnum;
import hua.it21735.thesis.persistence.service.PurchaseService;
import hua.it21735.thesis.rest.jpo.PurchaseJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
@RequestMapping(path = "/purchase",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PurchaseResource {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping
    @RequestMapping(path = "/get/all")
    public ResponseEntity<List<PointOfSale>> getAllPOS() {
        return ResponseEntity.ok(purchaseService.findAll());
    }

    @GetMapping
    @RequestMapping(path = "/get/byStatus")
    public ResponseEntity<List<PointOfSale>> getbyStatus(@RequestParam(name = "statusEnum") StatusEnum statusEnum) {
        return ResponseEntity.ok(purchaseService.findByStatus(statusEnum));
    }

    @GetMapping
    @RequestMapping(path = "/get/byStore")
    public ResponseEntity<List<PointOfSale>> getByStore(@RequestParam(name = "store") String storeName) {
        return ResponseEntity.ok(purchaseService.findByStore(storeName));
    }

    @GetMapping
    @RequestMapping(path = "/get/byCustomer")
    public ResponseEntity<List<PurchaseJpo>> getbyCustomer(@RequestParam(name = "customer") String email) {
        List<PointOfSale> pointOfSales = purchaseService.findbyCustomer(email);
        System.out.println("pointOfSales.size() = " + pointOfSales.size());
        List<PurchaseJpo> result = new ArrayList<>();
        for(PointOfSale pos : pointOfSales){
            result.add(purchaseService.toPurchaseJpo(pos));
        }
        System.out.println("result.size() = " + result.size());

        return ResponseEntity.ok(result);
    }

    @PostMapping
    @RequestMapping(path = "/save")
    public ResponseEntity<PurchaseJpo> savePointOfSale(@RequestBody PurchaseJpo purchaseJpo) {

        Long id = purchaseService.save(purchaseJpo);
        return id != null ? ResponseEntity.ok(purchaseJpo) : ResponseEntity.badRequest().build();
    }

}
