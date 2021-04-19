package file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PowerPlant;
import model.Route;

/**
 * 
 * @author Kubeka 217010763
 */
public class SaveToFile {
DataOutputStream dataSave = null;
	public SaveToFile(){
            
        }

    
    /**
     * Adding powerPlant
     * @param pp 
     * @return  
     */
    public ArrayList<PowerPlant> addPowerPlant(){
      	File file = new File("C:\\Users\\Mkhize\\Documents\\NetBeansProjects\\217010763_miniProject\\src\\data\\data.txt");
		
		Scanner input = null;
		ArrayList<PowerPlant> ppe = new ArrayList<>();
		
		try
		{
			input = new Scanner(file);
			while(input.hasNext())
			{
				String temp = input.nextLine();
				StringTokenizer token = new StringTokenizer(temp,":");
				PowerPlant s = new PowerPlant(token.nextToken(),Double.parseDouble(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()));
				ppe.add(s);
				
			}
			input.close();
		}
		catch(Exception ex)
		{
			
			return null;
		}
	
		return ppe;
	}

//writing a data.txt file
	public void writeToFile(String info) {
		BufferedWriter buf;
		try {
			buf = new BufferedWriter(new  FileWriter("C:\\Users\\Mkhize\\Documents\\NetBeansProjects\\217010763_miniProject\\src\\data\\data.txt", true));
			PrintWriter input = new PrintWriter(buf);
			input.println(info);
			input.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

public   ArrayList<Route> readRoute(){
	File file = new File("C:\\Users\\Mkhize\\Documents\\NetBeansProjects\\217010763_miniProject\\src\\data\\data2.txt");
		
		Scanner input = null;
		ArrayList<Route> pp = new ArrayList<>();
		
		try
		{
			input = new Scanner(file);
			while(input.hasNext())
			{
				String temp = input.nextLine();
				StringTokenizer token = new StringTokenizer(temp,":");
				Route s = new Route(token.nextToken(),Double.parseDouble(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()),Integer.parseInt(token.nextToken()));
				pp.add(s);
				
			}
			input.close();
		}
		catch(Exception ex)
		{
			
			return null;
		}
	
		return pp;
}

}
