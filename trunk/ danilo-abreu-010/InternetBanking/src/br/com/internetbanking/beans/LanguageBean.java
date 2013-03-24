package br.com.internetbanking.beans;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name ="lang")
@SessionScoped
public class LanguageBean implements Serializable{
	
	/**
	 * Serial VersionUID
	 */
	private static final long serialVersionUID = -7072003410120786569L;
	
	private Locale userLocale;
	
	public LanguageBean(){
		super();
		userLocale = Locale.getDefault();
	}
	
	public Locale getUserLocale() {
		return userLocale;
	}

	public void setUserLocale(Locale userLocale) {
		this.userLocale = userLocale;
	}

	public String doViewPortuguese(){		
		setUserLocale(new Locale("pt", "BR"));
		setViewLanguage();
		return null;
	}
	
	public String doViewEnglish(){ 
		setUserLocale(Locale.ENGLISH);
		setViewLanguage();
		return null;
	}
	
	private void setViewLanguage(){
		FacesContext.getCurrentInstance().getViewRoot().setLocale(getUserLocale());
	}
}
