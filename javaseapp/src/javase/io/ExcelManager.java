package javase.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//자바프로그램에서 excel 파일 제어
public class ExcelManager {
	FileInputStream fis;
	XSSFWorkbook workbook; //엑셀 파일
	XSSFSheet sheet; //시트
	
	public ExcelManager(){
		try {
			fis = new FileInputStream("D:/jsp_workspace/javaseapp/data/상품.xlsx");
			System.out.println(fis);
			
			workbook= new XSSFWorkbook(fis);
			sheet=workbook.getSheetAt(0);  //첫번째 시트 접근
			
			Iterator rowIt= sheet.iterator();
			
			while(rowIt.hasNext()) { //이터레이터 안의 요소중 다음 요소가 있는지 판단한다. 만일 다음 요소가 존재하면 true, 없으면 false 반환
				Row row= (Row)rowIt.next(); //다음 요소 접근
				
				//하나의 row에 포함된 Cell들을 Iterator를 통해 얻어옴
				Iterator<Cell> cellIt = row.cellIterator();
				while(cellIt.hasNext()) {
					Cell cell=cellIt.next();
					if(cell.getCellType()==CellType.STRING) {
						cell.getStringCellValue();
					}else if(cell.getCellType()==CellType.NUMERIC){
						cell.getNumericCellValue();
					}else if(cell.getCellType()==CellType.STRING) {
						cell.getStringCellValue();
					}
					System.out.print(cell);
				}
				System.out.println();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	public static void main(String[] args) {
		new ExcelManager();
	}
}
