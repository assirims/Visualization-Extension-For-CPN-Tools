package visualize;

import java.awt.Color;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import org.cpntools.simulator.extensions.*;
import org.cpntools.simulator.extensions.graphics.*;


/***************************************************************************************
 *                                                                                                                                              *
 *                                             Functions                                                                                    *
 *                                                                                                                                              *
 ***************************************************************************************/
public class Visualize extends AbstractExtension {
    
    public Visualize(){
        addInstrument(new Instrument("GUI"));
        addObserver(new Observer() {
            public void update(final Observable source, final Object value) {
                if (value instanceof Invocation) {
                    try {
                        ////////////////////////////////////////////////////////
                        /* Create and display the form */
                        java.awt.EventQueue.invokeLater(new Runnable() {
                            public void run() {
                                new VUI().setVisible(true);
                                }
                            });
                        ///////////////////////////////////////////////////////
                        } catch (final Exception e) { }
                    }
                }
            });
        }
    
    @Override
    public String getName() { return "Visualize"; }
    @Override
    public int getIdentifier() { return Visualize.TESTING; }
    
    private int nextObject = 0;
    private final Map objects = Collections.synchronizedMap(new HashMap());
    
    // ********************* 1 **************************    
    String create_canvas(String name) throws Exception {
        final Canvas c = new Canvas(channel, name, true, true);
        final String key = "object.ID." + ++nextObject;
        objects.put(key, c);
        return key;
        }
    // ********************* 2 **************************    
    String quickShap(String canvas, String shape, int x, int y, int width, int height) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            final String RecKey = "object.ID." + ++nextObject;
            if ("R".equalsIgnoreCase(shape)) {
            Rectangle r = new Rectangle(x, y, width, height).setBackground(Color.WHITE);
            c.add(r);
            objects.put(RecKey, r);    
            }else if ("C".equalsIgnoreCase(shape)){
                Ellipse e = new Ellipse(x, y, width, height).setBackground(Color.WHITE);
                c.add(e);
                objects.put(RecKey, e);
            }else{
                Text t = new Text(x, y,"ERROR! Check Parameters").setForeground(Color.RED);
                c.add(t);
                //objects.put(RecKey, t);
            }
            c.suspend(false);
            return RecKey;
        }
    }
    // ********************* 3 **************************    
    String label(String canvas, int x, int y, String text, String Fg) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            final String RecKey = "object.ID." + ++nextObject;
            Text t = new Text(x, y,text).setForeground(Color.decode(Fg));
            c.add(t);
            objects.put(RecKey, t);
            c.suspend(false);
            return RecKey;
        }
    } 
    // ********************* 4 **************************    
    String draw_shape(String canvas, String shape, int x, int y, int width, int height, int Lwidth, int curvature, String Bg, String Fg) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
        final String RecKey = "object.ID." + ++nextObject;
        if ("R".equalsIgnoreCase(shape)) {
        Rectangle r = new Rectangle(x, y, width, height);
        r.setBackground(Color.decode(Bg));
        r.setForeground(Color.decode(Fg));
        r.setCurvature(curvature);
        r.setLineWidth(Lwidth);
        c.add(r);
        objects.put(RecKey, r);
        }else if ("C".equalsIgnoreCase(shape)){
            Ellipse e = new Ellipse(x, y, width, height);
            e.setBackground(Color.decode(Bg));
            e.setForeground(Color.decode(Fg));
            e.setLineWidth(Lwidth);
            c.add(e);
            objects.put(RecKey, e);
        }else{
                Text t = new Text(x, y,"ERROR! Check Parameters").setForeground(Color.RED);
                c.add(t);
                //objects.put(RecKey, t);
            }
        c.suspend(false);
        return RecKey;
        }
    }      
    // ********************* 5 **************************    
    void move_object(String canvas, String object, String shape, int x, int y) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            if ("R".equalsIgnoreCase(object)) {
                final Rectangle r = (Rectangle) objects.get(shape);
                r.setPosition(new Point(x,y));
            }else if ("C".equalsIgnoreCase(object)){
                final Ellipse e = (Ellipse) objects.get(shape);
                e.setPosition(new Point(x,y));
            }else if ("L".equalsIgnoreCase(object)){
                final Text t = (Text) objects.get(shape);
                t.setPosition(new Point(x,y));
            }else{
                // do nothing
            }
            c.suspend(false);
        }
    }
    // ********************* 6 **************************   
    void resize_shape(String canvas, String object, String shape, int width, int heigh) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            if ("R".equalsIgnoreCase(object)) {
                final Rectangle r = (Rectangle) objects.get(shape);
                r.setSize(width, heigh);
            }else if ("C".equalsIgnoreCase(object)){
                final Ellipse e = (Ellipse) objects.get(shape);
                e.setSize(width, heigh);
            }else{
                // do nothing
            }
            c.suspend(false);
        }
    }
    // ********************* 7 **************************    
    void update_border(String canvas, String object, String shape, int Lwidth, int curvature) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);        
        synchronized (c) {
            if ("R".equalsIgnoreCase(object)) {
                final Rectangle r = (Rectangle) objects.get(shape);
                r.setLineWidth(Lwidth);
                r.setCurvature(curvature);
            }else if ("C".equalsIgnoreCase(object)){
                final Ellipse e = (Ellipse) objects.get(shape);
                e.setLineWidth(Lwidth);
                e.setCurvature(curvature);
            }else{
                // do nothing
            }
            c.suspend(false);
        }
    }
    // ********************* 8 **************************    
    void change_color(String canvas, String object, String shape, String Bg, String Fg) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);        
        synchronized (c) {
            if ("R".equalsIgnoreCase(object)) {
                final Rectangle r = (Rectangle) objects.get(shape);
                r.setBackground(Color.decode(Bg));
                r.setForeground(Color.decode(Fg));
            }else if ("C".equalsIgnoreCase(object)){
                final Ellipse e = (Ellipse) objects.get(shape);
                e.setBackground(Color.decode(Bg));
                e.setForeground(Color.decode(Fg));
            }else{
                // do nothing
            }
            c.suspend(false);
        }
    }
    // ********************* 9 **************************   
    String change_label_text(String canvas, String shape, String text) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            final Text t = (Text) objects.get(shape);
            final String RecKdy = "object.ID." + ++nextObject;
            Text tt = new Text(t.getPosition(), text);
            c.remove(t);
            c.add(tt);
            objects.put(t, tt);
            c.suspend(false);
            return RecKdy;
        }
    }
    // ********************* 10 *************************   
    void change_label_color(String canvas, String shape, String Fg) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            final Text t = (Text) objects.get(shape);
            t.setForeground(Color.decode(Fg));
            c.suspend(false);
        }
    }
    // ********************* 11 *************************    
    void remove_object(String canvas,String object, String shape) throws Exception{
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            if ("R".equalsIgnoreCase(object)) {
                final Rectangle r = (Rectangle) objects.get(shape);
                c.remove(r);
            }else if ("C".equalsIgnoreCase(object)){
                final Ellipse e = (Ellipse) objects.get(shape);
                c.remove(e);
            }else if ("L".equalsIgnoreCase(object)){
                final Text t = (Text) objects.get(shape);
                c.remove(t);
            }else{
                // do nothing
            }
            c.suspend(false);
        }  
    }
    // ********************* 12 *************************
    void shape_sets(String canvas, String object, int row, int column, int rspace, int cspace, int x, int y, int width, int height, int Lwidth, int curvature, String Bg, String Fg) throws Exception {
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            boolean suspended = c.suspend(true);
            for (int m = 0; m< row; m++) {
                for (int i = 0; i < column; i++) {
                    if ("R".equalsIgnoreCase(object)) {
                        c.add(rectangle_set(x, y, width, height, Lwidth, curvature, Bg, Fg).move(new Point(i * rspace, m * cspace)));
                    }else if ("C".equalsIgnoreCase(object)){
                        c.add(circle_set(x, y, width, height, Lwidth, curvature, Bg, Fg).move(new Point(i * rspace, m * cspace)));
                    }else{//do nothing
                    }
                }
            }
            c.suspend(suspended);
        }
    }
    
    Element<?> rectangle_set(int x, int y, int width, int height, int Lwidth, int curvature, String Bg, String Fg) throws Exception {
        final Group g = new Group();
        final Rectangle r;
        r = new Rectangle(x, y, width, height);
        r.setBackground(Color.decode(Bg));
        r.setForeground(Color.decode(Fg));
        r.setCurvature(curvature);
        r.setLineWidth(Lwidth);
        g.add(r);
        return g;
    }
    Element<?> circle_set(int x, int y, int width, int height, int Lwidth, int curvature, String Bg, String Fg) throws Exception {
        final Group g = new Group();
        final Ellipse e;
        e = new Ellipse(x, y, width, height);
        e.setBackground(Color.decode(Bg));
        e.setForeground(Color.decode(Fg));
        e.setCurvature(curvature);
        e.setLineWidth(Lwidth);
        g.add(e);
        return g;
    }   
 
    // ********************* 13 *************************
    void label_sets(String canvas, int row, int column, int rspace, int cspace, int x, int y, String Fg, String statictext, String printnumbers) throws Exception {
        final Canvas c = (Canvas) objects.get(canvas);
        synchronized (c) {
            boolean suspended = c.suspend(true);
            for (int m = 1; m<= row; m++) {
                for (int i = 0; i < column; i++) {
                    c.add(label_set(x, y, statictext+" "+(("No".equalsIgnoreCase(printnumbers)) ? "" : m), Fg).move(new Point(i * rspace, m * cspace)));
            }
            }
            c.suspend(suspended);
        }
    }
    
    Element<?> label_set(int x, int y, String text, String Fg) throws Exception {
        final Group g = new Group();
        final Text t = new Text(x, y,text).setForeground(Color.decode(Fg));
        g.add(t);
        return g;
    }
    
    
