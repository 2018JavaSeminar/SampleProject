package thymeleaf;

import java.util.Map;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import com.google.inject.Singleton;

import play.mvc.Http;
import play.twirl.api.Html;
import thymeleaf.dialect.PlayThymeleafDialect;

@Singleton
public class PlayThymeleafEngine extends TemplateEngine {

	/**
	 * コンストラクタ
	 */
	public PlayThymeleafEngine() {
		super();
		init();
	}

	/**
	 * 初期化処理
	 */
	private void init() {

		AbstractConfigurableTemplateResolver fResolver = new FileTemplateResolver();
		fResolver.setPrefix("app/views/");
		fResolver.setSuffix(".html");
		fResolver.setTemplateMode(TemplateMode.HTML);
		fResolver.setCacheTTLMs(60000L);
		fResolver.setCharacterEncoding("UTF-8");
		this.addTemplateResolver(fResolver);
		this.addDialect(new PlayThymeleafDialect());

	}

	public Html process(final String templatePath) {
		Context ctx = new Context();

		return Html.apply(this.process(templatePath, ctx));
	}

	public Html process(final String templatePath, final Map<String, Object> variables) {
		Context ctx = new Context();
		ctx.setVariables(variables);
		return Html.apply(this.process(templatePath, ctx));
	}

	public Html process(final String templatePath, final Http.Context hCtx) {
		Context ctx = new Context();
		ctx.setVariables(hCtx.args);
		return Html.apply(this.process(templatePath, ctx));
	}

}
