package _02.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import _02.Account;
import _02.service.AccountService;

@Controller
@RequestMapping("/_02")
public class AccountController {

	ServletContext context;

	AccountService service;
	
	@Autowired
	public AccountController(ServletContext context, AccountService service) {
		this.context = context;
		this.service = service;
	}

	@GetMapping("/showAccounts")
	public String getAccounts(Model model) {
		List<Account> accounts = service.findAll();
		model.addAttribute(accounts);
		return "_02/ShowAccounts";
	}
	
	@GetMapping("/modifyAccount/{no}/{type}")
	public String editAccountForm(Model model, 
			@PathVariable String no, @PathVariable String type) {
		Account account = service.findByPrimaryKey(no, type);
		model.addAttribute("account", account);
		return "_02/EditAccountForm";
	}
	
	@GetMapping("/insertAccount")
	public String showAccountForm(Model model) {
		System.out.println("1. 本方法送出新增Account資料的空白表單");
		Account bean = new Account();
		model.addAttribute("account", bean);
		return "_02/AccountForm";
	}
	
	@DeleteMapping("/modifyAccount")
	public String deleteAccount(
			@RequestParam String accountNo, 
			@RequestParam String accountType) {
		Account account = new Account();
		account.setAccountNo(accountNo);
		account.setAccountType(accountType);
		service.delete(account);	
		return "redirect:/_02/showAccounts";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// java.util.Date
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
		dateFormat.setLenient(false);
		CustomDateEditor ce = new CustomDateEditor(dateFormat, true); 
		binder.registerCustomEditor(Date.class, ce);
		// java.sql.Date		
		DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat2.setLenient(false);
		CustomDateEditor ce2 = new CustomDateEditor(dateFormat2, true); 
		binder.registerCustomEditor(java.sql.Date.class, ce2);
	}
	
	@GetMapping("/index")
	
	public String home() {
		return "_02/index";
	}
	
}
