package hua.it21735.thesis.db;

import hua.it21735.thesis.persistence.dao.*;
import hua.it21735.thesis.persistence.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Random;

@Configuration
public class SaveDummyData {

    Logger log = LoggerFactory.getLogger(SaveDummyData.class);


    @Bean
    public CommandLineRunner loadData(CategoryDao categoryDao, ColorDao colorDao, SizeDao sizeDao, MaterialDao materialDao, GarmentDao garmentDao,
                                      CustomerDao customerDao, AddressDao addressDao, StoreDao storeDao, PurchaseDetailsDao purchaseDetailsDao,
                                      StoreInventoryDao storeInventoryDao, PointOfSaleDao pointOfSaleDao, RecommendationDao recommendationDao,
                                      final GarmentDetailsDao garmentDetailsDao) {
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
            Colour brown = new Colour("BROWN", "BR");
            colorDao.save(brown);
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
            Category shirtsAndBlouses = new Category("Shirts and Blouses", "SB");
            Category jeans = new Category("Jeans", "JN");
            Category trousers = new Category("Trousers", "TR");
            Category denim = new Category("Denim", "DEN");
            Category accs = new Category("Accessories", "ACC");
            Category hoodies = new Category("Hoodies", "HOD");

            categoryDao.save(shirtsAndBlouses);
            categoryDao.save(jeans);
            categoryDao.save(trousers);
            categoryDao.save(denim);
            categoryDao.save(accs);
            categoryDao.save(hoodies);

            //save garments
            //001
            File image = new File(System.getProperty("user.dir") + "/images/001psathino-kapelo.jpg");
            byte[] bytes = Files.readAllBytes(image.toPath());

            GarmentDetails garmentDetails = new GarmentDetails(bytes, SizeEnum.OS, brown, false, 0);
            garmentDetailsDao.save(garmentDetails);

            Garment garment = new Garment("Ψάθινο Καπέλο", "Ψάθινο καπέλο θαλάσσης", 15.99F, accs, garmentDetails, TargetConsumer.F, "BH001");

            garmentDao.save(garment);

            saveGarments(garment, 10, garmentDao, storeInventoryDao, storeDao);
            materialDao.save(new Material("straw", 100, garment.getCode()));

            //002
            File image2 = new File(System.getProperty("user.dir") + "/images/002kokkino-hoodie.jpg");
            byte[] bytes2 = Files.readAllBytes(image2.toPath());

            GarmentDetails garmentDetails2 = new GarmentDetails(bytes2, SizeEnum.XS, red, false, 0);
            GarmentDetails garmentDetails3 = new GarmentDetails(bytes2, SizeEnum.S, red, false, 0);
            GarmentDetails garmentDetails4 = new GarmentDetails(bytes2, SizeEnum.M, red, false, 0);
            GarmentDetails garmentDetails5 = new GarmentDetails(bytes2, SizeEnum.L, red, false, 0);
            garmentDetailsDao.save(garmentDetails2);
            garmentDetailsDao.save(garmentDetails3);
            garmentDetailsDao.save(garmentDetails4);
            garmentDetailsDao.save(garmentDetails5);

            Garment garment2 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails2, TargetConsumer.F
                    , "RH001");
            garmentDao.save(garment2);

            Garment garment3 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails3, TargetConsumer.F
                    , "RH001");
            garmentDao.save(garment3);

            Garment garment4 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails4, TargetConsumer.F
                    , "RH001");
            garmentDao.save(garment4);

            Garment garment5 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails5, TargetConsumer.F
                    , "RH001");
            garmentDao.save(garment5);

            saveGarments(garment2, 10, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garment3, 20, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garment4, 60, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garment5, 5, garmentDao, storeInventoryDao, storeDao);

            materialDao.save(new Material("cotton", 100, garment2.getCode()));


            //003
            File image3 = new File(System.getProperty("user.dir") + "/images/003denim-shorts.jpg");
            byte[] bytes3 = Files.readAllBytes(image3.toPath());

            GarmentDetails garmentDetailsShorts = new GarmentDetails(bytes3, SizeEnum.XXS, blue, true, 0.2F);
            GarmentDetails garmentDetailsShorts1 = new GarmentDetails(bytes3, SizeEnum.XS, blue, true, 0.2F);
            GarmentDetails garmentDetailsShorts2 = new GarmentDetails(bytes3, SizeEnum.S, blue, true, 0.2F);
            GarmentDetails garmentDetailsShorts3 = new GarmentDetails(bytes3, SizeEnum.M, blue, true, 0.2F);
            GarmentDetails garmentDetailsShorts4 = new GarmentDetails(bytes3, SizeEnum.L, blue, true, 0.2F);
            garmentDetailsDao.save(garmentDetailsShorts);
            garmentDetailsDao.save(garmentDetailsShorts1);
            garmentDetailsDao.save(garmentDetailsShorts2);
            garmentDetailsDao.save(garmentDetailsShorts3);
            garmentDetailsDao.save(garmentDetailsShorts4);

            Garment garmentShorts = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts, TargetConsumer.F,
                    "DS004");
            garmentDao.save(garmentShorts);

            Garment garmentShorts1 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts1, TargetConsumer.F,
                    "DS004");
            garmentDao.save(garmentShorts1);

            Garment garmentShorts2 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts2, TargetConsumer.F,
                    "DS004");
            garmentDao.save(garmentShorts2);

            Garment garmentShorts3 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts3, TargetConsumer.F,
                    "DS004");
            garmentDao.save(garmentShorts3);

            Garment garmentShorts4 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts4, TargetConsumer.F,
                    "DS004");
            garmentDao.save(garmentShorts4);

            saveGarments(garmentShorts, 50, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garmentShorts1, 40, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garmentShorts2, 80, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garmentShorts3, 20, garmentDao, storeInventoryDao, storeDao);
            saveGarments(garmentShorts4, 100, garmentDao, storeInventoryDao, storeDao);

            materialDao.save(new Material("cotton", 100, garmentShorts.getCode()));

            // save inventory

            StoreInventory storeInventory = new StoreInventory(store, garment);
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

    private void saveGarments(Garment garment, int times, GarmentDao garmentDao, StoreInventoryDao storeInventoryDao, StoreDao storeDao) {

        List<Store> all = storeDao.findAll();
        StoreInventory storeInventory1 = new StoreInventory(all.get(0), garment);
        storeInventoryDao.save(storeInventory1);

        int c = 0;

        for (int i = 0; i < times; i++) {
            if (c > 3) {
                c = 0;
            }
            Garment g1 = new Garment(garment);
            g1.setGarmentDetails(garment.getGarmentDetails());
            garmentDao.save(g1);

            StoreInventory storeInventory = new StoreInventory(all.get(c++), g1);
            storeInventoryDao.save(storeInventory);
        }

    }

}
