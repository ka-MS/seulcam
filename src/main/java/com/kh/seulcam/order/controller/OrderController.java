package com.kh.seulcam.order.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.seulcam.cart.domain.Cart;
import com.kh.seulcam.cart.service.CartService;
import com.kh.seulcam.member.domain.Member;
import com.kh.seulcam.order.domain.Order;
import com.kh.seulcam.order.domain.OrderProduct;
import com.kh.seulcam.order.service.OrderService;
import com.kh.seulcam.product.domain.Product;

import oracle.net.aso.i;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService oService;
	
	
	//결제
	@RequestMapping(value="/pay.kh", method=RequestMethod.GET)
	public String showpay() {
		return "order/pay";
	}
	
	//주문
	@RequestMapping(value="/order/order.kh", method=RequestMethod.GET)
	public ModelAndView order(
			ModelAndView mv
			,HttpSession session) {
		Member memberId = (Member)session.getAttribute("loginUser");
		Member member = oService.printMemberInfo(memberId);
		List<OrderProduct>oList = oService.printProductInfo(memberId);
		System.out.println(oList);
		
		if(!oList.isEmpty()) {
			List<Product>pList = new ArrayList(); 
			 for(int i=0;i<oList.size();i++) { 
				 int productNo=oList.get(i).getProductNo();
				 
				 List<Product>ppList= oService.printAllProduct(productNo);
				pList.addAll(ppList);
				 	
			  }
			 int totalPrice=0;
			 for(int i=0;i<pList.size();i++) {
				 int price=pList.get(i).getProductPrice()*oList.get(i).getOrderCount();
				 totalPrice+=price;
				 
			 }
		mv.addObject("totalPrice",totalPrice);
		mv.addObject("pList",pList);
		}
		mv.addObject("oList",oList);
		mv.addObject("member",member);
		mv.setViewName("order/orderDetail");
		return mv;
	}
	
	//주문 완료
	@RequestMapping(value="/order/finish.kh", method=RequestMethod.GET)
	public String orderFinish() {
		
		return "order/orderComplete";
	}
	
	//주문 주소 변경
	@ResponseBody
	@RequestMapping(value="/order/addressChange.kh",method=RequestMethod.POST)
	public String addressChange(
			@ModelAttribute Member member
			) {
		int result = oService.changeAddress(member);
		return"success";
		
	}
	
	//주문성공
	@ResponseBody
	@RequestMapping(value="/order/payment/complete", method=RequestMethod.POST)
	public String orderComplete(
			@ModelAttribute Order order
			) {
		int result = oService.conpleteOrder(order);
		if(result>0) {
		return"success";
		}else {
		return"fail";
			
		}
	}


}
