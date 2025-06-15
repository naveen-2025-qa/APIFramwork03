package api.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static void readExcel(String path) throws FileNotFoundException {
		FileInputStream fis = new FileInputStream(path);
		XSSFWorkbook workbook = new XSSFWorkbook();
		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equals("Sheet1")) {
				XSSFSheet sheet = workbook.getSheetAt(i);

				// iterate over all the rows
				Iterator<Row> rows = sheet.iterator();
				// get firstRow
				Row firstrow = rows.next();

				// iterator over each cells in the firstrow
				Iterator<Cell> cells = firstrow.cellIterator();

				int k = 0;
				int column = 0;
				while (cells.hasNext()) {
					String value = cells.next().getStringCellValue();
					if (value.equals("password")) {
						// colum index for password
						column = k;
					}

					k++;
				}
				// iterating over each row
				while (rows.hasNext()) {
					Row row1 = rows.next();
					String value1 = row1.getCell(column).getStringCellValue();
					if (value1.equals("naveen123")) {
						Iterator<Cell> cells1 = row1.cellIterator();
						while (cells1.hasNext())

						{
							Cell cell1 = cells1.next();
							if (cell1.getCellType() == CellType.STRING) {
								System.out.println(cell1.getStringCellValue());
							} else {
								System.out.println(cell1.getNumericCellValue());
							}
						}
					}
				}

			}

		}
	}
	
	public static Object[][] getAllRows(String path) throws IOException {
	    FileInputStream fis = new FileInputStream(path);
	    XSSFWorkbook workbook = new XSSFWorkbook(fis);
	    int sheets = workbook.getNumberOfSheets();
	    for (int i = 0; i < sheets; i++) {
	        if (workbook.getSheetName(i).equals("Sheet1")) {
	            XSSFSheet sheet = workbook.getSheetAt(i);
	            int rowCount = sheet.getPhysicalNumberOfRows();

	            if (rowCount > 0) {
	                Row firstRow = sheet.getRow(0);
	                int columnCount = firstRow.getPhysicalNumberOfCells();

	                Object[][] data = new Object[rowCount - 1][columnCount];

	                for (int j = 1; j < rowCount; j++) {
	                    XSSFRow row = sheet.getRow(j);
	                    for (int k = 0; k < columnCount; k++) {
	                        Cell cell = row.getCell(k);
	                        if (cell != null) {
	                            if (cell.getCellType() == CellType.STRING) {
	                                data[j - 1][k] = cell.getStringCellValue();
	                            } else {
	                            	int value =Integer.parseInt(cell.toString());
	                                data[j - 1][k] = value;
	                            }
	                        }
	                    }
	                }
	                workbook.close();
	                fis.close();
	                return data;
	            }
	        }
	    }
	    workbook.close();
	    fis.close();
	    return null;
	}

}
