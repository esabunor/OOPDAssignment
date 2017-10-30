import java.util.*;

public class Tyrannosaurus extends Dinosaur
{	
	//instance variables
	private double biteForce;
	
	/***************************************************************
    *Purpose: Default constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/ 
	public Tyrannosaurus()
	{
		super("trex the dino", 20.0, "AGCT");
		this.biteForce = 640.2;
	}
	
	/***************************************************************
    *Purpose: Alternate constructor
    *Export: none                                        
    *Import: String name, double mass, String sequence, Double biteForce
	***************************************************************/ 
	public Tyrannosaurus(String name, double mass, String sequence, Double biteForce) 
	{
		super(name, mass, sequence);//calls super alternate constructor
		this.biteForce = validateBiteForce(biteForce);
	}

	/***************************************************************
    *Purpose: Copy constructor
    *Export: none                                        
    *Import: a Tyrannosaurus instance 
	***************************************************************/ 
	public Tyrannosaurus(Tyrannosaurus tyrannosaurus) 
	{
		super(tyrannosaurus.getName(), tyrannosaurus.getMass(), tyrannosaurus.getGenome().getSequence());
		this.biteForce = tyrannosaurus.getBiteForce();
	}
	
	/***************************************************************
    *Purpose: Accessor of biteForce
    *Export: biteForce                                        
    *Import: none
	***************************************************************/ 
	public double getBiteForce() 
	{
		return biteForce;
	}

	/***************************************************************
    *Purpose: Mutator for biteForce
    *Export: none                                        
    *Import: biteForce
	***************************************************************/ 
	public void setBiteForce(double biteForce) 
	{
		this.biteForce = validateBiteForce(biteForce);//validates biteforce
	}

	/***************************************************************
    *Purpose: calc Enclosure size of a tyrannosaurus
    *Export: size of dinosaur                                        
    *Import: none
	***************************************************************/ 
	public int calcEnclosureSize() 
	{
		return (int) ((Math.log10(this.getMass())/Math.log10(2.0)) * biteForce * 10.0);
	}
	
	public double validateBiteForce(double biteForce)
	{
		if (biteForce < 500.5)//throws exception if bite force is less than 500.5
			throw new IllegalArgumentException("Bite Force must be greater than 500.5");
		return biteForce;
	}

	/***************************************************************
    *Purpose: File string representation of dinosaur
    *Export: String                                        
    *Import: nome
	***************************************************************/ 
	public String toFile() 
	{
		return this.getName() + " , " + this.getMass() + " , " + this.getGenome().getSequence() + " , " + this.getBiteForce();
	}
	
	/***************************************************************
    *Purpose: clone method for Tyrannosaurus
    *Export: Tyrannosaurus. clone                                        
    *Import: none 
	***************************************************************/
	public Dinosaur clone() 
	{
		return new Tyrannosaurus(this);
	}
	
	/***************************************************************
    *Purpose: equals method for Tyrannosaurus
   	*Export: boolean                                         
    *Import: obj (Object) 
	***************************************************************/
	public boolean equals(Object obj)
	{
		boolean equals = false;
		Tyrannosaurus other = (Tyrannosaurus) obj;
		if (other.getGenome() == this.getGenome())
			if (other.getName() == this.getName())
				if (other.getMass() == this.getMass())
					if (other.getBiteForce() == this.biteForce)
						equals = true;
		return equals;
	}
	
	/***************************************************************
    *Purpose: toString method for Tyrannosaurus
    *Export: String                                         
    *Import: none
	***************************************************************/
	public String toString()
	{
		return "<TREX, " + this.getName() + " , " + this.getMass() + " , " + this.getGenome().getSequence() + " , " + this.getBiteForce() + "/>";
	}
	
}
