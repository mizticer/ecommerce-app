package com.wojcik.ecommerce.service;

import com.wojcik.ecommerce.client.CustomerClient;
import com.wojcik.ecommerce.client.PaymentClient;
import com.wojcik.ecommerce.client.ProductClient;
import com.wojcik.ecommerce.exception.NotFoundException;
import com.wojcik.ecommerce.kafka.OrderProducer;
import com.wojcik.ecommerce.model.Order;
import com.wojcik.ecommerce.model.OrderConfirmation;
import com.wojcik.ecommerce.model.command.OrderCommand;
import com.wojcik.ecommerce.model.command.OrderLineCommand;
import com.wojcik.ecommerce.model.command.PaymentCommand;
import com.wojcik.ecommerce.model.command.PurchaseCommand;
import com.wojcik.ecommerce.model.dto.Customer;
import com.wojcik.ecommerce.model.dto.OrderDto;
import com.wojcik.ecommerce.model.dto.Product;
import com.wojcik.ecommerce.model.mapper.OrderMapper;
import com.wojcik.ecommerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    @Transactional
    public OrderDto createOrder(OrderCommand command) {
        Customer customer = customerClient.findCustomerById(command.getCustomerId())
                .orElseThrow(() -> new NotFoundException(MessageFormat
                        .format("Cannot create order:: Customer with id={0} not found", command.getCustomerId())));

        List<Product> purchasedProducts = productClient.purchaseProducts(command.getProducts());
        Order order = orderRepository.save(orderMapper.toEntity(command));

        for (PurchaseCommand purchaseCommand : command.getProducts()) {
            orderLineService.save(
                    new OrderLineCommand(
                            null,
                            order.getId(),
                            purchaseCommand.getProductId(),
                            purchaseCommand.getQuantity()
                    ));
        }

        paymentClient.requestOrderPayment(PaymentCommand.builder()
                .orderId(order.getId())
                .amount(command.getAmount())
                .orderReference(order.getReference())
                .paymentMethod(command.getPaymentMethod())
                .customer(customer)
                .build());

        orderProducer.sendOrderConfirmation(
                OrderConfirmation.builder()
                        .orderReference(command.getReference())
                        .paymentMethod(command.getPaymentMethod())
                        .products(purchasedProducts)
                        .totalAmount(command.getAmount())
                        .customer(customer)
                        .build());

        return orderMapper.toDto(order);
    }

    @Transactional(readOnly = true)
    public List<OrderDto> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderDto findById(long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(MessageFormat
                        .format("Cannot find order:: Order with id={0} not found", id)));
        return orderMapper.toDto(order);
    }
}
