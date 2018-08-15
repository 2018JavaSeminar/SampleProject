package dao.modify;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.avaje.ebean.Ebean;

import dao.DaoUtil;
import views.form.ProductInfoManageForm;

public class ModProductEntityDAO {

	/**
	 * 更新処理
	 * @return 検索結果
	 */
	public int modProductInfo(ProductInfoManageForm form) {

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sysDate = sf.format(date);

		// 更新処理実行
		return DaoUtil.executeSql(
				Ebean.createSqlUpdate(
						DaoUtil.getSql(DaoUtil.getBasePath() + "/modify/" + DaoUtil.getMethodName() + ".sql"))
						.setParameter("prodNo", form.getProdNo()).setParameter("prodName", form.getProdName())
						.setParameter("price", form.getPrice()).setParameter("caption", form.getCaption())
						.setParameter("sysDate", sysDate));
	}

}
