package controllers.thymeleaf;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import logic.sql.ProductListLogic;
import models.entity.ProductEntityDTO;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import thymeleaf.PlayThymeleafEngine;
import views.form.ProductListDisplayInfo;
import views.form.ProductListForm;

@Singleton
public class ProductListController extends Controller {

	private Form<ProductListForm> forms;

	@Inject
	ProductListLogic logic;

	@Inject
	private PlayThymeleafEngine engine;

	@Inject
	public ProductListController(FormFactory factory) {
		this.forms = factory.form(ProductListForm.class);
	}

	public Result index() {
		Form<ProductListForm> reqForm = forms.bindFromRequest();

		if (reqForm.hasErrors()) {
			return badRequest(engine.process("thymeleaf/productList"));
		}

		ProductListForm form = reqForm.get();
		List<ProductEntityDTO> infoList = logic.search(form.getSearchWord());

		Http.Context context = ctx();
		context.args.put("displayInfo", setDisplayInfo(infoList));
		return ok(engine.process("thymeleaf/productList", context));
	}

	private List<ProductListDisplayInfo> setDisplayInfo(List<ProductEntityDTO> infoList) {
		List<ProductListDisplayInfo> dispInfoList = new ArrayList<>();
		infoList.forEach(info -> {
			ProductListDisplayInfo dispInfo = new ProductListDisplayInfo();
			dispInfo.setProdNo(info.getProdNo());
			dispInfo.setProdName(info.getProdName());
			dispInfo.setPrice(info.getPrice());
			dispInfo.setCaption(info.getCaption());
			dispInfo.setPrice(info.getPrice());
			dispInfo.setCaption(info.getCaption());
			dispInfo.setCreatedAt(info.getCreatedAt());
			dispInfo.setUpdatedAt(info.getUpdatedAt());
			dispInfoList.add(dispInfo);
		});
		return dispInfoList;
	}
}
