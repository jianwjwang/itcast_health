package com.itheima.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class POI {

    @Test
    public void test() throws IOException {
        //创建工作簿对象
        XSSFWorkbook xssf = new XSSFWorkbook(new FileInputStream("/Users/wangjian/Documents/code/test_project/itcast_health/health_parent/health_common/src/test/resources/workbook1.xlsx"));
        //读取第一个sheet页
        XSSFSheet sheet = xssf.getSheetAt(0);
        //遍历sheet页，获取每一行数据
        for (Row row : sheet) {
            //遍历每一个单元格
            System.out.print("---第 "+ row.getRowNum() +" 行:" );
            for (Cell cell : row) {
                String result = cell.getStringCellValue();
                System.out.print(result +" ");
            }
            System.out.println();
        }
        xssf.close();
    }

    @Test
    public void test1() throws IOException {
        //创建工作簿对象
        XSSFWorkbook xssf = new XSSFWorkbook();
        XSSFSheet shet = xssf.createSheet("shet");
        XSSFRow row0 = shet.createRow(0);
        row0.createCell(0).setCellValue("name");
        row0.createCell(1).setCellValue("age");
        XSSFRow row1 = shet.createRow(1);
        row1.createCell(0).setCellValue("zs");
        row1.createCell(1).setCellValue("23");
        FileOutputStream out = new FileOutputStream("/Users/wangjian/Documents/code/test_project/itcast_health/health_parent/health_common/src/test/resources/workbook2.xlsx");
        out.flush();
        xssf.write(out);
        xssf.close();
    }
}
