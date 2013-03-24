package br.com.internetbanking.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.internetbanking.model.Account;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		System.out.println(req.getRequestURI());
		
		HttpSession session  = req.getSession();
		
		Account account = (Account) session.getAttribute("account");
		String url = req.getRequestURL().toString();
		
		if(account == null && !url.contains("welcome.jsf") 
				&& !url.contains("logout") && !url.contains("index.")
				&& !url.contains("password.jsf") && !url.contains(".css") 
				&& !url.contains("javax.faces.resource"))
			((HttpServletResponse)res).sendRedirect( ((HttpServletRequest)req).getContextPath() + "/index.jsp" );
		else
			// pass the request along the filter chain
			chain.doFilter(request, response);		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
