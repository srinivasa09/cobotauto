/********************************************************************************************************************************
 * Change History:
 * 
 * Revision     Date            Updated by                  Comments
 * ---------------------------------------------------------------------------------------------------------------------------
 * 1.0          10-Mar-2018     Punnam Santosh Kumar        Creating the ExcelReader Class to read all the TestData
 * 
 * 
 * Copyright (c) Rock Interview. All Rights Reserved.
 ********************************************************************************************************************************/

package com.cobot.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.cobot.reports.Reports;

/**
 * The Class ExcelReader.
 */
public class ExcelReader {

    /** The Excel Work book. */
    public static XSSFWorkbook ExcelWBook;

    /** The Excel Work sheet. */
    public static XSSFSheet ExcelWSheet;

    /** The Cell. */
    public static XSSFCell Cell;

    /** The output file name. */
    public static String outputFileName;

    /** The last row num. */
    public static int lastRowNum = 0;

    /**
     * Gets the input data as an array.
     * @param fileName is the testdata excel file name
     * @param sheetName is the excel file sheet name
     * @param TcName is the testcase name
     * @return the input data as an array
     * @throws Exception to the calling place
     */
    public static Object[][] getInputArray(String fileName, String sheetName, String TcName) throws Exception {
        Reports.info("Running... getInputArray(String fileName, String sheetName, String TcName)");
        String[][] inputArray = null;
        try {
            outputFileName = "../Data/" + fileName;
            FileInputStream ExcelFile = new FileInputStream("../Data/" + fileName);

            ExcelWBook = new XSSFWorkbook(ExcelFile);
            ExcelWSheet = ExcelWBook.getSheet(sheetName);

            int startCol = 2;

            int rowIndexStart = -1;
            int rowIndexEnd = -1;

            int totalRows = ExcelWSheet.getLastRowNum() + 1;

            for (int i = 1; i <= totalRows; i++) {
                if (getCellData(i, 1).equals(TcName)) {
                    rowIndexStart = i + 1;
                    break;
                }
            }

            for (int j = rowIndexStart; j < totalRows; j++) {
                if (getCellData(j, 1) == null || getCellData(j, 1).trim().equals("")) {
                    rowIndexEnd = j + 1;
                } else {
                    break;
                }
            }

            int totalCols = ExcelWSheet.getRow(rowIndexStart - 1).getLastCellNum();

            int nonEmptyCols = 0;

            for (int i = 2; i < totalCols; i++) {
                if (getCellData(rowIndexStart, i) != null && !getCellData(rowIndexStart, i).equals("")) {
                    nonEmptyCols = nonEmptyCols + 1;
                }
            }

            inputArray = new String[rowIndexEnd - rowIndexStart][nonEmptyCols];

            int ci = 0, cj = 0;

            for (int i = rowIndexStart; i < rowIndexEnd; i++) {
                for (int j = startCol; j < nonEmptyCols + 2; j++) {
                    inputArray[ci][cj] = getCellData(i, j);
                    cj++;
                }
                cj = 0;
                ci++;
            }

        } catch (FileNotFoundException e) {
            Reports.fail("Could not read the Excel file: " + fileName + "(" + e.getMessage() + ")");
            throw e;
        } catch (IOException e) {
            Reports.fail("IOException in reading the Excel file: " + fileName + "(" + e.getMessage() + ")");
            throw e;
        }
        return inputArray;
    }

    /**
     * Gets the cell data using row and column numbers.
     * @param rowNum is the row number
     * @param colNum is the column number
     * @return the cell data
     * @throws Exception to the calling place
     */
    public static String getCellData(int rowNum, int colNum) throws Exception {
        try {
            Cell = ExcelWSheet.getRow(rowNum).getCell(colNum);

            if (Cell == null) {
                return "";
            }

            CellType dataType = Cell.getCellTypeEnum();

            if (dataType == CellType._NONE) {
                return "";
            } else if (dataType == CellType.NUMERIC) {
                Cell.setCellType(CellType.STRING);
            }

            return Cell.getStringCellValue();

        } catch (Exception e) {
            Reports.fail("Exception in getCellData(int RowNum, int ColNum) method: " + e.getMessage());
            return null;
        }
    }

    /**
     * Write cell data.
     * @param testMap the test map
     * @throws Exception the exception
     */
    public static void writeCellData(Map<String, String> testMap) throws Exception {
        try {
            Runtime.getRuntime().exec("cmd /c taskkill /f /im excel.exe");
            Thread.sleep(5000);
            Reports.info("Running... writeCellData(String tcName, String status)");
            FileInputStream inputStream = new FileInputStream(new File(outputFileName));
            Workbook workbook = WorkbookFactory.create(inputStream);

            XSSFCellStyle headerStyle = (XSSFCellStyle) workbook.createCellStyle();
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());

            XSSFCellStyle passStyle = (XSSFCellStyle) workbook.createCellStyle();
            passStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            passStyle.setFillForegroundColor(IndexedColors.GREEN.getIndex());

            XSSFCellStyle failStyle = (XSSFCellStyle) workbook.createCellStyle();
            failStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            failStyle.setFillForegroundColor(IndexedColors.RED.getIndex());

            XSSFCellStyle skipStyle = (XSSFCellStyle) workbook.createCellStyle();
            skipStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            skipStyle.setFillForegroundColor(IndexedColors.SKY_BLUE.getIndex());

            Sheet sheet = null;
            Row row = null;

            int sheetIndex = workbook.getSheetIndex("Output");
            if (sheetIndex != -1) {
                workbook.removeSheetAt(sheetIndex);
                lastRowNum = 0;
            }
            sheet = workbook.createSheet("Output");

            row = sheet.createRow(lastRowNum++);
            Cell siNum = row.createCell(0);
            siNum.setCellValue("SI.NO");
            siNum.setCellStyle(headerStyle);

            Cell testHeader = row.createCell(1);
            testHeader.setCellValue("TC_NAME");
            testHeader.setCellStyle(headerStyle);

            Cell testStatus = row.createCell(2);
            testStatus.setCellValue("STATUS");
            testStatus.setCellStyle(headerStyle);

            for (String key : testMap.keySet()) {
                row = sheet.createRow(lastRowNum);

                Cell siNo = row.createCell(0);
                siNo.setCellValue(lastRowNum++);

                Cell testName = row.createCell(1);
                testName.setCellValue(key);

                Cell tcStatus = row.createCell(2);
                tcStatus.setCellValue(testMap.get(key));
                if (testMap.get(key).equals("PASS")) {
                    tcStatus.setCellStyle(passStyle);
                } else if (testMap.get(key).equals("FAIL")) {
                    tcStatus.setCellStyle(failStyle);
                } else if (testMap.get(key).equals("SKIP")) {
                    tcStatus.setCellStyle(skipStyle);
                }
            }

            inputStream.close();

            FileOutputStream outputStream = new FileOutputStream(outputFileName);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        } catch (

        Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
