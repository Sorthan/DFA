package DFADiagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
/**
 *
 * @author vento
 */
public class Vertex {

    public int x;
    public int y;
    public String name;
    public int r;
    public int shift ;
    public boolean isSelect;
    
    public int id;
    public static int idGen = 0;
    
    public Vertex(int x, int y) {
        this.id = idGen;
        idGen++;
        this.r = 36;
        this.shift = 30;
        this.x = x;
        this.y = y;
        this.name = "";
        this.isSelect = false;
    }
    
    public void setDataOnVertex(String name){
        this.name = name;
        
    }

    public boolean inCircle(int x0, int y0) {
        return ((x0 - x) * (x0 - x) + (y0 - y) * (y0 - y)) <= r * r;
    }

    public void draw(Graphics2D g) {
        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));

        g.fillOval(x - r, y - r, r * 2, r * 2);

        g.setColor(Color.WHITE);
        g.fillOval(x - r + (r - shift) / 2, y - r + (r - shift) / 2, r * 2 - (r - shift), r * 2 - (r - shift));

        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.drawString(name, x - 10, y + 10);
    }
    
    public void drawAcceptStage(Graphics2D g){
        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));

        g.fillOval(x - r, y - r, r * 2, r * 2);
        

        g.setColor(Color.WHITE);
        g.fillOval(x - r + (r - shift) / 2, y - r + (r - shift) / 2, r * 2 - (r - shift), r * 2 - (r - shift));

        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.drawString(name, x - 10, y + 10);
    }

}
