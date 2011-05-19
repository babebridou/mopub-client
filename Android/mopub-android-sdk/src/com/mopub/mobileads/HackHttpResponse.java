package com.mopub.mobileads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

public class HackHttpResponse {

	HttpResponse response;
	String responseString;
	
	public HackHttpResponse(HttpResponse response) throws Exception {
		this.response = response;
		 HttpEntity entity = response.getEntity();
		 StringBuilder sb = new StringBuilder();
	        InputStream is;
	        try {
	            is = entity.getContent();
	        } catch (IllegalStateException e1) {
//	            pageFailed();
//	        	return;
	        	throw new Exception(e1);
	        } catch (IOException e1) {
//	            pageFailed();
//	            return;
	        	throw new Exception(e1);	        	
	        }

	        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

	        String line;
	        try {
	            while ((line = reader.readLine()) != null) {
	                sb.append(line + "\n");
	            }
	        } catch (IOException e) {
//	            pageFailed();
//	            return;
	        	throw new Exception(e);
	        } finally {
	            try {
	                is.close();
	            } catch (IOException e) {
	                // Ignore since at this point we have the data we need
	            }
	        }
	        this.responseString = sb.toString();
	}
}
