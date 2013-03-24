package br.com.internetbanking.validator;

import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import br.com.internetbanking.dao.AccountDAO;

@FacesValidator(value = "advantagedAccountNumberValidator")
public class AdvantagedAccountNumber implements Validator {
	
	private AccountDAO dao;
	
	public AdvantagedAccountNumber(){
		dao = new AccountDAO();
	}
	
	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {
		
		String number = (String)arg2;
		
		try {   
			
			dao.getByNumber(number);
			
		} catch (SQLException e) {			
			
			ResourceBundle bundle = ResourceBundle.getBundle("br.com.internetbanking.messages.messages",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			
			FacesMessage messages = new FacesMessage(bundle.getString("invalidAccount"));
			
			messages.setSeverity(FacesMessage.SEVERITY_INFO);
			
			throw new ValidatorException(messages);
		}
	}
}