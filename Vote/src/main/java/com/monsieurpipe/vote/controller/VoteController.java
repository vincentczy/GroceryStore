package com.monsieurpipe.vote.controller;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.monsieurpipe.vote.model.IpRestriction;
import com.monsieurpipe.vote.model.Vote;
import com.monsieurpipe.vote.model.VoteModel;
import com.monsieurpipe.vote.repository.IpRestrictionRepository;
import com.monsieurpipe.vote.repository.VoteRepository;

/**
 * Created by Administrator on 2017/9/14.
 */
@Controller
@RequestMapping(value="")
public class VoteController {
	
	@Autowired
	VoteRepository voteRepo;
	@Autowired
	IpRestrictionRepository ipRepo;
	
    @RequestMapping(value = "vote", method = RequestMethod.GET)
    @ResponseBody
    public List<Vote> getVote(HttpServletRequest request) {
    	List<Vote> list = voteRepo.findAll();
    	Long ip = ipToLong(getIp(request));
    	Calendar cal = Calendar.getInstance();
    	Date start = new Date();
    	cal.setTime(start);
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	start = cal.getTime();
    	cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
    	Date end = cal.getTime();
    	
    	// 找到该IP已投的品牌
    	List<IpRestriction> ipRes = ipRepo.findByIpAndDateBetween(ip, start, end);
    	if (ipRes.size() != 0) {
	    	String[] votes = ipRes.get(0).getVotes().split("\\.");
	    	Set set = new HashSet();
			for (int i = 0; i < votes.length; i++)
				set.add(votes[i]);
    		for (int i = 0; i < list.size(); i++)
    			if (set.contains(list.get(i).getName()))
    				list.get(i).setVoted(true);
    			else
    				list.get(i).setVoted(false);
    	}
    	
        return list;
    }
    
    @RequestMapping(value = "vote", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public String postVote(@RequestBody VoteModel votemodel, HttpServletRequest request) {
    	// 也可以在url后面+?name='', request.getParameter('name')
    	String name = votemodel.getName();
    	Calendar cal = Calendar.getInstance();
    	Date start = new Date();
    	cal.setTime(start);
    	cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
    	start = cal.getTime();
    	cal.set(Calendar.DATE, cal.get(Calendar.DATE) + 1);
    	Date end = cal.getTime();
    	String ipStr = getIp(request);
    	Long ip = ipToLong(ipStr);
    	//todo assemble ipLong
    	List<IpRestriction> list = ipRepo.findByIpAndDateBetween(ip, start, end);
    	String json = "{\"state\":\"success\"}";
    	if (list == null || list.size() == 0) {
    		
    		// 正常投票
    		Vote vote = voteRepo.findOneByName(name);
    		if(vote == null) {
    			json = "{\"state\":\"failure\",\"msg\":\"未找到该品牌.\"}";
    			return json;
    		}
    		IpRestriction ipRes = new IpRestriction();
    		ipRes.setIp(ip);
    		ipRes.setDate(new Date());
    		ipRes.setVotes(name);
    		ipRepo.save(ipRes);
    		vote.setVotes(vote.getVotes() + 1);
    		voteRepo.save(vote);
    	} else {
    		IpRestriction ipRes = list.get(0);
    		String[] votes = ipRes.getVotes().split(";");
    		
    		// 超过票数限制
    		if (votes.length > 4) {
    			json = "{\"state\":\"failure\",\"msg\":\"今天您已经投过5票，请明天再试.\"}";
    			return json;
    		}
    		
    		// 已经为统一品牌投票
    		for (int i = 0; i < votes.length; i++)
        		if (name.equals(votes[i])) {
        			json = "{\"state\":\"failure\",\"msg\":\"今天您已经为该品牌投过票了，请明天再试.\"}";
        			return json;
        		}
    		
    		// 正常投票
    		Vote vote = voteRepo.findOneByName(name);
    		if(vote == null) {
    			json = "{\"state\":\"failure\",\"msg\":\"未找到该品牌.\"}";
    			return json;
    		}
    		ipRes.setVotes(ipRes.getVotes() + ";" + name);
    		ipRepo.save(ipRes);
    		vote.setVotes(vote.getVotes() + 1);
    		voteRepo.save(vote);
    	}
    	
        return json;
    }
    
    private Long ipToLong(String ip) {
    	Long temp = 0l;
    	String[] segs = ip.split("\\.");
    	for (int i = 0; i < 4; i++) {
    		temp = temp * 1000 + Long.parseLong(segs[i]);
    	}
    	return temp;
    }
    
    /** 
     * @Description: 获取客户端IP地址   
     */  
    private String getIp(HttpServletRequest request) {   
       String ip = request.getHeader("x-forwarded-for");   
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
           ip = request.getHeader("Proxy-Client-IP");   
       }   
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
           ip = request.getHeader("WL-Proxy-Client-IP");   
       }   
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
           ip = request.getRemoteAddr();   
           if(ip.equals("127.0.0.1")){     
               //根据网卡取本机配置的IP     
               InetAddress inet=null;     
               try {     
                   inet = InetAddress.getLocalHost();     
               } catch (Exception e) {     
                   e.printStackTrace();     
               }     
               ip= inet.getHostAddress();     
           }  
       }   
       // 多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
       if(ip != null && ip.length() > 15){    
           if(ip.indexOf(",")>0){     
               ip = ip.substring(0,ip.indexOf(","));     
           }     
       }     
       return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }  
    
}
