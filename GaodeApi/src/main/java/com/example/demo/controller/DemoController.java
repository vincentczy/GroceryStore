package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.demo.util.GaodeApiFetcher;

/**
 * Created by Administrator on 2017/9/14.
 */
@Controller
@RequestMapping(value="/api")
public class DemoController {
	
	@Autowired
	GaodeApiFetcher fetcher;
	
    @RequestMapping(value = "list", method = RequestMethod.GET)
    @ResponseBody
    public String getList() {
        String json = "{\"sid\":\"10001\",\"status\":{\"code\":0,\"msg\":\"success\"},\"data\":{\"rows\":[{\"pubTime\":\"2015-09-10 10:38:34\",\"jamSpeed\":5,\"createTime\":\"2015-09-10 10:32:34\",\"eventId\":\"4201144185235417\",\"insertTime\":\"2015-09-10 10:38:34\",\"jamDist\":796,\"handleState\":1,\"pubRunStatus\":1,\"roadName\":\"枫树南路\",\"longTime\":4,\"xy\":\"114.130493,30.456610\"},{\"pubTime\":\"2015-09-10 10:38:34\",\"jamSpeed\":8,\"createTime\":\"2015-09-10 10:34:34\",\"eventId\":\"4201144185247410\",\"insertTime\":\"2015-09-10 10:38:34\",\"jamDist\":711,\"handleState\":1,\"pubRunStatus\":1,\"roadName\":\"白沙洲大道\",\"longTime\":4,\"xy\":\"114.277252,30.482262\"}],\"total\":10,\"sysTime\":1441852792283}}";
        return json;
    }
    
    @RequestMapping(value = "detail", method = RequestMethod.GET)
    @ResponseBody
    public String getDetail() {
        String json = "{\"sid\":\"10001\",\"status\":{\"code\":0,\"msg\":\"success\"},\"data\":{\"rows\":[{\"createTime\":\"2015-09-10 11:38:34\",\"dirvingDir\":3,\"eventJamDist\":0,\"eventJamSpeed\":0,\"length\":47,\"linkId\":\"5904801865530343450\",\"pubRunStatus\":0,\"reliability\":0,\"roadName\":\"沿河大道\",\"roadType\":7,\"sectionInfo\":\"\",\"sectionNum\":1,\"speed\":6,\"state\":3,\"travelTime\":362,\"xy\":\"114.284225,30.566847\",\"xys\":\"114.284721,30.566868;114.284470,30.566868;114.284271  ,30.566854;114.284225,30.566847\"},{\"createTime\":\"2015-09-10 11:38:34\",\"dirvingDir\":3,\"eventJamDist\":0,\"eventJamSpeed\":0,\"length\":59,\"linkId\":\"5904801865530345221\",\"pubRunStatus\":0,\"reliability\":0,\"roadName\":\"沿河大道\",\"roadType\":7,\"sectionInfo\":\"\",\"sectionNum\":1,\"speed\":9,\"state\":3,\"travelTime\":319,\"xy\":\"114.283630  ,30.566723\",\"xys\":\"114.284225,30.566847;114.284096,30.566854;114.284027,30.56685 3;114.283974,30.566843  ;114.283897,30.566826;114.283791,30.566790;114.283630,30.56 6723\"},{\"createTime\":\"2015-09-10 11:38:34\",\"dirvingDir\":3,\"eventJamDist\":0,\"eventJamSpeed\":0,\"length\":32,\"linkId\":\"5904801865530359454\",\"pubRunStatus\":0,\"reliability\":0,\"roadName\":\"沿河大道\",\"roadType\":7,\"sectionInfo\":\"\",\"sectionNum\":1,\"speed\":9,\"state\":3,\"travelTime\":102,\"xy\":\"114.282608,30.565311\",\"xys\":\"114.282623,30.565603;114.282608,30.565311\"}],\"event\":{\"city\":\"420100\",\"dist\":\"\",\"eventId\":\"4201144185223420\",\"handleState\":1,\"id\":67531160,\"insertTime\":{\"date\":10,\"day\":4,\"hours\":11,\"minutes\":40,\"month\":8,\"seconds\":34,\"time\":1441856434000,\"timezoneOffset\":-480,\"year\":115},\"isNormal\":0,\"jamDist\":852,\"jamSpeed\":8,\"longTime\":60,\"province\":\"\",\"pubRunStatus\":1,\"roadName\":\"沿河大道\",\"roadType\":0,\"state\":2,\"xy\":\"114.284225,30.566847\"},\"total\":7}}";
        return json;
    }
    
//    @RequestMapping(value = "fetch", method = RequestMethod.GET)
//    @ResponseBody
//    public String fetchJamEvent() throws JSONException {
//		return fetcher.getList(null, null, null, null, null);
//    }
    
}
