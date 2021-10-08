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
		
		System.out.println("[�׸� �߰�] \n"
				+ "���� > ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��˴ϴ�!");
			return;
		}
		
		System.out.println("ī�װ� > ");
		categori = sc.next();
		sc.nextLine();
		System.out.println("���� > ");
		desc = sc.nextLine();
		
		System.out.println("�������� > ");
		due_date = sc.nextLine();
		
		TodoItem t = new TodoItem(title, categori, desc, due_date);
		list.addItem(t);
		System.out.println("������ �߰��Ǿ����ϴ�.");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		
		int index = sc.nextInt();
		sc.nextLine();
		if(index >= l.getList().size()) {
			System.out.println("�������� �ʴ� ��ȣ�Դϴ�!");
		}
		else {
		l.deleteItem(l.getList().get(index-1));
		System.out.println("�����Ǿ����ϴ�.");
		}
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("[�׸� ����]\n"
				+ "������ �׸��� ��ȣ�� �Է��Ͻÿ� > ");
		
		int num = sc.nextInt();
		sc.nextLine();
		if (num >= l.getList().size()) {
			System.out.println("�������� �ʴ� ��ȣ�Դϴ�!");
			return;
		}

		System.out.println("�� ���� > ");
		String new_title = sc.nextLine().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("�ߺ��� �����Դϴ�!");
			return;
		}
		
		System.out.println("�� ī�װ� > ");
		String new_categori = sc.nextLine().trim();
		
		System.out.println("�� ���� > ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("�� �������� > ");
		String new_due_date = sc.nextLine().trim();
		
		
		l.deleteItem(l.getList().get(num - 1));
		l.addItem(new TodoItem(new_title, new_categori, new_description, new_due_date));
		System.out.println("�����Ǿ����ϴ�.");
		
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
		System.out.println(count + "���� �׸��� ã�ҽ��ϴ�.");
	}

	public static void listAll(TodoList l) {
		System.out.println("[��ü ���, �� " + l.length() + "��]");
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
			System.out.println("������ ���� �Ϸ�!!");
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
			System.out.println("������ �ε� �Ϸ�!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
