//package hua.it21735.thesis.db;
//
//import hua.it21735.thesis.persistence.dao.*;
//import hua.it21735.thesis.persistence.model.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.io.File;
//import java.nio.file.Files;
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class SaveDummyData {
//
//    Logger log = LoggerFactory.getLogger(SaveDummyData.class);
//
//
//    @Bean
//    public CommandLineRunner loadData(CategoryDao categoryDao, ColorDao colorDao, SizeDao sizeDao, MaterialDao materialDao, GarmentDao garmentDao,
//                                      CustomerDao customerDao, AddressDao addressDao, StoreDao storeDao, PurchaseDetailsDao purchaseDetailsDao,
//                                      StoreInventoryDao storeInventoryDao, PointOfSaleDao pointOfSaleDao, RecommendationDao recommendationDao
//    ) {
//        return (args) -> {
//
//            //save store address
//            Address storeAddress = new Address();
//            storeAddress.setCity("Athens");
//            storeAddress.setNumber("47");
//            storeAddress.setStreet("Patision");
//            storeAddress.setPostCode("10433");
//            addressDao.save(storeAddress);
//
//            //save store2 address
//            Address storeAddress2 = new Address();
//            storeAddress2.setCity("Kifisia");
//            storeAddress2.setNumber("13");
//            storeAddress2.setStreet("Charilaou Trikoupi");
//            storeAddress2.setPostCode("14564");
//            addressDao.save(storeAddress2);
//
//            //save store
//            Store store = new Store();
//            store.setName("S001");
//            store.setAddress(storeAddress);
//            store.setEmail("store@gmail.com");
//            store.setPhoneNumber("2108000056");
//            storeDao.save(store);
//
//            //save store2
//            Store store2 = new Store();
//            store2.setName("S002");
//            store2.setAddress(storeAddress2);
//            store2.setEmail("store2@gmail.com");
//            store2.setPhoneNumber("2108000034");
//            storeDao.save(store2);
//
//            //save customer address
//            Address address = new Address();
//            address.setCity("Marousi");
//            address.setNumber("12");
//            address.setStreet("Grammou");
//            address.setPostCode("15124");
//
//            addressDao.save(address);
//
//            //save customer
//            Customer customer = new Customer();
//            customer.setFirstName("Konstantinos");
//            customer.setLastName("Papadopoulos");
//            customer.setEmail("papadkon@gmail.com");
//            customer.setPhoneNumber("6972084380");
//            customer.setAddress(address);
//
//            customerDao.save(customer);
//
//            //save colours
//            Colour black = new Colour("BLACK", "BK");
//            colorDao.save(black);
//            Colour blue = new Colour("BLUE", "BL");
//            colorDao.save(blue);
//            Colour green = new Colour("GREEN", "GR");
//            colorDao.save(green);
//            Colour red = new Colour("RED", "R");
//            colorDao.save(red);
//            Colour yellow = new Colour("YELLOW", "Y");
//            colorDao.save(yellow);
//            Colour whiteBlue = new Colour("WHITE/BLUE", "WB");
//            colorDao.save(whiteBlue);
//            Colour multicolour = new Colour("MULTI-COLOUR", "MULT");
//            colorDao.save(multicolour);
//
//            //save sizes
//            sizeDao.save(new Size(SizeEnum.XS, "62-65", "86-89", "81-83", TargetConsumer.F));
//            sizeDao.save(new Size(SizeEnum.S, "67-70", "91-94", "86-88", TargetConsumer.F));
//            sizeDao.save(new Size(SizeEnum.M, "72-75", "97-99", "91-94", TargetConsumer.F));
//            sizeDao.save(new Size(SizeEnum.L, "79-83", "103-107", "98-100", TargetConsumer.F));
//            sizeDao.save(new Size(SizeEnum.XL, "86-91", "111-116", "103-108", TargetConsumer.F));
//
//            sizeDao.save(new Size(SizeEnum.XS, "62-65", "66", "89-92", TargetConsumer.M));
//            sizeDao.save(new Size(SizeEnum.S, "67-70", "71", "94-99", TargetConsumer.M));
//            sizeDao.save(new Size(SizeEnum.M, "72-75", "74-79", "101-107", TargetConsumer.M));
//            sizeDao.save(new Size(SizeEnum.L, "79-83", "82-87", "109-115", TargetConsumer.M));
//            sizeDao.save(new Size(SizeEnum.XL, "86-91", "92-97", "117-122", TargetConsumer.M));
//
//            sizeDao.save(new Size(SizeEnum.OS, "-", "-", "-", TargetConsumer.F));
//            sizeDao.save(new Size(SizeEnum.OS, "-", "-", "-", TargetConsumer.M));
//
//            //  save categories
//            categoryDao.save(new Category("Shirts and Blouses", "SB"));
//            categoryDao.save(new Category("Jeans", "JN"));
//            categoryDao.save(new Category("Trousers", "TR"));
//            categoryDao.save(new Category("Denim", "DEN"));
////
////            // save a couple of garments
//            File image3 = new File("D:/THESIS/jeans.jpg");
//            if (!image3.exists()) {
//                image3 = new File("/mnt/d/THESIS/jeans.jpg");
//            }
//
//            byte[] bytes3 = Files.readAllBytes(image3.toPath());
//
//            Garment garmentJeans = new Garment();
//            garmentJeans.setName("Skinny Jeans");
//            garmentJeans.setCode("SJ308");
//            garmentJeans.setDescription("Skinny blue jeans");
//            garmentJeans.setPrice((float) 40.99);
//            garmentJeans.setCategory(categoryDao.findByCategoryName("Denim")
//                    .orElse(new Category("Denim", "DEN")));
//
//            garmentJeans.setTargetConsumer(TargetConsumer.F);
//            //    garment.setMaterial(materialDao.findAll().get(0));
//            garmentJeans.setGarmentDetails(new GarmentDetails(bytes3, SizeEnum.S, colorDao.findByCode("BL").get(), false, 0));
//            System.out.println("double1jean");
//            garmentDao.save(garmentJeans);
//            File image2 = new File("D:/THESIS/blackJeans.jpg");
//            if (!image2.exists()) {
//                image2 = new File("/mnt/d/THESIS/blackJeans.jpg");
//            }
//
//            byte[] bytes2 = Files.readAllBytes(image2.toPath());
//
//            Garment jacket = new Garment();
//            jacket.setName("Leather Jacket");
//            jacket.setCode("LJ678");
//            jacket.setDescription("Black Leather Jacket");
//            jacket.setPrice((float) 80.99);
//            categoryDao.save(new Category("Jackets", "JAC"));
//            jacket.setCategory(categoryDao.findByCategoryName("Jackets")
//                    .orElse(new Category("Jackets", "JAC")));
//
//            jacket.setTargetConsumer(TargetConsumer.F);
//            //    garment.setMaterial(materialDao.findAll().get(0));
//            jacket.setGarmentDetails(new GarmentDetails(bytes2, SizeEnum.S, colorDao.findByCode("BK").get(), true, (float) 0.20));
//            System.out.println("double2jean");
//            garmentDao.save(jacket);
////
////            recommendationDao.save(new Recommendation("2", garmentJeans.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", jacket.getSkuNumber()));
////
////            File image2 = new File("D:/THESIS/blackJeans.jpg");
////            if (!image2.exists()) {
////                image2 = new File("/mnt/d/THESIS/blackJeans.jpg");
////            }
////
////            byte[] bytes2 = Files.readAllBytes(image2.toPath());
//
//            Garment garmentBlackJeans = new Garment();
//            garmentBlackJeans.setName("Skinny High-waisted Jeans");
//            garmentBlackJeans.setCode("SJ309");
//            garmentBlackJeans.setDescription("Skinny black jeans");
//            garmentBlackJeans.setPrice((float) 50.99);
//            garmentBlackJeans.setCategory(categoryDao.findByCategoryName("Denim")
//                    .orElse(new Category("Denim", "DEN")));
//
//            garmentBlackJeans.setTargetConsumer(TargetConsumer.F);
//            //    garment.setMaterial(materialDao.findAll().get(0));
//            garmentBlackJeans.setGarmentDetails(new GarmentDetails(bytes2, SizeEnum.S, colorDao.findByCode("BK").get(), false, 0));
//            System.out.println("double2jean");
//            garmentDao.save(garmentBlackJeans);
////
////            recommendationDao.save(new Recommendation("2", garmentJeans.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garmentBlackJeans.getSkuNumber()));
//
//            File image = new File("D:/THESIS/officeShirt.jpg");
//            if (!image.exists()) {
//                image = new File("/mnt/d/THESIS/officeShirt.jpg");
//            }
//
//            byte[] bytes = Files.readAllBytes(image.toPath());
//
//            Garment garment = new Garment();
//            garment.setName("Striped Shirt");
//            garment.setCode("BS01");
//            garment.setDescription("Striped Shirt with loose shoulders");
//            garment.setPrice((float) 20.99);
//            garment.setCategory(categoryDao.findByCategoryName("Shirts and Blouses")
//                    .orElse(new Category("T-Shirts", "TS")));
//
//            garment.setTargetConsumer(TargetConsumer.F);
//            //    garment.setMaterial(materialDao.findAll().get(0));
//            garment.setGarmentDetails(new GarmentDetails(bytes, SizeEnum.S, whiteBlue, true, 0.5F));
//            System.out.println("double1");
//            garmentDao.save(garment);
//            System.out.println("double2");
//            Garment test = new Garment();
//            test.setName("Basic T-Shirt");
//            test.setCode("BS01");
//            test.setDescription("Basic T-Shirt made of cotton");
//            test.setPrice((float) 20.99);
//            test.setCategory(categoryDao.findByCategoryName("Shirts and Blouses")
//                    .orElse(new Category("T-Shirts", "TS")));
//
//            test.setTargetConsumer(TargetConsumer.F);
//            test.setGarmentDetails(new GarmentDetails(bytes, SizeEnum.S, yellow, false, 0));
//            garmentDao.save(test);
//
//            Garment test2 = new Garment();
//            test2.setName("Basic T-Shirt");
//            test2.setCode("BS01");
//            test2.setDescription("Basic T-Shirt made of cotton");
//            test2.setPrice((float) 20.99);
//            test2.setCategory(categoryDao.findByCategoryName("Shirts and Blouses")
//                    .orElse(new Category("T-Shirts", "TS")));
//
//            test2.setTargetConsumer(TargetConsumer.F);
//            test2.setGarmentDetails(new GarmentDetails(bytes, SizeEnum.M, red, false, 0));
//            garmentDao.save(test2);
//
//            Garment garment1 = new Garment();
//            garment1.setName("Office Shirt");
//            garment1.setCode("OFS01");
//            garment1.setDescription("Shirt for the office");
//            garment1.setPrice((float) 50.0);
//            garment1.setCategory(categoryDao.findByCategoryName("Shirts and Blouses")
//                    .orElse(new Category("Shirts", "SH")));
//
//            garment1.setTargetConsumer(TargetConsumer.F);
//            //   garment1.setMaterial(materialDao.findAll().get(1));
//            garment1.setGarmentDetails(new GarmentDetails(bytes, SizeEnum.S, multicolour, true, 0.5F));
//            garmentDao.save(garment1);
//
//            Garment garment3 = new Garment();
//            garment3.setName("Office Shirt");
//            garment3.setCode("OFS02");
//            garment3.setDescription("Shirt for the office");
//            garment3.setPrice((float) 50.0);
//            garment3.setCategory(categoryDao.findByCategoryName("Shirts and Blouses")
//                    .orElse(new Category("Shirts", "SH")));
//
//            garment3.setTargetConsumer(TargetConsumer.F);
//            //   garment3.setMaterial(materialDao.findAll().get(1));
//            garment3.setGarmentDetails(new GarmentDetails(bytes, SizeEnum.M, multicolour, true, 0.5F));
//            garmentDao.save(garment3);
//
//            Garment garment4 = new Garment();
//            garment4.setName("Office Shirt");
//            garment4.setCode("OFS02");
//            garment4.setDescription("Shirt for the office");
//            garment4.setPrice((float) 50.0);
//            garment4.setCategory(categoryDao.findByCategoryName("Shirts and Blouses")
//                    .orElse(new Category("Shirts", "SH")));
//
//            garment4.setTargetConsumer(TargetConsumer.F);
//            //   garment3.setMaterial(materialDao.findAll().get(1));
//            garment4.setGarmentDetails(new GarmentDetails(bytes, SizeEnum.S, multicolour, true, 0.5F));
//            garmentDao.save(garment4);
//
//            //save materials
//            materialDao.save(new Material("cotton", 20, garment.getCode()));
//            materialDao.save(new Material("viscose", 80, garment.getCode()));
//            materialDao.save(new Material("cotton", 100, garment1.getCode()));
//            materialDao.save(new Material("cotton", 100, garment3.getCode()));
////            materialDao.save(new Material(60, 20, 20, 0, 0));
////            materialDao.save(new Material(20, 80, 0, 0, 0));
////            //materialDao.save(new Material(20, 0, 0, 0, 80));
//
//            StoreInventory storeInventory = new StoreInventory(store, garment);
//
//            storeInventoryDao.save(storeInventory);
//
//            StoreInventory storeInventory1 = new StoreInventory(store, garment1);
//            StoreInventory storeInventory2 = new StoreInventory(store, test);
//            StoreInventory storeInventory3 = new StoreInventory(store, test2);
//            StoreInventory storeInventory4 = new StoreInventory(store, garment3);
//            StoreInventory storeInventory5 = new StoreInventory(store2, garment4);
//
//            storeInventoryDao.save(storeInventory1);
//            storeInventoryDao.save(storeInventory2);
//            storeInventoryDao.save(storeInventory3);
//            storeInventoryDao.save(storeInventory4);
//            storeInventoryDao.save(storeInventory5);
//
//            List<PurchaseDetails> details = new ArrayList<>();
//            PurchaseDetails purchaseDetails = new PurchaseDetails();
//            purchaseDetails.setProduct(garment);
//            purchaseDetails.setPrice();
//
//            PurchaseDetails purchaseDetails1 = new PurchaseDetails();
//            purchaseDetails1.setProduct(garment3);
//            purchaseDetails1.setPrice();
//            details.add(purchaseDetails);
//            details.add(purchaseDetails1);
//            purchaseDetailsDao.save(purchaseDetails);
//            purchaseDetailsDao.save(purchaseDetails1);
//
//
//            PointOfSale pos = new PointOfSale();
//            pos.setStore(store);
//            pos.setDate(LocalDateTime.now());
//            pos.setCustomer(customer);
//            pos.setStatus(StatusEnum.PENDING);
//            pos.setDetails(details);
//
//            pointOfSaleDao.save(pos);
//
////            recommendationDao.save(new Recommendation("1", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garment1.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garment3.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", test.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", test2.getSkuNumber()));
////
////
//        };
//    }
//
//}