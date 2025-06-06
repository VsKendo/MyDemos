package test;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class test {
    public static void main(String[] args) {
        String algorithmName = "md5"; // 加密算法
        String password = "admin123";

        // 方法1：使用SimpleHash（与HashedCredentialsMatcher完全匹配）
        SimpleHash hash = new SimpleHash(
                algorithmName,               // 算法名称
                password,            // 原始密码
                null,                // 盐值（null表示无盐）
                2           // 迭代次数
        );
        String hexHash = hash.toHex(); // 获取十六进制结果

        System.out.println("原始密码: " + password);
        System.out.println("生成的密码(SimpleHash): " + hexHash);

        String loginName = "admin";
        String salt = "111111";
        SimpleHash simpleHash = new Md5Hash(loginName + "123456" + salt);
        System.out.println("网关登录用户名：" + loginName + " 的生成的密码(Md5Hash): " + simpleHash.toHex());
    }

}
