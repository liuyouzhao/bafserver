package com.hibernate.usage;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.*;

public class Init {
	private static SessionFactory sessionFactory;
	
	public static void init(Session session) {
		
		Transaction tx = null;
		tx = session.beginTransaction();
		
		User u = new User();
		u.setUsername("hujia");
		u.setPassword("8ash18dakshj12dsa");
		session.save(u);
		
		
		UserInfo uinfo = new UserInfo();
		uinfo.setAddress("xxx");
		uinfo.setUsername("hujia");
		uinfo.setAvander("aaa");
		uinfo.setCity("hangzhou");
		uinfo.setEmail("liuyouzhao@hotmail.com");
		uinfo.setPhone("12332123");
		uinfo.setSex(1);
		session.save(uinfo);
		
		UserAccess access = new UserAccess();
		access.setUsername("hujia");
		access.setUseraccess("AFCB29B2AFCB29B21C04CFFE1C04CFFE");
		session.save(access);
		
		Talk talk = new Talk();
		talk.setDate(null);
		talk.setPubid(0);
		talk.setTalk("Agree with your points");
		talk.setUsername("hujia");
		session.save(talk);
		
		GoodMenu menu = new GoodMenu();
		menu.setName("DianZiChanPin");
		menu.setDescription("DianZiChanPinLeiMu");
		session.save(menu);
		
		
		PubCache pubc = new PubCache();
		pubc.setContent("{'img':'http://oss-cn-hangzhou.aliyuncs.com/hujia_photo_001/1.jpg','text':'this is a img'}");
		pubc.setDate(null);
		pubc.setUsername("hujia");
		session.save(pubc);
		

		Payment pay = new Payment();
		pay.setBusid(0);
		pay.setDate(null);
		pay.setUuid("0000000000000000");
		pay.setPrice(100);
		session.save(pay);
		
		GoodKeyword keywords1 = new GoodKeyword();
		keywords1.setKeyword("ShouJi");
		GoodKeyword keywords2 = new GoodKeyword();
		keywords2.setKeyword("iphone");
		GoodKeyword keywords3 = new GoodKeyword();
		keywords3.setKeyword("Apple");
		GoodKeyword keywords4 = new GoodKeyword();
		keywords4.setKeyword("Patch");
		session.save(keywords1);
		session.save(keywords2);
		session.save(keywords3);
		session.save(keywords4);
		
		for(int i = 0; i < 50; i ++) {
			Good good = new Good();
			good.setDate(null);
			good.setUuid(i + "");
			good.setName("iphone" + i);
			good.setDescription("this is an iphone" + i);
			good.setDetail("{'img':'http://oss-cn-hangzhou.aliyuncs.com/hujia_photo_001/1.jpg','text':'this is a img'}");
			good.setMenu(0);
			good.setUsername("hujia");
			session.save(good);
		}
		
		PublishBoard pub = new PublishBoard();
		pub.setContent("{'img':'http://oss-cn-hangzhou.aliyuncs.com/hujia_photo_001/1.jpg','text':'this is a img'}");
		pub.setDate(null);
		pub.setUsername("hujia");
		session.save(pub);
		
		GoodShop goodshop = new GoodShop();
		goodshop.setDescription("this is a shop");
		goodshop.setUsername("hujia");
		session.save(goodshop);

		Bussiness bus = new Bussiness();
		bus.setBuyername("hujia");
		bus.setSellername("hujia");
		bus.setDate(null);
		bus.setGoodkey("0");
		bus.setUuid("0000000000000001");
		session.save(bus);
		
		tx.commit();
		session.close();
	}
	
	
	public static void main(String[] args) {
		Configuration configure = new Configuration();
		//ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configure.getProperties()).build();
		sessionFactory = configure.configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		init(session);
//		try {
//			tx = session.beginTransaction();
//			init(session);
//			tx.commit();
//		}
//		catch(Exception ex) {
//			System.out.println(ex.toString());
//			System.out.println(ex.getStackTrace());
//			if(tx != null) {
//				tx.rollback();
//			}
//		}
		sessionFactory.close();
	}

}
