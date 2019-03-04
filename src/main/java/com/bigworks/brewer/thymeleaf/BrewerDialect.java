package com.bigworks.brewer.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.bigworks.brewer.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.bigworks.brewer.thymeleaf.processor.MessageElementTagProcessor;
import com.bigworks.brewer.thymeleaf.processor.OrderElementTagProcessor;

public class BrewerDialect extends AbstractProcessorDialect{
	
	public BrewerDialect() {
		super("Bigworks Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
	}
	
	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processors = new HashSet<>();
		processors.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processors.add(new MessageElementTagProcessor(dialectPrefix));
		processors.add(new OrderElementTagProcessor(dialectPrefix));
		return processors;
	}	

}
