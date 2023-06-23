package hua.it21735.thesis.persistence.service;

import hua.it21735.thesis.persistence.dao.CategoryDao;
import hua.it21735.thesis.persistence.dao.ColorDao;
import hua.it21735.thesis.persistence.dao.GarmentDao;
import hua.it21735.thesis.persistence.dao.MaterialDao;
import hua.it21735.thesis.persistence.model.*;
import hua.it21735.thesis.rest.jpo.GarmentJpo;
import hua.it21735.thesis.rest.jpo.StoreAvailabilityJpo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class GarmentService {

    @Autowired
    private GarmentDao garmentDao;
    @Autowired
    private ColorDao colorDao;
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private MaterialDao materialDao;
    @Autowired
    private StoreService storeService;

    public List<Garment> findAll() {
        return garmentDao.findAll();
    }

    public Garment findById(Long id) {
        if (id != null) {
            Optional<Garment> garmentFound = garmentDao.findById(id);
            return garmentFound.orElse(null);
        }
        return null;
    }

    public List<Garment> findByName(String name) {
        if (name != null) {
            return garmentDao.findByName(name);
        }
        return null;
    }

    public Garment findByBarcode(Long barcode) {
        if (barcode != null) {
            Optional<Garment> garmentFound = garmentDao.findByBarcode(barcode);
            return garmentFound.orElse(null);
        }
        return null;
    }

    public List<Garment> findBySku(String sku) {
        if (sku != null) {
            return garmentDao.findBySkuNumber(sku);
        }
        return null;
    }

    public List<Garment> findByCategory(String category) {
        if (category != null) {
            return garmentDao.findByCategory(category);
        }
        return null;
    }

    public List<Garment> findByCode(String code) {
        if (code != null) {
            return garmentDao.findByCode(code);
        }
        return null;
    }

    public Long save(Garment garment) {
        if (garment != null) {
            Garment garmentSaved = garmentDao.save(garment);
            return garmentSaved.getBarcode();
        }
        return null;
    }

    public GarmentJpo garmentToJpo(Garment garment) {
        GarmentJpo garmentJpo = new GarmentJpo(garment.getName(), garment.getCode(),
                garment.getDescription(), garment.getPrice(), garment.getCategory().getCategoryName(),
                garment.getCategory().getCategoryCode(), garment.getGarmentDetails().getImage(),
                garment.getGarmentDetails().getSize(), garment.getGarmentDetails().getColour().getName(),
                garment.getGarmentDetails().getColour().getCode(), garment.getGarmentDetails().getDiscountPercentage(),
                garment.getTargetConsumer(), garment.isActive());

        List<Material> materialBlend = materialDao.findByGarmentCode(garment.getCode());
        garmentJpo.setMaterial(materialBlend);

        return garmentJpo;
    }

    public List<Material> getGarmentMaterial(String garmentCode) {
        return materialDao.findByGarmentCode(garmentCode);
    }

    public List<String> getGarmentColours(String garmentCode) {

        List<Garment> garments = findByCode(garmentCode);

        return garments.stream().map(Garment::getGarmentDetails).map(GarmentDetails::getColour).map(Colour::getName).distinct().collect(Collectors.toList());
    }

    public List<SizeEnum> getGarmentSizes(String garmentCode) {

        List<Garment> garments = findByCode(garmentCode);

        return garments.stream().map(Garment::getGarmentDetails).map(GarmentDetails::getSize).distinct().collect(Collectors.toList());
    }

    public Long save(GarmentJpo garmentJpo) {
        if (garmentJpo != null) {
            Garment garment = garmentJpo.toGarment();

            Optional<Colour> color = colorDao.findByCode(garmentJpo.getColorCode());
            Optional<Category> category = categoryDao.findByCategoryCode(garmentJpo.getCategoryCode());
            materialDao.saveAll(garmentJpo.getMaterial());
            if (!category.isPresent()) {
                categoryDao.save(garment.getCategory());
            } else {
                garment.setCategory(category.get());
            }
            if (!color.isPresent()) {
                colorDao.save(garment.getGarmentDetails().getColour());
            } else {
                garment.getGarmentDetails().setColour(color.get());
            }
            return save(garment);
        }
        return null;
    }

    public List<StoreAvailabilityJpo> getGarmentAvailability(Long barcode) {
        List<StoreAvailabilityJpo> storeAvailabilityJpos = new ArrayList<>();
        Garment garment = findByBarcode(barcode);
        List<String> colours_sizes = new ArrayList<>();
        List<String> colours_sizes_counted = new ArrayList<>();
        List<String> storesIds = storeService.findAll().stream().map(Store::getName).distinct().collect(Collectors.toList());

        if (garment != null) {
            for (String storeId : storesIds) {
                List<StoreInventory> storeInventory = storeService.getInventory(storeId);
                List<StoreInventory> list =
                        storeInventory.stream().filter(storeInventory1 -> storeInventory1.getGarment().getSkuNumber().split("-")[1].equals(garment.getSkuNumber().split("-")[1])).collect(Collectors.toList());

                for (StoreInventory l : list) {

                    GarmentDetails gd = l.getGarment().getGarmentDetails();
                    colours_sizes.add(gd.getColour().getName() + "_" + gd.getSize());
//                storeAvailabilityJpos.add(new StoreAvailabilityJpo(storeId, gd.getSize(), gd.getColour().getName(), 1));
                }
                for (String cs : colours_sizes) {
                    if (!colours_sizes_counted.contains(cs)) {
                        int q = (int) colours_sizes.stream().filter(colour_size -> colour_size.equals(cs)).count();
                        colours_sizes_counted.add(cs);
                        storeAvailabilityJpos.add(new StoreAvailabilityJpo(storeId, SizeEnum.valueOf(cs.split("_")[1]), cs.split("_")[0], q));

                    }
                }
            }
        }

        return storeAvailabilityJpos;
    }


    public List<String> findGarmentCodes() {
        return garmentDao.findGarmentCodes();
    }

    public List<String> findSkuNumbers() {
        return garmentDao.findSkuNumbers();
    }

}
