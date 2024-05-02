import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class Algorithms {
    public int[] pixels = new int[501*501];
    private Stack<Cords> stack = new Stack<>();

    public void calc(double fromA, double tillA, double fromB, double tillB) {
        for (int i = 0; i < 500*500; i++) {
            pixels[i] = (255<<24) | (0<<16) | (255<<8) | 0;
        }

        double m = 10;
        double z = 0;
        int pos = 0;

        double aInterval = (tillA-fromA)/500;
        double bInterval = (tillB-fromB)/500;

        for (double b = fromB; b < tillB; b += bInterval) {
            for (double a = fromA; a < tillA; a += aInterval) {
                double cR = a;
                double oldA = a;

                double cI = b;
                double oldB = b;

                int o = 0;
                do {
                    double nA = (oldA*oldA) - (oldB*oldB) + cR;
                    double nB = 2*oldA*oldB + cI;
                    z = (nA*nA) + (nB*nB);
                    oldA = nA;
                    oldB = nB;

                    o += 1;
                } while (o < 100 && z <= m);

                if (o >= 100) {
                    //black
                    pixels[pos] = (255<<24) | (0<<16) | (0<<8) | 0;
                } else if (o >= 25) {
                    //orange
                    pixels[pos] = (255<<24) | (255<<16) | (165<<8) | 0;
                } else if (o >= 20) {
                    //purple
                    pixels[pos] = (255<<24) | (160<<16) | (32<<8) | 240;
                } else if (o >= 15) {
                    //red
                    pixels[pos] = (255<<24) | (230<<16) | (0<<8) | 0;
                } else if (o >= 10) {
                    //yellow
                    pixels[pos] = (255<<24) | (230<<16) | (230<<8) | 0;
                } else if (o >= 5) {
                    //green
                    pixels[pos] = (255<<24) | (0<<16) | (230<<8) | 0;
                } else {
                    //blue
                    pixels[pos] = (255<<24) | (0<<16) | (0<<8) | 255;
                }
                pos++;
            }
            if (pos%500 > 0) {
                pos -= pos%500;
            }
        }
    }

    public void zoom(MouseEvent e, double AF, double AT, double BF, double BT) {
        double zoom = 500/4;

        if (!stack.isEmpty()) {
            AF = stack.lastElement().AF;
            AT = stack.lastElement().AT;
            BF = stack.lastElement().BF;
            BT = stack.lastElement().BT;
        } else {
            stack.push(new Cords(AF, AT, BF, BT));
        }

        if (SwingUtilities.isLeftMouseButton(e)){
            double mouseX = e.getX();
            double mouseY = e.getY();
            double newAF = AF;
            double newBF = BF;
            AF = AF + (AT - AF)*((mouseX-zoom)/500);
            AT = newAF + (AT - newAF)*((mouseX+zoom)/500);
            BF = BF + (BT - BF)*((mouseY-zoom)/500);
            BT = newBF + (BT - newBF)*((mouseY+zoom)/500);
            if (AF >= -2.0 && AT <= 2.0 && BF >= -2.0 && BT <= 2.0) {
                stack.push(new Cords(AF, AT, BF, BT));
                calc(AF, AT, BF, BT);
            } else {
                if (mouseX-zoom/2 < 0){
                    AF = -2.0;
                    AT = 2 + 4*(zoom/500);
                }
                if (mouseX+zoom/2 > 500){
                    AT = 2.0;
                    AF = 2 + 4*(500-zoom/500);
                }
                if (mouseY-zoom/2 < 0){
                    BF = -2.0;
                    BT = 2 + 4*(zoom/500);
                }
                if (mouseY+zoom/2 > 500){
                    BT = 2.0;
                    BF = 2 + 4*(500-zoom/500);
                }
                stack.push(new Cords(AF, AT, BF, BT));
                calc(AF, AT, BF, BT);
            }
        } else if (SwingUtilities.isRightMouseButton(e)){
            Cords k = stack.pop();
            AF=k.AF;
            AT=k.AT;
            BF=k.BF;
            BT=k.BT;
            calc(AF, AT, BF, BT);
        }
    }
}

    class Cords {
    public double AF;
    public double AT;
    public double BF;
    public double BT;

    public Cords(double AF, double AT, double BF, double BT) {
        this.AF = AF;
        this.AT = AT;
        this.BF = BF;
        this.BT = BT;
    }

    }