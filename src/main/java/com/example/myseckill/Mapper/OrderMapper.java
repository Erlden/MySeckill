package com.example.myseckill.Mapper;

import com.example.myseckill.bean.OrderInfo;
import com.example.myseckill.bean.SeckillOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface OrderMapper {
    @Select("select * from sk_order where user_id = #{userId} and goods_id = #{goodsId}")
    public SeckillOrder getOrderByUserIdGoodsId(@Param("userId") long userId,@Param("goodsId") long goodsId);

    @Insert("insert into sk_order_info(user_id,goods_id,goods_name,good_count,goods_price.order_channel,status,create_date)values("+"#{userId},#{goodsId},#{goodsName},#{goodsCount},#{goodsPrice},#{orderChannel},#{status},#{createDate})")
    @SelectKey(keyColumn = "id",keyProperty = "id",resultType = long.class,before = false,statement = "select last_insert_id()")
    public long insert(OrderInfo orderInfo);
    @Insert("insert into sk_order(user_id,goods_id,order_id)values(#{userId,#{goodsId},#{orderId}})")
    public int insertSeckillOrder(SeckillOrder seckillOrder);

    @Select("select * from sk_order_info where id = #{orderId}")
    public OrderInfo getOrderById(@Param("orderId")long orderId);
}