/***************************************************************************************
 *                                                                                                                                              *
 *                                   Implementation                                                                                      *
 *                                                                                                                                              *
 ***************************************************************************************/
    public final class Handler implements NamedRPCHandler{
        @Override
        public String structureName() { return "Visualize"; }
        // ********************* 1 **************************
        public String CreateCanvas(String name) throws Exception {
            return Visualize.this.create_canvas(name);
            }
        // ********************* 2 **************************
        public String QuickShap(String canvas, String shape, Integer x, Integer y, Integer width, Integer height) throws Exception{
            return Visualize.this.quickShap(canvas, shape, x, y, width, height);
        }
        // ********************* 3 **************************
        public String Lable(String canvas, Integer x, Integer y, String text, String color) throws Exception {
            return Visualize.this.label(canvas, x, y, text, color);
        }
        // ********************* 4 **************************
        public String DrawShap(String canvas, String shape, Integer x, Integer y, Integer width, Integer height, Integer Lwidth, Integer curvature, String Bg, String Fg) throws Exception{
            return Visualize.this.draw_shape(canvas, shape, x, y, width, height, Lwidth, curvature, Bg, Fg);
        }
        // ********************* 5 **************************
        public void MoveObject(String canvas,String object, String shape, Integer x, Integer y) throws Exception{
            move_object(canvas, object, shape, x, y);
        }
        // ********************* 6 **************************
        public void ResizeShape(String canvas, String object, String shape, Integer width, Integer heigh) throws Exception{
            resize_shape(canvas, object, shape, width, heigh);
        }
        // ********************* 7 **************************
        public void UpdateBorder(String canvas, String object, String shape, Integer Lwidth, Integer curvature) throws Exception{
            update_border(canvas, object, shape, Lwidth, curvature);
        }
        // ********************* 8 **************************
        public void ChangeColor(String canvas, String object, String shape, String Bg, String Fg) throws Exception{
            change_color(canvas, object, shape, Bg, Fg);
        }
        // ********************* 9 **************************
        public String ChangeLableText(String canvas, String shape, String text) throws Exception{
            return Visualize.this.change_label_text(canvas, shape, text);
        }
        // ********************* 10 *************************
        public void ChangeLabelColor(String canvas, String shape, String Fg) throws Exception{
            change_label_color(canvas, shape, Fg);
        }
        // ********************* 11 *************************
        public void RemoveObject(String canvas,String object, String shape) throws Exception{
            remove_object(canvas, object, shape);
        }
        // ********************* 12 *************************
        public void ShapeSet(String canvas, String object, Integer row, Integer column, Integer rspace, Integer cspace, Integer x, Integer y, Integer width, Integer height, Integer Lwidth, Integer curvature, String Bg, String Fg) throws Exception {
            shape_sets(canvas, object, row, column, rspace, cspace, x, y, width, height, Lwidth, curvature, Bg, Fg);
        }
        // ********************* 13 *************************
        public void LabelSet(String canvas, Integer row, Integer column, Integer rspace, Integer cspace, Integer x, Integer y, String Fg, String statictext, String printnumbers) throws Exception {
            label_sets(canvas, row, column, rspace, cspace, x, y, Fg, statictext , printnumbers);
        }        
    }
    
    @Override
    public Object getRPCHandler() {
        return new Handler();
        }
    
    }
