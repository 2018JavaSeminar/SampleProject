package controllers.thymeleaf;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;

import common.SampleConstants;
import common.SampleEnum;
import common.SampleUtil;
import logic.thymeleaf.ProductInfoManageLogic;
import play.data.Form;
import play.data.FormFactory;
import play.db.ebean.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import thymeleaf.PlayThymeleafEngine;
import views.form.ProductInfoManageDisplayInfo;
import views.form.ProductInfoManageForm;

public class ProductInfoManageController extends Controller implements SampleConstants {

	@Inject
	private ProductInfoManageLogic logic;

	@Inject
	private PlayThymeleafEngine engine;

	@Inject
	FormFactory formFactory;

	/**
	 * 初期表示処理
	 *
	 * @return 画面構成要素
	 */
	public Result init() {
		Http.Context context = ctx();
		context.args.put("displayInfo", new ProductInfoManageDisplayInfo());
		return ok(engine.process("thymeleaf/ProductInfoManage", context));
	}

	public Result edit(String prodNo) {

		// 商品番号が未設定の場合は、新規登録画面にリダイレクト
		if (StringUtils.isBlank(prodNo)) {
			return redirect(SampleUtil.makeUrl("/thymeleaf/product/info"));
		}

		// 商品番号を条件に商品情報を取得
		ProductInfoManageDisplayInfo displayInfo = logic.searchByKey(prodNo);
		Http.Context context = ctx();
		context.args.put("displayInfo", displayInfo);

		return ok(engine.process("thymeleaf/ProductInfoManage", context));
	}

	@Transactional
	public Result create() {
		return execute(false, 0);
	}

	@Transactional
	public Result update() {
		return execute(true, 1);
	}

	@Transactional
	public Result delete() {
		Form<ProductInfoManageForm> reqForm = formFactory.form(ProductInfoManageForm.class).bindFromRequest();

		int result = logic.delete(reqForm.get().getProdNo());
		if (result == SampleEnum.Result.STATUS_ERROR.getStatus()) {
			return badRequest("delete error");
		}
		return redirect(SampleUtil.makeUrl("/thymeleaf/index"));
	}

	private Result execute(boolean isEdit, int procDiv) {

		Form<ProductInfoManageForm> reqForm = formFactory.form(ProductInfoManageForm.class).bindFromRequest();
		if (reqForm.hasErrors()) {
			return badRequest(engine.process("thymeleaf/ProductInfoManage"));
		}
		logic.insertOrUpdate(reqForm.get(), procDiv);

		ProductInfoManageDisplayInfo displayInfo = logic.convertForm2Dto(reqForm.get());
		if (isEdit) {
			displayInfo.setMessage(SUCCESS_MODIFY);
		} else {
			displayInfo.setMessage(SUCCESS_REGISSTRY);
		}
		Http.Context context = ctx();
		context.args.put("displayInfo", displayInfo);
		return ok(engine.process("thymeleaf/ProductInfoManage", context));

	}

}
