package edu.xalead.web.servlet.backend.edu.xalead.web.servlet.backend.vo;

import java.util.ArrayList;

public class Page {
    private int offset = 0;//当前页索引
    private int pageNumber = 5;//每页记录数
    private int total;//总记录数

    public int getCurrentPage(){//返回当前页面数
        return offset/pageNumber +1;
    }

    public int getTotalPage(){//返回页面总数
        return total % pageNumber ==0?total/pageNumber:total/pageNumber+1;
    }
    public ArrayList<ArrayList<String>> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList<String>> data) {
        this.data = data;
    }

    private ArrayList<ArrayList<String>> data = null;

    public int getPrevious(){
        return offset - pageNumber < 0 ? 0 : offset - pageNumber;
    }

    public int getNext(){
        int lastIndex = total % pageNumber == 0 ? total - pageNumber : total - total%pageNumber;
        return offset >= lastIndex ? lastIndex : offset + pageNumber;
    }

    public int getFirst(){
        return 0;
    }

    public int getLast(){
        return total - (total % pageNumber == 0 ? pageNumber : total % pageNumber);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

