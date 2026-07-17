package com.pongs.order_service.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.pongs.order_service.model.entity.Order;

@Mapper
public interface OrderMapper {
    void insert(Order order);

    Order findById(Long id);

    void updateStatus(@Param("id") Long id, @Param("status") String status);
}
