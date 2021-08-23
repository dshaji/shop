package com.assessment.shop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assessment.shop.dto.GoodsDTO;
import com.assessment.shop.dto.OrderDTO;
import com.assessment.shop.model.Order;

import order.assessment.shop.repository.OrderRepository;


@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	private static final Double apple = 0.60;
	private static final Double orange = 0.25;

	public OrderDTO getOrderSummary(GoodsDTO goodsDTO) {
		Order order = new Order();
		OrderDTO orderDTO = new OrderDTO();
		order.setAppleCost(goodsDTO.getAppleCount() * apple);
		order.setOrangeCost(goodsDTO.getOrangeCount() * orange);
		order.setTotal(order.getAppleCost() + order.getOrangeCost());
		orderDTO.setAppleCount(order.getAppleCost());
		orderDTO.setOrangeCount(order.getOrangeCost());
		orderDTO.setTotal(order.getTotal());
		orderRepository.save(order);
		return orderDTO;
	}

	public List<OrderDTO> getAllOrders() {
		List<Order> orders = orderRepository.findAll();
		List<OrderDTO> orderList = new ArrayList<OrderDTO>();
		if (!orders.isEmpty()) {
			orderList = orders.stream().map(this::orderEntitytoDTO).collect(Collectors.toList());
		}
		return orderList;
	}

	public OrderDTO getOrderById(Integer id) {
		Order order = orderRepository.getById(id);
		OrderDTO orderDTO = orderEntitytoDTO(order);
		return orderDTO;
	}

	public OrderDTO getOffer(GoodsDTO goodsDTO) {
		OrderDTO orderDTO = new OrderDTO();
		Double appleCost = (goodsDTO.getAppleCount() % 2 + goodsDTO.getAppleCount() / 2) * apple;
		Double orangeCost = (goodsDTO.getOrangeCount() / 3) * 2 + (goodsDTO.getOrangeCount() % 3) * orange;
		orderDTO.setAppleCount(appleCost);
		orderDTO.setOrangeCount(orangeCost);
		Double total = appleCost + orangeCost;
		orderDTO.setTotal(total);
		return orderDTO;
	}

	public OrderDTO orderEntitytoDTO(Order order) {
		OrderDTO orderDTO = new OrderDTO();
		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setAppleCount(order.getAppleCost());
		orderDTO.setOrangeCount(order.getOrangeCost());
		orderDTO.setTotal(order.getTotal());
		return orderDTO;
	}

}
