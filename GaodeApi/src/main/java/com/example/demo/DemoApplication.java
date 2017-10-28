package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.demo.util.GaodeApiFetcher;

@SpringBootApplication
@EnableScheduling
public class DemoApplication {
	
	@Autowired
	GaodeApiFetcher fetcher;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
		
	}
	
	@Scheduled(fixedRate = 60000)
	public void fetchTask() {
		System.out.println("Fetch Task Running");
		String gaodeUrl = new File(".").getAbsolutePath() + "/gaodeUrl.txt";
		String ip = "";
        String port = "";
        String city = "";
        String serviceKey = "";
        BufferedReader br = null;
		try {
			br =new BufferedReader(new FileReader(gaodeUrl));
			br.readLine();
			ip = br.readLine();
			br.readLine();
			port = br.readLine();
			br.readLine();
			city = br.readLine();
			br.readLine();
			serviceKey = br.readLine();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
//        String url = "http://"+ip+":"+port+"/gate?sid=10001&resType=json&encode=utf-8&reqData={\"city\":\""
//					 +city+"\",\"orderType\":\"1\",\" isNormal \":\"1\"}&serviceKey="+serviceKey;
		
		String url = "http://"+ip+"/scms/sipsd/service/ThirdService/amapproxy?token=wlRCoRWnzKL859rOhcHdDOpgiupgC1CxL%2bptHcdlnir14WLOLshewIU3AcBR9r1rghBjE5INxJyb6GjdByj5ClVONIb0ce8C%2b97S0pX7iOO55jZTC4CZ3w%3d%3d&sid=10001&resType=json&encode=utf-8&reqData={\"city\":\"" 
		+city+ "\",\"orderType\":\"1\",'isNormal':\"0\"}&serviceKey="+serviceKey;
		System.out.println(" url  :  " + url);
		fetcher.getList(url, ip, port, city, serviceKey);
    }
}
