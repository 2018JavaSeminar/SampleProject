package dao.del;

import com.avaje.ebean.Ebean;

import dao.DaoUtil;

public class DelProductEntityDAO {

	/**
	 * 主キー検索
	 * @param word キーワード
	 * @return 検索結果
	 */
	public int delProductInfo(String prodNo) {

		return DaoUtil.executeSql(
				Ebean.createSqlUpdate(
						DaoUtil.getSql(DaoUtil.getBasePath() + "/delete/" + DaoUtil.getMethodName() + ".sql"))
						.setParameter("prodNo", prodNo));
	}

}
