package com.nwnu.test;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import com.nwnu.dao.StudentDao;
import com.nwnu.dao.TeacherDao;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;
import com.nwnu.util.BarChart;
import com.nwnu.util.SqlCreate;

/**
 * Filename: SystemTest.java
 * 
 * ������ϵͳ
 * 
 * @author Luor
 * @version 1.0
 */
public class SystemTest {
	
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	
	/**
	 * ��������
	 * 
	 * @param args
	 * @throws SQLException 
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws Exception {
		
		//ִ��
		Scanner sc = new Scanner(System.in);
		while(true) {
			//����
			SystemMenu.mainMenu();
			//��֧
			System.out.print("������Ҫִ�е�ѡ�");
			int choice = sc.nextInt();
			switch(choice) {
				case 1: {
					SystemMenu.firstMenu();
					System.out.print("������Ҫִ�е�ѡ�");
					boolean judge = true;
					while(judge) {
						int choice1 = sc.nextInt();
						switch(choice1) {
							case 1: {
								System.out.print("������Ҫ�����ѧ����Ϣ��ÿһ���ÿո��������\nѧ��   ����   �Ա�   ѧԺ   רҵ   �绰����    ��¼���ڣ�yyyyMMdd��   "
										+ "ʡ/������/ֱϽ��   ��/��/��   �Ƿ��Ⱦ��1=�ǣ�0=��   ���£�3x.x��\n");
								Student stu = new Student(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), 
										sc.next(), sc.next(), sc.next(), sc.next(), sc.nextBigDecimal());
								if (stuDao.addStudent(stu)) {
									System.out.print("������Ϣ�ɹ���\n");
								} else {
									System.out.print("������Ϣʧ�ܣ�\n");
								}
								judge = false;
								break;
							}
							case 2: {
								System.out.print("������Ҫ����Ľ�ʦ��Ϣ��ÿһ���ÿո��������\n���ţ�4λ��   ����   �Ա�   ѧԺ   �绰����    ��¼���ڣ�yyyyMMdd��   "
										+ "ʡ/������/ֱϽ�У���ϸ���ƣ�   ��/��/������ϸ���ƣ�   �Ƿ��Ⱦ����=1����=0��   ���£�3x.x��\n");
								Teacher tea = new Teacher(sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), sc.next(), 
										sc.next(), sc.next(), sc.next(), sc.nextBigDecimal());
								if (teaDao.addTeacher(tea)) {
									System.out.print("������Ϣ�ɹ���\n");
								} else {
									System.out.print("������Ϣʧ�ܣ�\n");
								}
								judge = false;
								break;
							}
							default: {
								System.out.print("\n�Ƿ����룡\n���������룺");
								break;
							}
						}
					}
					break;
				}
				case 2: {
					SystemMenu.secondMenu();
					System.out.print("������Ҫִ�е�ѡ�");
					boolean judge = true;
					while(judge) {
						int choice2 = sc.nextInt();
						switch(choice2) {
							case 1: {
								System.out.print("������Ҫ��ѯ�ĳ�Ա�������Ա��Ϣ��ÿһ���ÿո��������\n��ʦ/ѧ����1=��ʦ��2=ѧ����   ѧ��/����\n");
								int userType = sc.nextInt();
								if (userType == 1) {
									List<Teacher> teaList = new ArrayList<Teacher>();
									teaList = teaDao.quaryById(sc.next());
									System.out.print("����   ����   �Ա�   ѧԺ   �绰����   ��¼����   ʡ/������/ֱϽ��   ��/��/��   �Ƿ��Ⱦ   ����\n" + teaList);
								} else if (userType == 2) {
									List<Student> stuList = new ArrayList<Student>();
									stuList = stuDao.quaryById(sc.next());
									System.out.print("ѧ��   ����   �Ա�   ѧԺ   רҵ   �绰����   ��¼����   ʡ/������/ֱϽ��   ��/��/��   �Ƿ��Ⱦ   ����\n" + stuList);
								} else {
									System.out.print("�Ƿ����룡\n");
								}
								
								judge = false;
								break;
							}
							case 2: {
								System.out.print("������Ҫ��ѯ�ĳ�Ա�������Ա��Ϣ��ÿһ���ÿո��������\n��ʦ/ѧ����1=��ʦ��2=ѧ����   ѧ��/����   ��¼���ڣ�yyyyMMdd��\n");
								int userType = sc.nextInt();
								String id = sc.next();
								String dateString = sc.next();
								Date recordDate = new Date(sdf.parse(dateString).getTime());
								if (userType == 1) {
									Teacher tea = teaDao.findTeacherInfo(id, recordDate);
									System.out.print("����   ����   �Ա�   ѧԺ   �绰����   ��¼����   ʡ/������/ֱϽ��   ��/��/��   �Ƿ��Ⱦ   ����\n" + tea);
								} else if (userType == 2) {
									Student stu = stuDao.findStudentInfo(id, recordDate);
									System.out.print("ѧ��   ����   �Ա�   ѧԺ   רҵ   �绰����   ��¼����   ʡ/������/ֱϽ��   ��/��/��   �Ƿ��Ⱦ   ����\n" + stu);
								} else {
									System.out.print("�Ƿ����룡\n");
								}
								
								judge = false;
								break;
							}
							default: {
								System.out.print("\n�Ƿ����룡\n���������룺");
								break;
							}
						}
					}
					
					break;
				}
				case 3: {
					SqlCreate sql = new SqlCreate();
					SystemMenu.thirdMenu();
					System.out.print("������Ҫִ�е�ѡ�");
					boolean judge = true;
					while(judge) {
						int choice3 = sc.nextInt();
						switch(choice3) {
							case 1: {
								System.out.print("������Ҫ��ѯ���������䣨���ڸ�ʽ��yyyyMMdd����\n��ʼ����      ��ֹ����\n");
								
								JFrame frame = new JFrame("������Ϣͳ��ͼ");
								frame.add(new BarChart(sc.next(), sc.next(), choice3).getChartPanel());
								frame.setBounds(50, 50, 800, 400);
								frame.setVisible(true);
								
								judge = false;
								break;
							}
							case 2: {
								System.out.print("������Ҫ��ѯ���������䣨���ڸ�ʽ��yyyyMMdd����\n��ʼ����   ��ֹ����\n");
								
								JFrame frame = new JFrame("������Ϣͳ��ͼ");
								frame.add(new BarChart(sc.next(), sc.next(), choice3).getChartPanel());
								frame.setBounds(50, 50, 800, 400);
								frame.setVisible(true);
								
								judge = false;
								break;
							}
							default: {
								System.out.print("\n�Ƿ����룡\n���������룺");
								break;
							}
						}
					}
					
					break;
				}
				case 4: {
					System.out.print("\nϵͳ�رգ�ллʹ�ã�\n");
					sc.close();
					return;
				}
				default: {
					System.out.print("\n�Ƿ����룡\n���������룺");
					break;
				}
			}
		}
		
	}
	
}
