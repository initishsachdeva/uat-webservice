package com.webservice.uatwebservice.utility;

import com.webservice.uatwebservice.entity.JiraIssues;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelUtility {
    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))//for xlsx format
            return true;
        return false;
    }

    //it converts excel to list of jira items
    public static List<JiraIssues> convertExcelToList(InputStream is) {
        List<JiraIssues> list = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                JiraIssues jiraItems = new JiraIssues();
                int cellId = 0;
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cellId) {
                        case 0:
                            jiraItems.setType(cell.getStringCellValue());
                            break;
                        case 1:
                            jiraItems.setKey(cell.getStringCellValue());
                            break;
                        case 2:
                            jiraItems.setSummary(cell.getStringCellValue());
                            break;
                        case 3:
                            jiraItems.setAssignee(cell.getStringCellValue());
                            break;
                        case 4:
                            jiraItems.setComponent(cell.getStringCellValue());
                            break;
                        case 5:
                            jiraItems.setDescription(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellId++;
                }
                list.add(jiraItems);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

