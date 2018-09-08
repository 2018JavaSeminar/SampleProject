package thymeleaf.dialect;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;

import thymeleaf.processor.StaticFilePathCssProcessor;
import thymeleaf.processor.StaticFilePathJsProcessor;

public class PlayThymeleafDialect extends AbstractProcessorDialect {

	public PlayThymeleafDialect() {
		super("smp", "smp", 1000);
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		Set<IProcessor> processor = new HashSet<>();
		processor.add(new StaticFilePathCssProcessor(dialectPrefix));
		processor.add(new StaticFilePathJsProcessor(dialectPrefix));
		return processor;
	}

}
