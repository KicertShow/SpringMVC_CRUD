package _02.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import _02.Account;
import _02.service.AccountService;
import _02.validator.AccountValidator;

@Controller
@RequestMapping("/_02")
public class AccountInsertController {

	ServletContext context;

	AccountService service;

	@Autowired
	public AccountInsertController(ServletContext context, AccountService service) {
		this.context = context;
		this.service = service;
	}
	
	@PostMapping("/modifyAccount")
	public String modifyAccountData2(
			Account account, 
			BindingResult bindingResult 
			) {
			new AccountValidator().validate(account, bindingResult);
			System.out.println("修改Account: " + account);
			    
			if (bindingResult.hasErrors()) {
//				List<ObjectError> list = bindingResult.getAllErrors();
//				for(ObjectError error : list) {
//					System.out.println(error);
//				}
//				System.out.println("當表單資料有誤時，account==>" + account);
				
				return "_02_account/EditAccountForm";

			}
			
			service.update(account);
			return "redirect:/_02/showAccounts";
		}
	
	// 
	@PostMapping("/insertAccount")
	public String insertAccountData(
		@ModelAttribute("account") Account account 
		, BindingResult bindingResult, Model model
		) {
		String result = "";
		System.out.println("In PostMapping(\"insertAccount\"), 接收輸入資料的 Account: " + account + ", hashCode=" + account.hashCode());
		new AccountValidator().validate(account, bindingResult);
		    
		if (bindingResult.hasErrors()) {
			System.out.println("======================");
			List<ObjectError> list = bindingResult.getAllErrors();
			for(ObjectError error : list) {
				System.out.println("有錯誤：" + error);
			}
			System.out.println("======================");
			return "/_02/AccountForm";
		}
		account.setCreateTime(new Timestamp(System.currentTimeMillis()));
		try {
			service.save(account);
			result = "redirect:/_02/showAccounts";
		} 
		catch(Exception e) {
			model.addAttribute("insertErrorMessage", "帳戶編號+帳戶類型已經存在，無法新增");
			result = "_02/AccountForm";
		}
		return result;
	}
	
	@ModelAttribute("account")
	                 
	public Account editAccount(
			@PathVariable(value="accountNo", required = false) String accountNo,
			@PathVariable(value="accountType", required = false) String accountType
			
			) {
		Account account = new Account();
		if (accountNo != null) {
			account = service.findByPrimaryKey(accountNo, accountType);
			System.out.println("在@ModelAttribute修飾的方法 getAccountBean()中，讀到物件:" + account);
		} else {
			System.out.println("在@ModelAttribute修飾的方法 getAccountBean()中，無法讀取物件:" + account + ", hashCode=" + account.hashCode());
		}
		return account;
	}
}
