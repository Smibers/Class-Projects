
//Smith   Benjamin
//COSC-311 fall2024
//Project 1

package main;

public class DataBaseRecord 
{

	private String ID;
    private String first;
    private String last;
   
    DataBaseRecord(String id,String lastName,String firstName)
    {
        ID=id;
        first=firstName;
        last=lastName;
    }  
   


	public String getID() 
	{
		return ID;
	}



	public void setID(String iD) 
	{
		ID = iD;
	}



	public String getFirst() 
	{
		return first;
	}



	public void setFirst(String first) 
	{
		this.first = first;
	}



	public String getLast() 
	{
		return last;
	}



	public void setLast(String last) 
	{
		this.last = last;
	}



	public String toString()
    {
		//only output non-null values
		String output = "";
	    if (last != null && first != null && ID != null) 
	    {
	        output = last + ", " + first + " (" + ID + ")";
	        return output;
	    }
	    else {	   
	    	output=""; 
	    	return output;
	    }
    }

}
