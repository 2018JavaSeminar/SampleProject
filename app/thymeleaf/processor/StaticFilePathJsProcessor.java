package thymeleaf.processor;

import org.thymeleaf.context.ITemplateContext;
import org.thymeleaf.engine.AttributeName;
import org.thymeleaf.model.IProcessableElementTag;
import org.thymeleaf.processor.element.AbstractAttributeTagProcessor;
import org.thymeleaf.processor.element.IElementTagStructureHandler;
import org.thymeleaf.templatemode.TemplateMode;

import common.SampleUtil;

public class StaticFilePathJsProcessor extends AbstractAttributeTagProcessor {

	private static String ATTE_NAME = "jsPath";
	private static String SET_PARAM_HREF = "src";
	private static String STATIC_FILE_PATH = "/assets/";

	public StaticFilePathJsProcessor(String dialectPrefix) {
		super(TemplateMode.HTML, dialectPrefix, null, false, ATTE_NAME, true,
				10000, true);
	}

	@Override
	protected void doProcess(ITemplateContext context, IProcessableElementTag tag, AttributeName attributeName,
			String attributeValue, IElementTagStructureHandler structureHandler) {
		structureHandler.setAttribute(SET_PARAM_HREF, getFilePath(attributeValue));

	}

	private String getFilePath(String path) {
		return SampleUtil.makeUrl(STATIC_FILE_PATH + path);
	}

}