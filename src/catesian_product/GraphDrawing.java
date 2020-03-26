package catesian_product;
  
import DFADiagram.Edge_;
import DFADiagram.TempEdge;
import DFADiagram.Vertex;

import AutomataDFA.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import sun.awt.CustomCursor;
/**
 *
 * @author vento
 */

public class GraphDrawing extends JFrame implements MouseListener, MouseMotionListener, KeyListener {
    DFAAutomata automatainput = null;
    public static GraphDrawing gui;
    JTable tableInput = null;
    
    public void readInputAutomata(DFAAutomata dfa, JTable table){
        clear();
        automatainput = dfa;
        tableInput = table;
        System.out.println("INPUT COMPLETE");
        this.drawAutomata();
    }
    public static void main(String[] args) {
        try {
            gui = new GraphDrawing();
        } catch (Exception ex) {

        }
    }
    
    
    //Data of graph
    ArrayList<Vertex> Vertexs = new ArrayList<>();
    ArrayList<Edge_> Edge_s = new ArrayList<>();
    
    Object selected = null;
    TempEdge TempEdge = null; //TempEdge edge
    
    //UI 
    Canvas c;
    String mode = "Vertex"; //Vertex,Edge_
    
    //set font 
    Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 24);
    
    //find size monitor
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    
    JFrame frameHelp = new JFrame("Help");
    JFrame frameDelta = new JFrame("delta");

    JPanel boxSave = new JPanel();
    JPanel boxOpen = new JPanel();
    JPanel boxHelp = new JPanel();
    JPanel boxDeltaData = new JPanel();
    
    
    JScrollPane deltaScrollBar = new JScrollPane(boxDeltaData);

    JButton saveButt = new JButton();
    JButton openButt = new JButton();
    JButton helpButt = new JButton();
    JButton createButton = new JButton();
    JButton closeButton = new JButton();
    JButton catesianButton = new JButton();
    JButton unionButton = new JButton();
    JButton interceptButton = new JButton();
    JButton tableDelta = new JButton();
    JButton resetWindow = new JButton();

    JFileChooser pathSave = new JFileChooser();
    JFileChooser pathOpen = new JFileChooser();

    JLabel helpString = new JLabel();
    JLabel DeltaString = new JLabel();
    

    //-----###e
    JPanel menubar = new JPanel();
    int shift = 50;

    //////////////////////////////// Backup ////////////////////////////////
    class Backup {

        ArrayList<Vertex> VertexsBackup;
        ArrayList<Edge_> Edge_sBackup;

        public Backup() {
            this.VertexsBackup = Vertexs;
            this.Edge_sBackup = Edge_s;
        }

    }
    
    public GraphDrawing() {
        super("canvas");

        // create a empty canvas 
        c = new Canvas() {
            @Override
            public void paint(Graphics g) {
            }
        };
        c.setBackground(Color.white);
      
        // add mouse listener 
        c.addMouseListener(this);
        c.addMouseMotionListener(this);

        // add keyboard listener 
        c.addKeyListener(this);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        saveButt.setFont(sanSerifFont);
        openButt.setFont(sanSerifFont);
        helpButt.setFont(sanSerifFont);
        createButton.setFont(sanSerifFont);
        closeButton.setFont(sanSerifFont);
        catesianButton.setFont(sanSerifFont);
        unionButton.setFont(sanSerifFont);
        interceptButton.setFont(sanSerifFont);
        tableDelta.setFont(sanSerifFont);
        resetWindow.setFont(sanSerifFont);

        helpString.setFont(sanSerifFont);
        DeltaString.setFont(sanSerifFont);

        boxSave.setBackground(Color.white);
        boxOpen.setBackground(Color.white);
        
        boxDeltaData.setBackground(Color.blue);
        frameDelta.add(boxDeltaData);

        boxHelp.setBackground(Color.white);
        frameHelp.add(boxHelp);
        

        //button
        saveButt.setText("save");
        saveButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 100, 150, 23);
        getContentPane().add(saveButt);
        saveButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    saveButtAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        openButt.setText("open");
        openButt.setBounds((screenSize.width - getWidth()) - 400 + 150 + shift, 100, 150, 23);
        getContentPane().add(openButt);
        openButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    openButtAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        helpButt.setText("Help");
        helpButt.setBounds((screenSize.width - getWidth()) - 400 + shift, 40, 300, 23);
        getContentPane().add(helpButt);
        helpButt.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpButtAction(e);
            }
        });
        
        createButton.setText("CREATE AUTOMATA");
        createButton.setBounds((screenSize.width - getWidth()) - 400 + shift, 300, 300, 30);
        getContentPane().add(createButton);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CreateDFAButtonAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        catesianButton.setText("CATESIAN PRODUCT");
        catesianButton.setBounds((screenSize.width - getWidth()) - 400 + shift, 450, 300, 30);
        getContentPane().add(catesianButton);
        catesianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    tableDelta = null;
                    automatainput = null;
                    clear();
                    CatesianProductAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tableDelta.setText("DELTA TABLE");
        tableDelta.setBounds((screenSize.width - getWidth()) - 400 + shift, 350, 300, 23);
        getContentPane().add(tableDelta);
        tableDelta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    showDeltaTable(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        resetWindow.setText("RESET PROGRAM");
        resetWindow.setBounds((screenSize.width - getWidth()) - 400 + shift, 850, 300, 30);
        getContentPane().add(resetWindow);
        resetWindow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.setVisible(false);
                GraphDrawing guinew = new GraphDrawing();
                gui = guinew;
            }
        });
        
        closeButton.setText("EXIT PROGRAM");
        closeButton.setBounds((screenSize.width - getWidth()) - 400 + shift, 900, 300, 30);
        getContentPane().add(closeButton);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CloseProgram();
            }
        });
        
        unionButton.setText("union");
        unionButton.setBounds((screenSize.width - getWidth()) - 400 + shift, 500, 150, 23);
        getContentPane().add(unionButton);
        unionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showUnionbuttonAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        interceptButton.setText("intercept");
        interceptButton.setBounds((screenSize.width - getWidth()) - 400 + 150 + shift, 500, 150, 23);
        getContentPane().add(interceptButton);
        interceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    showInterceptbuttonAction(e);
                } catch (IOException ex) {
                    Logger.getLogger(GraphDrawing.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        //-----###e     
        menubar.setBackground(Color.cyan);
        menubar.setBounds((screenSize.width - getWidth()) - 400, 0, 400, (screenSize.height - getHeight()));
        c.setBounds(0, 0, (screenSize.width - getWidth()) - 400, (screenSize.height - getHeight()));
        setBounds(0, 0, (screenSize.width - getWidth()), (screenSize.height - getHeight()));
        //setUndecorated(true);
        //setVisible(true);
        add(c);
        add(menubar);
        // setSize(1500, 1000);
        show();
        
        
    }

    //
    void helpButtAction(ActionEvent e) {
        String help = "<html>";
        help += "Double click for create Vertex<br>";
        help += "Click on Vertex then type for rename<br>";
        help += "Click on Vertex or center of edge then it is blue you can edit etc move , rename , delete <br>";
        help += "Click on Vertex then press delete for remove Vertex<br>";
        help += "Press and hold spacebar with drag mouse for create edge<br>";
        help += "Click on character on edge then type for rename<br>";
        help += "Click on character on edge then drag mouse for move edge<br>";
        help += "Click on character on edge then press delete for remove edge<br>";
        help += "Press Button save for save Graph on canvas to json file<br>";
        help += "Press Button open for open Graph json file to canvas<br>";
        helpString.setText(help);
        boxHelp.add(helpString);
        boxHelp.setAutoscrolls(true);

        frameHelp.setBounds(screenSize.width / 2 - 500,screenSize.height/2-200, 1000, 400);
        frameHelp.setVisible(true);
    }
    void saveButtAction(ActionEvent e) throws IOException {
        pathSave.setBounds(60, 120, 750, 450);
        boxSave.add(pathSave);

        int ret = pathSave.showDialog(null, "save");
        String path = "";

        if (ret == JFileChooser.APPROVE_OPTION) {
            File filePath = pathSave.getSelectedFile();
            path = filePath.getPath();
            save(path);
        }
    }
    void openButtAction(ActionEvent e) throws IOException {
        clear();
        pathOpen.setBounds(60, 120, 750, 450);
        boxOpen.add(pathOpen);

        int ret = pathOpen.showDialog(null, "open");
        String path = "";

        if (ret == JFileChooser.APPROVE_OPTION) {
            File filePath = pathOpen.getSelectedFile();
            path = filePath.getPath();
            open(path);
            drawAutomata();
        }
    }
    
    int timeclickCreate = 0;
    void CreateDFAButtonAction(ActionEvent e) throws IOException{
        if(timeclickCreate == 0){   
            clear();
            Catesian_Product cp = new Catesian_Product();
            cp.CreateAutomata();
        }
        else if(timeclickCreate  > 0){
            JOptionPane.showMessageDialog(null, "You are already create now\nIf you want to create another one please click RESET PROGRAM button");
        }
        timeclickCreate += 1;
    }
    
    int timeclickDelta = 0;
    void showDeltaTable(ActionEvent e) throws IOException{
        if(tableInput == null){
            JOptionPane.showMessageDialog(null, "Now missing data\nPlease create automata or open file first!!!");
        }
        if(timeclickDelta==0){
            TableModel t = tableInput.getModel();
            JTable table = new JTable();
            table.setModel(t);
            boxDeltaData.add(table);
            boxDeltaData.setAutoscrolls(true);
            boxDeltaData.add(deltaScrollBar);
            timeclickDelta+=1;
        }
        
        
        frameDelta.setBounds(screenSize.width / 4 - 500,screenSize.height/4-200, 1000, 400);
        frameDelta.setVisible(true);
    }
    
    void CatesianProductAction(ActionEvent e) throws IOException{
        
    }
    
    void showUnionbuttonAction(ActionEvent e) throws IOException{
        
    }
    
    void showInterceptbuttonAction(ActionEvent e) throws IOException{
        
    }
    
    void CloseProgram(){
        System.exit(0);
    }
    
    public void save(String path) throws IOException {
        
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        FileWriter writer = new FileWriter(path);
        
        Backup backup = new Backup();
        writer.write(gson.toJson(backup));
        writer.close();
        
    }
    
    public void open(String path) throws FileNotFoundException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));

        Backup backup = gson.fromJson(bufferedReader, Backup.class);
        
        Vertexs = backup.VertexsBackup;
        Edge_s = backup.Edge_sBackup;
        
        //bind object reference
        for (Edge_ e : Edge_s) {
            if (e.vertexA != null) {
                int id = e.vertexA.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexA = v;
                        break;
                    }
                }
            }
            if (e.vertexB != null) {
                int id = e.vertexB.id;
                for (Vertex v : Vertexs) {
                    if (v.id == id) {
                        e.vertexB = v;
                        break;
                    }
                }
            }
        }
    }
    
    //set canvas is white
    public void clear() {
        Graphics2D g = (Graphics2D)c.getGraphics();
        g.setColor(Color.white);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    public void selected(int x, int y) {
        Object obj = null;
        for (Vertex s : Vertexs) {
            if (s.inCircle(x, y)) {
                s.isSelect = true;
                obj = s;
                break;
            }
        }
        if (obj == null) {
            for (Edge_ t : Edge_s) {
                if (t.inLine(x, y)) {
                    t.isSelect = true;
                    obj = t;
                    break;
                }
            }
        }
        if (obj == null) {
            if (selected == null) {
                return;
            } else {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    s.isSelect = false;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.isSelect = false;
                }
                selected = null;
            }
        } else {
            if (selected == null) {
                selected = obj;
            } else {
                if (obj == selected) {
                    return;
                } else {
                    if (selected instanceof Vertex) {
                        Vertex s = (Vertex) selected;
                        s.isSelect = false;
                    } else {
                        Edge_ t = (Edge_) selected;
                        t.isSelect = false;
                    }
                    selected = obj;
                }
            }
        }
    }
    public void draw() {
        clear();

        Graphics2D g = (Graphics2D) c.getGraphics();
        g.setFont(sanSerifFont);

        for (Edge_ t : Edge_s) {
            t.draw(g);
        }
        if (TempEdge != null) {
            TempEdge.line(g);
        }
        for (Vertex s : Vertexs) {
            s.draw(g);
        }
    }

