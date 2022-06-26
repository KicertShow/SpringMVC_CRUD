package _01.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import _01.model.CustomerBean;
import _01.service.CustomerService;
import _01.validate.CustomerValidator;

@Controller
@RequestMapping("/_01_customer")
public class CustomerSaveUpdateController {

	ServletContext context;

	CustomerService service;
	
	@Autowired
	public CustomerSaveUpdateController(ServletContext context, CustomerService service) {
		this.context = context;
		this.service = service;
	}
	
	@GetMapping("/insertCustomer")
	public String showCustomerForm(Model model) {
//		System.out.println("1. 本方法送出新增Customer資料的空白表單");
//		System.out.println(model);
		return "_01_customer/CustomerForm";
	}
	
	@PostMapping("/insertCustomer")
	public String insertCustomerData(
		@ModelAttribute CustomerBean bean 
		, BindingResult bindingResult 
		) {
		new CustomerValidator().validate(bean, bindingResult);
		System.out.println("新增會員: " + bean);
		    
		if (bindingResult.hasErrors()) {
			System.out.println("======================");
			List<ObjectError> list = bindingResult.getAllErrors();
			for(ObjectError error : list) {
				System.out.println("有錯誤：" + error);
			}
			System.out.println("======================");
			return "_01_customer/CustomerForm";
		}

		System.out.println("bean==>" + bean);
//		if (bean.getCustomerId() != null ) {
//			service.updateCustomer(bean);	
//		} 
		bean.setRegisterTime(new Timestamp(System.currentTimeMillis()));
		service.save(bean);
		return "redirect:/_01_customer/customers";
		                 
	}
	
	@PostMapping("/modifyCustomer/{id2}")
	public String modifyCustomerData2(
			@ModelAttribute("customerBean") CustomerBean bean 
			, BindingResult bindingResult 
			) {
			new CustomerValidator().validate(bean, bindingResult);
			System.out.println("修改會員(PUT, 11-05): " + bean);
			    
			if (bindingResult.hasErrors()) {
//				List<ObjectError> list = bindingResult.getAllErrors();
//				for(ObjectError error : list) {
//					System.out.println(error);
//				}
//				System.out.println("當表單資料有誤時，bean==>" + bean);
				return "_01_customer/EditCustomerForm";

			}
			service.updateCustomer(bean);	
			return "redirect:/_01_customer/customers";
		}
	
	// 
	
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
	
	@ModelAttribute
	public CustomerBean editCustomerBean(
			@RequestParam(value="customerId", required = false) Integer id) {
		CustomerBean cbean = new CustomerBean();
		if (id != null) {
			cbean = service.getCustomerById(id);
			System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，讀到物件:" + cbean);
		} else {
			cbean = new CustomerBean();
			cbean.setName("小明");
			cbean.setBirthday(java.sql.Date.valueOf("1980-2-1"));
			cbean.setTotalPayment(0.001);
			System.out.println("在@ModelAttribute修飾的方法 getCustomerBean()中，無法讀取物件:" + cbean);
		}
		return cbean;
	}
}
