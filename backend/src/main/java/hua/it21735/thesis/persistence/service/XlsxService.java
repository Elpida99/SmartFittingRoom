package hua.it21735.thesis.persistence.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Service
public class XlsxService {

    @Autowired
    StatisticsService service;
    @Autowired
    PurchaseService purchaseService;
    @Autowired
    GarmentService garmentService;


    public void mainMethod() {
        try (Workbook workbook = new XSSFWorkbook()) {
            savePopularGarments(workbook);
            savePopularGarmentsSku(workbook);
            saveSalesStatistics(workbook);
            saveCustomerSales(workbook);
            saveInventoryStatistics(workbook);
            try (FileOutputStream fileOut = new FileOutputStream("garmentStatistics.xlsx")) {
                workbook.write(fileOut);
                System.out.println("Excel file created successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void savePopularGarmentsSku(Workbook workbook) {
        Map<String, Long> popularGarments = service.getPopularGarmentsSKU();
        Sheet sheet = workbook.createSheet("Δημοφιλή SKU");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell h1 = headerRow.createCell(0);
        h1.setCellValue("Garment SKU");
        Cell h2 = headerRow.createCell(1);
        h2.setCellValue("Garment Name");
        Cell h3 = headerRow.createCell(2);
        h3.setCellValue("Try-on Count");

        for (Map.Entry<String, Long> entry : popularGarments.entrySet()) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);


            String garmentCode = entry.getKey().split("_NAME_")[0];
            String garmentName = entry.getKey().split("_NAME_").length > 1 ? entry.getKey().split("_NAME_")[1] : "";
            row.createCell(0).setCellValue(garmentCode);
            row.createCell(1).setCellValue(garmentName);
            row.createCell(2).setCellValue(entry.getValue());

        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);

    }


    public void savePopularGarments(Workbook workbook) {

        Map<String, Long> popularGarments = service.getPopularGarments();
        Sheet sheet = workbook.createSheet("Δημοφιλή ενδύματα");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell h1 = headerRow.createCell(0);
        h1.setCellValue("Garment Code");
        Cell h2 = headerRow.createCell(1);
        h2.setCellValue("Garment Name");
        Cell h3 = headerRow.createCell(2);
        h3.setCellValue("Try-on Count");

        for (Map.Entry<String, Long> entry : popularGarments.entrySet()) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);


            String garmentCode = entry.getKey().split("_NAME_")[0];
            String garmentName = entry.getKey().split("_NAME_").length > 1 ? entry.getKey().split("_NAME_")[1] : "";
            row.createCell(0).setCellValue(garmentCode);
            row.createCell(1).setCellValue(garmentName);
            row.createCell(2).setCellValue(entry.getValue());

        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);


    }


    public void saveSalesStatistics(Workbook workbook) {

        Map<String, Integer> garmentSales = service.getGarmentSales();
        Sheet sheet = workbook.createSheet("Επίδοση πωλήσεων");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell h1 = headerRow.createCell(0);
        h1.setCellValue("Garment Code");
        Cell h2 = headerRow.createCell(1);
        h2.setCellValue("Garment Name");
        Cell h3 = headerRow.createCell(2);
        h3.setCellValue("Overall Sales");
        Cell h4 = headerRow.createCell(3);
        h4.setCellValue("Average Price");

        for (Map.Entry<String, Integer> entry : garmentSales.entrySet()) {
//        for (String g : garmentSales) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);


            String garmentCode = entry.getKey().split("_NAME_")[0];
            String garmentName = entry.getKey().split("_NAME_").length > 1 ? entry.getKey().split("_NAME_")[1] : "";
            row.createCell(0).setCellValue(garmentCode);
            row.createCell(1).setCellValue(garmentName);
            row.createCell(2).setCellValue(entry.getValue());
            row.createCell(3).setCellValue(Math.ceil(purchaseService.getAveragePrice(garmentCode)));

        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);


    }

    public void saveCustomerSales(Workbook workbook) {

        Map<String, Integer[]> customerSales = service.getCustomerSales();

        Sheet sheet = workbook.createSheet("Δοκιμές και Πωλήσεις");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell h1 = headerRow.createCell(0);
        h1.setCellValue("Customer Code");
        Cell h2 = headerRow.createCell(1);
        h2.setCellValue("Customer Name");
        Cell h3 = headerRow.createCell(2);
        h3.setCellValue("Customer PhoneNumber");
        Cell h4 = headerRow.createCell(3);
        h4.setCellValue("TryOns");
        Cell h5 = headerRow.createCell(4);
        h5.setCellValue("Purchases");


        for (Map.Entry<String, Integer[]> entry : customerSales.entrySet()) {
//        for (String g : garmentSales) {
            Row row = sheet.createRow(sheet.getLastRowNum() + 1);


            String customerCode = entry.getKey().split("__")[0];
            String customerName = entry.getKey().split("__")[1];
            String customerPhoneNumber = entry.getKey().split("__")[2];
            row.createCell(0).setCellValue(customerCode);
            row.createCell(1).setCellValue(customerName);
            row.createCell(2).setCellValue(customerPhoneNumber);
            row.createCell(3).setCellValue(entry.getValue()[0]);
            row.createCell(4).setCellValue(entry.getValue()[1]);

        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);
        sheet.autoSizeColumn(4);


    }

    public void saveInventoryStatistics(Workbook workbook) {

        System.out.println("XlsxService.saveInventoryStatistics");
        Map<String, Integer> invStatistics = service.getInventory();

        Sheet sheet = workbook.createSheet("Διαχείριση αποθέματος");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell h1 = headerRow.createCell(0);
        h1.setCellValue("Store code");
        Cell h2 = headerRow.createCell(1);
        h2.setCellValue("Garment SKU");
        Cell h3 = headerRow.createCell(2);
        h3.setCellValue("Garment name");
        Cell h4 = headerRow.createCell(3);
        h4.setCellValue("Inventory");


        for (Map.Entry<String, Integer> entry : invStatistics.entrySet()) {

            Row row = sheet.createRow(sheet.getLastRowNum() + 1);


            String storeCode = entry.getKey().split("__")[0];
            System.out.println("storeCode = " + storeCode);
            String garmentSKU = entry.getKey().split("__")[1];
            System.out.println("garmentSKU = " + garmentSKU);
            String garmentName = garmentService.findBySku(garmentSKU).get(0).getName();
            System.out.println("garmentName = " + garmentName);
            row.createCell(0).setCellValue(storeCode);
            row.createCell(1).setCellValue(garmentSKU);
            row.createCell(2).setCellValue(garmentName);
            row.createCell(3).setCellValue(entry.getValue());

        }

        sheet.autoSizeColumn(0);
        sheet.autoSizeColumn(1);
        sheet.autoSizeColumn(2);
        sheet.autoSizeColumn(3);


    }
}
