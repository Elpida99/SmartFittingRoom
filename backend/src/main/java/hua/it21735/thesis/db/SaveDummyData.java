package hua.it21735.thesis.db;

import hua.it21735.thesis.persistence.dao.*;
import hua.it21735.thesis.persistence.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class SaveDummyData {

    Logger log = LoggerFactory.getLogger(SaveDummyData.class);


    @Bean
    public CommandLineRunner loadData(CategoryDao categoryDao, ColorDao colorDao, SizeDao sizeDao, MaterialDao materialDao, GarmentDao garmentDao,
                                      CustomerDao customerDao, AddressDao addressDao, StoreDao storeDao, PurchaseDetailsDao purchaseDetailsDao,
                                      StoreInventoryDao storeInventoryDao, PointOfSaleDao pointOfSaleDao, RecommendationDao recommendationDao
    ) {
        return (args) -> {

            // save store addresses
            Address storeAddress = new Address("Athens", "47", "Patision", "10433");
            Address storeAddress2 = new Address("Kifisia", "13", "Charilaou Trikoupi", "14564");
            Address storeAddress3 = new Address("Athens", "40", "Theodorou Diligianni", "10438");
            Address storeAddress4 = new Address("Peristeri", "58", "Mistriotou", "12137");

            addressDao.save(storeAddress);
            addressDao.save(storeAddress2);
            addressDao.save(storeAddress3);
            addressDao.save(storeAddress4);

            // save stores
            Store store = new Store("S001", storeAddress, "store@gmail.com", "2108000056");
            Store store2 = new Store("S002", storeAddress2, "store2@gmail.com", "2108000057");
            Store store3 = new Store("S003", storeAddress3, "store3@gmail.com", "2108000058");
            Store store4 = new Store("S004", storeAddress4, "store4@gmail.com", "2108000059");

            storeDao.save(store);
            storeDao.save(store2);
            storeDao.save(store3);
            storeDao.save(store4);

            // save customer addresses
            Address customerAddress = new Address("Athens", "14", "Amerikis", "10672");
            Address customerAddress2 = new Address("Kallithea", "100", "Skra", "17673");
            Address customerAddress3 = new Address("Chalandri", "17", "Agiou Georgiou", "15234");
            Address customerAddress4 = new Address("Kifisia", "2", "Kolokotroni", "14562");
            Address customerAddress5 = new Address("Chalandri", "107", "Agias Paraskevis ", "15234");
            Address customerAddress6 = new Address("Marousi", "67", "Kritis", "15122");
            Address customerAddress7 = new Address("Athens", "340", "Acharnon", "11145");
            Address customerAddress8 = new Address("Nea Ionia", "111", "El. Venizelou", "14231");
            Address customerAddress9 = new Address("Chalandri", "29", "Kosta Varnali", "15233");
            Address customerAddress10 = new Address("Athens", "61", "Stournari", "10432");
            Address customerAddress11 = new Address("Athens", "32", "Kallirrois", "14231");
            Address customerAddress12 = new Address("Agia Paraskevi", "1", "Konstantinou Paleologou", "15342");

            addressDao.save(customerAddress);
            addressDao.save(customerAddress2);
            addressDao.save(customerAddress3);
            addressDao.save(customerAddress4);
            addressDao.save(customerAddress5);
            addressDao.save(customerAddress6);
            addressDao.save(customerAddress7);
            addressDao.save(customerAddress8);
            addressDao.save(customerAddress9);
            addressDao.save(customerAddress10);
            addressDao.save(customerAddress11);
            addressDao.save(customerAddress12);


            // save customers
            Customer customer = new Customer("Konstantina", "Michelakou", randomPhoneNumberGen(), "konmich@gmail.com");
            customer.setAddress(customerAddress);

            Customer customer2 = new Customer("Eleni", "Michelakou", randomPhoneNumberGen(), "elmich@gmail.com");
            customer2.setAddress(customerAddress);

            Customer customer3 = new Customer("Emilia", "Stefanidi", randomPhoneNumberGen(), "emstefan@gmail.com");
            customer3.setAddress(customerAddress2);

            Customer customer4 = new Customer("Charalampos", "Kokkinos", randomPhoneNumberGen(), "charkokkinos@gmail.com");
            customer4.setAddress(customerAddress3);

            Customer customer5 = new Customer("Nikoleta", "Katsarou", randomPhoneNumberGen(), "nikatsarou@gmail.com");
            customer5.setAddress(customerAddress4);

            Customer customer6 = new Customer("Vasiliki", "Iordanou", randomPhoneNumberGen(), "vasiord@gmail.com");
            customer6.setAddress(customerAddress5);

            Customer customer7 = new Customer("Areti", "Antoniou", randomPhoneNumberGen(), "anton.areti@gmail.com");
            customer7.setAddress(customerAddress6);

            Customer customer8 = new Customer("Takis", "Stephanidis", randomPhoneNumberGen(), "stephanidis_takis@gmail.com");
            customer8.setAddress(customerAddress7);

            Customer customer9 = new Customer("Sofoklis", "Demetriou", randomPhoneNumberGen(), "sofdemet@gmail.com");
            customer9.setAddress(customerAddress8);

            Customer customer10 = new Customer("Grigoris", "Kontos", randomPhoneNumberGen(), "gregkontos@gmail.com");
            customer10.setAddress(customerAddress9);

            Customer customer11 = new Customer("Agni", "Giannopoulou", randomPhoneNumberGen(), "agnigiann@gmail.com");
            customer11.setAddress(customerAddress10);

            Customer customer12 = new Customer("Nikos", "Georgiou", randomPhoneNumberGen(), "nikos_georgiou@gmail.com");
            customer12.setAddress(customerAddress11);

            Customer customer13 = new Customer("Konstantinos", "Papageorgiou", randomPhoneNumberGen(), "nikpapageo@gmail.com");
            customer13.setAddress(customerAddress12);

            customerDao.save(customer);
            customerDao.save(customer2);
            customerDao.save(customer3);
            customerDao.save(customer4);
            customerDao.save(customer5);
            customerDao.save(customer6);
            customerDao.save(customer7);
            customerDao.save(customer8);
            customerDao.save(customer9);

            customerDao.save(customer10);
            customerDao.save(customer11);
            customerDao.save(customer12);
            customerDao.save(customer13);

            //save colours
            Colour black = new Colour("BLACK", "BK");
            colorDao.save(black);
            Colour blue = new Colour("BLUE", "BL");
            colorDao.save(blue);
            Colour green = new Colour("GREEN", "GR");
            colorDao.save(green);
            Colour red = new Colour("RED", "R");
            colorDao.save(red);
            Colour yellow = new Colour("YELLOW", "Y");
            colorDao.save(yellow);
            Colour whiteBlue = new Colour("WHITE/BLUE", "WB");
            colorDao.save(whiteBlue);
            Colour multicolour = new Colour("MULTI-COLOUR", "MULT");
            colorDao.save(multicolour);

            //  save sizes
            sizeDao.save(new Size(SizeEnum.XS, "62-65", "86-89", "81-83", TargetConsumer.F));
            sizeDao.save(new Size(SizeEnum.S, "67-70", "91-94", "86-88", TargetConsumer.F));
            sizeDao.save(new Size(SizeEnum.M, "72-75", "97-99", "91-94", TargetConsumer.F));
            sizeDao.save(new Size(SizeEnum.L, "79-83", "103-107", "98-100", TargetConsumer.F));
            sizeDao.save(new Size(SizeEnum.XL, "86-91", "111-116", "103-108", TargetConsumer.F));

            sizeDao.save(new Size(SizeEnum.XS, "62-65", "66", "89-92", TargetConsumer.M));
            sizeDao.save(new Size(SizeEnum.S, "67-70", "71", "94-99", TargetConsumer.M));
            sizeDao.save(new Size(SizeEnum.M, "72-75", "74-79", "101-107", TargetConsumer.M));
            sizeDao.save(new Size(SizeEnum.L, "79-83", "82-87", "109-115", TargetConsumer.M));
            sizeDao.save(new Size(SizeEnum.XL, "86-91", "92-97", "117-122", TargetConsumer.M));

            sizeDao.save(new Size(SizeEnum.OS, "-", "-", "-", TargetConsumer.F));
            sizeDao.save(new Size(SizeEnum.OS, "-", "-", "-", TargetConsumer.M));

            //  save categories
            Category category = new Category("Shirts and Blouses", "SB");
            Category category1 = new Category("Jeans", "JN");
            Category category2 = new Category("Trousers", "TR");
            Category category3 = new Category("Denim", "DEN");
            Category category4 = new Category("Accessories", "ACC");

            categoryDao.save(category);
            categoryDao.save(category1);
            categoryDao.save(category2);
            categoryDao.save(category3);
            categoryDao.save(category4);

            //save garments
            GarmentDetails garmentDetails = new GarmentDetails(null, SizeEnum.S, black, false, 0);
            Garment garment = new Garment("", "", 0.0F, category, garmentDetails, TargetConsumer.F);
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
//            recommendationDao.save(new Recommendation("2", garmentJeans.getSkuNumber()));
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

//            List<PurchaseDetails> details = new ArrayList<>();
//            PurchaseDetails purchaseDetails = new PurchaseDetails();
//            purchaseDetails.setProduct(garmentDao.findAll().get(0));
//            purchaseDetails.setPrice();
//
//            PurchaseDetails purchaseDetails1 = new PurchaseDetails();
//            purchaseDetails1.setProduct(garmentDao.findAll().get(4));
//            purchaseDetails1.setPrice();
//
//            PurchaseDetails purchaseDetails2 = new PurchaseDetails();
//            purchaseDetails2.setProduct(garmentDao.findAll().get(4));
//            purchaseDetails2.setPrice();
//
//            PurchaseDetails purchaseDetails3 = new PurchaseDetails();
//            purchaseDetails3.setProduct(garmentDao.findAll().get(4));
//            purchaseDetails3.setPrice();
//
//            details.add(purchaseDetails);
//            details.add(purchaseDetails1);
//            details.add(purchaseDetails2);
//            details.add(purchaseDetails3);
//            purchaseDetailsDao.save(purchaseDetails);
//            purchaseDetailsDao.save(purchaseDetails1);
//            purchaseDetailsDao.save(purchaseDetails2);
//            purchaseDetailsDao.save(purchaseDetails3);
//
//
//            PointOfSale pos = new PointOfSale();
//            pos.setStore(storeDao.findAll().get(0));
//            pos.setDate(LocalDateTime.now());
//      //      pos.setCustomer(customer);
//            pos.setStatus(StatusEnum.PENDING);
//            pos.setDetails(details);
//
//            pointOfSaleDao.save(pos);

//            recommendationDao.save(new Recommendation("1", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garment1.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garment3.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", test.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", test2.getSkuNumber()));
////
//
        };
    }

    private static String randomPhoneNumberGen() {
        Random random = new Random();
        char[] digits = new char[10];
        digits[0] = '6';
        digits[1] = '9';
        digits[2] = (char) (random.nextInt(9) + '1');
        for (int i = 3; i < 10; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return String.copyValueOf(digits);
    }

}
