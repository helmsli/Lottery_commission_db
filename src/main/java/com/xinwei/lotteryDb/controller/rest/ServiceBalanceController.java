package com.xinwei.lotteryDb.controller.rest;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinwei.lotteryDb.Const.UserBalanceApplyConst;
import com.xinwei.lotteryDb.domain.UserBalanceApply;
import com.xinwei.lotteryDb.domain.UserBalanceApplyResult;
import com.xinwei.lotteryDb.service.ServiceUserBlance;

@RestController
@RequestMapping("/serviceUserBlance")
public class ServiceBalanceController {
	@Resource(name = "serviceUserBalanceImpl")
	private ServiceUserBlance serviceUserBlance;

	@PostMapping(value = "/updateBalance")
	public UserBalanceApplyResult updateBlance(@RequestBody UpdateBalRequest updateBalRequest) {

		try {
			UserBalanceApplyResult userBalanceApplyResult = serviceUserBlance
					.updateUserBalance(updateBalRequest.getNowUseBalance(), updateBalRequest.getUserBalanceApply());
			return userBalanceApplyResult;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			String errorStr = errors.toString();
			UserBalanceApplyResult userBalanceApplyResult = new UserBalanceApplyResult();
			userBalanceApplyResult.setErrorMessage(errorStr);
			userBalanceApplyResult.setResult(UserBalanceApplyConst.RESULT_FAILURE);
			return userBalanceApplyResult;
		}
	}

	@PostMapping(value = "/queryTransaction")
	public UserBalanceApplyResult queryTransaction(@RequestBody UserBalanceApply userBalanceApply) {
		UserBalanceApplyResult result = serviceUserBlance.queryTransaction(userBalanceApply);
		return result;
	}

}
