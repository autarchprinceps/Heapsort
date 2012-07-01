package heapsort;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author autarch
 */
public class HeapView extends javax.swing.JPanel {
    /**
     *
     */
    int[] content;
    int first;
    int second;
    Color firstC;
    Color secondC;
    public int wait;
    public int finished;
    /**
     *
     */
    public HeapView() {
        this.setDoubleBuffered(true);
        wait = 1000;
        finished = 0;
        initComponents();
    }

    public synchronized void sendToAnimation(Command c, Wrap pos) {
        pos.setI(pos.getI() + 1);
        switch(c.Type) {
            case ComparisonFlat:
                first = c.FirstIndex;
                second = c.SecondIndex;
                firstC = Color.blue;
                secondC = Color.blue;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                first = -1;
                second = -1;
                content = c.State;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                break;
            case ComparisonParent:
                first = c.FirstIndex;
                second = c.SecondIndex;
                firstC = Color.orange;
                secondC = Color.blue;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                first = -1;
                second = -1;
                content = c.State;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                break;
            case Swap:
                first = c.FirstIndex;
                second = c.SecondIndex;
                firstC = Color.red;
                secondC = Color.red;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                first = -1;
                second = -1;
                content = c.State;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                break;
            case Finish:
                first = c.FirstIndex;
                second = c.SecondIndex;
                firstC = Color.magenta;
                secondC = Color.magenta;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                finished++;
                first = -1;
                second = -1;
                content = c.State;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                break;
            case Init:
                first = -1;
                second = -1;
                content = c.State;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                break;
            case End:
                first = -1;
                second = -1;
                content = c.State;
                repaint();
                try {
                    Thread.sleep(wait);
                } catch(InterruptedException ex) {
                    Logger.getLogger(HeapView.class.getName()).log(Level.WARNING, null, ex);
                }
                break;
            default:
                System.out.println("*@# in sendToAnimation");
                break;
        }
    }

    int calcMaxTier() {
        int result = 0;
        int length = content.length - finished;
        while(true) {
            length -= (int)StrictMath.round(StrictMath.pow(2, result));
            result++;
            if(length <= 0) {
                return result;
            }
        }
    }

    int radius = 15;

    @Override
    public void paint(Graphics g) {
        if(content != null) {
            Graphics2D g2 = (Graphics2D)g;
            g2.setColor(Color.white);
            g2.clearRect(0, 0, this.getWidth(), this.getHeight());
            g2.setColor(Color.black);
            g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            if(content.length - finished <= 1) {
                return;
            }
            int max = calcMaxTier();
            int yoffset = (this.getHeight() - 30) / (max - 1);
            for(int tier = 1; tier <= max; tier++) {
                int count = (int)StrictMath.round(StrictMath.pow(2, tier - 1));
                int offset = this.getWidth() / (count + 1);
                int ypos = 15 + yoffset * (tier - 1);
                for(int slot = 1; slot <= count; slot++) {
                    int middlePosition = slot * offset;
                    int index = count - 2 + slot;
                    if(index >= content.length - finished) {
                        break;
                    }
                    if(index > 0) {
                        int x2 = ((slot + 1) / 2) * (this.getWidth() / (count / 2 + 1));
                        int y2 = (10 + yoffset * (tier - 2));
                        /*double alpha = StrictMath.atan((y2 - ypos) / (middlePosition- x2) );
                        int k = (int)StrictMath.round(StrictMath.cos(alpha) * radius);
                        int µ = (int)StrictMath.round(StrictMath.sin(alpha) * radius);*/
                        //g2.drawLine(middlePosition + k, ypos - µ, x2 - k, y2 + µ);
                        g2.drawLine(middlePosition, ypos, x2, y2);
                    }
                    if(index == first) { // TODO different colors when different instruction
                        g2.setColor(firstC);
                    } else if(index == second) {
                        g2.setColor(secondC);
                    }
                    g2.fillOval(middlePosition - radius, ypos - radius, radius * 2, radius * 2);
                    g2.setColor(Color.white);
                    g2.drawString("" + content[index], middlePosition - 10, ypos + 5);
                    g2.setColor(Color.black);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 677, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 441, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
