package com.qa.itera.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {
	
	public static final String LOGIN_PAGE_TITLE = "- Testautomation practice page";
	public static final String ACC_PAGE_TITLE = "- Testautomation practice page";
	public static final int ACC_PAGE_SECTIONS_COUNT = 7;
	
	public static final int PRODUCT_IMAGES_COUNT_Camilla = 7;
	public static final String ACCOUNT_CREATION_SUCCESS_MESSG = "Registration Successful";
	public static final String REGISTER_SHEET_NAME = "register";
	
	
	
	public static List<String> expectedAccSecList() {
		List<String> expEcList = new ArrayList<String>();
		expEcList.add("Name");
		expEcList.add("Company");
		expEcList.add("Address");
		expEcList.add("City");
		expEcList.add("Phone");
		expEcList.add("Email");
		expEcList.add("");
		 
		System.out.println(expEcList);
		return expEcList;
	}

}
