package DFADiagram;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.QuadCurve2D;

/**
 *
 * @author vento
 */
public class Edge_ {

    public Vertex vertexA;
    public Vertex vertexB;
    public String name;

    public boolean isSelect;

    public int x_center;
    public int y_center;
    public int r_center;
    public void setEgdeName(String name){
        this.name = name;     
    }
    
    public String getEgdeName(){
        return this.name;
    }
    public Edge_(Vertex a, Vertex b) {
        this.vertexA = a;
        this.vertexB = b;

        this.r_center = 50;
        
        this.name = getEgdeName();
        
        this.isSelect = false;
        if (a == null) {
            this.name = null;
        }
    }

    public boolean inLine(int x0, int y0) {
        return ((x0 - x_center) * (x0 - x_center) + (y0 - y_center) * (y0 - y_center)) <= r_center * r_center ;
    }

    public void draw(Graphics2D g) {
        g.setColor(isSelect ? Color.BLUE : Color.BLACK);
        g.setStroke(new BasicStroke(2));
        if(vertexA != vertexB){
            g.draw(new QuadCurve2D.Float(vertexA.x, vertexA.y, x_center, y_center, vertexB.x, vertexB.y));
        }else{
            double angle = Math.atan2(y_center - vertexA.y, x_center - vertexA.x);
            double dx = Math.cos(angle);
            double dy = Math.sin(angle);
           
            int rc = (int)(vertexA.r*Math.sqrt(2));
            int xloop = vertexA.x - vertexA.r + (int)(dx*rc);
            int yloop = vertexA.y - vertexA.r + (int)(dy*rc);
            
            g.drawArc(xloop, yloop , vertexA.r*2, vertexA.r*2, 0, 360); 
        }
        g.drawString(name, x_center, y_center);
       
    }
    
}