package com.example.myseckill.Mapper;

import com.example.myseckill.Vo.GoodsVo;
import com.example.myseckill.bean.Goods;
import com.example.myseckill.bean.SeckillGoods;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface GoodsMapper {
    @Select("select g.*,sg.stock_count,sg.start_date,sg,end_date,sg.seckill_price,sg.version from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();
    @Select("select g.*,sg.stock_count,sg.start_date,sg,end_date,sg.seckill_price,sg.version from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id where g.id=#{goodsId}")
    public GoodsVo getGoodsVobyGoodsId(@Param("goodsId") long goodsId);
    @Update("update sk_goods_seckill set stock_count = stock_count - 1,version = version+1 where goods_id = #{goodsId} and stock_count > 0 and version = #{version}")
    public int reduceStockByVersion(SeckillGoods seckillGoods);
    @Select("select version from sk_goods_seckill where goods_id = #{goodsId}")
    public int getVersionByGoodsId(@Param("goodsId") long goodsId);
}
