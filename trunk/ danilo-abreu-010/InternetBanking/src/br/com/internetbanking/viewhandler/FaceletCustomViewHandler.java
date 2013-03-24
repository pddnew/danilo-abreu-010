package br.com.internetbanking.viewhandler;

import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

import com.sun.facelets.FaceletViewHandler;

/**
 * This class was extract from http://www.gregbugaj.com/?p=164 on October 24 2012
 * <BR/>
 *  It intend to solve the Unhandle ViewExpiredException
 *  
 *  {@link http://www.gregbugaj.com/?p=164 on October 24 2012}
 *  @since October 24 2012
 *  @author  http://www.gregbugaj.com/?p=164 on October 24 2012
 * */
public class FaceletCustomViewHandler extends FaceletViewHandler{
	
	public FaceletCustomViewHandler(ViewHandler parent) {
		super(parent);
	}
	
	@Override
    public UIViewRoot restoreView(FacesContext facesContext, String viewId) {
        UIViewRoot root = super.restoreView(facesContext, viewId);
        // do not allow ViewExpiredException
        if (root == null) {
            root = createView(facesContext, "/index.jsp");
            facesContext.renderResponse();
        }
        return root;
    }
}
