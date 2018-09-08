package logic.sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dao.find.FindProductEntityDAO;
import models.entity.ProductEntityDTO;
import play.libs.Json;

public class ProductListLogic {

	@Inject
	FindProductEntityDAO dao;

	/**
	 * 検索処理を行うためのロジッククラス
	 *
	 * @param word
	 * @return
	 */
	public List<ProductEntityDTO> search(String word) {

		//検索処理
		JsonNode json;
		if (StringUtils.isEmpty(word)) {
			json = Json.toJson(dao.findProductInfoListAll());
		} else {
			json = Json.toJson(dao.findProductInfoListByKeyWord(word));
		}
		List<ProductEntityDTO> list = new ArrayList<ProductEntityDTO>();

		// 返却型変換
		ObjectMapper mapper = new ObjectMapper();
		try {
			list = mapper.readValue(json.toString(), new TypeReference<List<ProductEntityDTO>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return list;
	}

}
