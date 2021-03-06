package edu.scau.model;

public class Cate {
    private String cate;

    private String parent;

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate == null ? null : cate.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }
    
    @Override
    public boolean equals(Object obj) {
    	if(obj instanceof Cate){
    		return cate.equals(((Cate) obj).getCate()) && parent.equals(((Cate) obj).getParent());
    	}
    	return false;
    }
}