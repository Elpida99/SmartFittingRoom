//package hua.it21735.thesis.db;
//
//import hua.it21735.thesis.persistence.dao.*;
//import hua.it21735.thesis.persistence.model.*;
//import hua.it21735.thesis.persistence.service.PurchaseService;
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
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//@Configuration
//public class SaveDummyData {
//
//    Logger log = LoggerFactory.getLogger(SaveDummyData.class);
//    private final TryOnDao tryOnDao;
//
//    private final CategoryDao categoryDao;
//    private final ColorDao colorDao;
//    private final SizeDao sizeDao;
//    private final MaterialDao materialDao;
//    private final GarmentDao garmentDao;
//    private final CustomerDao customerDao;
//    private final AddressDao addressDao;
//    private final StoreDao storeDao;
//    private final PurchaseDetailsDao purchaseDetailsDao;
//    private final StoreInventoryDao storeInventoryDao;
//    private final PurchaseService pointOfSaleDao;
//    private final RecommendationDao recommendationDao;
//    private final GarmentDetailsDao garmentDetailsDao;
//
//    public SaveDummyData(final TryOnDao tryOnDao, final CategoryDao categoryDao, final ColorDao colorDao, final SizeDao sizeDao, final MaterialDao materialDao, final GarmentDao garmentDao, final CustomerDao customerDao, final AddressDao addressDao, final StoreDao storeDao, final PurchaseDetailsDao purchaseDetailsDao, final StoreInventoryDao storeInventoryDao, final PurchaseService pointOfSaleDao, final RecommendationDao recommendationDao, final GarmentDetailsDao garmentDetailsDao) {
//        this.tryOnDao = tryOnDao;
//        this.categoryDao = categoryDao;
//        this.colorDao = colorDao;
//        this.sizeDao = sizeDao;
//        this.materialDao = materialDao;
//        this.garmentDao = garmentDao;
//        this.customerDao = customerDao;
//        this.addressDao = addressDao;
//        this.storeDao = storeDao;
//        this.purchaseDetailsDao = purchaseDetailsDao;
//        this.storeInventoryDao = storeInventoryDao;
//        this.pointOfSaleDao = pointOfSaleDao;
//        this.recommendationDao = recommendationDao;
//        this.garmentDetailsDao = garmentDetailsDao;
//    }
//
//
//    @Bean
//    public CommandLineRunner loadData() {
//        return (args) -> {
//
//            // save store addresses
//            Address storeAddress = new Address("Athens", "47", "Patision", "10433");
//            Address storeAddress2 = new Address("Kifisia", "13", "Charilaou Trikoupi", "14564");
//            Address storeAddress3 = new Address("Athens", "40", "Theodorou Diligianni", "10438");
//            Address storeAddress4 = new Address("Peristeri", "58", "Mistriotou", "12137");
//
//            addressDao.save(storeAddress);
//            addressDao.save(storeAddress2);
//            addressDao.save(storeAddress3);
//            addressDao.save(storeAddress4);
//
//            // save stores
//            Store store = new Store("S001", storeAddress, "store@gmail.com", "2108000056");
//            Store store2 = new Store("S002", storeAddress2, "store2@gmail.com", "2108000057");
//            Store store3 = new Store("S003", storeAddress3, "store3@gmail.com", "2108000058");
//            Store store4 = new Store("S004", storeAddress4, "store4@gmail.com", "2108000059");
//
//            storeDao.save(store);
//            storeDao.save(store2);
//            storeDao.save(store3);
//            storeDao.save(store4);
//
//            // save customer addresses
//            Address customerAddress = new Address("Athens", "14", "Amerikis", "10672");
//            Address customerAddress2 = new Address("Kallithea", "100", "Skra", "17673");
//            Address customerAddress3 = new Address("Chalandri", "17", "Agiou Georgiou", "15234");
//            Address customerAddress4 = new Address("Kifisia", "2", "Kolokotroni", "14562");
//            Address customerAddress5 = new Address("Chalandri", "107", "Agias Paraskevis ", "15234");
//            Address customerAddress6 = new Address("Marousi", "67", "Kritis", "15122");
//            Address customerAddress7 = new Address("Athens", "340", "Acharnon", "11145");
//            Address customerAddress8 = new Address("Nea Ionia", "111", "El. Venizelou", "14231");
//            Address customerAddress9 = new Address("Chalandri", "29", "Kosta Varnali", "15233");
//            Address customerAddress10 = new Address("Athens", "61", "Stournari", "10432");
//            Address customerAddress11 = new Address("Athens", "32", "Kallirrois", "14231");
//            Address customerAddress12 = new Address("Agia Paraskevi", "1", "Konstantinou Paleologou", "15342");
//
//            addressDao.save(customerAddress);
//            addressDao.save(customerAddress2);
//            addressDao.save(customerAddress3);
//            addressDao.save(customerAddress4);
//            addressDao.save(customerAddress5);
//            addressDao.save(customerAddress6);
//            addressDao.save(customerAddress7);
//            addressDao.save(customerAddress8);
//            addressDao.save(customerAddress9);
//            addressDao.save(customerAddress10);
//            addressDao.save(customerAddress11);
//            addressDao.save(customerAddress12);
//
//
//            // save customers
//            Customer customer = new Customer("Konstantina", "Michelakou", randomPhoneNumberGen(), "konmich@gmail.com");
//            customer.setAddress(customerAddress);
//
//            Customer customer2 = new Customer("Eleni", "Michelakou", randomPhoneNumberGen(), "elmich@gmail.com");
//            customer2.setAddress(customerAddress);
//
//            Customer customer3 = new Customer("Emilia", "Stefanidi", randomPhoneNumberGen(), "emstefan@gmail.com");
//            customer3.setAddress(customerAddress2);
//
//            Customer customer4 = new Customer("Charalampos", "Kokkinos", randomPhoneNumberGen(), "charkokkinos@gmail.com");
//            customer4.setAddress(customerAddress3);
//
//            Customer customer5 = new Customer("Nikoleta", "Katsarou", randomPhoneNumberGen(), "nikatsarou@gmail.com");
//            customer5.setAddress(customerAddress4);
//
//            Customer customer6 = new Customer("Vasiliki", "Iordanou", randomPhoneNumberGen(), "vasiord@gmail.com");
//            customer6.setAddress(customerAddress5);
//
//            Customer customer7 = new Customer("Areti", "Antoniou", randomPhoneNumberGen(), "anton.areti@gmail.com");
//            customer7.setAddress(customerAddress6);
//
//            Customer customer8 = new Customer("Takis", "Stephanidis", randomPhoneNumberGen(), "stephanidis_takis@gmail.com");
//            customer8.setAddress(customerAddress7);
//
//            Customer customer9 = new Customer("Sofoklis", "Demetriou", randomPhoneNumberGen(), "sofdemet@gmail.com");
//            customer9.setAddress(customerAddress8);
//
//            Customer customer10 = new Customer("Grigoris", "Kontos", randomPhoneNumberGen(), "gregkontos@gmail.com");
//            customer10.setAddress(customerAddress9);
//
//            Customer customer11 = new Customer("Agni", "Giannopoulou", randomPhoneNumberGen(), "agnigiann@gmail.com");
//            customer11.setAddress(customerAddress10);
//
//            Customer customer12 = new Customer("Nikos", "Georgiou", randomPhoneNumberGen(), "nikos_georgiou@gmail.com");
//            customer12.setAddress(customerAddress11);
//
//            Customer customer13 = new Customer("Konstantinos", "Papageorgiou", randomPhoneNumberGen(), "nikpapageo@gmail.com");
//            customer13.setAddress(customerAddress12);
//
//            customerDao.save(customer);
//            customerDao.save(customer2);
//            customerDao.save(customer3);
//            customerDao.save(customer4);
//            customerDao.save(customer5);
//            customerDao.save(customer6);
//            customerDao.save(customer7);
//            customerDao.save(customer8);
//            customerDao.save(customer9);
//
//            customerDao.save(customer10);
//            customerDao.save(customer11);
//            customerDao.save(customer12);
//            customerDao.save(customer13);
//
//            //save colours
//            Colour black = new Colour("BLACK", "BK");
//            colorDao.save(black);
//            Colour brown = new Colour("BROWN", "BR");
//            colorDao.save(brown);
//            Colour lightBrown = new Colour("LIGHT BROWN", "LBR");
//            colorDao.save(lightBrown);
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
//            Colour white = new Colour("WHITE", "W");
//            colorDao.save(white);
//            Colour multicolour = new Colour("MULTI-COLOUR", "MULT");
//            colorDao.save(multicolour);
//
//            //  save sizes
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
//            Category shirtsAndBlouses = new Category("Shirts and Blouses", "SB");
//            Category jeans = new Category("Jeans", "JN");
//            Category trousers = new Category("Trousers", "TR");
//            Category denim = new Category("Denim", "DEN");
//            Category accs = new Category("Accessories", "ACC");
//            Category hoodies = new Category("Hoodies", "HOD");
//            Category crochet = new Category("Crochet and Knit", "CRKN");
//
//            categoryDao.save(shirtsAndBlouses);
//            categoryDao.save(jeans);
//            categoryDao.save(trousers);
//            categoryDao.save(denim);
//            categoryDao.save(accs);
//            categoryDao.save(hoodies);
//            categoryDao.save(crochet);
//
//            //save garments
//            //001
//            File image = new File(System.getProperty("user.dir") + "/images/001psathino-kapelo.jpg");
//            byte[] bytes = Files.readAllBytes(image.toPath());
//
//            GarmentDetails garmentDetails = new GarmentDetails(bytes, SizeEnum.OS, brown, false, 0);
//            garmentDetailsDao.save(garmentDetails);
//
//            Garment garment = new Garment("Ψάθινο Καπέλο", "Ψάθινο καπέλο θαλάσσης", 15.99F, accs, garmentDetails, TargetConsumer.F, "BH001");
//
//            garmentDao.save(garment);
//
//            saveGarments(garment, 10);
//            materialDao.save(new Material("straw", 100, garment.getCode()));
//
//            //bucket-hat
//            File imageHat = new File(System.getProperty("user.dir") + "/images/bucket-hat.jpg");
//            byte[] bytesHat = Files.readAllBytes(imageHat.toPath());
//
//            GarmentDetails garmentDetailsHat = new GarmentDetails(bytesHat, SizeEnum.OS, lightBrown, false, 0);
//            garmentDetailsDao.save(garmentDetailsHat);
//
//            Garment garmentHat = new Garment("Καπέλο τύπου \"Bucket\"", "Καπέλο Bucket κοτλέ", 9.99F, accs, garmentDetailsHat, TargetConsumer.M,
//                    "BH002");
//
//            garmentDao.save(garmentHat);
//
//            saveGarments(garmentHat, 15);
//            materialDao.save(new Material("polyester", 100, garmentHat.getCode()));
//
//
//            //002
//            File image2 = new File(System.getProperty("user.dir") + "/images/002kokkino-hoodie.jpg");
//            byte[] bytes2 = Files.readAllBytes(image2.toPath());
//
//            GarmentDetails garmentDetails2 = new GarmentDetails(bytes2, SizeEnum.XS, red, false, 0);
//            GarmentDetails garmentDetails3 = new GarmentDetails(bytes2, SizeEnum.S, red, false, 0);
//            GarmentDetails garmentDetails4 = new GarmentDetails(bytes2, SizeEnum.M, red, false, 0);
//            GarmentDetails garmentDetails5 = new GarmentDetails(bytes2, SizeEnum.L, red, false, 0);
//            garmentDetailsDao.save(garmentDetails2);
//            garmentDetailsDao.save(garmentDetails3);
//            garmentDetailsDao.save(garmentDetails4);
//            garmentDetailsDao.save(garmentDetails5);
//
//            Garment garment2 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails2, TargetConsumer.F
//                    , "RH001");
//            garmentDao.save(garment2);
//
//            Garment garment3 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails3, TargetConsumer.F
//                    , "RH001");
//            garmentDao.save(garment3);
//
//            Garment garment4 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails4, TargetConsumer.F
//                    , "RH001");
//            garmentDao.save(garment4);
//
//            Garment garment5 = new Garment("Φούτερ \"Tomato Soup\"", "Κόκκινο φούτερ με σχέδιο", 25.99F, hoodies, garmentDetails5, TargetConsumer.F
//                    , "RH001");
//            garmentDao.save(garment5);
//
//            saveGarments(garment2, 10);
//            saveGarments(garment3, 20);
//            saveGarments(garment4, 60);
//            saveGarments(garment5, 5);
//
//            materialDao.save(new Material("cotton", 100, garment2.getCode()));
//
//
//            //003
//            File image3 = new File(System.getProperty("user.dir") + "/images/003denim-shorts.jpg");
//            byte[] bytes3 = Files.readAllBytes(image3.toPath());
//
//            GarmentDetails garmentDetailsShorts = new GarmentDetails(bytes3, SizeEnum.XXS, blue, true, 0.2F);
//            GarmentDetails garmentDetailsShorts1 = new GarmentDetails(bytes3, SizeEnum.XS, blue, true, 0.2F);
//            GarmentDetails garmentDetailsShorts2 = new GarmentDetails(bytes3, SizeEnum.S, blue, true, 0.2F);
//            GarmentDetails garmentDetailsShorts3 = new GarmentDetails(bytes3, SizeEnum.M, blue, true, 0.2F);
//            GarmentDetails garmentDetailsShorts4 = new GarmentDetails(bytes3, SizeEnum.L, blue, true, 0.2F);
//            garmentDetailsDao.save(garmentDetailsShorts);
//            garmentDetailsDao.save(garmentDetailsShorts1);
//            garmentDetailsDao.save(garmentDetailsShorts2);
//            garmentDetailsDao.save(garmentDetailsShorts3);
//            garmentDetailsDao.save(garmentDetailsShorts4);
//
//            Garment garmentShorts = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts, TargetConsumer.F,
//                    "DS004");
//            garmentDao.save(garmentShorts);
//
//            Garment garmentShorts1 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts1, TargetConsumer.F,
//                    "DS004");
//            garmentDao.save(garmentShorts1);
//
//            Garment garmentShorts2 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts2, TargetConsumer.F,
//                    "DS004");
//            garmentDao.save(garmentShorts2);
//
//            Garment garmentShorts3 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts3, TargetConsumer.F,
//                    "DS004");
//            garmentDao.save(garmentShorts3);
//
//            Garment garmentShorts4 = new Garment("Τζιν σόρτς", "Τζιν σόρτς με σκισίματα", 34.99F, denim, garmentDetailsShorts4, TargetConsumer.F,
//                    "DS004");
//            garmentDao.save(garmentShorts4);
//
//            saveGarments(garmentShorts, 50);
//            saveGarments(garmentShorts1, 40);
//            saveGarments(garmentShorts2, 80);
//            saveGarments(garmentShorts3, 20);
//            saveGarments(garmentShorts4, 100);
//
//            materialDao.save(new Material("cotton", 100, garmentShorts.getCode()));
//
//            //005
//            File image4 = new File(System.getProperty("user.dir") + "/images/005-white-button-shirt.jpg");
//            byte[] bytes4 = Files.readAllBytes(image4.toPath());
//
//            GarmentDetails garmentDetailsShirt = new GarmentDetails(bytes4, SizeEnum.XXS, blue, false, 0.0F);
//            GarmentDetails garmentDetailsShirt1 = new GarmentDetails(bytes4, SizeEnum.XS, blue, false, 0.0F);
//            GarmentDetails garmentDetailsShirt2 = new GarmentDetails(bytes4, SizeEnum.S, blue, false, 0.0F);
//            GarmentDetails garmentDetailsShirt3 = new GarmentDetails(bytes4, SizeEnum.M, blue, false, 0.0F);
//            GarmentDetails garmentDetailsShirt4 = new GarmentDetails(bytes4, SizeEnum.L, blue, false, 0.0F);
//            garmentDetailsDao.save(garmentDetailsShirt);
//            garmentDetailsDao.save(garmentDetailsShirt1);
//            garmentDetailsDao.save(garmentDetailsShirt2);
//            garmentDetailsDao.save(garmentDetailsShirt3);
//            garmentDetailsDao.save(garmentDetailsShirt4);
//
//            Garment garmentShirt = new Garment("Λευκή μπλούζα με κουμπιά", "Λευκή μπλούζα με καφέ κουμπιά", 25.99F, shirtsAndBlouses,
//                    garmentDetailsShirt, TargetConsumer.F, "WS065");
//            garmentDao.save(garmentShirt);
//
//            Garment garmentShirt1 = new Garment("Λευκή μπλούζα με κουμπιά", "Λευκή μπλούζα με καφέ κουμπιά", 25.99F, shirtsAndBlouses,
//                    garmentDetailsShirt1, TargetConsumer.F, "WS065");
//            garmentDao.save(garmentShirt1);
//
//            Garment garmentShirt2 = new Garment("Λευκή μπλούζα με κουμπιά", "Λευκή μπλούζα με καφέ κουμπιά", 25.99F, shirtsAndBlouses,
//                    garmentDetailsShirt2, TargetConsumer.F, "WS065");
//            garmentDao.save(garmentShirt2);
//
//            Garment garmentShirt3 = new Garment("Λευκή μπλούζα με κουμπιά", "Λευκή μπλούζα με καφέ κουμπιά", 25.99F, shirtsAndBlouses,
//                    garmentDetailsShirt3, TargetConsumer.F, "WS065");
//            garmentDao.save(garmentShirt3);
//
//            Garment garmentShirt4 = new Garment("Λευκή μπλούζα με κουμπιά", "Λευκή μπλούζα με καφέ κουμπιά", 25.99F, shirtsAndBlouses,
//                    garmentDetailsShirt4, TargetConsumer.F, "WS065");
//            garmentDao.save(garmentShirt4);
//
//            saveGarments(garmentShirt, 50);
//            saveGarments(garmentShirt1, 40);
//            saveGarments(garmentShirt2, 80);
//            saveGarments(garmentShirt3, 20);
//            saveGarments(garmentShirt4, 100);
//
//            materialDao.save(new Material("cotton", 20, garmentShirt.getCode()));
//            materialDao.save(new Material("viscose", 80, garmentShirt.getCode()));
//
//            //004-poncho
//            File image5 = new File(System.getProperty("user.dir") + "/images/004-poncho.jpg");
//            byte[] bytes5 = Files.readAllBytes(image5.toPath());
//
//            GarmentDetails garmentDetailsPoncho = new GarmentDetails(bytes5, SizeEnum.OS, white, false, 0);
//            garmentDetailsDao.save(garmentDetailsPoncho);
//
//            Garment garmentPoncho = new Garment("Πλεκτό Πόντσο", "Χειροποίητο πλεκτό πόντσο", 59.99F, crochet, garmentDetailsPoncho,
//                    TargetConsumer.F, "CP324");
//
//            garmentDao.save(garmentPoncho);
//
//            saveGarments(garmentPoncho, 70);
//            materialDao.save(new Material("cotton", 80, garment.getCode()));
//            materialDao.save(new Material("acrylic", 20, garment.getCode()));
//
//            recommendationDao.save(new Recommendation("1", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("4", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("5", garment.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garmentPoncho.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garmentPoncho.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", garmentPoncho.getSkuNumber()));
//            recommendationDao.save(new Recommendation("4", garmentPoncho.getSkuNumber()));
//            recommendationDao.save(new Recommendation("5", garmentPoncho.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garmentShorts.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garmentShorts1.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", garmentShorts2.getSkuNumber()));
//            recommendationDao.save(new Recommendation("4", garmentShorts3.getSkuNumber()));
//            recommendationDao.save(new Recommendation("5", garmentShorts4.getSkuNumber()));
//            recommendationDao.save(new Recommendation("1", garmentShirt.getSkuNumber()));
//            recommendationDao.save(new Recommendation("2", garmentShirt1.getSkuNumber()));
//            recommendationDao.save(new Recommendation("3", garmentShirt2.getSkuNumber()));
//            recommendationDao.save(new Recommendation("4", garmentShirt3.getSkuNumber()));
//            recommendationDao.save(new Recommendation("5", garmentShirt4.getSkuNumber()));
//
//            //multi-colour-t-shirt
//            File image6 = new File(System.getProperty("user.dir") + "/images/multi-colour-t-shirt.jpg");
//            byte[] bytes6 = Files.readAllBytes(image6.toPath());
//
//            //red
//            GarmentDetails garmentDetailsRedShirtXS = new GarmentDetails(bytes6, SizeEnum.XS, red, false, 0);
//            GarmentDetails garmentDetailsRedShirtS = new GarmentDetails(bytes6, SizeEnum.S, red, false, 0);
//            GarmentDetails garmentDetailsRedShirtM = new GarmentDetails(bytes6, SizeEnum.M, red, false, 0);
//            GarmentDetails garmentDetailsRedShirtL = new GarmentDetails(bytes6, SizeEnum.L, red, false, 0);
//            garmentDetailsDao.save(garmentDetailsRedShirtXS);
//            garmentDetailsDao.save(garmentDetailsRedShirtS);
//            garmentDetailsDao.save(garmentDetailsRedShirtM);
//            garmentDetailsDao.save(garmentDetailsRedShirtL);
//
//            Garment garmentRedShirtXS = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsRedShirtXS, TargetConsumer.M, "TS008");
//            Garment garmentRedShirtS = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F, shirtsAndBlouses
//                    , garmentDetailsRedShirtS, TargetConsumer.M, "TS008");
//            Garment garmentRedShirtM = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F, shirtsAndBlouses
//                    , garmentDetailsRedShirtM, TargetConsumer.M, "TS008");
//            Garment garmentRedShirtL = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F, shirtsAndBlouses
//                    , garmentDetailsRedShirtL, TargetConsumer.M, "TS008");
//
//            garmentDao.save(garmentRedShirtXS);
//            garmentDao.save(garmentRedShirtS);
//            garmentDao.save(garmentRedShirtM);
//            garmentDao.save(garmentRedShirtL);
//
//            saveGarments(garmentRedShirtXS, 30);
//            saveGarments(garmentRedShirtS, 20);
//            saveGarments(garmentRedShirtM, 20);
//            saveGarments(garmentRedShirtL, 40);
//
//            //blue
//            GarmentDetails garmentDetailsBlueShirtXS = new GarmentDetails(bytes6, SizeEnum.XS, blue, false, 0);
//            GarmentDetails garmentDetailsBlueShirtS = new GarmentDetails(bytes6, SizeEnum.S, blue, false, 0);
//            GarmentDetails garmentDetailsBlueShirtM = new GarmentDetails(bytes6, SizeEnum.M, blue, false, 0);
//            GarmentDetails garmentDetailsBlueShirtL = new GarmentDetails(bytes6, SizeEnum.L, blue, false, 0);
//            garmentDetailsDao.save(garmentDetailsBlueShirtXS);
//            garmentDetailsDao.save(garmentDetailsBlueShirtS);
//            garmentDetailsDao.save(garmentDetailsBlueShirtM);
//            garmentDetailsDao.save(garmentDetailsBlueShirtL);
//
//            Garment garmentBlueShirtXS = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsBlueShirtXS, TargetConsumer.M, "TS008");
//            Garment garmentBlueShirtS = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsBlueShirtS, TargetConsumer.M, "TS008");
//            Garment garmentBlueShirtM = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsBlueShirtM, TargetConsumer.M, "TS008");
//            Garment garmentBlueShirtL = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsBlueShirtL, TargetConsumer.M, "TS008");
//
//            garmentDao.save(garmentBlueShirtXS);
//            garmentDao.save(garmentBlueShirtS);
//            garmentDao.save(garmentBlueShirtM);
//            garmentDao.save(garmentBlueShirtL);
//
//            saveGarments(garmentBlueShirtXS, 30);
//            saveGarments(garmentBlueShirtS, 20);
//            saveGarments(garmentBlueShirtM, 20);
//            saveGarments(garmentBlueShirtL, 40);
//
//            //white
//            GarmentDetails garmentDetailsWhiteShirtXS = new GarmentDetails(bytes6, SizeEnum.XS, white, false, 0);
//            GarmentDetails garmentDetailsWhiteShirtS = new GarmentDetails(bytes6, SizeEnum.S, white, false, 0);
//            GarmentDetails garmentDetailsWhiteShirtM = new GarmentDetails(bytes6, SizeEnum.M, white, false, 0);
//            GarmentDetails garmentDetailsWhiteShirtL = new GarmentDetails(bytes6, SizeEnum.L, white, false, 0);
//            garmentDetailsDao.save(garmentDetailsWhiteShirtXS);
//            garmentDetailsDao.save(garmentDetailsWhiteShirtS);
//            garmentDetailsDao.save(garmentDetailsWhiteShirtM);
//            garmentDetailsDao.save(garmentDetailsWhiteShirtL);
//
//            Garment garmentWhiteShirtXS = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsWhiteShirtXS, TargetConsumer.M, "TS008");
//            Garment garmentWhiteShirtS = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsWhiteShirtS, TargetConsumer.M, "TS008");
//            Garment garmentWhiteShirtM = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsWhiteShirtM, TargetConsumer.M, "TS008");
//            Garment garmentWhiteShirtL = new Garment("Κοντομάνικο Μπλουζάκι", "Κοντομάνικο Μπλουζάκι με στρογγυλη λαιμόκοψη", 9.99F,
//                    shirtsAndBlouses, garmentDetailsWhiteShirtL, TargetConsumer.M, "TS008");
//
//            garmentDao.save(garmentWhiteShirtXS);
//            garmentDao.save(garmentWhiteShirtS);
//            garmentDao.save(garmentWhiteShirtM);
//            garmentDao.save(garmentWhiteShirtL);
//
//            saveGarments(garmentWhiteShirtXS, 30);
//            saveGarments(garmentWhiteShirtS, 20);
//            saveGarments(garmentWhiteShirtM, 20);
//            saveGarments(garmentWhiteShirtL, 40);
//
//            materialDao.save(new Material("cotton", 100, garmentRedShirtS.getCode()));
//
//            //kaparntina
//
//            //009-black-jeans
//            File image7 = new File(System.getProperty("user.dir") + "/images/009-black-jeans.jpg");
//            byte[] bytes7 = Files.readAllBytes(image7.toPath());
//
//            //red
//            GarmentDetails garmentDetailsJeansXS = new GarmentDetails(bytes7, SizeEnum.XS, black, false, 0);
//            GarmentDetails garmentDetailsJeansS = new GarmentDetails(bytes7, SizeEnum.S, black, false, 0);
//            GarmentDetails garmentDetailsJeansM = new GarmentDetails(bytes7, SizeEnum.M, black, false, 0);
//            GarmentDetails garmentDetailsJeansL = new GarmentDetails(bytes7, SizeEnum.L, black, false, 0);
//            garmentDetailsDao.save(garmentDetailsJeansXS);
//            garmentDetailsDao.save(garmentDetailsJeansS);
//            garmentDetailsDao.save(garmentDetailsJeansM);
//            garmentDetailsDao.save(garmentDetailsJeansL);
//
//            Garment garmentJeansXS = new Garment("Μαύρο Τζιν Παντελόνι", "Παντελόνι τζιν σε στενή γραμμή", 49.99F, denim, garmentDetailsJeansXS,
//                    TargetConsumer.M, "DJ011");
//            Garment garmentJeansS = new Garment("Μαύρο Τζιν Παντελόνι", "Παντελόνι τζιν σε στενή γραμμή", 49.99F, denim, garmentDetailsJeansS,
//                    TargetConsumer.M, "DJ011");
//            Garment garmentJeansM = new Garment("Μαύρο Τζιν Παντελόνι", "Παντελόνι τζιν σε στενή γραμμή", 49.99F, denim, garmentDetailsJeansM,
//                    TargetConsumer.M, "DJ011");
//            Garment garmentJeansL = new Garment("Μαύρο Τζιν Παντελόνι", "Παντελόνι τζιν σε στενή γραμμή", 49.99F, denim, garmentDetailsJeansL,
//                    TargetConsumer.M, "DJ011");
//
//            garmentDao.save(garmentJeansXS);
//            garmentDao.save(garmentJeansS);
//            garmentDao.save(garmentJeansM);
//            garmentDao.save(garmentJeansL);
//
//            saveGarments(garmentJeansXS, 30);
//            saveGarments(garmentJeansS, 50);
//            saveGarments(garmentJeansM, 30);
//            saveGarments(garmentJeansL, 40);
//
//            recommendationDao.save(new Recommendation("6", garmentJeansXS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("6", garmentRedShirtXS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("6", garmentBlueShirtXS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("6", garmentWhiteShirtXS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("6", garmentHat.getSkuNumber()));
//
//            recommendationDao.save(new Recommendation("7", garmentJeansS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("7", garmentRedShirtS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("7", garmentBlueShirtS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("7", garmentWhiteShirtS.getSkuNumber()));
//            recommendationDao.save(new Recommendation("7", garmentHat.getSkuNumber()));
//
//            recommendationDao.save(new Recommendation("8", garmentJeansM.getSkuNumber()));
//            recommendationDao.save(new Recommendation("8", garmentRedShirtM.getSkuNumber()));
//            recommendationDao.save(new Recommendation("8", garmentBlueShirtM.getSkuNumber()));
//            recommendationDao.save(new Recommendation("8", garmentWhiteShirtM.getSkuNumber()));
//            recommendationDao.save(new Recommendation("8", garmentHat.getSkuNumber()));
//
//            recommendationDao.save(new Recommendation("9", garmentJeansL.getSkuNumber()));
//            recommendationDao.save(new Recommendation("9", garmentRedShirtL.getSkuNumber()));
//            recommendationDao.save(new Recommendation("9", garmentBlueShirtL.getSkuNumber()));
//            recommendationDao.save(new Recommendation("9", garmentWhiteShirtL.getSkuNumber()));
//            recommendationDao.save(new Recommendation("9", garmentHat.getSkuNumber()));
//
//            materialDao.save(new Material("cotton", 70, garmentJeansS.getCode()));
//            materialDao.save(new Material("viscose", 25, garmentJeansS.getCode()));
//            materialDao.save(new Material("elastane", 5, garmentJeansS.getCode()));
//
//            saveTryOns(100);
//
//
//            List<PurchaseDetails> details = new ArrayList<>();
//            PurchaseDetails purchaseDetails = new PurchaseDetails();
//            purchaseDetails.setProduct(garment);
//            purchaseDetails.setPrice();
//
//            PurchaseDetails purchaseDetails1 = new PurchaseDetails();
//            purchaseDetails1.setProduct(garmentBlueShirtM);
//            purchaseDetails1.setPrice();
//
//            PurchaseDetails purchaseDetails2 = new PurchaseDetails();
//            purchaseDetails2.setProduct(garmentJeansL);
//            purchaseDetails2.setPrice();
//
//            PurchaseDetails purchaseDetails3 = new PurchaseDetails();
//            purchaseDetails3.setProduct(garmentWhiteShirtM);
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
//            pos.setStore(store);
//            pos.setDate(LocalDateTime.now());
//            pos.setCustomer(customer);
//            pos.setStatus(StatusEnum.COMPLETED);
//            pos.setDetails(details);
//
//            pointOfSaleDao.save(pos);
//
//
//            List<PurchaseDetails> details1 = new ArrayList<>();
//            PurchaseDetails purchaseDetails_1 = new PurchaseDetails();
//            purchaseDetails_1.setProduct(garmentHat);
//            purchaseDetails_1.setPrice();
//
//            PurchaseDetails purchaseDetails_2 = new PurchaseDetails();
//            purchaseDetails_2.setProduct(garmentHat);
//            purchaseDetails_2.setPrice();
//
//            PurchaseDetails purchaseDetails_3 = new PurchaseDetails();
//            purchaseDetails_3.setProduct(garmentPoncho);
//            purchaseDetails_3.setPrice();
//
//            PurchaseDetails purchaseDetails_4 = new PurchaseDetails();
//            purchaseDetails_4.setProduct(garmentRedShirtL);
//            purchaseDetails_4.setPrice();
//
//            details1.add(purchaseDetails_2);
//            details1.add(purchaseDetails_2);
//            details1.add(purchaseDetails_3);
//            details1.add(purchaseDetails_4);
//            purchaseDetailsDao.save(purchaseDetails_2);
//            purchaseDetailsDao.save(purchaseDetails_2);
//            purchaseDetailsDao.save(purchaseDetails_3);
//            purchaseDetailsDao.save(purchaseDetails_4);
//
//
//            PointOfSale pos1 = new PointOfSale();
//            pos1.setStore(store2);
//            pos1.setDate(LocalDateTime.now());
//            pos1.setCustomer(customer5);
//            pos1.setStatus(StatusEnum.COMPLETED);
//            pos1.setDetails(details1);
//
//            pointOfSaleDao.save(pos1);
//        };
//    }
//
//    private static String randomPhoneNumberGen() {
//        Random random = new Random();
//        char[] digits = new char[10];
//        digits[0] = '6';
//        digits[1] = '9';
//        digits[2] = (char) (random.nextInt(9) + '1');
//        for (int i = 3; i < 10; i++) {
//            digits[i] = (char) (random.nextInt(10) + '0');
//        }
//        return String.copyValueOf(digits);
//    }
//
//    private void saveGarments(Garment garment, int times) {
//
//        List<Store> all = storeDao.findAll();
//        StoreInventory storeInventory1 = new StoreInventory(all.get(0), garment);
//        storeInventoryDao.save(storeInventory1);
//
//        int c = 0;
//
//        for (int i = 0; i < times; i++) {
//            if (c > 3) {
//                c = 0;
//            }
//            Garment g1 = new Garment(garment);
//            g1.setGarmentDetails(garment.getGarmentDetails());
//            garmentDao.save(g1);
//
////            TryOn tryon = new TryOn(LocalDateTime.now(), all.get(c), null, Collections.singletonList(g1));
////            tryOnDao.save(tryon);
//
//            StoreInventory storeInventory = new StoreInventory(all.get(c++), g1);
//            storeInventoryDao.save(storeInventory);
//
//
//        }
//
//    }
//
//    private void saveTryOns(int times) {
//
//        List<Store> all = storeDao.findAll();
//
//        List<Garment> allGarments = garmentDao.findAll();
//        List<Customer> allCustomers = customerDao.findAll();
//        Random random = new Random();
//        int i1 = 0;
//
//        int c = 0;
//
//        for (int i = 0; i < times; i++) {
//            if (c > 3) {
//                c = 0;
//            }
//            i1 = random.nextInt(allGarments.size()-1);
//
//            Customer customer = null;
//            if(i1 % 2 == 0) {
//                customer = allCustomers.get(random.nextInt(allCustomers.size()-1));
//            }
//
//            TryOn tryon = new TryOn(LocalDateTime.now(), all.get(c++), customer, Collections.singletonList(allGarments.get(i1)));
//            tryOnDao.save(tryon);
//
//
//        }
//
//    }
//
//}
