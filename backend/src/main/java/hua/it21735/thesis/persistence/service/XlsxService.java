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


    public void mainMethod() {
        try (Workbook workbook = new XSSFWorkbook()) {
            savePopularGarments(workbook);
            savePopularGarmentsSku(workbook);
            saveSalesStatistics(workbook);
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
}
