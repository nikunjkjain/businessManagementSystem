package com.webmaven.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.webmaven.controller.UserController;

public class Utility {

private static final Logger logger = Logger.getLogger(UserController.class);
	
private static Utility instance;

    private Utility(){}
    
    public static synchronized Utility getInstance(){
        if(instance == null){
            instance = new Utility();
        }
        return instance;
    }
    
    public boolean isValidSession(HttpSession session) {
    	Boolean sessVar = (Boolean) session.getAttribute(BmsConstants.ISVALID);
    	logger.info("===> isValidSession :sessVar:"+sessVar );
    	boolean isValid = false;
    	if(sessVar != null) {
    		isValid = true;
    	}
    	return isValid; 
    }
}
