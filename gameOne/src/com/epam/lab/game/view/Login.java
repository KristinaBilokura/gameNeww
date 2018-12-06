package com.epam.lab.game.view;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.epam.lab.game.exselUtils.ExcelReader.SAMPLE_XLSX_FILE_PATH;


public class Login {
    public static boolean authenticate(String username, String password) {
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheetAt(0);
        DataFormatter dataFormatter = new DataFormatter();
        List<String> user = new ArrayList<>();

        // 3. Or you can use Java 8 forEach loop with lambda
        System.out.println("\n\nIterating over Rows and Columns using Java 8 forEach with lambda\n");
        sheet.forEach(row -> {
            row.forEach(cell -> {
                String cellValue = dataFormatter.formatCellValue(cell);
                user.add(cellValue);
            });
            System.out.println();
        });
        try {
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (username.equals(user.get(0)) && password.equals(user.get(1))) {
            return true;
        }
        return false;
    }
}
