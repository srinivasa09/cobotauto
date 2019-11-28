package com.cobot.lib;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import com.cobot.utils.AppData;

public class APICalls {
    public static void Execute_postAPI(String postUrl, String inputJson) throws Exception {
//        String[] headerNames = AppData.properties.getProperty("headerNames").split(";");
//        String[] headerValues = AppData.properties.getProperty("headerValues").split(";");
//        HttpClient httpClient = HttpClientBuilder.create().build();
//        try {
//            HttpPost request = new HttpPost(postUrl);
//            StringEntity params = new StringEntity(inputJson);
//            for (int i = 0; i < headerNames.length; i++) {
//                request.addHeader(headerNames[i], headerValues[i]);
//            }
//            request.setEntity(params);
//            httpClient.execute(request);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
    	
		URL url = new URL ("http://localhost:8080/api/job/updatestatus");
		
		System.out.println(inputJson);
		
		HttpURLConnection con = (HttpURLConnection)url.openConnection();
		con.setRequestMethod("POST");
		
		con.setRequestProperty("Content-Type", "application/json; utf-8");
		con.setRequestProperty("Accept", "application/json");
		
		con.setDoOutput(true);
		
		//JSON String need to be constructed for the specific resource. 
		//We may construct complex JSON using any third-party JSON libraries such as jackson or org.json
		//String jsonInputString = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}";
		String testStr = "{\"jobId\": \"JOB-37\",\"status\": \"testa\",\"reason\": \"\"}";
		
		try(OutputStream os = con.getOutputStream()){
			byte[] input = inputJson.getBytes("utf-8");
			os.write(input, 0, input.length);			
		}

		int code = con.getResponseCode();
		System.out.println(code);
		
		try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))){
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println(response.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
    }
}