package _02.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import _02.Account;

public class AccountValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Account.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Account account = (Account) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountNo", "", "帳戶編號欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "accountType", "", "帳戶類型欄不能空白");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "姓名欄位不正確");
//		ValidationUtils.rejectIfEmpty(errors, "balance", "", "餘額欄不能空白");
		if (account.getBalance() == null) {
			errors.rejectValue("balance", "typeMismatch.balance", "餘額格式錯誤");
		}
	}

}
