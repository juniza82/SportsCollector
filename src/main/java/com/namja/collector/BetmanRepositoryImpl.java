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
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BetmanRepositoryImpl implements BetmanRepository {

	private static Logger logger = LoggerFactory.getLogger(BetmanRepositoryImpl.class);
	
	@Override
	public RecentMatchHistory getRecentMatchHistory(String url) throws ClientProtocolException, IOException {
		
		logger.info(String.format("SCHEDULE::START-GET_RECENT_MATCH_HISTORY::TARGET -> %s", url));
		
		try {
		
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
	        Document doc = Jsoup.parse(responseBody);
	        Element recentMatchHistoryElement = doc.getElementsByClass("dataH02").first().getElementsByTag("tbody").first();
	        
	        // 2사이즈 배열 [ 0 : 팀명 / 1 : 순위 ]
	        String[] firstTeamNameRankInfo = recentMatchHistoryElement.getElementsByTag("tr").first().getElementsByTag("td").get(2).text().split(" ");
	        
	        return null;
		
		} finally {
			logger.info(String.format("SCHEDULE::END-GET_RECENT_MATCH_HISTORY::TARGET -> %s", url));
		}
	}

	@Override
	public RecentMatchHistory getRecentMatchHistory2(String url)
			throws ClientProtocolException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
