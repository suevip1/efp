package com.baiwang.einvoice.service.skService;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.baiwang.einvoice.util.ConfigUtil;

@Service
public class SkService {
	
	private static final Log logger = LogFactory.getLog(SkService.class);
	
//	private String skUrl = "http://192.168.6.14:1008/SKServer/SKDo";
	private String skUrl = ConfigUtil.get("request.sk.url");
	
	public String reqestSK(String xml){
		logger.info("///////请求税控的xml///////////" + xml);
		String resultXml = "";
        try {
            URL url = new URL(skUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            
            OutputStream out = conn.getOutputStream();
            
            byte[] content = IOUtils.toByteArray(new ByteArrayInputStream(xml.getBytes("gbk")));
            out.write(content);
            out.flush();
            
            resultXml = IOUtils.toString(conn.getInputStream(), "gbk");
            
        } catch (Exception e) {
        	logger.error("///////请求税控error///////////");
            e.printStackTrace();
        }
		
		return resultXml;
	}
	
	
	
}
