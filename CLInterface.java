import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

/***************************************************************
*Date: Mon 30th October                                       
*Author: Tega Esabunor-Nukie 19048895                         
*Activity: OOPDASSIGNMENT                                     
***************************************************************/ 
public class CLInterface 
{	
	private static JurassicPark jp; //private static variable of this class holding a reference to a Jurassic Park
	
	/***************************************************************
    *Purpose: To insert a dinosaur to the jurassicpark      
    *Date: Mon 30th October                                       
    *Import: a newly constructed dinosaur                       
    *Export: none                      
    *Assertion: will either create a new jurassic park or add to it                 
    ***************************************************************/
	public static void addDinosaur(Dinosaur dinosaur)
	{
		if(jp == null)
			jp = new JurassicPark();//constructs a jurassic park of 100 dinosaurs
		jp.addDinosaur(dinosaur);//calls the addDinosaur instance method of the jurassic park
	}
	
	/***************************************************************
    *Purpose: To convert a standard input string to an array  of dinosaur properties     
    *Date: Mon 30th October                                       
    *Import: none                       
    *Export: none                        
    *Assertion:                   
    ***************************************************************/
	public static void inputDinosaur()
	{
		System.out.println("What type of dinosaur");
		System.out.println("1. Triceratops");
		System.out.println("2. Plesiosaur");
		System.out.println("3. Tyrannosaurus");
		Scanner sc = new Scanner(System.in);
		int option = new Integer(sc.nextLine()).intValue();//read a new line and convert it to Integer obj, then int
		System.out.println("Provide the dinosaur information in the following format");
		System.out.println("Name, Mass, Genome, Other Characteristic");
		String dinoString = sc.nextLine();
		String[] dinoArr = dinoString.split(",");// split dinosaurs characteristics to a String array
		switch(option)
		{
		case 1:
			addDinosaur(instantiateTriceratops(dinoArr));//constructs a Triceratop
			break;
		case 2:
			addDinosaur(instantiatePlesiosaur(dinoArr));//construct a Plesiosaur
			break;
		case 3:
			addDinosaur(instantiateTyrannosaurus(dinoArr));//constructs a Tyrannosaurus
			break;
		default:
			System.out.println("Invalid option");
		}
	}
	
	/***************************************************************
    *Purpose: Called to display all the dinosaurs in the jurassic park      
    *Date: Mon 30th October                                       
    *Import: none                      
    *Export: none                        
    *Assertion:                   
    ***************************************************************/
	public static void displayDinosaurs()
	{
		if(jp == null)//if the jurassic park has not been constructed an exception is thrown
			throw new IllegalArgumentException("Jurassic park has not been constructed there are no dinosaurs");
		int count = jp.getCount();
		//terniary operator to produce a col value that is 0 if the count is a multiple of 10 or its remainder less 1 when divided by 10
		int row = count / 10, col = ((count % 10) == 0)? 0 : (count % 10) - 1;//a jurassic park always has a fixed column of 10 but a varying row size to accomodate
		for(int i = 0; i <= row; i++)
		{
			for(int j = 0; j <= col; j++)
			{
				System.out.println(jp.getDinosaurs()[i][j].toString());//toString method produces the required Standard output representation of each dinosaur
			}
		}
	}
	
	/***************************************************************
    *Purpose: Calls the individual calcEnclosureSize of each dinosaur in the park      
    *Date: Mon 30th October                                       
    *Import: NOne                    
    *Export: None
    *Assertion: 
    ***************************************************************/
	public static void calcParkSize()
	{
		int size = 0;
		if(jp == null)//if the jurassic park has not been constructed an exception is thrown telling the user that no park is available
			throw new IllegalArgumentException("Jurassic park has not been constructed so size = 0");
		int count = jp.getCount();
		//terniary operator to produce a col value that is 0 if the count is a multiple of 10 or its remainder less 1 when divided by 10
		int col = ((count % 10) == 0)? 0 : (count % 10) - 1, row = count /10;
		
		for(int i = 0; i <= row; i++)
		{
			for(int j = 0; j <= col; j++)
			{	
				size += jp.getDinosaurs()[i][j].calcEnclosureSize();//calls the individual calcEnclosureSize method by means of polymorphism
				
			}
		}
		System.out.println("Enclosure size is: " + size);
	}
	
