package com.yanszero.pj04;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yanszero.pj04.entity.User;
import com.yanszero.pj04.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class Pj04ApplicationTests {

    // 自動注入功能
    @Autowired
    private UserMapper userMapper;

    @Test
    public void findAll() {

        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加
    @Test
    public void testAdd() {
        User user = new User();
        user.setName("李四");
        user.setAge(20);
        user.setEmail("8787@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }

    //修改
    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1512478092018302978L);
        user.setName("failMaerio");
        user.setAge(30);
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    @Test
    public void testOptimisticLocker() {
        // 根據ID先查詢
        User user = userMapper.selectById(1512488097824002050L);
        user.setName("張三");
        //樂觀鎖不用自己加1
       // user.setVersion(user.getVersion()+1);
        int count = userMapper.updateById(user);
        System.out.println(count);
    }

    //簡單條件查詢
    @Test
    public void testSelect2(){
        Map<String,Object> columnMap= new HashMap<>();
        columnMap.put("name","Jack");
        columnMap.put("age",20);
        List<User> users = userMapper.selectByMap(columnMap);
        System.out.println(users);
    }


    // 多個ID查詢
    @Test
    public void testSelect1(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        System.out.println(users);
    }

    //分页查询
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1,3);
        Page<User> userPage = userMapper.selectPage(page,null);
        //返回对象得到分页所有数据
        long pages= userPage.getPages(); //總頁數
        long current = userPage.getCurrent(); //當前頁
        List<User> records= userPage.getRecords();//查询数据集合
        long total = userPage.getTotal(); //总记录数
        boolean hasNext = userPage.hasNext();  //下一页
        boolean hasPrevious = userPage.hasPrevious(); //上一页

        System.out.println(pages);
        System.out.println(current);
        System.out.println(records);
        System.out.println(total);
        System.out.println(hasNext);

    }

    @Test
    public void testSelectMapsPage() {
        //Page不需要泛型
        Page<Map<String, Object>> page = new Page<>(1, 5);
        Page<Map<String, Object>> pageParam = userMapper.selectMapsPage(page, null);
        List<Map<String, Object>> records = pageParam.getRecords();
        records.forEach(System.out::println);
        System.out.println(pageParam.getCurrent());
        System.out.println(pageParam.getPages());
        System.out.println(pageParam.getSize());
        System.out.println(pageParam.getTotal());
        System.out.println(pageParam.hasNext());
        System.out.println(pageParam.hasPrevious());
    }


    // 刪除類
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(1512488097824002050L);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchIds() {
        int result = userMapper.deleteBatchIds(Arrays.asList(8, 9, 10));
        System.out.println(result);
    }

    @Test
    public void testDeleteByMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "Jone");
        map.put("age", 18);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    @Test
    public void testLogicDeleteSelect() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 條件查詢
    // 注意：seletOne()返回的是一条实体记录，当出现多条时会报错
    @Test
    public void testSelectOne() {
        QueryWrapper<User>queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "Tom");
        User user = userMapper.selectOne(queryWrapper);//只能返回一条记录，多余一条则抛出异常
        System.out.println(user);
    }

    @Test
    public void testSelectMaps() {
        QueryWrapper<User>queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age")
                    .like("name", "e")
                    .likeRight("email", "5");
        List<Map<String, Object>>maps = userMapper.selectMaps(queryWrapper);//返回值是Map列表
        maps.forEach(System.out::println);
    }

    @Test
    public void  testQuerySelect(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // ge : >=
        //queryWrapper.ge("age",21);
        // eq : =
        //queryWrapper.eq("name","Tom");
        // between, not between :
        //queryWrapper.between("age",21,25);
        //queryWrapper.notBetween("age",10,20)

        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }
    @Test
    public void testQuery() {
        // ge、gt、le、lt、isNull、isNotNull
        // 查出後刪除
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("name")
                    .ge("age", 12)
                    .isNotNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("delete return count = " + result);
    }

}
