package main;

public class IndexRecord {
    private String key; // [0] index
    private int where; // [1] index
    
    
    
    public IndexRecord(String key, int where) {
		super();
		this.key = key;
		this.where = where;
	}
	// Getters and Setters and CompareTo
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getWhere() {
		return where;
	}
	public void setWhere(int where) {
		this.where = where;
	}
	public int compareTo(String compare) {
		// TODO Auto-generated method stub
		return key.compareToIgnoreCase(compare);
	}

  
}
