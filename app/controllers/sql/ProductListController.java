package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import logic.ProductListSqlLogic;
import models.entity.ProductEntityDTO;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.form.ProductListDisplayInfo;
import views.form.ProductListForm;

@Singleton
public class ProductListSqlController extends Controller {

	private Form<ProductListForm> forms;
	private Form<ProductListDisplayInfo> displayInfo;

	@Inject
	ProductListSqlLogic logic;

	@Inject
	public ProductListSqlController(FormFactory factory) {
		this.forms = factory.form(ProductListForm.class);
		this.displayInfo = factory.form(ProductListDisplayInfo.class);
	}

	public Result index() {
		Form<ProductListForm> reqForm = forms.bindFromRequest();

		if (reqForm.hasErrors()) {
			return badRequest(views.html.productListSql.render(reqForm, new ArrayList<>()));
		}

		ProductListForm form = reqForm.get();
		List<ProductEntityDTO> infoList = logic.search(form.getSearchWord());
		return ok(views.html.productListSql.render(forms.fill(form), fill(setDisplayInfo(infoList))));
	}

	private List<Form<ProductListDisplayInfo>> fill(List<ProductListDisplayInfo> items) {
		return items.stream()
				.map(displayInfo::fill)
				.collect(Collectors.toList());
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
