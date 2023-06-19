package tech.ibrave.metabucket.shared.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    // todo update missing if-else case
    public static void createCell(XSSFSheet sheet,
                                  Row row,
                                  int columnCount,
                                  Object valueOfCell,
                                  CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (valueOfCell instanceof Integer value) {
            cell.setCellValue(value);
        } else if (valueOfCell instanceof Long value) {
            cell.setCellValue(value);
        } else if (valueOfCell instanceof Boolean value) {
            cell.setCellValue(value);
        } else {
            cell.setCellValue(String.valueOf(valueOfCell));
        }
        cell.setCellStyle(style);
    }

    public static String getStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        }
        return "";
    }
}
