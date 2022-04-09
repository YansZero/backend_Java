package com.yanszero.ytdemo.oil.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yanszero.ytdemo.model.oil.OilSet;
import com.yanszero.ytdemo.oil.mapper.OilSetMapper;
import com.yanszero.ytdemo.oil.service.OilSetService;
import org.springframework.stereotype.Service;

@Service
public class OilSetServiceImpl extends ServiceImpl<OilSetMapper, OilSet> implements OilSetService {

    // 因為 MP中 ServiceImpl 有幫忙注入了 @Autowired baseMapper 所以不用寫

}
