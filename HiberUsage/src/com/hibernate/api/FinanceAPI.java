package com.hibernate.api;

import java.util.ArrayList;
import java.util.HashMap;

import com.hibernate.controller.FinanceController;

public class FinanceAPI extends BaseAPI {
	
	public static String get(int last) {
		if(last == 0) {
			return FinanceController.getNewestFinanceValues().toString();
		}
		else {
			ArrayList list = FinanceController.getNewestFinanceValues(last);
			StringBuffer buf = new StringBuffer();
			buf.append("[");
			int counter = 0;
			for(Object obj : list) {
				buf.append(obj.toString());
				if(counter != list.size() - 1)
					buf.append(",");
				counter ++;
			}
			buf.append("]");
			return buf.toString();
		}
	}
}
