package _02;

import java.io.Serializable;
/*
 ID Class必須:
 1. 實作java.io.Serializable介面
 2. 定義預設建構子
 3. 定義複合主鍵的所有組成欄位，並為這些欄位準備Getter/Setter
 4. Override hashCode()與equals()方法 
 */
public class AccountId implements Serializable {
	private static final long serialVersionUID = 1L;

	private String accountNo;

    private String accountType;

    public AccountId() {
	}

	public AccountId(String accountNo, String accountType) {
        this.accountNo = accountNo;
        this.accountType = accountType;
    }

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNo == null) ? 0 : accountNo.hashCode());
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AccountId))
			return false;
		AccountId other = (AccountId) obj;
		if (accountNo == null) {
			if (other.accountNo != null)
				return false;
		} else if (!accountNo.equals(other.accountNo))
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		return true;
	}

}