package sudokupuzzle;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;
import javax.swing.undo.UndoManager;
import java.util.Stack;



class MyFrame extends JFrame{
          
          Border border = BorderFactory.createLineBorder(new Color(51,153,255));
          UndoManager undoManager = new UndoManager();
          Stack<UndoLabel> undoStack = new Stack<>();
          Stack<String> colsStack = new Stack<>();
          int n;
          JPanel bPanel;
          int counter;//counts blank cells
          
	  public MyFrame() throws IOException {
                JTextArea textArea = new JTextArea();
		setTitle("Sudoku Puzzle");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450,400); 
		setLocation(200,200); 
		setLayout(new BorderLayout());
                
                ReadFile r = new ReadFile();
                final ArrayList<String[]> ea = r.FileR("sudoku//easy_solution.txt");  //C://Users//user//Desktop//
                final ArrayList<String[]> in = r.FileR("sudoku//intermediate_solution.txt");
                final ArrayList<String[]> ex = r.FileR("sudoku//expert_solution.txt");
                
		//**** SUDOKU BOARD *****
		final SudokuPanel sp = new SudokuPanel();   
		add(sp);
                
                //mouse listener
                final MouseListener ml = new MouseListener(){
                     @Override
                     public void mouseClicked(MouseEvent e) {
                        JLabel clickedLabel = (JLabel)e.getSource();
                        
                        if(!clickedLabel.getText().equals("")){
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                if((clickedLabel.getText().equals(sp.getRows().get(i)[j].getText())) && (!clickedLabel.getBackground().equals(new Color(102,178,255)))){
                                        sp.getRows().get(i)[j].setBackground(new Color(255,255,200));
                                        sp.getRows().get(i)[j].setBorder(BorderFactory.createLineBorder(null));
                                        clickedLabel.setBorder(border);
                                }else if((!clickedLabel.getText().equals(sp.getRows().get(i)[j].getText())) && (!sp.getRows().get(i)[j].getBackground().equals(new Color(102,178,255)))){
                                    if(sp.getRows().get(i)[j].isFocusable()){
                                        sp.getRows().get(i)[j].setBackground(Color.WHITE);
                                        sp.getRows().get(i)[j].setBorder(BorderFactory.createLineBorder(null));
                                    }
                                    else if(!sp.getRows().get(i)[j].isFocusable()){
                                        sp.getRows().get(i)[j].setBackground(new Color(224,224,224));
                                        sp.getRows().get(i)[j].setBorder(BorderFactory.createLineBorder(null));
                                    } 
                                }
                                
                            }
                        }
                        }else {
                            clickedLabel.setBackground(new Color(255,255,200));  //(153,204,255)
                            clickedLabel.setBorder(border);
                            for(int i=0; i<9; i++){
                                for(int j=0; j<9; j++){
                                    if((sp.getRows().get(i)[j].isFocusable()) && (!clickedLabel.equals(sp.getRows().get(i)[j])) && (!sp.getRows().get(i)[j].getBackground().equals(new Color(102,178,255)))){
                                        sp.getRows().get(i)[j].setBackground(Color.WHITE);
                                       sp.getRows().get(i)[j].setBorder(BorderFactory.createLineBorder(null));
                                    }
                                    else if((!sp.getRows().get(i)[j].isFocusable()) && (!clickedLabel.equals(sp.getRows().get(i)[j]))){
                                        sp.getRows().get(i)[j].setBackground(new Color(224,224,224));
                                        sp.getRows().get(i)[j].setBorder(BorderFactory.createLineBorder(null));
                                    } 
                                }
                            }
                        
                        }
                                                
                     }    
          
                     @Override
                     public void mouseExited(MouseEvent e) {}
          
                     @Override
                     public void mousePressed(MouseEvent e) {}

                     @Override
                     public void mouseReleased(MouseEvent e) {}

                     @Override
                     public void mouseEntered(MouseEvent e) {}
                };
                
                //end of mouse listener
		
                
                //**** MENU ****
                
		JMenu myMenu = new JMenu("New Game");
		myMenu.setBackground(Color.LIGHT_GRAY);
		
                JMenuItem easy = new JMenuItem("Easy");
                
                ActionListener ac_easy = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        n=1;
                        enable(bPanel);
                        ReadFile rf = new ReadFile();
                        ArrayList<String[]> al = new ArrayList<>();
                        try {
                            al = rf.FileR("sudoku//easy.txt");
                        } catch (IOException ex) {
                            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                        counter=countBlank(al);
                        
                        while(!undoStack.isEmpty()){
                            undoStack.pop();
                        }
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                sp.getRows().get(i)[j].addMouseListener(ml);
                                if(al.get(i)[j+1].equals(".")){
                                    sp.getRows().get(i)[j].setText("");
                                    sp.getCols().get(j)[i]="";
                                    sp.getRows().get(i)[j].setFocusable(true);
                                    sp.getRows().get(i)[j].setOpaque(true);
                                    sp.getRows().get(i)[j].setBackground(Color.WHITE);
                                    sp.getRows().get(i)[j].setBorder(BorderFactory.createLineBorder(null));
                                     
                                }else{
                                    sp.getRows().get(i)[j].setText(al.get(i)[j+1]);
                                    sp.getCols().get(j)[i]=al.get(i)[j+1];
                                    sp.getRows().get(i)[j].setFocusable(false);
                                    sp.getRows().get(i)[j].setOpaque(true);
                                    sp.getRows().get(i)[j].setBackground(new Color(224,224,224));
                                }
                                
                            }
                        }
                        
                        Component[] com=bPanel.getComponents();
                        for(Component c : com){
                            c.setEnabled(true);
                        }
                     }
                };
                easy.addActionListener(ac_easy);
		myMenu.add(easy);
		
		JMenuItem intermediate = new JMenuItem("Intermediate");
                ActionListener ac_intermediate = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        n=2;
                        enable(bPanel);
                        ReadFile rf = new ReadFile();
                        ArrayList<String[]> al = new ArrayList<>();
                        try {
                            al = rf.FileR("sudoku//intermediate.txt");
                        } catch (IOException ex) {
                            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        counter=countBlank(al);
                        
                        while(!undoStack.isEmpty()){
                            undoStack.pop();
                        }
                        
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                sp.getRows().get(i)[j].addMouseListener(ml);
                                if(al.get(i)[j+1].equals(".")){
                                     sp.getRows().get(i)[j].setText("");
                                     sp.getCols().get(j)[i]="";
                                     sp.getRows().get(i)[j].setFocusable(true);
                                     sp.getRows().get(i)[j].setOpaque(true);
                                     sp.getRows().get(i)[j].setBackground(Color.WHITE);
                                     
                                }else{
                                    sp.getRows().get(i)[j].setText(al.get(i)[j+1]);
                                    sp.getCols().get(j)[i]=al.get(i)[j+1];
                                    sp.getRows().get(i)[j].setFocusable(false);
                                    sp.getRows().get(i)[j].setOpaque(true);
                                    sp.getRows().get(i)[j].setBackground(new Color(224,224,224));
                                }
                            }
                        }
                    }
                };
                intermediate.addActionListener(ac_intermediate);
		myMenu.add(intermediate);
		
		JMenuItem expert = new JMenuItem("Expert");
                ActionListener ac_expert = new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        n=3;
                        enable(bPanel);
                        ReadFile rf = new ReadFile();
                        ArrayList<String[]> al = new ArrayList<>();
                        try {
                            al = rf.FileR("sudoku//expert.txt");
                        } catch (IOException ex) {
                            Logger.getLogger(MyFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
                       
                        counter=countBlank(al);
                        
                        while(!undoStack.isEmpty()){
                            undoStack.pop();
                        }
                        
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                sp.getRows().get(i)[j].addMouseListener(ml);
                                if(al.get(i)[j+1].equals(".")){
                                     sp.getRows().get(i)[j].setText("");
                                     sp.getCols().get(j)[i]="";
                                     sp.getRows().get(i)[j].setFocusable(true);
                                     sp.getRows().get(i)[j].setOpaque(true);
                                     sp.getRows().get(i)[j].setBackground(Color.WHITE);
                                }else{
                                    sp.getRows().get(i)[j].setText(al.get(i)[j+1]);
                                    sp.getCols().get(j)[i]=al.get(i)[j+1];
                                    sp.getRows().get(i)[j].setFocusable(false);
                                    sp.getRows().get(i)[j].setOpaque(true);
                                    sp.getRows().get(i)[j].setBackground(new Color(224,224,224));
                                }
                            }
                        }
                    }
                };
                expert.addActionListener(ac_expert);
		myMenu.add(expert);
		
		JMenuBar bar = new JMenuBar();
		bar.add(myMenu);
		setJMenuBar(bar);
                
                
		//MORE BUTTONS...
		bPanel = new JPanel();
		GridLayout gl = new GridLayout(2,0);
                gl.setHgap(5);
                gl.setVgap(5);
                bPanel.setLayout(gl);//(new GridLayout(2,0));
                
		final JButton but1 = new JButton("1");
                ActionListener b1 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                          
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"1", sp.getRows())) || (sp.checkCol(j,"1",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("1"))) { 
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("1");
                                                sp.getCols().get(j)[i]="1";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","1");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                                
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but1.addActionListener(b1);
		bPanel.add(but1);
				
		JButton but2 = new JButton("2");
		ActionListener b2 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"2", sp.getRows())) || (sp.checkCol(j,"2",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("2"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("2");
                                                sp.getCols().get(j)[i]="2";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","2");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but2.addActionListener(b2);
                bPanel.add(but2);
			
		JButton but3 = new JButton("3");
                ActionListener b3 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"3", sp.getRows())) || (sp.checkCol(j,"3",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("3"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("3");
                                                sp.getCols().get(j)[i]="3";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","3");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but3.addActionListener(b3);
		bPanel.add(but3);
		
		JButton but4 = new JButton("4");
                ActionListener b4 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"4", sp.getRows())) || (sp.checkCol(j,"4",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("4"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("4");
                                                sp.getCols().get(j)[i]="4";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","4");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but4.addActionListener(b4);
		bPanel.add(but4);
		
		JButton but5 = new JButton("5");
                ActionListener b5 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"5", sp.getRows())) || (sp.checkCol(j,"5",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("5"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("5");
                                                sp.getCols().get(j)[i]="5";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","5");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but5.addActionListener(b5);
		bPanel.add(but5);
		
		JButton but6 = new JButton("6");
                ActionListener b6 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"6", sp.getRows())) || (sp.checkCol(j,"6",sp.getCols(), sp.getRows()))  || (sb.checkSubPanel("6"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("6");
                                                sp.getCols().get(j)[i]="6";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","6");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                     }
                                }      
                  }  
                };
                but6.addActionListener(b6);
		bPanel.add(but6);
		
		JButton but7 = new JButton("7");
                ActionListener b7 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"7", sp.getRows())) || (sp.checkCol(j,"7",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("7"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("7");
                                                sp.getCols().get(j)[i]="7";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","7");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but7.addActionListener(b7);
		bPanel.add(but7);
		
		JButton but8 = new JButton("8");
                ActionListener b8 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"8", sp.getRows())) || (sp.checkCol(j,"8",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("8"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("8");
                                                sp.getCols().get(j)[i]="8";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","8");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }      
                  }  
                };
                but8.addActionListener(b8);
		bPanel.add(but8);
			
		JButton but9 = new JButton("9");
                ActionListener b9 = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                    SudokuSubPanel  sb;
                    search:     for(int i=0; i<9; i++){
                                    for(int j=0; j<9; j++){
                                        if((sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200))) && (sp.getRows().get(i)[j].getText().equals(""))){
                                            sb = (SudokuSubPanel) sp.getRows().get(i)[j].getParent();
                                            if((sp.checkRow(i,"9", sp.getRows())) || (sp.checkCol(j,"9",sp.getCols(), sp.getRows())) || (sb.checkSubPanel("9"))) {
                                                break search;
                                            }
                                            else{
                                                sp.getRows().get(i)[j].setText("9");
                                                sp.getCols().get(j)[i]="9";
                                                counter--;
                                                UndoLabel l=new UndoLabel(sp.getRows().get(i)[j],"","9");
                                                undoStack.push(l);
                                                
                                                if(counter==0){
                                                    if(n==1)
                                                        checkSolution(sp.getRows(),ea,bPanel);
                                                    else if(n==2)
                                                        checkSolution(sp.getRows(),in,bPanel);
                                                    else
                                                        checkSolution(sp.getRows(),ex,bPanel);
                                                }
                                            }
                                        }
                                    }
                                }             
                  }  
                };
                but9.addActionListener(b9);
		bPanel.add(but9);
		
		JButton erase = new JButton();
		ImageIcon icon = new ImageIcon("images//eraser.png");
		Image image = icon.getImage().getScaledInstance(27,27,Image.SCALE_DEFAULT);
		icon = new ImageIcon(image);
		erase.setIcon(icon);
                ActionListener del = new ActionListener(){
                  public void actionPerformed(ActionEvent e){
                        
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){ 
                                if((sp.getRows().get(i)[j].getBorder().equals(border))&& (sp.getRows().get(i)[j].isFocusable()) && (!sp.getRows().get(i)[j].getText().equals(""))){
                                    String o = sp.getRows().get(i)[j].getText();
                                    String n = "";
                                    UndoLabel l = new UndoLabel(sp.getRows().get(i)[j], o, n);
                                    sp.getRows().get(i)[j].setText("");
                                    sp.getCols().get(j)[i]="";
                                    counter++;
                                    undoStack.push(l);
                                }
                            }
                        }
                        
                  }  
                };
                erase.addActionListener(del);
		bPanel.add(erase);
		
		JButton undo = new JButton();
		ImageIcon icon2 = new ImageIcon("images//undo.png");
		Image image2 = icon2.getImage().getScaledInstance(27,27,Image.SCALE_DEFAULT);
		icon2 = new ImageIcon(image2);
		undo.setIcon(icon2);
                undo.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if(!undoStack.isEmpty()){
                            JLabel l = undoStack.peek().getLabel();
                            String s= undoStack.peek().getOld_value();
                            if(s.equals("")){
                                counter++;
                            }
                            else{
                                counter--;
                            }
                            for(int i=0; i<9; i++){
                                for(int j=0; j<9; j++){ 
                                    if(sp.getRows().get(i)[j].equals(l)){
                                        sp.getCols().get(j)[i]=s;
                                    }
                                }
                            }
                        
                            l.setText(s);
                            undoStack.pop();
                        }
                    }
                });
		bPanel.add(undo);
                
                final JCheckBox verify = new JCheckBox("Verify against solution");
                verify.addItemListener(new ItemListener(){
                    public void itemStateChanged(ItemEvent e) {
                        ArrayList<String[]> al = new ArrayList<>();
                        switch(n){
                            case 1:
                                al = ea;
                                break;
                            case 2: 
                                al = in;
                                break;
                            case 3: 
                                al = ex;
                                break;
                            default: 
                                break;                  
                        }
                        
                        if(verify.isSelected()){  //if checkbox is checked
                            for(int i=0; i<9; i++){
                                for(int j=0; j<9; j++){//
                                    if((sp.getRows().get(i)[j].isFocusable())  && (!sp.getRows().get(i)[j].getText().equals("")) && (!(sp.getRows().get(i)[j].getText()).equals(al.get(i)[j+1]))){
                                        sp.getRows().get(i)[j].setBackground(new Color(102,178,255));
                                    }
                                }
                            }
                        }
                        else{
                            for(int i=0; i<9; i++){
                                for(int j=0; j<9; j++){
                                    if(sp.getRows().get(i)[j].isFocusable() && (!sp.getRows().get(i)[j].getBackground().equals(new Color(255,255,200)))){
                                        sp.getRows().get(i)[j].setBackground(Color.WHITE);
                                    }
                                }
                            }
                        }
                            
                    }
                });
                bPanel.add(verify);
		
		JButton solution = new JButton();
		ImageIcon icon3 = new ImageIcon("images//rubik.png");
		Image image3 = icon3.getImage().getScaledInstance(27,27,Image.SCALE_DEFAULT);
		icon3 = new ImageIcon(image3);
		solution.setIcon(icon3);
                solution.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        ArrayList<String[]> al = new ArrayList<>();
                        switch(n){
                            case 1:
                                al = ea;//easy
                                break;
                            case 2: 
                                al = in;//intermediate
                                break;
                            case 3: 
                                al = ex;//expert
                                break;
                            default: 
                                break;
                        }
                        
                        for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                if(sp.getRows().get(i)[j].isFocusable()){
                                    sp.getRows().get(i)[j].setText(al.get(i)[j+1]);
                                }
                            }
                        }
                        disable(bPanel);
                    }
                });
		bPanel.add(solution);
		add(bPanel, BorderLayout.SOUTH);
                
	  }
          
          private void disable(JPanel panel){
            Component[] com=panel.getComponents();
            for(Component c : com){
                c.setEnabled(false);
            }
          }
          
          private void enable(JPanel panel){
            Component[] com=panel.getComponents();
                for(Component c : com){
                    c.setEnabled(true);
                }
          }
          
          private int countBlank(ArrayList <String[]> s){
            int counter = 0;
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    if(s.get(i)[j+1].equals(".")){
                        counter++;
                    }
                }
            }  
            return counter;
          }
          
          private void checkSolution(ArrayList<JLabel[]> jl, ArrayList<String[]> sol, JPanel p){
                boolean b=true;
                label:  for(int i=0; i<9; i++){
                            for(int j=0; j<9; j++){
                                if((jl.get(i)[j].isFocusable()) && (!jl.get(i)[j].getText().equals("")) && (!jl.get(i)[j].getText().equals(sol.get(i)[j+1]))){
                                    b=false;
                                    break label;
                                }
                            }
                        }
                if(b){
                    disable(p);
                    JOptionPane.showMessageDialog(null, "Success!");
                }
          }
          
	  public static void main(String[] args) throws IOException {
	    Frame f = new MyFrame();
	    f.show();
          }

    }