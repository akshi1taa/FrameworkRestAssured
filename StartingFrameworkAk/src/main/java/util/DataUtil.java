package util;

import java.util.Hashtable;

public class DataUtil {

	public static Object[][] getData1(Xls_Reader xl, String testName)
	{
		String sheetname = "retailSheet";
		int testStartRowNum=1;
		
		while(!xl.getCellData(sheetname, 0, testStartRowNum).equals(testName))
		{
			testStartRowNum++;
		}
		
		System.out.println("Test starts from row no."+testStartRowNum);
		
		int colStartRowNum=testStartRowNum+1;
		int dataStartRowNum=testStartRowNum+2;
		
		int rows=0;
		
		while(!xl.getCellData(sheetname, 0, dataStartRowNum+rows).contentEquals(""))
		{
			rows++;
		}
		System.out.println("Total Data Rows are "+rows);
		
		int cols=0;
		
		while(!xl.getCellData(sheetname, cols,colStartRowNum).contentEquals(""))
		{
			cols++;
		}
		System.out.println("Total Data Comuns are "+cols);
		
		Object[][] data=new Object[rows][1];
		
		int dataRow=0;
		Hashtable<String,String> table=null;
		
		for(int rnum=dataStartRowNum; rnum<dataStartRowNum+rows;rnum++)		//need to visualize data and table clearly
		{
			table=new Hashtable<String,String>();
			
			for(int cnum=0;cnum<cols; cnum++)
			{
				String key=xl.getCellData(sheetname, cnum, colStartRowNum);
				String value=xl.getCellData(sheetname, cnum, rnum);
				
				table.put(key, value);
			}
			
			data[dataRow][0]=table;
			dataRow++;
			
		}
		
		return data;
	}
	
	
}
