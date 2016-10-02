package sudokupuzzle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;



public class ReadFile {
    public ArrayList<String[]> FileR (String filePath) throws IOException {
    
        ArrayList<String[]> sudokuFile = new ArrayList<>();       
        
	File f = new File(filePath);  
	BufferedReader reader = null;
	String [] a = null;
	try {
		reader= new BufferedReader(new FileReader(f));
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	
	String line;
        while((line = reader.readLine()) != null) {
            a = line.split(" ");
        }
              
        for(int i=0; i<a.length; i++){
            String[] parts = null;
            for(int j=1; j<10; j++){
                parts = a[i].split("");  
            }
            sudokuFile.add(parts);
        }
	
        /*for(int j=0; j<sudokuFile.size(); j++){
            for(int i=1; i<10; i++){
                System.out.print(sudokuFile.get(j)[i]);
            }
            System.out.println();
        }*/
	return sudokuFile;
		
    }
}
