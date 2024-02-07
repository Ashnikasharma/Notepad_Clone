package notepad;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.filechooser.*;
import java.io.*;

public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text;
    
   Notepad(){
       setTitle("Notepad");
       
       ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad.png "));
       Image icon = notepadIcon.getImage();
       setIconImage(icon);
       
       JMenuBar menubar = new JMenuBar();
       menubar.setBackground(Color.WHITE);

       JMenu file = new JMenu("File");
       file.setFont(new Font("AERIAL",Font.PLAIN,14));
       
       JMenuItem newdoc = new JMenuItem("New");
       newdoc.addActionListener(this);
       newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
       
       JMenuItem open = new JMenuItem("Open");
       open.addActionListener(this);
       open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        
       JMenuItem save = new JMenuItem("Save");
       save.addActionListener(this);
       save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
               
       JMenuItem print = new JMenuItem("Print");
       print.addActionListener(this);
       print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
       
       JMenuItem exit = new JMenuItem("Exit");
       exit.addActionListener(this);
       exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
       
       
       file.add(newdoc);
       file.add(open);
       file.add(save);
       file.add(print);
       file.add(exit);
       
       menubar.add(file);
       
       JMenu edit = new JMenu("Edit");
       edit.setFont(new Font("AERIAL",Font.PLAIN,14));
       
       JMenuItem copy = new JMenuItem("Copy");
       copy.addActionListener(this);
       copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
       
       JMenuItem paste = new JMenuItem("Paste");
       paste.addActionListener(this);
       paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
       
       JMenuItem cut = new JMenuItem("Cut");
       cut.addActionListener(this);
       cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
       
       JMenuItem selectall = new JMenuItem("Select All");
       selectall.addActionListener(this);
       selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
       
       edit.add(copy);
       edit.add(paste);
       edit.add(cut);
       edit.add(selectall);

       menubar.add(edit);
       
       JMenu helpmenu = new JMenu("Help");
       helpmenu.setFont(new Font("AERIAL",Font.PLAIN,14));
      
       JMenuItem help = new JMenuItem("About");
       help.addActionListener(this);
       help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));
       
       helpmenu.add(help);
       
       menubar.add(helpmenu);
       
       setJMenuBar(menubar);
       
       area = new JTextArea();
       area = new JTextArea();
       area.setFont(new Font("SAN_SERIF", Font.PLAIN, 20));
       area.setLineWrap(true);
       area.setWrapStyleWord(true);
     
       JScrollPane pane = new JScrollPane(area);
       pane.setBorder(BorderFactory.createEmptyBorder());
       add(pane);
       
       setExtendedState(JFrame.MAXIMIZED_BOTH);
       
       setVisible(true);
   }
   @Override
   public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")) {
            area.setText("");
        } else if (ae.getActionCommand().equals("Open")) {
           JFileChooser chooser = new JFileChooser("D:/Java");
           chooser.setAcceptAllFileFilterUsed(false); 
           FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt"); 
           chooser.addChoosableFileFilter(restrict);
           
           int action = chooser.showOpenDialog(this);
           
           if(action != JFileChooser.APPROVE_OPTION) {
               return;
           }
           
           File file = chooser.getSelectedFile();
               try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader,null);
           } catch (Exception e) {
               e.printStackTrace();
           }
        } else if(ae.getActionCommand().equals("Save")){
            JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
    
            int action = SaveAs.showOpenDialog(this);
                    
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            
            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            } 
        } else if (ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception e){
                e.printStackTrace();
            } 
        }else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        } else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        }else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        }else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();  
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }else if (ae.getActionCommand().equals("Select All")) {
            area.selectAll();
        }else if (ae.getActionCommand().equals("About")){
            new About().setVisible(true);
        }
    }
    
    public static void main(String[] args) {
        new Notepad();
     
    }
    
}
