package com.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ItemsDAO;
import com.entity.Cart;
import com.entity.Items;

/**
 * Servlet implementation class CarServlet
 */
@WebServlet(name="CartServlet" ,urlPatterns={"/CartServlet"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private String action;//表示购物车动作：add,show,delete
	private ItemsDAO idao = new ItemsDAO();//商品业务逻辑类对象
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		
		//PrintWriter out = response.getWriter();
		if(request.getParameter("action")!=null){
			this.action = request.getParameter("action");
			
			//添加商品进购物车
			if(action.equals("add")) {
				if(addToCart(request,response)){
					request.getRequestDispatcher("/success.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/failure.jsp").forward(request, response);
				}
			}
			
			//显示购物车
			if(action.equals("show")){
				request.getRequestDispatcher("/cart.jsp").forward(request, response);
			}
			
			//如果是执行删除购物车中的商品
			if(action.equals("delete")) {
				if(deleteFromCart(request,response)){
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}else{
					request.getRequestDispatcher("/cart.jsp").forward(request, response);
				}
			}
		}
		
	}

	//添加商品进购物车的方法
	private boolean addToCart(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		String number = request.getParameter("num");
		Items item = idao.getItemsById(Integer.parseInt(id));
		
		//是否是第一次给购物车添加商品，需要给session中创建一个新的购物车对象
		if(request.getSession().getAttribute("cart") == null){
			Cart cart = new Cart();
			request.getSession().setAttribute("cart",cart);
		}
		
		//不是第一次添加进购物车
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		if(cart.addGoodsInCart(item, Integer.parseInt(number))){
			return true;
		}else{
			return false;
		}
		
	}
	
	//从购物车中删除商品
	private boolean deleteFromCart(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		Cart cart = (Cart)request.getSession().getAttribute("cart");
	    Items item = idao.getItemsById(Integer.parseInt(id));
	    
	    if(cart.removeGoodsFromCart(item)){
	    	return true;
	    }else{
	    	return false;
	    }
	}

}
