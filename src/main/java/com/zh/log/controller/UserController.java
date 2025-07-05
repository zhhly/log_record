package com.zh.log.controller;

import com.zh.log.annotation.SysLogs;
import com.zh.log.entity.User;
import com.zh.log.response.Resp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


/**
 * <p>
 * UserController类
 * </p>
 *
 * @author zh
 * @date 2025-06-19 21:24
 */
@RestController
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    // 获取请求的用户名、请求方式、访问地址、模块名称、登录ip、操作时间，记录到数据库的日志表中
    @GetMapping("/getUser/{id}")
    @SysLogs(operation = "获取用户信息", type = "INFO")
    public Resp<User> getById(@PathVariable("id") Integer id){
        // 手动抛出异常
        // throw new AppException(AppExceptionCodeMsg.USERNAME_NOT_EXISTS);
        // 服务器内部异常
        // int i = 1/0;
        return Resp.success("获取用户信息", new User(id, "张三", 20));
    }

    @GetMapping("/getTest")
    @SysLogs(operation = "模拟测试")
    public int getTest(){
        // String [] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        // System.out.println(groupAnagrams(strs));
        // System.out.println(isValid("[({})][(())]"));
        findLucky(new int[]{2,2,3,4});
        return 0;
    }
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>> map = new HashMap();
        for(String s : strs){
            // 变成字节数组，
            char[] c = s.toCharArray();
            // 字节数组排序
            Arrays.sort(c);
            // 将字节数组转换成String
            String s1 = new String(c);
            if (!map.containsKey(s1)) {
                map.put(s1,new ArrayList<>());
            }
            map.get(s1).add(s);
        }
        Collection<List<String>> values = map.values();
        return new ArrayList<>(values);
    }

    public boolean isValid(String s) {
        int n = s.length();
        if (n % 2 == 1) {
            return false;
        }

        Map<Character, Character> map = new HashMap<Character, Character>() {{
            put(')', '(');
            put(']', '[');
            put('}', '{');
        }};
        Deque<Character> stack = new LinkedList<Character>();
        for(char c : s.toCharArray()){
            if (map.containsKey(c)){
                if(stack.isEmpty() || stack.peek() != map.get(c)){
                    return false;
                }
                stack.pop();
            }else{
                // 如果不在map里面则加入stack栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public int findLucky(int[] arr) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int a: arr){
            map.put(a,map.getOrDefault(a,0)+1);
        }
        int ans = -1;
        for(Map.Entry<Integer, Integer> me : map.entrySet()){
            if(me.getKey()==me.getValue()){
                ans = Math.max(ans, me.getKey());
            }
        }
        map.forEach((k,v)->{
            System.out.println(k+"-"+v);
        });
        return ans;
    }

}



