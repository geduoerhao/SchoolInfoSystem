package com.nwnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nwnu.dao.DbUtil;
import com.nwnu.pojo.Student;

/**
 * Filename: StudentDao.java
 * 
 * ����ѯѧ����Ϣ
 * 
 * @author Luor
 * @version 1.0
 */
public class StudentDao {
	
	/**
	 * ������ѧ������
	 * 
	 * @param s
	 * @return ִ�н��
	 * @throws SQLException
	 */
	public boolean addStudent(Student s) throws SQLException {
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "Insert into studentinfo(id,name,sex,college,major,phoneNumber,recordDate,province,city,diagnosed,temperature)"
				+"values(?,?,?,?,?,?,?,?,?,?,?)";
		//Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//����
		ptmt.setString(1, s.getId());
		ptmt.setString(2, s.getName());
		ptmt.setString(3, s.getSex());
		ptmt.setString(4, s.getCollege());
		ptmt.setString(5, s.getMajor());
		ptmt.setString(6, s.getPhoneNumber());
		ptmt.setDate(7, s.getRecordDate());
		ptmt.setString(8, s.getProvince());
		ptmt.setString(9, s.getCity());
		ptmt.setString(10, s.getDiagnosed());
		ptmt.setBigDecimal(11, s.getTemperature());
		//ִ��
		try {
			ptmt.execute();
			return true;
		} catch(SQLException e) {
			return false;
		}
	}
	
	/**
	 * ��ͨ��ѧ�Ų�ѯ��Ϣ
	 * 
	 * @param id
	 * @return ѧ���б�
	 * @throws SQLException
	 */
	public List<Student> quaryById(String id) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from studentinfo where id = " + id;
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
		List<Student> stuList = new ArrayList<Student>();
		Student s = null;
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			stuList.add(s);
		}
		return stuList;
	}
	
	/**
	 * ��ͨ��ѧ�������ڲ�ѯ����
	 * 
	 * @param id
	 * @param recordDate
	 * @return ѧ��ʵ��
	 * @throws SQLException
	 */
	public Student findStudentInfo(String id, Date recordDate) throws SQLException {
		Student s = null;
		//��ȡ���ݿ�����
		Connection conn = DbUtil.getConnection();
		//SQL
		String sql = "select * from studentinfo where id = ? and recordDate = ?";
		//Ԥ����
		PreparedStatement ptmt = conn.prepareStatement(sql);
		//����
		ptmt.setString(1, id);
		ptmt.setDate(2, recordDate);
		//ִ��
		ResultSet rs = ptmt.executeQuery();
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
		}
		return s;
	}
	
	/**
	 * 
	 * @param dateBegin
	 * @param dateEnd
	 * @return ѧ���б�
	 * @throws SQLException
	 */
	public List<Student> quaryByDate(Date dateBegin, Date dateEnd) throws SQLException {
		Connection conn = DbUtil.getConnection();
		Statement stmt = conn.createStatement();
		//SQL
		String sql = "select * from studentinfo where recordDate between '" + dateBegin + "' and '" + dateEnd + "'";
		//ִ��
		ResultSet rs = stmt.executeQuery(sql);
		//����û���Ϣ
		List<Student> stuList = new ArrayList<Student>();
		Student s = null;
		while(rs.next()) {
			s = new Student(rs.getString("id"), rs.getString("name"), rs.getString("sex"), rs.getString("college"), 
					rs.getString("major"), rs.getString("phoneNumber"), rs.getDate("recordDate"), rs.getString("province"), 
					rs.getString("city"), rs.getString("diagnosed"), rs.getBigDecimal("temperature"));
			stuList.add(s);
		}
		return stuList;
	}
	
}
