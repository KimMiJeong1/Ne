package com.hk.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.member.vo.Member;

@Repository
public class MemberDAO {

	//root-context에서 데이터소스를 넣기 위해 hikari를 id를 dataSource를 준것을 @Autowired로 줘서 사용
   @Autowired
   DataSource dataSource;
   
   public List<Member> memberList() { 
      Connection connection;
       PreparedStatement pStmt = null;
       ResultSet rs = null;
       String query = "select mno,email,pwd,mname,cre_date,mod_date from members ORDER BY MNO ASC";
       List<Member> members = new ArrayList<Member>(); // 다형성
       
       try {
          connection = dataSource.getConnection();
          pStmt = connection.prepareStatement(query);//기존에는 Connection connection;했던거로 디비에서 정보를 가져왔는데 지금  connection에 hikari인 dataSource를 연결 
         rs = pStmt.executeQuery();
         while(rs.next()) { 
            // 1개의 Row씩 꺼내주세요..
            Member member = new Member();
            member.setMno(rs.getInt("mno"));
            member.setEmail(rs.getString("email"));
            member.setPwd(rs.getString("pwd"));
            member.setMname(rs.getString("mname"));
            member.setCre_date(rs.getDate("cre_date"));
            member.setMod_date(rs.getDate("mod_date"));
            
            members.add(member);
         }

      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } finally { 
         if(rs !=null)
            try {
               rs.close();
            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
         if(pStmt !=null)
            try {
               pStmt.close();
            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
      }
      
      return members;      
   }
}