	/***************************************************************
    *Purpose: To do the actually dinosaur reading and saving to array
    *Date: Mon 30th October                                       
    *Import: the filename to read the dinosaurs from and the dinosaurcount                   
    *Export: A string of the truncated area                        
    *Assertion:                   
    ***************************************************************/
	public static void readDinosaurs(String filename, int dinosaurCount)
	{
		InputStreamReader ins = null;
		FileInputStream fin = null;
		BufferedReader bfRdr = null;
		String line;
		Dinosaur[][] dinosaurs = null;//stores the dinosaurs temporarily before stored in jurassic ark
		Pattern pattern = Pattern.compile("(((\\s)*[\\w+\\,](\\s)*)+((\\s)*\\w+(\\s)*\\,)+((\\s)*\\d+(\\.)*\\d+(\\s)*\\,)((\\s)*\\w+(\\s)*\\,)((\\s)*[(\\w+).]*))");//regular expression that matches a single valid dinosaur input
		try 
		{
			fin = new FileInputStream(filename);
			ins = new InputStreamReader(fin);
			bfRdr = new BufferedReader(ins);
			line = bfRdr.readLine();
			
			int dinosaurCount2 = 0;//a second count to control the position of where dinosaurs are stored
			dinosaurs = new Dinosaur[(dinosaurCount / 10) + 1][10];
			int row, col;
			while(line != null)
			{
				
				Matcher matcher = pattern.matcher(line);//a matcher that determines how often an where a match of the regular expression occurs in the input string
				while(matcher.find())//while there's still a match
				{	
					dinosaurCount2++;
					col = ((dinosaurCount2 % 10) == 0)? 0 : (dinosaurCount2 % 10) - 1;
					row = dinosaurCount2 /10;
					String dinoString = line.substring(matcher.start(), matcher.end());//.start and .end give the posittion of the matched string which is extracted by .substring method
					String[] dinoStringArr = dinoString.split(",");
					//the first entry of the dinoStringArr is ignored i.e TRI, TREX, and PLESIO
					if(dinoStringArr[0].trim().equalsIgnoreCase("tri"))//instantiates a Triceratops
						dinosaurs[row][col] = instantiateTriceratops(new String[]{dinoStringArr[1],dinoStringArr[2],dinoStringArr[3],dinoStringArr[4]});
					else if(dinoStringArr[0].trim().equalsIgnoreCase("trex"))//constructs a Tyrannosaurus
						dinosaurs[row][col] = instantiateTyrannosaurus(new String[]{dinoStringArr[1],dinoStringArr[2],dinoStringArr[3],dinoStringArr[4]});
					else if(dinoStringArr[0].trim().equalsIgnoreCase("plesio"))//constructs a Plesiosarur
						dinosaurs[row][col] = instantiatePlesiosaur(new String[]{dinoStringArr[1],dinoStringArr[2],dinoStringArr[3],dinoStringArr[4]});
					else
						throw new IllegalArgumentException("The file is not in a correct format");// an exception is thrown if it doesn't match TRI, TREX, or PLESIO
					
				}
				line = bfRdr.readLine();
			}
			jp = new JurassicPark(dinosaurs, dinosaurCount);//a new jurassic park is the constructed with the dinosaurs array
		}
		catch (IOException e) 
		{
			System.out.println("An error occured while parsing the file " + e.getMessage());
		}
		
		
		
	}
	
