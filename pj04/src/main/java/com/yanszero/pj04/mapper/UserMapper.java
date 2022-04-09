package com.yanszero.pj04.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yanszero.pj04.entity.User;
import org.springframework.stereotype.Repository;

//核心類
@Repository
public interface UserMapper extends BaseMapper<User> {

}
