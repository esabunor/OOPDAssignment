import java.util.*;

public class Genome 
{
	private String sequence;
	
	/***************************************************************
    *Purpose: Default constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/  
	public Genome()
	{
		this.sequence = "AGTC";
	}
	
	/***************************************************************
    *Purpose: Alternate constructor
    *Export: none                                        
    *Import: sequence for genome 
	***************************************************************/ 
	public Genome(String sequence)
	{
		this.sequence = validateSequence(sequence);
	}
	
	/***************************************************************
    *Purpose: Copy constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/ 
	public Genome(Genome inGenome)
	{
		this.sequence = inGenome.getSequence();
	}

	/***************************************************************
    *Purpose: Accessor for sequence
    *Export: this.sequence                                        
    *Import: none 
	***************************************************************/ 
	public String getSequence() 
	{
		return sequence;
	}

	/***************************************************************
    *Purpose: Mutator for sequence
    *Export: none                                        
    *Import: sequence 
	***************************************************************/ 
	public void setSequence(String sequence) 
	{
		this.sequence = validateSequence(sequence);//validates sequence
	}
	
	/***************************************************************
    *Purpose: Validates sequence
    *Export: none                                        
    *Import: sequence 
	***************************************************************/ 
	public String validateSequence(String sequence)
	{	sequence = sequence.trim().toUpperCase();//removes whitespaces and converts to lowercase
		for(int i = 0; i < sequence.length(); i++)
		{
			//loops through the characters in the sequence ensuring that its either A G C or T
			//throws an exception if otherwise
			if(!(sequence.charAt(i) == 'A' || sequence.charAt(i) == 'G' || sequence.charAt(i) == 'C' || sequence.charAt(i) == 'T' ))
				throw new IllegalArgumentException("Sequence must be a string of AGTC");
		}
		return sequence;
	}
	
	/***************************************************************
    *Purpose: clone method for genome
    *Export: genome cloen                                        
    *Import: none 
	***************************************************************/
	public Genome clone()
	{
		return new Genome(this);//constructs using this
	}
	
	/***************************************************************
    *Purpose: toString method for genome
    *Export: sequence                                         
    *Import: none 
	***************************************************************/
	public String toString()
	{
		return this.sequence;
	}
	
	/***************************************************************
    *Purpose: equals method for genome
    *Export: boolean                                         
    *Import: obj (Objectg) 
	***************************************************************/
	public boolean equals(Object obj)
	{
		Genome other = (Genome) obj;
		boolean equals = false;
		if (this.sequence.equalsIgnoreCase(other.getSequence()))
			equals = true;
		return equals;
	}
}
