package com.example.demo.service.impl;

import com.example.demo.dao.LoginRecordDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.LoginRecord;
import com.example.demo.entity.User;
import com.example.demo.entity.UserCustom;
import com.example.demo.entity.UserVo;
import com.example.demo.service.UserService;
import com.example.demo.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @program:SpringBoot_demo2
 * @description:实现层
 * @author:Lili Chen
 * @create:2018-12-05-17:27
 **/
@Service//告诉spring容器这是一个bean
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private LoginRecordDao loginRecordDao;

    /**
    * @Description:  注册
    * @Param: [user]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Transactional
    @Override
    public boolean register(User user) {
        User user1=userDao.queryUserByPhone(user.getPhone());
        User user2=userDao.queryUserByName(user.getName());
        if(user.getPassword().length()<4){
            return false;
        }
        if(user1==null&&user2==null){
            String passWord = MD5Utils.md5(user.getPassword());
            user.setPassword(passWord);
            userDao.addUser(user);
            return true;
        }
        return false;
    }

    /**
    * @Description: 登录
    * @Param: [user, date]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public boolean login(User user, Date date) {
        String password= MD5Utils.md5(user.getPassword());
        user.setPassword(password);
        User user1=userDao.queryUserByPhone(user.getPhone());
        if(user1!=null){//用户不为空
            if(user.getPassword().equals(user1.getPassword())){
                LoginRecord record2=loginRecordDao.querryRecordByPhone(user.getPhone());
                if(record2!=null){
                    loginRecordDao.deleteRecord(record2.getLoginRecordId());//成功登录删除失败记录
                }
                return true;
            }else{//密码输入错误
                LoginRecord record=loginRecordDao.querryRecordByPhone(user.getPhone());
                if(record==null){//记录为空
                    record=new LoginRecord();
                    record.setuPhone(user.getPhone());
                    record.setLoginDate(date);
                    record.setFailNum(1);
                    record.setLockFlag("0");
                    loginRecordDao.addRecord(record);
                }else{//记录不为空
                    record.setuPhone(user.getPhone());
                    record.setLoginDate(date);
                    record.setFailNum(record.getFailNum()+1);
                    if(record.getFailNum()==3){
                        record.setLockFlag("1");//锁定
                    }else{
                        record.setLockFlag("0");
                    }
                    loginRecordDao.updateRecord(record);
                    return false;
                }
            }
        }
        return false;
    }
    /**
    * @Description: 通过电话号码查找用户
    * @Param: [phone]
    * @return: com.example.demo.entity.User
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public User queryUserByPhone(String phone) {
        User user=null;
        user =userDao.queryUserByPhone(phone);
        return user;
    }

    /**
    * @Description: 通过用户id查找用户
    * @Param: [id]
    * @return: com.example.demo.entity.User
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public User queryUserById(Integer id) {
        return userDao.queryUserById(id);
    }

    /**
    * @Description: 更改密码
    * @Param: [userVo]
    * @return: boolean
    * @Author: Lili Chen
    * @Date: 2018/12/13
    */
    @Override
    public boolean updatePassword(UserVo userVo) {
        String password= MD5Utils.md5(userVo.getUser().getPassword());
        String rePassword= MD5Utils.md5(userVo.getRePassword());
        userVo.setRePassword(rePassword);
        User user2=userVo.getUser();
        user2.setPassword(password);
        userVo.setUser(user2);
        User user=userDao.queryUserByPhone(userVo.getUser().getPhone());
        if(userVo.getRePassword().length()<4){
            return false;
        }
        if(user!=null){
            if(userVo.getRePassword().equals(userVo.getUser().getPassword())){
                userDao.updatePassword(userVo);
                return true;
            }

        }
        return false;
    }


    /**
    * @Description: 生成4位数的验证码
    * @Param: []
    * @return: java.lang.String
    * @Author: Lili Chen
    * @Date: 2018/12/14
    */
    @Override
    public String getCode(){
        String code = (int) (Math.random() * 9000 + 1000) + "";//强制类型转换为整数，因为random函数返回double
        return code;
    }

    @Override
    public User queryUserByName(String name) {
        return userDao.queryUserByName(name);
    }

    @Override
    public List<UserCustom> queryUserList() {
        return userDao.queryUserList();
    }

    /** 
    * @Description: 更改角色名 
    * @Param: [uId, roleId] 
    * @return: boolean 
    * @Author: Lili Chen 
    * @Date: 2018/12/28 
    */
    @Override
    public boolean updateRole(Integer uId, Integer roleId) throws Exception {
        User user1=userDao.queryUserById(uId);
        if(user1!=null){
            if(roleId==user1.getRoleId()){
                throw new Exception("角色名相同，不需要修改");
            }
            user1.setRoleId(roleId);
            userDao.updateRole(user1);
            return true;
        }
        return false;
    }


}