package com.MicroServices.Order.Service;

import com.MicroServices.Order.DTO.OrderDTO;
import com.MicroServices.Order.Model.Order;
import com.MicroServices.Order.Repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public List<OrderDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> modelMapper.map(order, OrderDTO.class)).collect(Collectors.toList());
    }

    public Order addOrder(Order order, UriComponentsBuilder uriComponentsBuilder) {
        return orderRepository.save(order);
    }

    public OrderDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(RuntimeException::new);
        return modelMapper.map(order, (Type) OrderDTO.class);
    }

    public void deleteOrderById(Long id) {
        orderRepository.deleteById(id);
    }

    public OrderDTO updateOrder(Long id, Order order) throws ChangeSetPersister.NotFoundException {
        Order orderUpdate = orderRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
        orderUpdate.setId(order.getId());
        orderUpdate.setOrderDateTime(order.getOrderDateTime());
        orderUpdate.setCustomerName(order.getCustomerName());
        orderRepository.save(orderUpdate);
        return modelMapper.map(orderUpdate, OrderDTO.class);
    }

    public Order addOrder(Order order) {
        return order;
    }
}