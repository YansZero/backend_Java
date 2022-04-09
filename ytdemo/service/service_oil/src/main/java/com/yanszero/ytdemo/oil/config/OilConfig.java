package com.yanszero.ytdemo.oil.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//MapperScan 是為了讓OilSetService 中的動態方法自動生成
@MapperScan("com.yanszero.ytdemo.oil.mapper")
public class OilConfig {
}
