import java.io.*;
import java.util.*;

/***************************************************************
*Date: Mon 30th October                                       
*Author: Tega Esabunor-Nukie 19048895                         
*Activity: OOPDASSIGNMENT                                     
***************************************************************/ 
public class JurassicPark 
{
	//instance variables
	private Dinosaur[][] dinosaurs;
	private int count;//no of dinosaurs
	private boolean constructed;//if constructed
	
	/***************************************************************
    *Purpose: Default constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/ 
	public JurassicPark()
	{
		this.dinosaurs = new Dinosaur[10][10];//initialize with 100 dinosauruses
		this.count = 0;
		this.constructed = true;
	}

	/***************************************************************
    *Purpose: Alternate constructor
    *Export: none                                        
    *Import: Dinosaur[][] dinosaurus, int count
	***************************************************************/ 
	public JurassicPark(Dinosaur[][] dinosaurus, int count) 
	{
		if(count > 1000)//does not allow more than 1000 dinosaurs
			throw new IllegalArgumentException("Dinosaurs cannot be more than 1000");
		this.dinosaurs = dinosaurus;
		this.count = count;
		this.constructed = true;
	}
	
	/***************************************************************
    *Purpose: Copy constructor
    *Export: none                                        
    *Import: a JurassicPark instance 
	***************************************************************/ 
	public JurassicPark(JurassicPark jp)
	{
		this.dinosaurs = jp.getDinosaurs();
		this.count = jp.getCount();
		this.constructed = jp.isConstructed();
	}

	/***************************************************************
    *Purpose: Accessor for Dinosaurs array
    *Export: this.name                                        
    *Import: none 
	***************************************************************/ 
	public Dinosaur[][] getDinosaurs() 
	{	
		//create a deep copy of the dinosaur by iterating of the instance varibale and cloning
		//each instance stored in the array
		Dinosaur[][] copyDinosaur = new Dinosaur[this.dinosaurs.length][this.dinosaurs[0].length];
		int row = count / 10, col = ((count % 10) == 0)? 0 : (count % 10) - 1; 
		for(int i = 0; i <= row; i++)
		{
			for (int j = 0; j <= col; j++)
			{
				copyDinosaur[i][j] = dinosaurs[i][j].clone();//calls the dinosaurs clone method
			}
		}
		return copyDinosaur;
	}
	
	/***************************************************************
    *Purpose: Accessor for count
    *Export: this.count                                        
    *Import: none 
	***************************************************************/
	public int getCount() 
	{
		return count;
	}
	
	/***************************************************************
    *Purpose: Accessor for constructed
    *Export: this.constructed                                        
    *Import: none 
	***************************************************************/
	public boolean isConstructed() 
	{
		return constructed;
	}
	
	/***************************************************************
    *Purpose: Mutator for Dinosaurs
    *Export: none                                        
    *Import: array of dinosaurus 
	***************************************************************/ 
	public void setDinosaurs(Dinosaur[][] dinosaurus) 
	{
		if (dinosaurus.length * dinosaurus[0].length > 1000)//checks if the array contains more than 1000
			throw new IllegalArgumentException("Jurassic Park cannot have more than 1000 dinosaurs");
		this.dinosaurs = dinosaurus;
	}
	
	/***************************************************************
    *Purpose: Mutator for count
    *Export: none                                        
    *Import: count 
	***************************************************************/ 
	public void setCount(int count) 
	{
		this.count = count;
	}
	
	/***************************************************************
    *Purpose: Mutator for constructed
    *Export: none                                        
    *Import: constructed 
	***************************************************************/ 
	public void setConstructed(boolean constructed) 
	{
		this.constructed = constructed;
	}
		
	/***************************************************************
    *Purpose: Adds a new dinosaur to the array of dinosaurs
    *Export: none                                        
    *Import: dinosaur 
	***************************************************************/ 
	public void addDinosaur(Dinosaur dinosaur) 
	{
		int row, col;
		//checks if an additional dinosaur will not exceed the maximum of 1000 
		if((count + 1) < (dinosaurs.length * dinosaurs[0].length) && (count + 1) < 1000)
		{	
			count++;
			col = ((count % 10) == 0)? 0 : (count % 10) - 1;
			row = count /10;
			dinosaurs[row][col] = dinosaur;
			
		}
		else 
		{	
			//throw exception if no more space
			throw new IllegalArgumentException("There's no more space in ther jurassic park");
		}
	}
	
	/***************************************************************
    *Purpose: clone method for jurassicpark
    *Export: jurassic park cloen                                        
    *Import: none 
	***************************************************************/
	public JurassicPark clone()
	{
		return new JurassicPark(this);
	}
	
	/***************************************************************
    *Purpose: toString method for jurassic park
    *Export: boolean                                         
    *Import: obj (Object) 
	***************************************************************/
	public String toString()
	{
		return "Jurassic Park with " + count + " number of dinosaurs";
	}
	
	/***************************************************************
    *Purpose: equals method for jurassicpark
    *Export: boolean                                         
    *Import: obj (Objectg) 
	***************************************************************/
	public boolean equals(Object obj)
	{
		boolean isEquals = false;
		JurassicPark other = (JurassicPark) obj;
		if(other.getDinosaurs() == this.dinosaurs)
			if(this.constructed == other.isConstructed())
				if(other.getCount() == this.count)
					isEquals = true;
		return isEquals;
	}
	
}

