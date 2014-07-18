package com.hibernate.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import com.hibernate.api.API;
import com.hibernate.model.Finance;
import com.hibernate.model.User;

public class FinanceController {
	
	public static Finance getNewestFinanceValues() {
		Session session = API.openSession();
		
		Criteria criteria = session.createCriteria(Finance.class);
		criteria.addOrder(Property.forName("id").desc());
		criteria.setMaxResults(0);
		
		ArrayList list = (ArrayList) criteria.list();
		
		session.close();
		
		if(list == null) {
			return null;
		}
		
		Finance finance = (Finance) list.get(0);
		return finance;
	}
	
	public static ArrayList getNewestFinanceValues(int last) {
		Session session = API.openSession();
		
		Criteria criteria = session.createCriteria(Finance.class);
		criteria.addOrder(Property.forName("id").desc());
		criteria.setMaxResults(last);
		ArrayList list = (ArrayList) criteria.list();
		
		session.close();
		return list;
	}
}
