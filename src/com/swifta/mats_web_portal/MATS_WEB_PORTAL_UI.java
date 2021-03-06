package com.swifta.mats_web_portal;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("mats_web_portal")
//@PreserveOnRefresh

public class MATS_WEB_PORTAL_UI extends UI {
	
	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MATS_WEB_PORTAL_UI.class)
	public static class Servlet extends VaadinServlet {
	}
	
	
	
	@Override
	protected void init(VaadinRequest request) {
		new Navigator(this, this);
		getNavigator().addView(Login.LOGIN,  Login.class);
		getNavigator().addView(WorkAreax.WORK_AREA, WorkAreax.class);
		UI.getCurrent().getNavigator().addViewChangeListener(new ViewChangeListener(){

			@Override
			public boolean beforeViewChange(ViewChangeEvent event) {
				boolean isLoggedIn = UI.getCurrent().getSession().getAttribute("user") != null;
				boolean isLoginView = event.getNewView() instanceof Login;
				if(isLoggedIn && isLoginView){
					return false;
				}else if(!isLoggedIn && !isLoginView){
					getNavigator().navigateTo(Login.LOGIN);
					return false;
					
				}
				return true;
				
			}

			@Override
			public void afterViewChange(ViewChangeEvent event) {
				
				
			}
			
		});
		
	}
	
	
	
	
	
	
	
	
	


	
}