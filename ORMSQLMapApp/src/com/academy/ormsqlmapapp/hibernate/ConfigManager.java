package com.academy.ormsqlmapapp.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

//mybatis에서의 설정 xml을 읽어들여 쿼리수행 객체를 반환하는 객체를 정의하듯이
//hibernate에서도 설정 xml을 읽어들여 업무를 수행시킬 수 있는 Session 객체를 반환하는 설정 객체를 정의한다.
public class ConfigManager {
	private static ConfigManager instance;
	SessionFactory sessionFactory;
	
	private ConfigManager() {
		Configuration configuration= new Configuration();
		configuration.configure("com/academy/ormsqlmapapp/hibernate/hibernate.cfg.xml");
		ServiceRegistry serviceRegistry= new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		sessionFactory=configuration.buildSessionFactory(serviceRegistry);
	}
	
	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public static ConfigManager getInstance() {
		if(instance==null) {
			instance= new ConfigManager();
		}
		return instance;
	}
}
