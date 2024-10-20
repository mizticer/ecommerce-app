package com.wojcik.ecommerce.service;

import com.wojcik.ecommerce.model.OrderLine;
import com.wojcik.ecommerce.model.command.OrderLineCommand;
import com.wojcik.ecommerce.model.dto.OrderLineDto;
import com.wojcik.ecommerce.model.mapper.OrderLineMapper;
import com.wojcik.ecommerce.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public OrderLine save(OrderLineCommand orderLineCommand) {
        OrderLine orderLine = orderLineMapper.toEntity(orderLineCommand);
        return orderLineRepository.save(orderLine);
    }

    public List<OrderLineDto> findByOrderId(Long id) {
        return orderLineRepository.findAllByOrderId(id)
                .stream()
                .map(orderLineMapper::toOrderLineDto)
                .toList();
    }
}
