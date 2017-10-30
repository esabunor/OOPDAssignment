import java.io.*;
import java.util.*;

/***************************************************************
*Date: Mon 30th October                                       
*Author: Tega Esabunor-Nukie 19048895                         
*Activity: OOPDASSIGNMENT                                     
***************************************************************/ 
public abstract class Dinosaur 
{
	//Shared instance variable of all subclasses
	private String name;
	private double mass;
	private Genome genome;
	
	/***************************************************************
    *Purpose: Default constructor
    *Export: none                                        
    *Import: none 
	***************************************************************/   
	public Dinosaur()
	{
		this.mass = 0.0;
		this.name = "Default dinosaur";
		this.genome = new Genome();
		
	}
	
	/***************************************************************
    *Purpose: Alternate constructor
    *Export: none                                        
    *Import: name, mass, sequence for genome 
	***************************************************************/ 
	public Dinosaur(String name, double mass, String sequence) 
	{
		this.name = validateName(name);
		this.mass = validateMass(mass);
		this.genome = new Genome(sequence);//constructs a new genome with sequence
	}
	
	/***************************************************************
    *Purpose: Copy constructor
    *Export: none                                        
    *Import: a Dinosaur instance 
	***************************************************************/ 
	public Dinosaur(Dinosaur dinosaur)
	{
		this.mass = dinosaur.getMass();
		this.name  = dinosaur.getName();
		this.genome = dinosaur.getGenome();
	}

	/***************************************************************
    *Purpose: Accessor for Name
    *Export: this.name                                        
    *Import: none 
	***************************************************************/ 
	public String getName() 
	{
		return name;
	}

	/***************************************************************
    *Purpose: Accessor for mass
    *Export: this.mass                                        
    *Import: none 
	***************************************************************/ 
	public double getMass() 
	{
		return mass;
	}

	/***************************************************************
    *Purpose: Accessor for genome
    *Export: none                                        
    *Import: none 
	***************************************************************/ 
	public Genome getGenome() 
	{
		return genome.clone();//clones genome because its an object
	}

	/***************************************************************
    *Purpose: Mutator for name
    *Export: none                                        
    *Import: name 
	***************************************************************/ 
	public void setName(String name) 
	{
		this.name = validateName(name);//validateName returns name if valid
	}

	/***************************************************************
    *Purpose: Mutator for mass
    *Export: none                                        
    *Import: mass 
	***************************************************************/ 
	public void setMass(double mass) 
	{
		this.mass = validateMass(mass);//validates mass 
	}

	/***************************************************************
    *Purpose: Mutator for genome
    *Export: none                                        
    *Import: genome 
	***************************************************************/ 
	public void setGenome(Genome genome) 
	{
		this.genome = genome;
	}
	
	/***************************************************************
    *Purpose: Validator for name
    *Export: none                                        
    *Import: name 
	***************************************************************/ 
	private String validateName(String name)
	{
		if(name.equalsIgnoreCase(""))//if name is empty an exceotion will be thrown
			throw new IllegalArgumentException("Name can't be empty ");
		return name;
	}
	
	/***************************************************************
    *Purpose: Validator for mass
    *Export: none                                        
    *Import: mass 
	***************************************************************/ 
	private double validateMass(double mass)
	{
		if(mass < 0)//does not permit a negative mass
			throw new IllegalArgumentException("Mass cannot be less not zero");
		return mass;
	}
	
	/***************************************************************
    *Purpose: Writes all Triceratops in a dinosaur array
    *Export: none                                        
    *Import: array of dinosaurs and count 
	***************************************************************/ 
	public static void printTriceratops(Dinosaur[][] dinosaurs, int count)
	{
		PrintWriter pw = null;
		//terniary operator to produce a col value that is 0 if the count is a multiple of 10 or its remainder less 1 when divided by 10
		int col = ((count % 10) == 0)? 0 : (count % 10) - 1, row = count /10;//a jurassic park always has a fixed column of 10 but a varying row size to accomodate
		try {
			pw = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't write to file " + e.getMessage());
		}
		for(int i = 0; i<= row; i++)
		{
			for (int j = 0; j <= col; j++)
			{
				if(dinosaurs[i][j] instanceof Triceratops)//checks if an instance of Triceratops
					pw.println(dinosaurs[i][j].toFile());//file representation of dinosaur
			}
		}
		pw.close();
	}
	
	/***************************************************************
    *Purpose: Writes all Plesiosaur in a dinosaur array
    *Export: none                                        
    *Import: array of dinosaurs and count 
	***************************************************************/ 
	public static void printPlesiosaur(Dinosaur[][] dinosaurs, int count)
	{
		PrintWriter pw = null;
		//terniary operator to produce a col value that is 0 if the count is a multiple of 10 or its remainder less 1 when divided by 10
		int col = ((count % 10) == 0)? 0 : (count % 10) - 1, row = count /10;//a jurassic park always has a fixed column of 10 but a varying row size to accomodate
		try {
			pw = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		for(int i = 0; i <= row; i++)
		{
			for (int j = 0; j <= col; j++)
			{
				if(dinosaurs[i][j] instanceof Plesiosaur)
					pw.println(dinosaurs[i][j].toFile());//checks if an instance of Plesiosaur
			}
		}
		pw.close();
	}
	
	/***************************************************************
    *Purpose: Writes all Triceratops in a dinosaur array
    *Export: none                                        
    *Import: array of dinosaurs and count 
	***************************************************************/ 
	public static void printTyrannosaurus(Dinosaur[][] dinosaurs, int count)
	{
		PrintWriter pw = null;
		//terniary operator to produce a col value that is 0 if the count is a multiple of 10 or its remainder less 1 when divided by 10
		int col = ((count % 10) == 0)? 0 : (count % 10) - 1, row = count /10;
		try {
			pw = new PrintWriter("output.txt");
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		for(int i = 0; i <= row; i++)
		{
			for (int j = 0; j <= col; j++)
			{
				if(dinosaurs[i][j] instanceof Tyrannosaurus)//checks if an instance of Tyrannosaurus
					pw.println(dinosaurs[i][j].toFile());
			}
		}
		pw.close();
	}

	
	//abstract methods to be implemented by sub classes
	public abstract String toFile();
	public abstract Dinosaur clone();
	public abstract int calcEnclosureSize();

}