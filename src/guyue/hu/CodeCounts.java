package guyue.hu;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
*@author guyue E-mail:398456458@qq.com
*version1.0 ����ʱ��:2017��11��19�� ����1:19:52
*describ 
*/
public class CodeCounts {
	
	private int codeLine = 0;//��Ч��
	private int commentLine = 0;//ע����
	private int blankLine = 0;//����
	private final static String pattern1 = "^//";
	private final static String pattern2 = "^/\\*";
	private final static String pattern3 = "\\*/$";
	private boolean isComment = false;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CodeCounts cc = new CodeCounts();
		cc.countF();
		System.out.println("��Ч��:" + cc.codeLine + " ע����:" + cc.commentLine + " ����:" + cc.blankLine);
	}
	
	public void countF() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("F:\\CodeCount\\src\\guyue\\hu\\CodeCounts.java"));
			String line = null;
			Pattern p1 = Pattern.compile(pattern1);
			Pattern p2 = Pattern.compile(pattern2);
			Pattern p3 = Pattern.compile(pattern3);
			Matcher m1 = null;
			Matcher m2 = null;
			Matcher m3 = null;
			while((line = br.readLine()) != null) {
				line = line.trim();
				//1.����				
				if(line.equals("")) {
					blankLine ++;
					continue;
				}
				//2.�ǿ���
				m1 = p1.matcher(line);
				m2 = p2.matcher(line);
				m3 = p3.matcher(line);
				if(m1.find()) {
					commentLine ++;
				} else if(m2.find()) {
					commentLine ++;
					if(! m3.find()) {
						isComment = true;
					}
				} else {
					if(isComment) {
						commentLine ++;
						if(m3.find()) {
							isComment = false;
						}
					} else {
						codeLine ++;
					}
				}
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
