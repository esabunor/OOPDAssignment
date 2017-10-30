import java.util.*;

/***************************************************************
*Date: Mon 30th October                                       
*Author: Tega Esabunor-Nukie 19048895                         
*Activity: OOPDASSIGNMENT                                     
***************************************************************/ 
public class Triceratops extends Dinosaur
{
	private int numHorns;
	
	/***************************************************************
    *Purpose: Default constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/ 
	public Triceratops()
	{
		super("tricera the dino", 20.0, "AGCT");//calls super alternate constructor
		this.numHorns = 3;
	}
	
	/***************************************************************
    *Purpose: Alternate constructor
    *Export: none                                        
    *Import: String name, double mass, String sequence, int numHorns
	***************************************************************/ 
	public Triceratops(String name, double mass, String sequence, int numHorns) 
	{
		super(name, mass, sequence);
		this.numHorns = validateNumHorns(numHorns);
	}

	/***************************************************************
    *Purpose: Copy constructor
    *Export: none                                        
    *Import: a Triceratops instance 
	***************************************************************/ 
	public Triceratops(Triceratops triceratops) 
	{
		super(triceratops.getName(), triceratops.getMass(), triceratops.getGenome().getSequence());//super constructor call
	}

	/***************************************************************
    *Purpose: calc Enclosure size of a triceratops
    *Export: size of dinosaur                                        
    *Import: none
	***************************************************************/ 
	public int calcEnclosureSize() 
	{
		return (int) (this.getMass() * 5.5 * Math.pow(4.0,  this.numHorns));
	}

	/***************************************************************
    *Purpose: Accessor of numHorns
    *Export: numHorns                                        
    *Import: none
	***************************************************************/ 
	public int getNumHorns() 
	{
		return numHorns;
	}

	/***************************************************************
    *Purpose: Mutator for numHorns
    *Export: none                                        
    *Import: numHorns
	***************************************************************/ 
	public void setNumHorns(int numHorns) 
	{
		this.numHorns = validateNumHorns(numHorns);
	}

	/***************************************************************
    *Purpose: File string representation of dinosaur
    *Export: none                                        
    *Import: a Triceratop0s instance 
	***************************************************************/ 
	public String toFile() 
	{
		//returns how a dionsaur is to represented in a file
		return this.getName() + " , " + this.getMass() + " , " + this.getGenome().getSequence() + " , " + this.getNumHorns();
	}
	
	/***************************************************************
    *Purpose: validates numHOrns
    *Export: numHorns                                        
    *Import: numHorns 
	***************************************************************/ 
	private int validateNumHorns(int numHorns)
	{
		if(numHorns % 3 != 0)//numhorns must be a multiple of 3
			throw new IllegalArgumentException("Number of Horns must be a multiple of 3");
		return numHorns;
	}
	
	/***************************************************************
    *Purpose: equals method for Triceratops
    *Export: boolean                                         
    *Import: obj (Objectg) 
	***************************************************************/
	public boolean equals(Object obj)
	{
		boolean equals = false;
		Triceratops other = (Triceratops) obj;
		if (other.getGenome() == this.getGenome())
			if (other.getName() == this.getName())
				if (other.getMass() == this.getMass())
					if (other.getNumHorns() == this.numHorns)
						equals = true;
		return equals;
	}
	
	/***************************************************************
    *Purpose: clone method for Triceratops
    *Export: Triceratops cloen                                        
    *Import: none 
	***************************************************************/
	public Triceratops clone() 
	{
		return new Triceratops(this);//construct an instance using this
	}
	
	/***************************************************************
    *Purpose: toString method for Triceratops
    *Export: String                                         
    *Import: none
	***************************************************************/
	public String toString()
	{
		return "<TRI, " + this.getName() + " , " + this.getMass() + " , " + this.getGenome().getSequence() + " , " + this.getNumHorns() + "/>";
	}
	
}
