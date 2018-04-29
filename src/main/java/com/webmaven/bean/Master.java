package com.webmaven.bean;

import java.util.HashMap;
import java.util.Map;

public class Master {

	private String masterId = "";
	private String masterKey = "";
	private String masterValue = "";
	private String status = "";
	
	Map<String, HashMap<String, String>> masterIdKeyValMap = new HashMap<String, HashMap<String, String>>();
	Map<String, HashMap<String, String>> masterIdValKeyMap = new HashMap<String, HashMap<String, String>>();
	Map<String, String> masterKeyValMap = new HashMap<String, String>();
	Map<String, String> masterValKeyMap = new HashMap<String, String>();
	
	public String getMasterId() {
		return masterId;
	}
	public Map<String, HashMap<String, String>> getMasterIdKeyValMap() {
		return masterIdKeyValMap;
	}
	public void setMasterIdKeyValMap(Map<String, HashMap<String, String>> masterIdKeyValMap) {
		this.masterIdKeyValMap = masterIdKeyValMap;
	}
	public Map<String, HashMap<String, String>> getMasterIdValKeyMap() {
		return masterIdValKeyMap;
	}
	public void setMasterIdValKeyMap(Map<String, HashMap<String, String>> masterIdValKeyMap) {
		this.masterIdValKeyMap = masterIdValKeyMap;
	}
	public Map<String, String> getMasterKeyValMap() {
		return masterKeyValMap;
	}
	public void setMasterKeyValMap(Map<String, String> masterKeyValMap) {
		this.masterKeyValMap = masterKeyValMap;
	}
	public Map<String, String> getMasterValKeyMap() {
		return masterValKeyMap;
	}
	public void setMasterValKeyMap(Map<String, String> masterValKeyMap) {
		this.masterValKeyMap = masterValKeyMap;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	public String getMasterKey() {
		return masterKey;
	}
	public void setMasterKey(String masterKey) {
		this.masterKey = masterKey;
	}
	public String getMasterValue() {
		return masterValue;
	}
	public void setMasterValue(String masterValue) {
		this.masterValue = masterValue;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Master [masterId=" + masterId + ", masterKey=" + masterKey + ", masterValue=" + masterValue
				+ ", status=" + status + "]";
	}
	
}
