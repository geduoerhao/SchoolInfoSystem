package com.nwnu.util;

import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.nwnu.dao.StudentDao;
import com.nwnu.dao.TeacherDao;
import com.nwnu.pojo.Student;
import com.nwnu.pojo.Teacher;

/**
 * Filename: BarChart.java
 * 
 * ����״ͼ����
 * 
 * @author Luor
 * @version 1.0
 */
public class BarChart {
	
	private static ChartPanel panel;
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	private static StudentDao stuDao = new StudentDao();
	private static TeacherDao teaDao = new TeacherDao();
	
	public BarChart(String dateBeginString, String dateEndString, int choice) {
		//�����������������
		Date dateBegin = null;
		Date dateEnd = null;
		try {
			dateBegin = new Date(sdf.parse(dateBeginString).getTime());
			dateEnd = new Date(sdf.parse(dateEndString).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			System.out.print("�������������/��ʽ����\n");
		}
		String title = "";
		if (choice == 1) {
			title = dateBegin + "��" + dateEnd + "��Ⱦ��Ա����ͳ��";
		} else if (choice == 2) {
			title = dateBegin + "��" + dateEnd + "��Ա�����ֲ�ͳ��";
		}
		
		//�������ݼ�
		CategoryDataset dataSet = getDateSet(dateBegin, dateEnd);
		//����ͼ��
		JFreeChart chart = ChartFactory.createBarChart(title, "��������", "����", dataSet, 
				PlotOrientation.VERTICAL, true, false, false);
		//������
		//��ȡͼ���������
		CategoryPlot plot = chart.getCategoryPlot();
		//ˮƽ�ײ��б�
		CategoryAxis domainAxis = plot.getDomainAxis();
		//��ֱ����
		domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12));
		//ˮƽ�ײ�����
		domainAxis.setLabelFont(new Font("����", Font.BOLD,16));
		//��ȡ��״
		ValueAxis rangeAxis = plot.getRangeAxis();
		rangeAxis.setLabelFont(new Font("����",Font.BOLD,15));
		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 12));
		//���ñ�������
		chart.getTitle().setFont(new Font("����",Font.BOLD,20));
		
		panel = new ChartPanel(chart, true);
	}
	
	public static CategoryDataset getDateSet(Date dateBegin, Date dateEnd) {
		DefaultCategoryDataset dataSet = new DefaultCategoryDataset();
		List<Student> stuList = new ArrayList<Student>();
		List<Teacher> teaList = new ArrayList<Teacher>();
		
		try {
			stuList = stuDao.quaryByDate(dateBegin, dateEnd);
			teaList = teaDao.quaryByDate(dateBegin, dateEnd);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("��ѯ����\n");
		}
		
		for (Student s:stuList) {
			int count = 0;
			if (s.getDiagnosed().equalsIgnoreCase("0")) {
				count++;
			}
			dataSet.addValue(count, "ѧ��", s.getRecordDate());
		}
		for (Teacher t:teaList) {
			int count = 0;
			if (t.getDiagnosed().equalsIgnoreCase("0")) {
				count++;
			}
			dataSet.addValue(count, "��ʦ", t.getRecordDate());
		}
		
		return dataSet;
	}
	
	public ChartPanel getChartPanel() {
		return panel;
	}
}
