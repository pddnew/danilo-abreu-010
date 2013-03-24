package br.com.internetbanking.validator;

import java.util.Date;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value="expirationDateValidator")
public class ExpirationDateValidator implements Validator {

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object arg2)
			throws ValidatorException {

		Date expDate = (Date)arg2;
		
		if(expDate.after(new Date())){
			ResourceBundle bundle = ResourceBundle.getBundle("br.com.internetbanking.messages.messages",
					FacesContext.getCurrentInstance().getViewRoot().getLocale());
			
			FacesMessage messages = new FacesMessage(bundle.getString("invalidExpirationDate"));
			
			messages.setSeverity(FacesMessage.SEVERITY_INFO); 
			
			throw new ValidatorException(messages);
		}
	}

}