////////////////////////////////  UI EVENT  ////////////////////////////////
    // 3.1 mouse listener and mouse motion listener mehods 
     // keyboard listener and keyboard motion listener mehods 
    @Override
    public void keyTyped(KeyEvent ke) { 
        //System.out.println("key " + ke.getKeyChar() + " = " + (int) ke.getKeyChar());
        if ((int) ke.getKeyChar() == 19) {
            try {
                //ctrl + S
                save("backup.json");
            } catch (IOException ex) {

            }
        } else if ((int) ke.getKeyChar() == 15) {
            try {
                //ctrl + O 
                open("backup.json");
            } catch (IOException ex) {
                
            }
        } else if ((int) ke.getKeyChar() == 14) {
            //ctrl + N 
            
        } else if ((int) ke.getKeyChar() == 12) {
            //ctrl + L

        } else if ((int) ke.getKeyChar() == 18) {
            //ctrl + R 

        } else if ((int) ke.getKeyChar() == 9) {

        } else if ((int) ke.getKeyChar() == 1) {
            //ctrl + A 

        }

        if (selected instanceof Vertex) {
            Vertex s = (Vertex) selected;
            int status = (int) ke.getKeyChar();
            if (status == 8) { //delete
                if (s.name.length() > 1) {
                    s.name = s.name.substring(0, s.name.length() - 1).trim();
                } else {
                    s.name = "".trim();
                }

            } else if (status == 127) { // space
                ArrayList<Edge_> TempEdge = new ArrayList<>();
                for (Edge_ t : Edge_s) {
                    if (t.vertexA == selected || t.vertexB == selected) {
                        TempEdge.add(t);
                    }
                }
                for (Edge_ t : TempEdge) {
                    Edge_s.remove(t);
                }
                Vertexs.remove(selected);
                selected = null;

            } else {
                s.name += ke.getKeyChar();
                s.name = s.name.trim();
            }

        } else if (selected instanceof Edge_) {
            Edge_ t = (Edge_) selected;
            int status = (int) ke.getKeyChar();
            if (status == 8) {
                if (t.name.length() > 1) {
                    t.name = t.name.substring(0, t.name.length() - 1).trim();
                } else {
                    t.name = "".trim();
                }
            } else if (status == 127) {
                Edge_s.remove(selected);
                selected = null;

            } else {
                if (ke.getKeyChar() == ' ') {
                    return;
                }
                t.name += ke.getKeyChar();
                t.name = t.name.trim();
            }

        }
        draw();
    }
    @Override
    public void keyPressed(KeyEvent ke) {
        if ((int) ke.getKeyChar() == 32) {
            //press space bar
            mode = "Edge_";
        }
    }
    @Override
    public void keyReleased(KeyEvent ke) {
        //release space bar
        mode = "Vertex";
    }
    
    @Override
    public void mouseMoved(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        selected(x, y);
        draw();
    }
    
    public void drawAutomata(){
        clear();
        int x = 5;
        int y = 5;
        selected(x, y);
        for(String statename : automatainput.stage){
            Vertex stateVertex = new Vertex(x, y);
            stateVertex.setDataOnVertex(statename);
            x += 30;
            y += 30;
            Vertexs.add(stateVertex);
        }
        draw();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Vertex")) {
            if (selected != null) {
                if (selected instanceof Vertex) {
                    Vertex s = (Vertex) selected;
                    for (Edge_ t : Edge_s) {
                        if (t.vertexA == s || t.vertexB == s) {
                            int difx = x - s.x;
                            int dify = y - s.y;
                            if (t.vertexA != t.vertexB) {
                                if (t.vertexA != null) {
                                    t.x_center = (t.vertexA.x + t.vertexB.x) / 2;
                                    t.y_center = (t.vertexA.y + t.vertexB.y) / 2;
                                } 
                            }else {
                                t.x_center += difx;
                                t.y_center += dify;
                            }

                        }
                    }
                    s.x = x;
                    s.y = y;
                } else {
                    Edge_ t = (Edge_) selected;
                    t.x_center = x;
                    t.y_center = y;
                }
            }

        } else if (mode.equals("Edge_")) {
            try {
                Vertex Vertex = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        Vertex = s;
                    }
                }
                if (Vertex != null) {
                    if (Vertex != TempEdge.vertexA) {
                        double angle = Math.atan2(TempEdge.vertexA.y - Vertex.y, TempEdge.vertexA.x - Vertex.x);
                        double dx = Math.cos(angle);
                        double dy = Math.sin(angle);
                        TempEdge.x1 = Vertex.x + (int) (Vertex.r * dx);
                        TempEdge.y1 = Vertex.y + (int) (Vertex.r * dy);
                    } else {
                        TempEdge.x1 = x;
                        TempEdge.y1 = y;
                    }
                } else {
                    TempEdge.x1 = x;
                    TempEdge.y1 = y;
                }
            } catch (Exception ex) {

            }
        }
        draw();
    }
   
    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Vertex")) {
            TempEdge = null;
        } else if (mode.equals("Edge_")) {
            try {
                TempEdge.x1 = x;
                TempEdge.y1 = y;
                Vertex vertexB = null;
                for (Vertex s : Vertexs) {
                    if (s.inCircle(x, y)) {
                        TempEdge.x1 = s.x;
                        TempEdge.y1 = s.y;
                        vertexB = s;
                        Edge_ edge = new Edge_(TempEdge.vertexA, vertexB);
                        
                        if(s!=TempEdge.vertexA){
                            edge.x_center = (TempEdge.vertexA.x + s.x)/2;
                            edge.y_center = (TempEdge.vertexA.y + s.y)/2;
                        }else{
                            double angle = Math.atan2(y - TempEdge.vertexA.y, x - TempEdge.vertexA.x);
                            double dx = Math.cos(angle);
                            double dy = Math.sin(angle);

                            int rc = 3*TempEdge.vertexA.r;
     
                            edge.x_center = TempEdge.vertexA.x + (int)(dx*rc);
                            edge.y_center = TempEdge.vertexA.y + (int)(dy*rc);
                            
                        }
                        
                        Edge_s.add(edge);
                        break;
                    }
                }
                TempEdge = null; 
            } catch (Exception ex) {
            }
        }
        //draw();
    }
    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        if (mode.equals("Edge_")) {
            TempEdge = new TempEdge(x, y);
            for (Vertex s : Vertexs) {
                if (s.inCircle(x, y)) {
                    TempEdge.setA(s);
                    break;
                }
            }
        }
    }
   
     @Override
    public void mouseExited(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
}