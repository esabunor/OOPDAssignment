import java.util.*;

/***************************************************************
*Date: Mon 30th October                                       
*Author: Tega Esabunor-Nukie 19048895                         
*Activity: OOPDASSIGNMENT                                     
***************************************************************/ 
public class Plesiosaur extends Dinosaur 
{
	private boolean hasTailFin;
	
	/***************************************************************
    *Purpose: Default constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/ 
	public Plesiosaur()
	{
		super("plesio the dino", 20.0, "AGCT");
		this.hasTailFin = true;
	}
	
	/***************************************************************
    *Purpose: Alternate constructor
    *Export: none                                        
    *Import: String name, double mass, String sequence, boolean hasTailFin
	***************************************************************/ 
	public Plesiosaur(String name, double mass, String sequence, boolean hasTailFin) 
	{
		super(name, mass, sequence);//calls alternate super constructor
		this.hasTailFin = hasTailFin;
	}

	/***************************************************************
    *Purpose: Copy constructor
    *Export: none                                        
    *Import: a Triceratops instance 
	***************************************************************/ 
	public Plesiosaur(Plesiosaur plesiosaur) 
	{
		super(plesiosaur.getName(), plesiosaur.getMass(), plesiosaur.getGenome().getSequence());
		this.hasTailFin = plesiosaur.isHasTailFin();
	}


	/***************************************************************
    *Purpose: calc Enclosure size of a triceratops
    *Export: size of dinosaur                                        
    *Import: none
	***************************************************************/ 
	public int calcEnclosureSize() 
	{
		return (int) (this.getMass() * Math.PI * Math.pow(10.0, 3.0) );//mass * PI * 10^3
	}

	/***************************************************************
    *Purpose: Accessor of hasTailFin
    *Export: hasTailFin                                        
    *Import: none
	***************************************************************/ 
	public boolean isHasTailFin() 
	{
		return hasTailFin;
	}

	/***************************************************************
    *Purpose: File string representation of dinosaur
    *Export: none                                        
    *Import: a Plesiosaur instance 
	***************************************************************/ 
	public String toFile() 
	{
		return this.getName() + " , " + this.getMass() + " , " + this.getGenome().getSequence() + " , " + this.isHasTailFin() ;
	}
	
	/***************************************************************
    *Purpose: equals method for plesiosaur
    *Export: boolean                                         
    *Import: obj (Objectg) 
	***************************************************************/
	public boolean equals(Object obj)
	{
		boolean equals = false;
		Plesiosaur other = (Plesiosaur) obj;
		if (other.getGenome() == this.getGenome())
			if (other.getName() == this.getName())
				if (other.getMass() == this.getMass())
					if (other.isHasTailFin() == this.hasTailFin)
						equals = true;
		return equals;
	}
	
	/***************************************************************
    *Purpose: clone method for PLESIO
    *Export: Triceratops cloen                                        
    *Import: none 
	***************************************************************/
	public Dinosaur clone() 
	{
		
		return new Plesiosaur(this);
	}
	
	/***************************************************************
    *Purpose: toString method for Plesiosaur
    *Export: String                                         
    *Import: none
	***************************************************************/
	public String toString()
	{
		return "<PLESIO, " + this.getName() + " , " + this.getMass() + " , " + this.getGenome().getSequence() + " , " + this.isHasTailFin() + "/>";
	}
}
