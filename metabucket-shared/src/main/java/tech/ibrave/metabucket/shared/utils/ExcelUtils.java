package tech.ibrave.metabucket.shared.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * Author: hungnm
 * Date: 05/06/2023
 */
public final class ExcelUtils {

    public static void createHeader(XSSFWorkbook workbook,
                                    XSSFSheet sheet,
                                    List<String> headerValues) {
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);
        for (int i = 0; i < headerValues.size(); i++) {
            var cell = row.createCell(i);
            // Header CellType must be CellType.STRING.
            cell.setCellValue(headerValues.get(i));
        }
    }

    public static void createCell(XSSFSheet sheet,
                                  Row row,
                                  int columnCount,
                                  Object valueOfCell,
                                  CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer) {
            cell.setCellValue((Integer) valueOfCell);
        } else if (valueOfCell instanceof Long) {
            cell.setCellValue((Long) valueOfCell);
        } else if (valueOfCell instanceof String) {
            cell.setCellValue((String) valueOfCell);
        } else {
            cell.setCellValue((Boolean) valueOfCell);
        }
        cell.setCellStyle(style);
    }
}
