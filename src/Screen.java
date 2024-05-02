import javax.swing.*;
import java.awt.*;

public class Screen extends javax.swing.JFrame {
    public JPanel drawingP;
    private JPanel buttonP;
    public JButton draw;
    public JSpinner ASpinnerF;
    public JSpinner ASpinnerT;
    public JSpinner BSpinnerF;
    public JSpinner BSpinnerT;
    private JLabel FT;
    Screen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(740, 540);
        setLayout(new FlowLayout());

        initPanels();

        add(drawingP);
        add(buttonP);

        setVisible(true);
    }

    private void initPanels() {
        drawingP = new JPanel(null);
        drawingP.setVisible(true);
        drawingP.setLocation(10, 10);
        drawingP.setSize(500, 500);
        drawingP.setPreferredSize(new Dimension(500, 500));
        drawingP.setMaximumSize(new Dimension(500, 500));
        drawingP.setBackground(Color.black);

        buttonP = new JPanel(null);
        buttonP.setVisible(true);
        buttonP.setLocation(520, 10);
        buttonP.setSize(200, 500);
        buttonP.setPreferredSize(new Dimension(200, 500));
        buttonP.setMaximumSize(new Dimension(200, 500));
        buttonP.setBackground(Color.MAGENTA);

        initButtons();
        buttonP.add(draw);

        initFT();
        buttonP.add(FT);

        initSpinners();
        buttonP.add(ASpinnerF);
        buttonP.add(ASpinnerT);
        buttonP.add(BSpinnerF);
        buttonP.add(BSpinnerT);
    }

    private void initButtons() {
        draw = new JButton("Draw");
        draw.setLocation(10, 10);
        draw.setSize(180, 20);
        draw.setVisible(true);
    }

    private void initFT() {
        FT = new JLabel("From:               Till:");
        FT.setLocation(10, 40);
        FT.setSize(180, 20);
        FT.setVisible(true);
    }


    private void initSpinners() {
        ASpinnerF = new JSpinner();
        ASpinnerF.setModel(new SpinnerNumberModel(-2.0, -4.0, -1.0, 0.01));
        ASpinnerF.setLocation(5, 70);
        ASpinnerF.setSize(90, 20);
        ASpinnerF.setVisible(true);

        ASpinnerT = new JSpinner();
        ASpinnerT.setModel(new SpinnerNumberModel(2.0, 1.0, 4.0, 0.01));
        ASpinnerT.setLocation(100, 70);
        ASpinnerT.setSize(85, 20);
        ASpinnerT.setVisible(true);

        BSpinnerF = new JSpinner();
        BSpinnerF.setModel(new SpinnerNumberModel(-2.0, -4.0, -1.0, 0.01));
        BSpinnerF.setLocation(5, 100);
        BSpinnerF.setSize(90, 20);
        BSpinnerF.setVisible(true);

        BSpinnerT = new JSpinner();
        BSpinnerT.setModel(new SpinnerNumberModel(2.0, 1.0, 4.0, 0.01));
        BSpinnerT.setLocation(100, 100);
        BSpinnerT.setSize(85, 20);
        BSpinnerT.setVisible(true);
    }


}
