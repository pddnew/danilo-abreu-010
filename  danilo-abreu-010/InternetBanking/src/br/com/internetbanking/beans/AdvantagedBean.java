package br.com.internetbanking.beans;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.internetbanking.exception.ServiceException;
import br.com.internetbanking.model.Account;
import br.com.internetbanking.model.AdvantagedAccount;
import br.com.internetbanking.service.ServiceAdvantaged;

@ManagedBean(name = "advantaged")
@RequestScoped
public class AdvantagedBean {
	
	private AdvantagedAccount advantaged;
	
	public AdvantagedBean(){
		super();
		advantaged = new AdvantagedAccount();
	}
	
	public String save(){
		ServiceAdvantaged service = ServiceAdvantaged.getInstance();
		
		Map<String, Object> params = FacesContext.getCurrentInstance()
										.getExternalContext().getRequestMap();
		
		Account account = (Account)params.get("account");
		
		advantaged.setAccount(account);
		
		try {
			service.save(advantaged);
		} catch (ServiceException e) {
			return "failed";			
		}
		return "success";
	}
	
	
	public AdvantagedAccount getAdvantaged() {
		return advantaged;
	}


	public void setAdvantaged(AdvantagedAccount advantaged) {
		this.advantaged = advantaged;
	}	
}
