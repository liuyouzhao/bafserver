package com.hibernate.usage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.hibernate.model.Finance;

public class AddFinance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Configuration configure = new Configuration();
		SessionFactory sessionFactory = configure.configure().buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		try {
			Finance finance = new Finance();
			finance.setFinance0(198.27 * (Math.random() + 0.5));
			finance.setFinance1(Math.random() + 0.15);
			finance.setFinance2(Math.random() + 0.15);
			finance.setFinance3(Math.random() + 0.15);
			finance.setFinance4(Math.random() + 0.15);
			finance.setFinance5(Math.random() + 0.15);
			finance.setFinance6(Math.random() + 0.15);
			finance.setDate(null);
			
			session.save(finance);
			
			tx.commit();
		}
		catch(Exception ex) {
			tx.rollback();
			
			ex.printStackTrace();
		}
		
		session.close();
	}

}
