package com.webmaven.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.webmaven.bean.User;
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
    
    public String getUserIdFromSession (HttpSession session) {
    	
    	User uDetails = (User) session.getAttribute(BmsConstants.USERDETAILS);
    	
    	if (uDetails != null)
			return uDetails.getUsername();
			
		logger.warn("Got User Obj as Null hence retruning userName as Null");
    	return null;
    }
}
