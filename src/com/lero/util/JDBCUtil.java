package com.lero.util;

import java.sql.*;

//宸ュ叿绫�(鍒涘缓)
public class JDBCUtil {
    //鑾峰彇鏁版嵁搴撹繛鎺ョ殑鏂规硶
    public Connection getConn(){
        Connection conn=null;
        String url="jdbc:mysql://localhost:3306/db_dorm?useUnicode=true&characterEncoding=utf-8";
        String username="root";
        String userpassword="root";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(url,username,userpassword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  conn;
    }


    //鍏抽棴鏁版嵁搴撹繛鎺ャ�佸叧闂墽琛屽璞°�佸叧闂粨鏋滈泦瀵硅薄锛堝叧闂祫婧愶級
    public void closeAll(ResultSet rs, Statement st,Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    //鎵ц澧炲垹鏀圭殑閫氱敤鏂规硶
    public int exec(String sql,Object[] obj){
        int count=0;
        PreparedStatement pst=null;
        Connection conn=getConn();
        //棰勭紪璇憇ql璇彞骞惰繑鍥炴墽琛屽璞�
        try {
              pst=conn.prepareStatement(sql);
            for (int i = 0; i < obj.length; i++) {
                pst.setObject(i+1,obj[i]);
            }
           count=pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeAll(null,pst,conn);
        }
        return  count;

    }
}
