package com.namja.collector;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.lang.management.OperatingSystemMXBean;
import java.lang.management.ThreadMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
public class SportsMetaDataCollectorServiceImpl implements SportsMetaDataCollectorService {
	
	private static Logger logger = LoggerFactory.getLogger(SportsMetaDataCollectorServiceImpl.class);
	
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

}
