package sudokupuzzle;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.lang3.ArrayUtils;



public class SudokuPanel extends JPanel{
    
	ArrayList <JLabel[]> rows = new ArrayList <JLabel[]>();
        ArrayList <String[]> columns = new ArrayList <String[]>();
        
	SudokuSubPanel sbp11 = null;
        SudokuSubPanel sbp12 = null;
        SudokuSubPanel sbp13 = null;
        SudokuSubPanel sbp21 = null;
        SudokuSubPanel sbp22 = null;
        SudokuSubPanel sbp23 = null;
        SudokuSubPanel sbp31 = null;
        SudokuSubPanel sbp32 = null;
        SudokuSubPanel sbp33 = null;
                
	public SudokuPanel(){
                setLayout(new GridLayout(3,3));
		
		sbp11 = new SudokuSubPanel();
                sbp12 = new SudokuSubPanel();
		sbp13 = new SudokuSubPanel();
		sbp21 = new SudokuSubPanel();
		sbp22 = new SudokuSubPanel();
		sbp23 = new SudokuSubPanel();
		sbp31 = new SudokuSubPanel();
		sbp32 = new SudokuSubPanel();
		sbp33 = new SudokuSubPanel();
	
		this.add(sbp11);
		this.add(sbp12);
		this.add(sbp13);
		this.add(sbp21);
		this.add(sbp22);
		this.add(sbp23);
		this.add(sbp31);
		this.add(sbp32);
		this.add(sbp33);
	}
        
        public ArrayList<JLabel[]> getRows(){
            JLabel[] temp1 = (JLabel[]) ArrayUtils.addAll(sbp11.getFirstRow(),sbp12.getFirstRow());
            JLabel[] firstRow = (JLabel[]) ArrayUtils.addAll(temp1,sbp13.getFirstRow()); 
            rows.add(firstRow);
            JLabel[] temp2 = (JLabel[]) ArrayUtils.addAll(sbp11.getSecondRow(),sbp12.getSecondRow());
            JLabel[] secondRow = (JLabel[]) ArrayUtils.addAll(temp2,sbp13.getSecondRow());
            rows.add(secondRow);
            JLabel[] temp3 = (JLabel[]) ArrayUtils.addAll(sbp11.getThirdRow(),sbp12.getThirdRow());
            JLabel[] thirdRow = (JLabel[]) ArrayUtils.addAll(temp3,sbp13.getThirdRow());
            rows.add(thirdRow);
            
            JLabel[] temp4 = (JLabel[]) ArrayUtils.addAll(sbp21.getFirstRow(),sbp22.getFirstRow());
            JLabel[] fourthRow = (JLabel[]) ArrayUtils.addAll(temp4, sbp23.getFirstRow());
            rows.add(fourthRow);
            JLabel[] temp5 = (JLabel[]) ArrayUtils.addAll(sbp21.getSecondRow(),sbp22.getSecondRow());
            JLabel[] fifthRow = (JLabel[]) ArrayUtils.addAll(temp5,sbp23.getSecondRow());
            rows.add(fifthRow);
             JLabel[] temp6 = (JLabel[]) ArrayUtils.addAll(sbp21.getThirdRow(),sbp22.getThirdRow());
            JLabel[] sixthRow = (JLabel[]) ArrayUtils.addAll(temp6,sbp23.getThirdRow());
            rows.add(sixthRow);
            
            JLabel[] temp7 = (JLabel[]) ArrayUtils.addAll(sbp31.getFirstRow(),sbp32.getFirstRow());
            JLabel[] seventhRow = (JLabel[]) ArrayUtils.addAll(temp7,sbp33.getFirstRow());
            rows.add(seventhRow);
            JLabel[] temp8 = (JLabel[]) ArrayUtils.addAll(sbp31.getSecondRow(),sbp32.getSecondRow());
            JLabel[] eighthRow = (JLabel[]) ArrayUtils.addAll(temp8,sbp33.getSecondRow());
            rows.add(eighthRow);
            JLabel[] temp9 = (JLabel[]) ArrayUtils.addAll(sbp31.getThirdRow(),sbp32.getThirdRow());
            JLabel[] ninthRow = (JLabel[]) ArrayUtils.addAll(temp9,sbp33.getThirdRow());
            rows.add(ninthRow);
            
            return rows;
        }
	
        public ArrayList <String[]> getCols(){
          
            String[] temp1 = (String[]) ArrayUtils.addAll(sbp11.getFirstCol(),sbp12.getFirstCol());
            String[] firstCol = (String[]) ArrayUtils.addAll(temp1, sbp13.getFirstCol()); 
            columns.add(firstCol);
            String[] temp2 = (String[]) ArrayUtils.addAll(sbp11.getSecondCol(),sbp12.getSecondCol());
            String[] secondCol = (String[]) ArrayUtils.addAll(temp2, sbp13.getSecondCol());
            columns.add(secondCol);
            String[] temp3 = (String[]) ArrayUtils.addAll(sbp11.getThirdCol(),sbp12.getThirdCol());
            String[] thirdCol = (String[]) ArrayUtils.addAll(temp3, sbp13.getThirdCol());
            columns.add(thirdCol);
            
            String[] temp4 = (String[]) ArrayUtils.addAll(sbp21.getFirstCol(),sbp22.getFirstCol());
            String[] fourthCol = (String[]) ArrayUtils.addAll(temp4, sbp23.getFirstCol());
            columns.add(fourthCol);
            String[] temp5 = (String[]) ArrayUtils.addAll(sbp21.getSecondCol(),sbp22.getSecondCol());
            String[] fifthCol = (String[]) ArrayUtils.addAll(temp5, sbp23.getSecondCol());
            columns.add(fifthCol);
            String[] temp6 = (String[]) ArrayUtils.addAll(sbp21.getThirdCol(),sbp22.getThirdCol());
            String[] sixthCol = (String[]) ArrayUtils.addAll(temp6, sbp23.getThirdCol());
            columns.add(sixthCol);
            
            String[] temp7 = (String[]) ArrayUtils.addAll(sbp31.getFirstCol(),sbp32.getFirstCol());
            String[] seventhCol = (String[]) ArrayUtils.addAll(temp7,sbp33.getFirstCol());
            columns.add(seventhCol);
            String[] temp8 = (String[]) ArrayUtils.addAll(sbp31.getSecondCol(),sbp32.getSecondCol());
            String[] eighthCol = (String[]) ArrayUtils.addAll(temp8,sbp33.getSecondCol());
            columns.add(eighthCol);
            String[] temp9 = (String[]) ArrayUtils.addAll(sbp31.getThirdCol(),sbp32.getThirdCol());
            String[] ninthCol = (String[]) ArrayUtils.addAll(temp9,sbp33.getThirdCol());
            columns.add(ninthCol);           
                        
            return columns;
        }
     
        public boolean checkRow(int i, String s, ArrayList<JLabel[]> a){
            boolean b=false;
            for(int j=0; j<a.get(i).length; j++){
                if(s.equals(a.get(i)[j].getText())){
                    a.get(i)[j].setBackground(Color.red);
                    b=true;
                    break;
                }  else{ 
                    b=false;
                }
            }
            return b;
        }
        
        public boolean checkCol(int i, String s, ArrayList<String[]> as, ArrayList<JLabel[]> aj){
            boolean b=false;
            for(int k=0; k<as.get(i).length; k++){
                if(s.equals(as.get(i)[k])){
                    aj.get(k)[i].setBackground(Color.red);
                    b=true;
                    break;
                }else{ 
                    b=false;
                }
            }
            return b;
        }
        
}