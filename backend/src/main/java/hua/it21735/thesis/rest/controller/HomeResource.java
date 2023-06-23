package hua.it21735.thesis.rest.controller;

import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class HomeResource {

    @Autowired
    SizeService service;


//    @GetMapping("/other")
//    public String otherPage(Model model) {
//        model.addAttribute("data", StudentUtils.buildStudents());
//        return "other.html";
//    }

//    @GetMapping
//    @RequestMapping(path = "/get/all")
//    public ModelAndView getAllGarments() {
//        ModelAndView mav = new ModelAndView("list-sizes");
//        mav.addObject("sizes", service.findAll());
//        return mav;
//    }


    @GetMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @RequestMapping(path = "/test")
    public ResponseEntity<?> findAll() {
        Garment garment = new Garment();
        garment.setName("blue jeans");
//        garment.setColors(Arrays.asList(Color.BLACK, Color.RED));
//        Material material = new Material(100, 0, 0, 0, 0);
//        garment.setMaterial(material);
//        garment.setSizes(Arrays.asList(new Size(SizeEnum.S, (float) 1.54, (float) 51.6), new Size(SizeEnum.M, (float) 1.74, (float) 60.6)));
//        garment.setProductCode(1232313L);
//        garment.setTargetConsumer(TargetConsumer.F);
//        garment.setPrice((float) 23.45);

        return ResponseEntity.ok(garment);
    }
}
