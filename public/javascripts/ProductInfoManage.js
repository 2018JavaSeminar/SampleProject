
function exec(procDiv) {
	var fm = document.productInfoManageForm;
	if (procDiv == 0){
		fm.action = "/thymeleaf/product/regist";
	} else if (procDiv == 1) {
		fm.action = "/thymeleaf/product/update";
	} else if (procDiv == 2) {
		fm.action = "/thymeleaf/product/delete";
	}
	fm.submit();
}