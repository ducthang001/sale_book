package ptithcm.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import ptithcm.controller.AccountController;
import ptithcm.serviceimpl.AccountServiceImpl;
public class LoggerInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	AccountServiceImpl accountServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		String url = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getRequestURI();
		session.setAttribute("url_forward", url);
		if(AccountController.getUser() != null && accountServiceImpl.checkIDandUsernameExist(AccountController.getUser().getUsername(), AccountController.getUser().getUser_id()) == false) {
			response.sendRedirect(request.getContextPath() + "/account/logout.htm");
			return false;
		}
		if(session != null) {
			System.out.println("------------");
			System.out.println("username = "+session.getAttribute("username"));
			System.out.println("role = "+session.getAttribute("role"));
			System.out.println("------------");
			if(session.getAttribute("username") != null && AccountController.getUser() != null && session.getAttribute("role") != null && (int) session.getAttribute("role") == 1) {
				return true;
			}
			
			if(session.getAttribute("username") != null && AccountController.getUser() != null && session.getAttribute("role") != null && (int) session.getAttribute("role") == 0) {
				response.sendRedirect(request.getContextPath() + "/account/info.htm");
				return false;
			}
		}
		response.sendRedirect(request.getContextPath() + "/account/login.htm");
		return false;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
}
