package com.academy.shopping.model.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;

@Component
public class ExcelParser {
	
	public List getParseResult(File file) {
		List<Product> productList = new ArrayList();
		//1) 엑셀파일 접근 객체
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			System.out.println("엑셀 접근 성공");
			
			//엑셀파일의 구성 시트 접근
			XSSFSheet sheet = workbook.getSheetAt(0);
			
			//하나의 row 접근
			int totalRow=sheet.getPhysicalNumberOfRows(); //실제 사용자가 입력한 레코드 수
			System.out.println("로우 수는 "+totalRow);
			for(int j=1; j<totalRow; j++) {
				XSSFRow row= sheet.getRow(j);  //한줄의 row 접근
				Product product = new Product();
				XSSFCell cell;
				String sdata = null;
				double idata = 0;
			//하나의 row에 소속된 각셀을 접근하자
				for(int i=0; i<row.getPhysicalNumberOfCells();i++){
					cell = row.getCell(i);
					if(cell.getCellType()==CellType.STRING) {
						sdata=cell.getStringCellValue();
					}else if(cell.getCellType()==CellType.NUMERIC){
						idata=cell.getNumericCellValue();
					}
					if(i==0) {
						product.setProduct_name(cell.getStringCellValue());
					} else if(i==1) {
						product.setBrand(sdata);
					} else if(i==2) {
						product.setPrice((int)idata);
					} else if(i==3) {
						product.setDiscount((int)idata);
					} else if(i==4) {
						product.setMemo(sdata);
					} else if(i==5) {
						product.setDetail(sdata);
					} else if(i==6) {
						product.setProduct_img(sdata);
					}else if(i==7) {
						SubCategory subCategory = new SubCategory();
						subCategory.setSubcategory_id((int)idata);
						product.setSubcategory(subCategory);
					}					
				} productList.add(product);
			}System.out.println("엑셀 분석 결과: "+productList);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productList;
	}
	
}
