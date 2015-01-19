package com.namja.collector;

public interface SportsMetaDataCollectorService {

	/**
	 * 스포츠 크롤링 데몬 상태 로그 스케줄러
	 */
	public void reportCurrentDeamonStatus();
	
	/**
	 * 최근  맞대결 전적 수집 스케줄러
	 */
	public void recentMatchHistory();
	
	/**
	 * 프로토 승부식 수집 스케줄러
	 */
	public void recentMatchProtoHistory();
	
}
