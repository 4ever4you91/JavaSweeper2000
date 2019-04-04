import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import sweeper.Box;
import sweeper.Coard;
import sweeper.Game;
import sweeper.Ranges;

import static sweeper.GameState.BOMBED;
import static sweeper.GameState.PLAYED;

public class JavaSweeper extends JFrame {
    private Game game;
    private JPanel panel;
    private JLabel label;
    private final int CALLS = 9;
    private final int BOMBS = 9;
    private final int ROWS = 9;
    private final int IMAGE_SIZE = 50;
    public static void main(String[] args) {
        new JavaSweeper();
    }
    private JavaSweeper()
    {
        game = new Game(CALLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }
    private void initLabel()
    {
        label = new JLabel("Wellcome!");
        add (label, BorderLayout.SOUTH);
    }
    private void initPanel(){
        panel = new JPanel()
        {
            @Override
            protected void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                for (Coard coard : Ranges.getAllCoards())
                {

                    g.drawImage((Image) game.getBox(coard).image,
                            coard.x * IMAGE_SIZE, coard.y * IMAGE_SIZE, this);
                }
            }
        };
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX()/IMAGE_SIZE;
                int y = e.getY()/IMAGE_SIZE;
                Coard coard = new Coard(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coard);
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coard);
                label.setText(getMessage());
                panel.repaint();
            }
        });
        panel.setPreferredSize(new Dimension(
                Ranges.getSize().x * IMAGE_SIZE,
                Ranges.getSize().y * IMAGE_SIZE));
        add(panel);
    }
    private String getMessage() {
        switch (game.getState()) {
            case PLAYED:
                return "Think twice";
            case BOMBED:
                return "YOU LOSE";
            case WINNER:
                return "CONGRATULATIONS";
            default:
                return "NONE";

        }
    }

    private void initFrame(){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Java Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("icon"));
        pack();


    }
    private void setImages()
    {
        for (Box box : Box.values())
            box.image = getImage(box.name().toLowerCase());
    }

    private Image getImage(String name){
        String filename = "img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
