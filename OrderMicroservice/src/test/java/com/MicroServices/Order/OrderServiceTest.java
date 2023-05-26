package com.MicroServices.Order;

import com.MicroServices.Order.DTO.OrderDTO;
import com.MicroServices.Order.Model.Order;
import com.MicroServices.Order.Repository.OrderRepository;
import com.MicroServices.Order.Service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository OrderRepository;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private OrderService OrderService;

    @Test
    void testGetAllOrders() {
        List<Order> Orders = Arrays.asList(
                new Order(),
                new Order()
        );
        Mockito.when(OrderRepository.findAll()).thenReturn(Orders);
        List<OrderDTO> result = OrderService.getAllOrders();
        List<OrderDTO> OrderDTOS = Orders.stream().map(prc -> modelMapper.map(prc, OrderDTO.class)).collect(Collectors.toList());
        assertEquals(OrderDTOS, result);
        Mockito.verify(OrderRepository).findAll();
    }

    @Test
    void testAddOrder() {
        Order Order = new Order();
        Mockito.when(OrderRepository.save(Mockito.any(Order.class))).thenReturn(Order);
        Order result = OrderService.addOrder(Order);
        assertEquals(Order, result);
        Mockito.verify(OrderRepository).save(Mockito.any(Order.class));
    }

    @Test
    void testGetOrderById() {
        Long OrderId = 1L;
        Order Order = new Order();
        Mockito.when(OrderRepository.findById(OrderId)).thenReturn(Optional.of(Order));
        OrderDTO result = OrderService.getOrderById(OrderId);
        OrderDTO OrderDTO = modelMapper.map(Order, OrderDTO.class);
        assertEquals(OrderDTO, result);
        Mockito.verify(OrderRepository).findById(OrderId);
    }

    @Test
    void testDeleteOrderById() {
        Long OrderId = 1L;
        OrderService.deleteOrderById(OrderId);
        Mockito.verify(OrderRepository).deleteById(OrderId);
    }

    @Test
    void testUpdateOrder() throws ChangeSetPersister.NotFoundException {
        Long OrderId = 1L;
        Order originalOrder = new Order();
        Order updatedOrder = new Order();
        Mockito.when(OrderRepository.findById(OrderId)).thenReturn(Optional.of(originalOrder));
        Mockito.when(OrderRepository.save(Mockito.any(Order.class))).thenReturn(updatedOrder);
        OrderDTO result = OrderService.updateOrder(OrderId, updatedOrder);
        OrderDTO OrderDTO = modelMapper.map(originalOrder, OrderDTO.class);
        assertEquals(OrderDTO, result);
        Mockito.verify(OrderRepository).findById(OrderId);
        Mockito.verify(OrderRepository).save(Mockito.any(Order.class));
    }
}
