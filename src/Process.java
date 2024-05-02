import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.MemoryImageSource;

public class Process {
    Screen screen;
    Algorithms algorithms;

    public void start() {
        screen  = new Screen();

        screen.draw.addActionListener(e -> drawIsPresed());
    }

    private void drawIsPresed() {
        algorithms = new Algorithms();
        algorithms.calc((Double) screen.ASpinnerF.getValue(), (Double) screen.ASpinnerT.getValue(), (Double) screen.BSpinnerF.getValue(), (Double) screen.BSpinnerT.getValue());

        screen.drawingP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                drawZoom(evt);
            }
        });


        Graphics g = screen.drawingP.getGraphics();

        Image img = screen.createImage( new MemoryImageSource(500, 500, algorithms.pixels, 0, 500));
        g.drawImage(img, 0, 0, null);
    }

    private void drawZoom(MouseEvent e) {
        algorithms.zoom(e, (Double) screen.ASpinnerF.getValue(), (Double) screen.ASpinnerT.getValue(), (Double) screen.BSpinnerF.getValue(), (Double) screen.BSpinnerT.getValue());

        Graphics g = screen.drawingP.getGraphics();

        Image img = screen.createImage( new MemoryImageSource(500, 500, algorithms.pixels, 0, 500));
        g.drawImage(img, 0, 0, null);
    }
}
