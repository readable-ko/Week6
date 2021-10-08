package com.todo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc, categori, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 추가] \n"
				+ "제목 > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목이 중복됩니다!");
			return;
		}
		
		System.out.println("카테고리 > ");
		categori = sc.next();
		sc.nextLine();
		System.out.println("내용 > ");
		desc = sc.nextLine();
		
		System.out.println("마감일자 > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, categori, desc, due_date);
		list.addItem(t);
		System.out.println("내용이 추가되었습니다.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 삭제]\n"
				+ "삭제할 항목의 번호를 입력하시오 > ");
		
		int index = sc.nextInt();
		sc.nextLine();
		if(index >= l.getList().size()) {
			System.out.println("존재하지 않는 번호입니다!");
		}
		else {
		l.deleteItem(l.getList().get(index-1));
		System.out.println("삭제되었습니다.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[항목 수정]\n"
				+ "수정할 항목의 번호를 입력하시오 > ");
		
		int num = sc.nextInt();
		sc.nextLine();
		if (num >= l.getList().size()) {
			System.out.println("존재하지 않는 번호입니다!");
			return;
		}

		System.out.println("새 제목 > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("중복된 제목입니다!");
			return;
		}
		
		System.out.println("새 카테고리 > ");
		String new_categori = sc.nextLine().trim();
		
		System.out.println("새 내용 > ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("새 마감일자 > ");
		String new_due_date = sc.nextLine().trim();
		
		
		l.deleteItem(l.getList().get(num - 1));
		l.addItem(new TodoItem(new_title, new_categori, new_description, new_due_date));
		System.out.println("수정되었습니다.");
		
	}
	

	public static void find(TodoList l, String str, int version) {
		int count = 0;
		if(version == 1) {
			for(int i = 0; i < l.getList().size(); ++i) {
				if(l.getList().get(i).toString().contains(str.trim()) == true) {
					System.out.println(i+1 + ". " + l.getList().get(i).toString());
					count++;
				}
			}
		}else {
			for(int i = 0; i < l.getList().size(); ++i) {
				if(l.getList().get(i).getCategory().contains(str.trim()) == true) {
					System.out.println(i+1 + ". " + l.getList().get(i).toString());
					count++;
				}
			}
		}
		System.out.println(count + "개의 항목을 찾았습니다.");
	}

	public static void listAll(TodoList l) {
		System.out.println("[전체 목록, 총 " + l.length() + "개]");
		int num = 1;
		for (TodoItem item : l.getList()) {
			System.out.println(num + ". " + item.toString());
			num++;
		}
	}
	
	public static void saveList(TodoList l, String filename) {
		try {
			Writer w = new FileWriter(filename);
			for(TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			System.out.println("데이터 저장 완료!!");
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void loadList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			
			String temp;
			while((temp = br.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(temp,"##");
				TodoItem item = new TodoItem(st);
				l.addItem(item);
			}
			br.close();
			System.out.println("데이터 로딩 완료!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
