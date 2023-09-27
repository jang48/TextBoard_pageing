package org.example;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private int currentPage;
    private int pageSize;
    private List<Article> items;

    public Page(List<Article> items, int pageSize, int currentPage) {
        this.items = items;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) items.size() / pageSize);
    }

    public void nextPage() {
        if (currentPage < getTotalPages()) {
            currentPage++;
        }
    }

    public void prevPage() {
        if (currentPage > 1) {
            currentPage--;
        } else if (currentPage == 1){
            System.out.println("첫번째 페이지입니다. 확인해주세요.");
        }
    }

    public int goToPage(int page) {
        if (page >= 1 && page <= getTotalPages()) {
            return currentPage = page;
        } return 0;
    }

    public List<Article> getCurrentPageItems() {
        int startIndex = (currentPage - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, items.size());
        return items.subList(startIndex, endIndex);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void pageNumber(int currentPage, int totalPage){
        for (int i = Math.max(currentPage - 2, 1); i <= Math.min(currentPage < 3 ? 5 :  currentPage + 2, totalPage); i++) {
            if (i == currentPage) {
                System.out.print("[" + i + "] ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.println(">>");
    }
}
