package Utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {
    private static final Logger logger = LogManager.getLogger(ExcelUtil.class);
    private Sheet sheet;

    public ExcelUtil(String sheetName) {
        try {
            String path = ConfigurationReader.get("excel.path");
            FileInputStream fileInputStream = new FileInputStream(path);
            Workbook workbook = new XSSFWorkbook(fileInputStream);
            this.sheet = workbook.getSheet(sheetName);
            logger.info("Excel dosyası yüklendi: {} Sayfa: {}", path, sheetName);
        } catch (IOException e) {
            logger.error("Excel dosyası yüklenemedi.", e);
        }
    }

    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(colIndex);
        return cell.toString();
    }
}
