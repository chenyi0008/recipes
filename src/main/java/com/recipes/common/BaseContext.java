package com.recipes.common;

/**
 * 基于Threadlocal封装工具类，用户保存和获取当前登录id
 */
public class BaseContext {
    private static ThreadLocal<Integer> currentUserId = new ThreadLocal<>();
    private static ThreadLocal<String> currentUsername = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setUserId(Integer id){
        currentUserId.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Integer getUserId(){
        return currentUserId.get();
    }


    public static String getUsername() {
        return currentUsername.get();
    }

    public static void setUsername(String username) {
        currentUsername.set(username);
    }
}
