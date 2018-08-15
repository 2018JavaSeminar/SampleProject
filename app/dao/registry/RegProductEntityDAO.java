package dao.registry;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.avaje.ebean.Ebean;

import dao.DaoUtil;
import views.form.ProductInfoManageForm;

public class RegProductEntityDAO {

	/**
	 * 登録処理
	 * @return 検索結果
	 */
	public int regProductInfo(ProductInfoManageForm form) {

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sysDate = sf.format(date);

		// 登録処理実行
		return DaoUtil.executeSql(
				Ebean.createSqlUpdate(
						DaoUtil.getSql(DaoUtil.getBasePath() + "/registry/" + DaoUtil.getMethodName() + ".sql"))
						.setParameter("prodNo", form.getProdNo()).setParameter("prodName", form.getProdName())
						.setParameter("price", form.getPrice()).setParameter("caption", form.getCaption())
						.setParameter("sysDate", sysDate));
	}

}
