package com.todo.menu;
public class Menu {

    public static void displaymenu()
    {
        System.out.println();
        System.out.println("<ToDoList ���� ��ɾ� ����>");
        System.out.println("ADD - �׸� �߰�");
        System.out.println("DEL - �׸� ����");
        System.out.println("EDIT - �׸� ����");
        System.out.println("ls - ��ü ���");
        System.out.println("ls_name - �������� ����");
        System.out.println("ls_name_desc - �������� ����");
        System.out.println("ls_date - ��¥�� ����");
        System.out.println("ls_date_desc - ��¥���� ����");
        System.out.println("ls_cate - ī�װ��� ����");
        System.out.println("ls_comp - �Ϸ��׸� ����");
        System.out.println("comp <Ű����> - <Ű����> �׸� �Ϸ� üũ");
        System.out.println("find <Ű����> - <Ű����>�� �����ϰ� �ִ� �׸��� ���");
        System.out.println("find_cate <Ű����> - <Ű����>�� �����ϰ� �ִ� ī�װ��� ���");
        System.out.println("exit - ����");
    }
    
    public static void prompt() {
    	System.out.print("\nCommand > ");
    }
}
