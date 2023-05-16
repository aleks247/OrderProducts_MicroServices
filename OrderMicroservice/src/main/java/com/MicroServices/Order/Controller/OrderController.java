package com.MicroServices.Order.Controller;

import com.MicroServices.Order.DTO.OrderDTO;
import com.MicroServices.Order.Service.OrderService;
import com.MicroServices.Order.Model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDTOS = orderService.getAllOrders();
        var status = orderDTOS.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        return ResponseEntity.status(status).body(orderDTOS);
    }

    @PostMapping("/add")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, UriComponentsBuilder uriComponentsBuilder) {
        URI location = uriComponentsBuilder.path("/orders/{id}").buildAndExpand(orderService.addOrder(order, uriComponentsBuilder).getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/getOrder/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<Order> deleteOrderById(@PathVariable("id") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateOrder{id}")
    public ResponseEntity<OrderDTO> updateOrder(Long id, @RequestBody Order order) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(orderService.updateOrder(id, order));
    }
}
