package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.GarmentDao;
import hua.it21735.thesis.persistence.dao.RecommendationDao;
import hua.it21735.thesis.persistence.model.Garment;
import hua.it21735.thesis.persistence.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    @Autowired
    private RecommendationDao recommendationDao;

    @Autowired
    private GarmentDao garmentDao;

    public Long save(Recommendation recommendation) {
        Recommendation saved = recommendationDao.save(recommendation);
        return saved.getId();
    }

    public Recommendation getById(Long id) {
        Optional<Recommendation> recommendation = recommendationDao.findById(id);
        return recommendation.orElse(null);
    }

    public List<Recommendation> getBySku(String sku) {
        List<Recommendation> finalList = new ArrayList<>();
        List<Recommendation> list = recommendationDao.findByGarment_sku(sku);
        List<String> outfits = list.stream().map(Recommendation::getOutfit).distinct().collect(Collectors.toList());
        for (String o : outfits) {
            finalList.addAll(recommendationDao.findByOutfit(o));
        }

        finalList.removeIf(recommendation -> recommendation.getGarment_sku().equals(sku));

        return finalList;
    }

    @Transactional
    public List<Garment> getOutfit(String sku) {
        List<Garment> garments = new ArrayList<>();
        List<Recommendation> list = getBySku(sku);
        if(list!=null && !list.isEmpty()) {
            List<Recommendation> list2 = recommendationDao.findByOutfit(list.get(0).getOutfit());
            for (Recommendation rec : list2) {
                Garment e = garmentDao.findBySkuNumber(rec.getGarment_sku()).get(0);
                if (!e.getSkuNumber().equals(sku)) {
                    garments.add(e);
                }
            }
        }

        return garments;
    }
}
