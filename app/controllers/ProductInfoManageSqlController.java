package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.commons.lang3.StringUtils;

import common.SampleEnum;
import logic.ProductInfoManageSqlLogic;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import views.form.ProductInfoManageForm;

@Singleton
public class ProductInfoManageSqlController extends Controller {

	private Form<ProductInfoManageForm> forms;

	@Inject
	private ProductInfoManageSqlLogic logic;

	@Inject
	public ProductInfoManageSqlController(FormFactory factory) {
		this.forms = factory.form(ProductInfoManageForm.class);
	}

	/**
	 * 初期表示処理
	 *
	 * @return 画面構成要素
	 */
	public Result init() {
		ProductInfoManageForm form = new ProductInfoManageForm();
		return ok(views.html.productInfoManageSql.render(forms.fill(form), false, null));
	}

	public Result edit(String prodNo) {

		// 商品番号が未設定の場合は、新規登録画面にリダイレクト
		if (StringUtils.isBlank(prodNo)) {
			return redirect(routes.ProductInfoManageSqlController.init());
		}

		return ok(views.html.productInfoManageSql.render(forms.fill(logic.searchByKey(prodNo)), true, null));
	}

	@Transactional
	public Result create() {
		return execute(false, 0);
	}

	@Transactional
	public Result update(String prodNo) {
		return execute(true, 1);
	}

	@Transactional
	public Result delete(String prodNo) {
		int result = logic.delete(prodNo);
		if (result == SampleEnum.Result.STATUS_ERROR.getStatus()) {
			return badRequest("delete error");
		}
		return redirect(routes.ProductListSqlController.index());
	}

	private Result execute(boolean isEdit, int procDiv) {
		Form<ProductInfoManageForm> reqForm = forms.bindFromRequest();

		if (reqForm.hasErrors()) {
			return badRequest(views.html.productInfoManageSql.render(reqForm, isEdit, null));
		}
		logic.insertOrUpdate(reqForm.get(), procDiv);
		return ok(views.html.productInfoManageSql.render(forms.fill(reqForm.get()), true, "保存しました"));

	}

}
