package com.academy.shopping.model.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.academy.shopping.exception.ProductException;
import com.academy.shopping.exception.UploadException;
import com.academy.shopping.model.domain.Product;
import com.academy.shopping.model.domain.SubCategory;
import com.academy.shopping.model.util.ExcelParser;
import com.academy.shopping.model.util.FileManager;

@Service
public class ProductServiceImpl implements ProductService{

	//하위 카테고리 목록을 가져올 DAO
	@Autowired
	private SubCategoryDAO subCategoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Autowired
	private ExcelParser excelParser;
	
	Thread thread;
	
	@Override
	public List selectAll() {
		
		return productDAO.selectAll();
	}

	@Override
	public List selectBySubId(int subcategory_id) {
		
		return productDAO.selectBySubId(subcategory_id);
	}

	@Override
	public Product select(int product_id) {
		
		return productDAO.select(product_id);
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void regist(Product product, String savePath) throws UploadException, ProductException{
		String filename=fileManager.save(product, savePath);
		product.setProduct_img(filename);
		productDAO.insert(product);
	}

	@Override
	public void update(Product product) {
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void remove(Product product, String dest) {
		//파일 삭제
		fileManager.removeFile(dest);
		//db 삭제
		productDAO.delete(product);
	}

	@Override
	public List selectByTopId(int topcategory_id) {
		List<SubCategory> subCategoryList = subCategoryDAO.selectByTopCategoryId(topcategory_id);
		List productList=new ArrayList();  //서브 카테고리마다 소속된 상품들을 누적시킬 리스트
		for(int i=0; i<subCategoryList.size();i++) {
			SubCategory subCategory=subCategoryList.get(i);
			List<Product> list=productDAO.selectBySubId(subCategory.getSubcategory_id());
			for(Product product : list) {
				productList.add(product);
			}
			
		}
		
		return productList;
	}

	@Transactional(propagation = Propagation.REQUIRED) //트랜잭션 처리
	public void registByExcel(File file, String read, String des) {
		//엑셀을 간접적으로 해석하여 insert DAO에게 시킬것임
		//2003년 이후 최신 버전에서의 전담객체 XSSF~~
		List<Product> list=excelParser.getParseResult(file);
			for(int i=0; i< list.size(); i++) {
				//이미지를 서버에 저장하기 (스프링과 상관없이 개발자의 javaSE 능력으로 해결)
				Product product = list.get(i);
				FileInputStream fis = null;
				FileOutputStream fos=null;
				try {
					long time=System.currentTimeMillis();
					String ext=fileManager.getExt(product.getProduct_img());
					String filename=time+"."+ext;
					
					fis = new FileInputStream(read+"/"+product.getProduct_img());
					fos = new FileOutputStream(des+"/"+filename);  //빈 파일
					//기존 DTO에 생성된 파일명을 대체하자
					product.setProduct_img(filename);
					int data=-1;
					while(true) {
						data=fis.read();
						if(data==-1) break;
						fos.write(data);
					}System.out.println("파일 복사 완료");
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					if(fos!=null)
						try {
							fos.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					if(fis!=null)
						try {
							fis.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				productDAO.insert(product);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
	}
	
}
