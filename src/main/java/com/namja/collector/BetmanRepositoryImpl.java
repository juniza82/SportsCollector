package com.namja.collector;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Repository;

@Repository
public class BetmanRepositoryImpl implements BetmanRepository {

	@Override
	public RecentMatchHistory getRecentMatchHistory(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
		ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
            	
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
        
        String responseBody = httpclient.execute(new HttpGet(url), responseHandler);
        System.out.println("----------------------------------------");
        System.out.println(responseBody);        
		return null;
	}
	
}
