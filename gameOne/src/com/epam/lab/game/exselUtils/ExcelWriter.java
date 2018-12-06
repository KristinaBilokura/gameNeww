package com.epam.lab.game.exselUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriter {

    private static String[] columns = {"Name", "Email", "Password"};

    public static void parse(User user) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("User");

        Font headerFont = workbook.createFont();
        headerFont.setBold(false);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.BLACK.getIndex());

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);



        int rowNum= 0;
        Row row = sheet.createRow(rowNum);

        row.createCell(0)
                .setCellValue(user.getName());

        row.createCell(1)
                .setCellValue(user.getPassword());

        row.createCell(2)
                .setCellValue(user.getEmail());


        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream("poi-generated-file.xlsx");
        workbook.write(fileOut);
        fileOut.close();

        workbook.close();
    }
}