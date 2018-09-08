package controllers.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Singleton;

import logic.basic.ProductListLogic;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import views.form.ProductListDisplayInfo;
import views.form.ProductListForm;

@Singleton
public class ProductListController extends Controller {

	private Form<ProductListForm> forms;
	private Form<ProductListDisplayInfo> displayInfo;

	@Inject
	public ProductListController(FormFactory factory) {
		this.forms = factory.form(ProductListForm.class);
		this.displayInfo = factory.form(ProductListDisplayInfo.class);
	}

	public Result index() {
		Form<ProductListForm> reqForm = forms.bindFromRequest();

		if (reqForm.hasErrors()) {
			return badRequest(views.html.basic.productList.render(reqForm, new ArrayList<>()));
		}

		ProductListForm form = reqForm.get();
		ProductListLogic logic = new ProductListLogic();
		List<ProductListDisplayInfo> infoList = logic.search(form.getSearchWord());
		return ok(views.html.basic.productList.render(forms.fill(form), fill(infoList)));
	}

	private List<Form<ProductListDisplayInfo>> fill(List<ProductListDisplayInfo> items) {
		return items.stream()
				.map(displayInfo::fill)
				.collect(Collectors.toList());
	}
}
