package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.model.TryOn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

    @Autowired
    private GarmentService garmentService;
    @Autowired
    private TryOnService tryOnService;

    @Transactional
    public Map<String, Long> getPopularGarments() {
        List<String> garmentCodes = garmentService.findGarmentCodes();
        List<TryOn> allTryOns = tryOnService.findAll();
        Map<String, Long> map = new HashMap<>();

        for (String code : garmentCodes) {

            long tryOns =
                    allTryOns.stream().filter(tryOn -> tryOn.getGarments()
                                    .stream()
                                    .map(Garment::getCode)
                                    .collect(Collectors.toList())
                                    .contains(code))
                            .count();

            String name = "";
            List<Garment> byCode = garmentService.findByCode(code);
            if (!byCode.isEmpty())
                name = byCode.get(0).getName();
            map.put(code + "_NAME_" + name, tryOns);
        }


        return map;

    }

    @Transactional
    public Map<String, Long> getPopularGarmentsSKU() {
        List<String> garmentCodes = garmentService.findSkuNumbers();
        List<TryOn> allTryOns = tryOnService.findAll();
        Map<String, Long> map = new HashMap<>();

        for (String code : garmentCodes) {

            long tryOns =
                    allTryOns.stream().filter(tryOn -> tryOn.getGarments()
                                    .stream()
                                    .map(Garment::getSkuNumber)
                                    .collect(Collectors.toList())
                                    .contains(code))
                            .count();

            String name = "";
            List<Garment> byCode = garmentService.findBySku(code);
            if (!byCode.isEmpty())
                name = byCode.get(0).getName();
            map.put(code + "_NAME_" + name, tryOns);
        }


        return map;

    }


}