package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil
{
	Workbook wb;

	public ExcelFileUtil() throws Throwable
	{
		FileInputStream fi = new FileInputStream("C://Users//ABC//Desktop//New folder//new login.xls");
		wb = WorkbookFactory.create(fi);
	}
	
	public int rowCount(String sheet)
	{
		Sheet s = wb.getSheet(sheet);
		return wb.getSheet(sheet).getLastRowNum();
	}
	
	public int colCount(String sheet, int rownum)
	{
		return wb.getSheet(sheet).getRow(rownum).getLastCellNum();
	}
	
	public String getData(String sheet, int rownum, int colnum)
	{
		String data;
		if (wb.getSheet(sheet).getRow(rownum).getCell(colnum).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int)wb.getSheet(sheet).getRow(rownum).getCell(colnum).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(sheet).getRow(rownum).getCell(colnum).getStringCellValue();
		}
		return data;
	}
	
	public void setData(String sheet, int rownum, int colnum, String status) throws Throwable
	{
		Sheet s = wb.getSheet(sheet);
		Row r = s.getRow(rownum);
		Cell c = r.createCell(colnum);
		c.setCellValue(status);

		if (status.equalsIgnoreCase("pass"))
		{
			CellStyle cs = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cs.setFont(font);
			r.getCell(colnum).setCellStyle(cs);
		}
		
		if (status.equalsIgnoreCase("fail"))
		{
			CellStyle cs = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cs.setFont(font);
			r.getCell(colnum).setCellStyle(cs);
		}
		
		if (status.equalsIgnoreCase("block"))
		{
			CellStyle cs = wb.createCellStyle();
			Font font = wb.createFont();
			font.setColor(IndexedColors.PINK.getIndex());
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			cs.setFont(font);
			r.getCell(colnum).setCellStyle(cs);
		}

		FileOutputStream fo = new FileOutputStream(new File("C://Users//ABC//Desktop//New folder//new loginoutput.xls"));
		wb.write(fo);
	}
}
