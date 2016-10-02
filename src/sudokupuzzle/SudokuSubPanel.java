package sudokupuzzle;
import javax.swing.*;
import java.awt.*;



class SudokuSubPanel extends JPanel {
	JLabel[][] labels = new JLabel[3][3];  
       
	public SudokuSubPanel(){
		for(int i=0; i<3; i++){
                    for(int j=0; j<3; j++){
			labels[i][j]=new JLabel("", SwingConstants.CENTER);
			labels[i][j].setBorder(BorderFactory.createLineBorder(Color.black));
			labels[i][j].setPreferredSize(new Dimension(100,100));
                        labels[i][j].setFocusable(false);
			this.add(labels[i][j]);
                    }
		}
                setLayout(new GridLayout(3,3));
		setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
	}
	
        public boolean checkSubPanel(String s){
            boolean b=false;
            search: for(int i=0; i<3; i++){
                        for(int j=0; j<3; j++){
                            if(s.equals(this.labels[i][j].getText())){
                                this.labels[i][j].setBackground(Color.red);
                                b=true;
                                break search;
                            }else{ 
                                b=false;
                            }
                        }
                    }
            return b;
        }
        
	public JLabel[] getFirstRow(){
                JLabel [] label = new JLabel[3];
		for(int i=0; i<3; i++){
			label[i] = labels[0][i];
		}
		return label;
	}
	
	public JLabel[] getSecondRow(){
		JLabel [] label = new JLabel[3];
		for(int i=0; i<3; i++){
			label[i] = this.labels[1][i];
		}
		return label;
	}
	
	public JLabel[] getThirdRow(){
		JLabel [] label = new JLabel[3];
		for(int i=0; i<3; i++){
			label[i] = this.labels[2][i];
		}
		return label;
	}
		
	public String[] getFirstCol(){
		String [] label = new String[3];
		for(int i=0; i<3; i++){
			label[i] = this.labels[i][0].getText();
		}
		return label;
	}
	
	public String[] getSecondCol(){
		String [] label = new String[3];
		for(int i=0; i<3; i++){
			label[i] = this.labels[i][1].getText();
		}
		return label;
	}
	
	public String[] getThirdCol(){
		String [] label = new String[3];
		for(int i=0; i<3; i++){
			label[i] = this.labels[i][2].getText();
		}
		return label;
	}
        
}