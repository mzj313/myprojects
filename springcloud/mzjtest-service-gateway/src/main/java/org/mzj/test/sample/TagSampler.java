package org.mzj.test.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.sleuth.Sampler;
import org.springframework.cloud.sleuth.Span;

//自定义抽样收集策略
//默认情况下，Sleuth会使用PercentageBasedSampler实现的抽样策略
public class TagSampler implements Sampler{
	private static Logger logger  = LoggerFactory.getLogger(TagSampler.class); 

	//如果返回false，则该跟踪信息不被输出到后续对接的远程分析系统，如Zipkin
	@Override
	public boolean isSampled(Span span) {
		logger.info("tags:" + span.tags());
		return true;
	}

}
