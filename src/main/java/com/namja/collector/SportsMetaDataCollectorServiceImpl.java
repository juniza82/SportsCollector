package com.namja.collector;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SportsMetaDataCollectorServiceImpl implements SportsMetaDataCollectorService {
	
	private static Logger logger = LoggerFactory.getLogger(SportsMetaDataCollectorServiceImpl.class);

	@Autowired BetmanRepositoryImpl betmanRepository;
	
    @Scheduled(fixedRate = 5000)
    @Override
    public void reportCurrentDeamonStatus() {
    	
    	logger.info(String.format("[DEAMON-MONITOR] ##########################################################################################"));
    	ThreadMXBean threads = ManagementFactory.getThreadMXBean();
    	logger.info(String.format("[DEAMON-MONITOR] THREAD INFO:: ThreadCount:%s, TotalStartedThreadCount:%s, DaemonThreadCount:%s, PeakThreadCount:%s", 
                 threads.getThreadCount(), threads.getTotalStartedThreadCount(), threads.getDaemonThreadCount(), threads.getPeakThreadCount()));
          
         // cpu 모니터링 
         OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
         double systemLoadAverage = operatingSystemMXBean.getSystemLoadAverage();
         System.out.println(String.format("[DEAMON-MONITOR] CPU INFO:%s", systemLoadAverage));
          
         // 메모리 모니터링 
         MemoryMXBean memBean = ManagementFactory.getMemoryMXBean() ;
         MemoryUsage heap = memBean.getHeapMemoryUsage();
         MemoryUsage nonHeap = memBean.getNonHeapMemoryUsage();
         logger.info(String.format("[DEAMON-MONITOR] Heap    : Init: %d, Used: %d, Committed: %d, Max.: %d", heap.getInit(), heap.getUsed(), heap.getCommitted(), heap.getMax()));
         logger.info(String.format("[DEAMON-MONITOR] Non-Heap: Init: %d, Used: %d, Committed: %d, Max.: %d", nonHeap.getInit(), nonHeap.getUsed(), nonHeap.getCommitted(), nonHeap.getMax()));
         logger.info(String.format("[DEAMON-MONITOR] ##########################################################################################"));
         
    }
    
    @Scheduled(fixedRate = 5000)
    @Override
    public void recentMatchHistory() {
    	
    	try {
			betmanRepository.getRecentMatchHistory("http://www.betman.co.kr/sportsMatchRecord.so?method=inquireMatchRecord&item=BK&league=BK001&id=1&seq=&teamId1=35&teamId2=10&isToto=&viewType=recent");
		} catch (IOException e) {
			e.printStackTrace();
		}

    }
    
    /**
	 * 프로토 승부식 수집 스케줄러
	 */
    @Scheduled(fixedRate = 5000)
    @Override
    public void recentMatchProtoHistory() {
    	
    	try {
    		logger.info(String.format("[JUNIZA-MONITOR] ##########################################################################################"));
    		logger.info(String.format("[JUNIZA-MONITOR] 수집 스케줄러 시작"));
			betmanRepository.getRecentMatchProtoHistory("http://www.betman.co.kr/gameInfoMain.so?gameId=G101&gameRound=150005");
			logger.info(String.format("[JUNIZA-MONITOR] 수집 스케줄러 끝"));
			logger.info(String.format("[JUNIZA-MONITOR] ##########################################################################################"));
		} catch (IOException e) {
			e.printStackTrace();
		}

    }

}
