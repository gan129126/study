package com.jt.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import com.jt.web.thread.UserThreadLocal;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;

	@RequestMapping("/show")
	public String findCartByUserId(Model model) {
		Long userId = UserThreadLocal.get().getId();
		//获取购物车列表信息
		List<Cart> cartList = cartService.findCartByUserId(userId);
		model.addAttribute("cartList", cartList);
		return "cart";	//返回购物车页面
	}
	
	@RequestMapping("/add/{itemId}")
	public String saveCart(@PathVariable Long itemId,Cart cart) {
		
		Long userId = UserThreadLocal.get().getId();
		cart.setUserId(userId);
		cart.setItemId(itemId);
		cartService.saveCart(cart);
		//跳转到购物车列表页面
		return "redirect:/cart/show.html";
	}
	
	@RequestMapping("/update/num/{itemId}/{num}")
	@ResponseBody
	public SysResult updateCartNum(
			@PathVariable Long itemId,
			@PathVariable Integer num) {
		try {
			Long userId = UserThreadLocal.get().getId();
			Cart cart = new Cart();
			cart.setItemId(itemId);
			cart.setUserId(userId);
			cart.setNum(num);
			cartService.updateCartNum(cart);
			return SysResult.oK();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SysResult.build(201, "购物车数量修改失败");
	}
	
}