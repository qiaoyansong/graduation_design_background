package com.qiaoyansong.handler;

import com.qiaoyansong.entity.background.UserType;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ：Qiao Yansong
 * @date ：Created in 2021/2/8 19:54
 * description：实现枚举类UserType 到数据库表的转换
 */
public class UserTypeToTable implements TypeHandler<UserType> {
    @Override
    public void setParameter(PreparedStatement preparedStatement, int i, UserType userType, JdbcType jdbcType) throws SQLException {
        preparedStatement.setInt(i, Integer.valueOf(userType.getType()));
    }

    @Override
    public UserType getResult(ResultSet resultSet, String s) throws SQLException {
        int result = resultSet.getInt(s);
        switch (result){
            case 0 : return UserType.GENERAL_USER;
            case 1 : return UserType.ADMINISTRATOR_INFORMATION;
            case 2 : return UserType.ADMINISTRATOR_ACTIVITY;
            case 3 : return UserType.ADMINISTRATOR_HELP_INFORMATION ;
            case 4 : return UserType.ADMINISTRATOR_AUCTION_INFORMATION;
        }
        return UserType.ADMINISTRATOR_PUBLIC_GOODS;
    }

    @Override
    public UserType getResult(ResultSet resultSet, int i) throws SQLException {
        int result = resultSet.getInt(i);
        switch (result){
            case 0 : return UserType.GENERAL_USER;
            case 1 : return UserType.ADMINISTRATOR_INFORMATION;
            case 2 : return UserType.ADMINISTRATOR_ACTIVITY;
            case 3 : return UserType.ADMINISTRATOR_HELP_INFORMATION ;
            case 4 : return UserType.ADMINISTRATOR_AUCTION_INFORMATION;
        }
        return UserType.ADMINISTRATOR_PUBLIC_GOODS;
    }

    @Override
    public UserType getResult(CallableStatement callableStatement, int i) throws SQLException {
        int result = callableStatement.getInt(i);
        switch (result){
            case 0 : return UserType.GENERAL_USER;
            case 1 : return UserType.ADMINISTRATOR_INFORMATION;
            case 2 : return UserType.ADMINISTRATOR_ACTIVITY;
            case 3 : return UserType.ADMINISTRATOR_HELP_INFORMATION ;
            case 4 : return UserType.ADMINISTRATOR_AUCTION_INFORMATION;
        }
        return UserType.ADMINISTRATOR_PUBLIC_GOODS;
    }
}
