package com.example.demo.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.*;
import com.example.demo.repository.JamEventRepository;
import com.example.demo.repository.JamEventRowRepository;

/**
 * Hello world!
 *
 */
@Service
public class GaodeApiFetcher 
{       
	@Autowired
	JamEventRowRepository jerRepo;	
	@Autowired
	JamEventRepository jeRepo;
	    
//  String url = "http://ip:prot/gate?sid=10001&resType=json&encode=utf-8&reqData={\"city\":\"420100\",\"orderType\":\"1\",\" isNormal \":\"1\"}&serviceKey=9C61A255A5C7A0CEC072EF8F2BFC0018";
    public String getList(String url, String ip, String port, String city, String serviceKey){
    	if (url == null)
    		url = "http://localhost:8080/api/list";
		String s = HttpRequestUtil.sendGet(url);
		if (s == null) 
			return "Couldn't get response body.";
		JsonObject response = new JsonParser().parse(s).getAsJsonObject();
		JsonObject status = response.get("status").getAsJsonObject();
		if (status == null || status.get("code").getAsInt() != 0)
			return "Wrong response status.";
		JsonArray rows = response.get("data").getAsJsonObject().get("rows").getAsJsonArray();
		int len = rows.size();
		for (int i = 0; i < len; i++) {
			JsonObject row = rows.get(i).getAsJsonObject();
			String eventId = row.get("eventId").getAsString();
			String insertTime = row.get("insertTime").getAsString();
//			System.out.println(eventId);
//			getDetail(eventId);
			try {
				insertTime = URLEncoder.encode(insertTime,"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			String detailUrl = "http://"+ip+"/scms/sipsd/service/ThirdService/amapproxy?token=wlRCoRWnzKL859rOhcHdDOpgiupgC1CxL%2bptHcdlnir14WLOLshewIU3AcBR9r1rghBjE5INxJyb6GjdByj5ClVONIb0ce8C%2b97S0pX7iOO55jZTC4CZ3w%3d%3d&sid=10002&resType=json&encode=utf-8&reqData={\"city\":\""
								+city+"\",\"eventId\":\""
								+eventId+"\",\"type\":\"1\",\"insertTime\":\""
								+insertTime+"\"}&serviceKey=" + serviceKey;
			System.out.println(" detail url  :  " + detailUrl);
			if (ip == null)
				detailUrl = null;
			getDetail(detailUrl);
		}
		return "Success";
    }
    
//    String url = "http://ip:prot/gate?sid=10002&resType=json&encode=utf-8&reqData={\"city\":\"420100\",\"eventId\":\"4201144185235417\",\"type\":\"1\",\" insertTime \":\"2015-09-10 10:38:34\"}&serviceKey=9C61A255A5C7A0CEC072EF8F2BFC0018";
    public String getDetail(String url){
    	if (url == null)
    		url = "http://localhost:8080/api/detail";
		String s = HttpRequestUtil.sendGet(url);
		if (s == null) 
			return "Couldn't get response body.";
		JsonObject response = new JsonParser().parse(s).getAsJsonObject();

		JsonObject status = response.getAsJsonObject("status");
		if (status == null || status.get("code").getAsInt() != 0)
			return "Wrong response status.";
		Gson gson = new Gson();
		JsonObject data = response.get("data").getAsJsonObject();
		JsonObject event = data.get("event").getAsJsonObject();
		JamEvent je = gson.fromJson(event, JamEvent.class);
		JsonObject time = event.get("insertTime").getAsJsonObject();
		je.setDate(time.get("date").getAsInt());
		je.setDay(time.get("day").getAsInt());
		je.setHours(time.get("hours").getAsInt());
		je.setMinutes(time.get("minutes").getAsInt());
		je.setMonth(time.get("month").getAsInt());
		je.setSeconds(time.get("seconds").getAsInt());
		je.setYear(time.get("year").getAsInt());
		je.setTime(time.get("time").getAsInt());
		je.setTimzoneOffset(time.get("timezoneOffset").getAsInt());
		jeRepo.save(je);
		JsonArray rows = data.getAsJsonObject().get("rows").getAsJsonArray();
		int len = rows.size();
		for (int i = 0; i < len; i++) {
			JsonObject row = rows.get(i).getAsJsonObject();
//			System.out.println(row);
			JamEventRow jer = gson.fromJson(row, JamEventRow.class);
			jer.setEventId(event.get("eventId").getAsLong());
			jerRepo.save(jer);			
		}
		return "Success";
    }
    
}
