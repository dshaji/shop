package com.assessment.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.assessment.shop.dto.GoodsDTO;
import com.assessment.shop.dto.OrderDTO;
import com.assessment.shop.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping(value = "/getOrder")
	public ResponseEntity<OrderDTO> getOrder(GoodsDTO goodsDTO) {
		OrderDTO orderDTO = orderService.getOrderSummary(goodsDTO);
		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/getAllOrders")
	public ResponseEntity<List<OrderDTO>> getAllOrders() {
		List<OrderDTO> orderList = orderService.getAllOrders();
		return new ResponseEntity<>(orderList, HttpStatus.OK);
	}

	@PostMapping(value = "/getOrderById")
	public ResponseEntity<OrderDTO> getOrderById(@RequestParam Integer id) {
		OrderDTO orderDTO = orderService.getOrderById(id);
		return new ResponseEntity<>(orderDTO, HttpStatus.OK);
	}

	@PostMapping(value = "/offer")
	public ResponseEntity<OrderDTO> getOfferOrder(GoodsDTO goodsDTO) {
		OrderDTO orderDTO = orderService.getOffer(goodsDTO);
		return new ResponseEntity<OrderDTO>(orderDTO, HttpStatus.OK);
	}

}
