package com.example.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.demo.util.GaodeApiFetcher;

@Component
public class FetchTask {
	
	@Autowired
	GaodeApiFetcher fetcher;

//	@Scheduled(fixedRate = 5000)
//	public void fetchTask() {
//		System.out.println("Fetch Task Running");
//        String ip = "";
//        String port = "";
//        String city = "";
//        String serviceKey = "";
//		String url = "http://"+ip+":"+port+"/gate?sid=10001&resType=json&encode=utf-8&reqData={\"city\":\""
//					 +city+"\",\"orderType\":\"1\",\" isNormal \":\"1\"}&serviceKey="+serviceKey;
//		fetcher.getList(null, ip, port, city, serviceKey);
//    }
	
}