	/***************************************************************
    *Purpose: Counts how many dinosaurs are available in the file and calls readDinosaurs after that     
    *Date: Mon 30th October                                       
    *Import: none                    
    *Export: none                      
    *Assertion: will call readDinosaurs if a valid file is provide or throw an exception                 
    ***************************************************************/
	public static void readFile() throws Exception
	{
		if(jp != null)
			throw new Exception("Jurassic Park cannot be ocnstructed it has already been constructed");// an exception is thrown if its constructed already
		InputStreamReader ins = null;
		FileInputStream fin = null;
		BufferedReader bfRdr = null;
		String line;
		Pattern pattern = Pattern.compile("(((\\s)*[\\w+\\,](\\s)*)+((\\s)*\\w+(\\s)*\\,)+((\\s)*\\d+(\\.)*\\d+(\\s)*\\,)((\\s)*\\w+(\\s)*\\,)((\\s)*[(\\w+).]*))");//regular expression that matches a single valid dinosaur input
		
		int dinosaurCount = 0;//maintains a count of the dinosaurs in the file
		Scanner sc = new Scanner(System.in);
		System.out.println("Please provide the name of the file to read ");
		String filename = sc.nextLine();//reads in the filename
		try 
		{
			fin = new FileInputStream(filename);
			ins = new InputStreamReader(fin);
			bfRdr = new BufferedReader(ins);
			line = bfRdr.readLine();
			
			while(line != null)//while there's still a match
			{
				Matcher matcher = pattern.matcher(line);//a matcher that determines how often an where a match of the regular expression occurs in the input string
				while(matcher.find())
				{	
					dinosaurCount++;
					String dinoString = line.substring(matcher.start(), matcher.end());
					String[] dinoStringArr = dinoString.split(",");
					if(!(dinoStringArr[0].trim().equalsIgnoreCase("tri") || dinoStringArr[0].trim().equalsIgnoreCase("trex") || dinoStringArr[0].trim().equalsIgnoreCase("plesio")))
						throw new IllegalArgumentException("The file is not in a correct format");// an exception is thrown if it doesn't match TRI, TREX, or PLESIO
				}
				line = bfRdr.readLine();
			}
			
			
		} 
		catch (IllegalArgumentException e2)
		{
			System.out.println("The file is in an invalid format");
		}
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				bfRdr.close();
			} catch (IOException e) {
				System.out.println("Error while closing stream " + e.getMessage());
			}
		}
		readDinosaurs(filename, dinosaurCount);//proceeds to call readDinosaurs which does the actually saving to array
	}
	
	/***************************************************************
    *Purpose: Constructs a Plesiosaur     
    *Date: Mon 30th October                                       
    *Import: An array of Strings of dinosaur properties                    
    *Export: none                       
    *Assertion:                   
    ***************************************************************/
	private static Dinosaur instantiatePlesiosaur(String[] dinoStringArr) 
	{
		String name = dinoStringArr[0];
		double mass = new Double(dinoStringArr[1].trim()).doubleValue();//converts string to double value
		String sequence = dinoStringArr[2];
		boolean hasTailFin = new Boolean(dinoStringArr[3].trim()).booleanValue();//converts string to boolean value
		return new Plesiosaur(name, mass, sequence, hasTailFin);//constructs Plesiosaur
	}
	
	/***************************************************************
    *Purpose: Constructs a Tyrannosaurus       
    *Date: Mon 30th October                                       
    *Import: An array of Strings of dinosaur properties                    
    *Export: none
    *Assertion:                   
    ***************************************************************/
	private static Dinosaur instantiateTyrannosaurus(String[] dinoStringArr) 
	{
		String name = dinoStringArr[0];
		double mass = new Double(dinoStringArr[1].trim()).doubleValue();//converts to double value
		String sequence = dinoStringArr[2];
		double biteForce = new Double(dinoStringArr[3].trim()).doubleValue();//converts string to double value
		return new Tyrannosaurus(name, mass, sequence, biteForce);//constructs Tyrannosaurus
	}
	
	/***************************************************************
    *Purpose: Constructs a Triceratops       
    *Date: Mon 30th October                                       
    *Import: An array of Strings of dinosaur properties                    
    *Export: none
    *Assertion:                    
    ***************************************************************/
	private static Dinosaur instantiateTriceratops(String[] dinoStringArr) 
	{
		String name = dinoStringArr[0];
		double mass = new Double(dinoStringArr[1].trim()).doubleValue();//converts to double value
		String sequence = dinoStringArr[2];
		int numHorns = new Integer(dinoStringArr[3].trim()).intValue();//converts string to int value
		return new Triceratops(name, mass, sequence, numHorns);
	}
	
	/***************************************************************
    *Purpose: Writes to output.txt the available dinosaurs 
    *Date: Mon 30th October                                       
    *Import: none                      
    *Export: none                       
    *Assertion:                   
    ***************************************************************/
	public static void writeFile()
	{
		if(jp != null)
		{
			System.out.println("Select which type of dinosaur you want to print to file");
			System.out.println("a. Triceratops");
			System.out.println("b. Tyrannosaurus");
			System.out.println("c. Plesiosaur");
			Scanner sc = new Scanner(System.in);
			String option = sc.nextLine().toLowerCase();//converts option to lowercase for comparing
			switch(option.charAt(0))//extracts the first character
			{
			case 'a':
				//write triceratops
				Dinosaur.printTriceratops(jp.getDinosaurs(), jp.getCount());
				break;
			case 'b':
				//write tyrannosaurus
				Dinosaur.printTyrannosaurus(jp.getDinosaurs(), jp.getCount());
				break;
			case 'c':
				//write plesiosaur
				Dinosaur.printPlesiosaur(jp.getDinosaurs(), jp.getCount());
				break;
			default:
				System.out.println("invalid option");
			}
		}
		else 
		{
			System.out.println("There are no dinosaurs in the jurassic park" );//if jurassic park hasnt been constructed
		}
		
	}

	/***************************************************************
    *Purpose: Main interface method exposing the main menu      
    *Date: Mon 30th October                                       
    *Import: none                       
    *Export: none                    
    *Assertion:                   
    ***************************************************************/
	public static void run()
	{
		boolean exit = false;
		while(!exit)
		{
			
			try
			{	
				System.out.println("Select from the following options");
				System.out.println("1. Add a dinosaur");
				System.out.println("2. Display Dinosaurs");
				System.out.println("3. Calculate Park Size");
				System.out.println("4. Read in a jurassic park file");
				System.out.println("5. Write out to jurassic park file");
				System.out.println("6. Exit");
				Scanner sc = new Scanner(System.in);
				int option = new Integer(sc.nextLine()).intValue();
				switch(option)
				{
				case 1:
					inputDinosaur();//called to read dinosaur from stdin
					break;
				case 2:
					displayDinosaurs();//displays all dinosaurs in the park
					break;
				case 3:
					calcParkSize();//calculates the size
					break;
				case 4:
					try 
					{
						readFile();
					} 
					catch (Exception e) 
					{
						
						System.out.println("You have provided an invalid file " + e.getMessage());
					}
					break;
				case 5:
					writeFile();//called to write to output.txt
					break;
				case 6:
					exit = true;
					System.out.println("Goodbye");
					break;
				default:
					System.out.println("Invalid selection");
					break;
				}
			}
			catch (Exception e2)
			{
				System.out.println("You have provided an invalid input please try again " + e2.getMessage());
			}
			
		}
		
	}
}
;