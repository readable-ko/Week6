package com.todo.dao;

import java.util.*;

import com.todo.service.TodoSortByDate;
import com.todo.service.TodoSortByDateDesc;
import com.todo.service.TodoSortByName;

public class TodoList {
	private List<TodoItem> list;

	public TodoList() {
		this.list = new ArrayList<TodoItem>();
	}

	public void addItem(TodoItem t) {
		list.add(t);
	}

	public void deleteItem(TodoItem t) {
		list.remove(t);
	}

	void editItem(TodoItem t, TodoItem updated) {
		int index = list.indexOf(t);
		list.remove(index);
		list.add(updated);
	}

	public ArrayList<TodoItem> getList() {
		return new ArrayList<TodoItem>(list);
	}

	public void sortByName() {
		Collections.sort(list, new TodoSortByName());

	}

	public void listAll() {
		System.out.println("\n"
				+ "inside list_All method\n");
		int i = 1;
		for (TodoItem myitem : list) {
			System.out.println(i + ". [" + myitem.getCategory() + "]" + myitem.getTitle() + " - "
		+ myitem.getDesc() + " - " + myitem.getDue_date() + " - " + myitem.getCurrent_date());
			i++;
		}
	}
	
	public void reverseList() {
		Collections.reverse(list);
	}

	public void sortByDate() {
		Collections.sort(list, new TodoSortByDate());
	}
	
	public void sortByDateDec() {
		Collections.sort(list, new TodoSortByDateDesc());
	}
	
	public void sortByCate() {
		HashSet<String> uniCate = new HashSet<String>();
		for(TodoItem item : list) {
			uniCate.add(item.getCategory().trim());
		}
		String[] sortedCate = uniCate.toString().replace("[", "").replace("]", "").split(", ");
		Arrays.sort(sortedCate);
		System.out.println(Arrays.toString(sortedCate) + "\n총 " + sortedCate.length + "개의 카테고리가 등록되어 있습니다.");
	}

	public int indexOf(TodoItem t) {
		return list.indexOf(t);
	}
	
	@SuppressWarnings("unused")
	public int length() {
		int count = 0;
		for(TodoItem item : list) {
			count++;
		}
		return count;
	}

	public Boolean isDuplicate(String title) {
		for (TodoItem item : list) {
			if (title.equals(item.getTitle())) return true;
		}
		return false;
	}
}
