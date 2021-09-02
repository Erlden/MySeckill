package com.example.myseckill.service;

import com.example.myseckill.Mapper.GoodsMapper;
import com.example.myseckill.Vo.GoodsVo;
import com.example.myseckill.bean.Goods;
import com.example.myseckill.bean.SeckillGoods;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GoodsService {

    private static final int DEFAULT_MAX_RETRIES = 5;
    @Autowired
    GoodsMapper goodsMapper;

    public List<GoodsVo> listGoodsVo(){
        return goodsMapper.listGoodsVo();
    }
    public GoodsVo getGoodsVoByGoodsId(long goodsId){
        return goodsMapper.getGoodsVobyGoodsId(goodsId);
    }
    public boolean reduceStock(GoodsVo goods){
        int numAttempts=0;
        int ret=0;
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goods.getId());
        sg.setVersion(goods.getVersion());
        do {
            numAttempts++;
            try {
                sg.setVersion(goodsMapper.getVersionByGoodsId(goods.getId()));
                ret = goodsMapper.reduceStockByVersion(sg);

            }catch (Exception e){
                e.printStackTrace();
            }
            if (ret != 0){
                break;
            }
        }while (numAttempts < DEFAULT_MAX_RETRIES);
        return ret>0;
    }

}
