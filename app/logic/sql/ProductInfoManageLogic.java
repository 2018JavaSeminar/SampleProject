package logic.sql;

import java.io.IOException;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.SampleEnum;
import dao.del.DelProductEntityDAO;
import dao.find.FindProductEntityDAO;
import dao.modify.ModProductEntityDAO;
import dao.registry.RegProductEntityDAO;
import models.entity.ProductEntityDTO;
import play.libs.Json;
import views.form.ProductInfoManageForm;

public class ProductInfoManageLogic {

	@Inject
	FindProductEntityDAO find;
	@Inject
	RegProductEntityDAO reg;
	@Inject
	ModProductEntityDAO mod;
	@Inject
	DelProductEntityDAO del;

	public void insertOrUpdate(ProductInfoManageForm form, int procDiv) {

		if (procDiv == SampleEnum.ProcDiv.INS.getProcDiv()) {
			reg.regProductInfo(form);
		} else {
			mod.modProductInfo(form);
		}
	}

	public ProductInfoManageForm searchByKey(String prodNo) {

		JsonNode json;
		json = Json.toJson(find.findProductInfoByKey(prodNo));

		// 返却型変換
		ObjectMapper mapper = new ObjectMapper();
		try {
			return convertEntity2Form(mapper.readValue(json.toString(), ProductEntityDTO.class));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ProductInfoManageForm();
	}

	public int delete(String prodNo) {

		if (StringUtils.isBlank(prodNo)) {
			System.out.println("not key delete error:" + prodNo);
			return SampleEnum.Result.STATUS_ERROR.getStatus();
		}

		JsonNode json;
		json = Json.toJson(find.findProductInfoByKey(prodNo));

		// 返却型変換
		ObjectMapper mapper = new ObjectMapper();
		ProductEntityDTO dto;
		try {
			dto = mapper.readValue(json.toString(), ProductEntityDTO.class);
		} catch (IOException e) {
			e.printStackTrace();
			return SampleEnum.Result.STATUS_ERROR.getStatus();
		}
		if (StringUtils.isBlank(dto.getProdNo())) {
			System.out.println("no data:" + prodNo);
			return SampleEnum.Result.STATUS_ERROR.getStatus();
		}

		del.delProductInfo(prodNo);

		return SampleEnum.Result.STATUS_SUCCESS.getStatus();

	}

	/**
	 * Entityの値をFormに詰め替えます
	 *
	 * @param entity
	 * @return
	 */
	private ProductInfoManageForm convertEntity2Form(ProductEntityDTO entity) {
		ProductInfoManageForm form = new ProductInfoManageForm();
		form.setProdNo(entity.getProdNo());
		form.setProdName(entity.getProdName());
		form.setPrice(entity.getPrice());
		form.setCaption(entity.getCaption());
		return form;
	}

}
