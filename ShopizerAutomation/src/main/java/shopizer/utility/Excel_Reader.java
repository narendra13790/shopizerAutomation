package shopizer.utility;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;

/**
 * Description of class: <br/>
 * it provide all the functionality to manipulate the excel sheet like adding the sheet ,creating the sheet , fetching
 * the data from sheet
 * 
 */
public class Excel_Reader
{
	private String sExcelFilePath;
	private FileInputStream fis = null;
	private XSSFWorkbook workbook = null;
	private XSSFSheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;
	
	private static Logger logger = Logger.getLogger(Excel_Reader.class);

	private Excel_Reader(String sExcelFilePath)
	{
		this.sExcelFilePath = sExcelFilePath;
		try
		{
			fis = new FileInputStream(sExcelFilePath);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * counts the no of the rows in a sheet
	 * 
	 * @param sSheetName
	 *          - name of excel sheet
	 * @return returns no of the rows.
	 */
	public int getRowCount(String sSheetName)
	{
		int index = workbook.getSheetIndex(sSheetName);
		if (index == -1)
			return 0;
		else
		{
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}
	}

	

	/**
	 * counts number of columns in a sheet
	 * 
	 * @param sheetName
	 *          - name of the sheet
	 * @return returns the no of the columns
	 */
	public int getColumnCount(String sheetName)
	{
		// check if sheet exists
		if (!isSheetExist(sheetName))
			return -1;
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		if (row == null)
			return -1;
		return row.getLastCellNum();
	}

	/**
	 * returns the data from a cell
	 * 
	 * @param sSheetName
	 *          -name of the excel sheet from where data has to be fetch.
	 * @param sColName
	 *          -name of the column
	 * @param iRowNum
	 *          - row number
	 * @return it returns the value of cell.
	 */
	public String getCellData(String sSheetName, String sColName, int iRowNum)
	{
		try
		{
			if (iRowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sSheetName);
			int col_Num = -1;
			if (index == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(0);
			
			for (int i = 0; i < row.getLastCellNum(); i++)
			{
				// System.out.println(row.getCell(i).getStringCellValue().trim());
				if (row.getCell(i).getStringCellValue().trim().equals(sColName.trim()))
					col_Num = i;
			}
			if (col_Num == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(iRowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(col_Num);
			if (cell == null)
				return "";
			// System.out.println(cell.getCellType());
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA)
			{
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.MONTH) + 1 + "/" + cellText;
					// System.out.println(cellText);
				}
				return cellText;
			}
			else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		}
		catch (Exception e)
		{
			logger.error("row " + iRowNum + " or column " + sColName + " does not exist in excecl file: " + sExcelFilePath, e);
			return "row " + iRowNum + " or column " + sColName + " does not exist in excel file: " + sExcelFilePath;
		}
	}

	/**
	 * get the value from cell.
	 * 
	 * @param sSheetName
	 *          - name of the excel sheet.
	 * @param iColNum
	 *          - nth number column.
	 * @param iRowNum
	 *          - nth number row
	 * @return returns the data from a cell
	 */
	public String getCellData(String sSheetName, int iColNum, int iRowNum)
	{
		try
		{
			if (iRowNum <= 0)
				return "";
			int index = workbook.getSheetIndex(sSheetName);
			if (index == -1)
				return "";
			sheet = workbook.getSheetAt(index);
			row = sheet.getRow(iRowNum - 1);
			if (row == null)
				return "";
			cell = row.getCell(iColNum);
			if (cell == null)
				return "";
			if (cell.getCellType() == Cell.CELL_TYPE_STRING)
				return cell.getStringCellValue();
			else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA)
			{
				String cellText = String.valueOf(cell.getNumericCellValue());
				if (HSSFDateUtil.isCellDateFormatted(cell))
				{
					// format in form of M/D/YY
					double d = cell.getNumericCellValue();
					Calendar cal = Calendar.getInstance();
					cal.setTime(HSSFDateUtil.getJavaDate(d));
					cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
					cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
					// System.out.println(cellText);
				}
				return cellText;
			}
			else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
				return "";
			else
				return String.valueOf(cell.getBooleanCellValue());
		}
		catch (Exception e)
		{
			logger.error("row " + iRowNum + " or column " + iColNum + " does not exist in excecl file: " + sExcelFilePath, e);
			return "row " + iRowNum + " or column " + iColNum + " does not exist in excel file: " + sExcelFilePath;
		}
	}

	
	
	/**
	 * check whether sheets exists.
	 * 
	 * @param sSheetName
	 *          - name of the sheet
	 * @return return true if sheet exist otherwise false
	 */
	public boolean isSheetExist(String sSheetName)
	{
		int index = workbook.getSheetIndex(sSheetName);
		
		if (index == -1)
		{
			index = workbook.getSheetIndex(sSheetName.toUpperCase());
			if (index == -1)
				return false;
			else
				return true;
		}
		else
			return true;
	}

	/**
	 * it returns the nth position of the row
	 * 
	 * @param sSheetName
	 *          name of the sheet.
	 * @param sColName
	 *          - name of the column. the row number will be find according this col name.
	 * @param sCellValue
	 *          - value of the cell . row no will be found corresponding this value.
	 * @return it returns the position means row number where above sCellValue find.
	 */
	public int getCellRowNum(String sSheetName, String sColName, String sCellValue)
	{
		for (int i = 2; i <= getRowCount(sSheetName); i++)
		{
			
			if (getCellData(sSheetName, sColName, i).equalsIgnoreCase(sCellValue))
			{
				return i;
			}
		}
		return -1;
	}

	// This method creates a new object of excel reader and opens the file passed
	// as parameter.
	public static String[][] getDataForTests(String sFileName,String sSheetName,
			String sTextToIdentifyStartRow)
	{
		// find the row num from which test starts
		Excel_Reader objXls = new Excel_Reader(sFileName);
		int testStartRowNum = 0;
		// find the row num from which test starts
		for (int iRowNum = 0; iRowNum <= objXls.getRowCount(sSheetName); iRowNum++)
		{
			if (objXls.getCellData(sSheetName, 0, iRowNum).contains(sTextToIdentifyStartRow))
			{
				testStartRowNum = iRowNum;
				break;
			}
		}
		int colStartRowNum = testStartRowNum;
		int totalCols = 0;
		while (!objXls.getCellData(sSheetName, totalCols, colStartRowNum).equals(""))
		{
			totalCols++;
		}
		// rows
		int dataStartRowNum = testStartRowNum ;
		int totalRows = 0;
		while (!objXls.getCellData(sSheetName, 0, dataStartRowNum + totalRows).equals(""))
		{
			totalRows++;
		}
		logger.debug("Total  test data  are:  " + totalRows);
		logger.debug("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		String[][] arrData = new String[totalRows][totalCols];
		for (int iRowNum = dataStartRowNum; iRowNum < (dataStartRowNum + totalRows); iRowNum++)
		{
			for (int iColNum = 0; iColNum < totalCols; iColNum++)
			{
				arrData[iRowNum - dataStartRowNum][iColNum] = objXls.getCellData(sSheetName, iColNum, iRowNum);
			}
		}
		return arrData;
	}

	private void closeWorkBook() {
		
	}
}