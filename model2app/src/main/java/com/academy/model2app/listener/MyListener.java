package com.academy.model2app.listener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

//서버가 가동될 때 무언가 하고싶을때
public class MyListener implements ServletContextListener{
	FileInputStream fis;
	Properties props = new Properties();
	
	//웹컨테이너가 가동될 때 호출되는 메서드
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context= sce.getServletContext();
		String path=context.getInitParameter("contextConfigLocation"); // = /WEB-INF/mapping.properties in mapping.properties
		
		try {
			fis= new FileInputStream(context.getRealPath(path));
			props.load(fis);
			context.setAttribute("props", props);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//웹컨테이너가 중단될 때 호출되는 메서드
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
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
