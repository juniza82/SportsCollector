package com.namja.collector;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface BetmanRepository {

	/**
	 * 최근 경기 메치에 대해서 가져온다.
	 * @param url 수집 대상 페이지 url
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public RecentMatchHistory getRecentMatchHistory(String url) throws ClientProtocolException, IOException;
	
	public RecentMatchHistory getRecentMatchHistory2(String url) throws ClientProtocolException, IOException;
	
}
