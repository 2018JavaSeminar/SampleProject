package dao.find;

import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlRow;

import dao.DaoUtil;

public class FindProductEntityDAO {

	/**
	 * 全件検索
	 * @return 検索結果
	 */
	public List<SqlRow> findProductInfoListAll() {
		return Ebean.createSqlQuery(DaoUtil.getSql(DaoUtil.getBasePath() + "/find/" + DaoUtil.getMethodName() + ".sql"))
				.findList();
	}

	/**
	 * あいまい検索
	 * @param word キーワード
	 * @return 検索結果
	 */
	public List<SqlRow> findProductInfoListKeyWord(String word) {
		return Ebean.createSqlQuery(DaoUtil.getSql(DaoUtil.getBasePath() + "/find/" + DaoUtil.getMethodName() + ".sql"))
				.setParameter("prodName", word).setParameter("caption", word).findList();
	}

	/**
	 * 主キー検索
	 * @param word キーワード
	 * @return 検索結果
	 */
	public SqlRow findProductInfoByKey(String prodNo) {

		return Ebean.createSqlQuery(DaoUtil.getSql(DaoUtil.getBasePath() + "/find/" + DaoUtil.getMethodName() + ".sql"))
				.setParameter("prodNo", prodNo).findUnique();
	}

}
