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
	
	/**
	 * 프로토 승부식에 대해서 가져온다.
	 * @param url 수집 대상 페이지 url
	 * @URL http://www.betman.co.kr/gameInfoMain.so?gameId=G101&gameRound=150005
	 * @gameId=G101&gameRound=150005 바뀔수 있다.
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public RecentMatchHistory getRecentMatchProtoHistory(String url) throws ClientProtocolException, IOException;
	
}
