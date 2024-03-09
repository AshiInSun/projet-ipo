import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import static java.awt.event.KeyEvent.*;
public class FenetreJeu extends JPanel implements KeyListener{
    private Terrain terrain;
    private int tailleCase = 32;
    private int hauteur, largeur;
    private JFrame frame;
    private Joueur j;
    Image brick;

    public FenetreJeu(Terrain t) {
        this.hauteur = t.getHauteur();
        this.largeur = t.getLargeur();
        this.terrain = t;
        j = terrain.getJoueur();
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(largeur * tailleCase, hauteur * tailleCase));

        JFrame frame = new JFrame("Donjon");
        this.frame = frame;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);
        frame.addKeyListener(this);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int x = 0; x < terrain.getHauteur();x++) {
            for (int y = 0; y < terrain.getLargeur(); y++) {
                Case c = terrain.getCase(x, y);
                if (c instanceof CaseIntraversable) {
                    //g.setColor( Color.BLACK);
                    //g.fillRect(y*tailleCase,x*tailleCase,tailleCase,tailleCase);
                    try {
                        brick = ImageIO.read(new File("brique.png"));
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                    g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                } else {
                    if (c instanceof Sortie) {
                        //g.setColor(Color.BLUE);
                        //g.fillOval(y * tailleCase, x * tailleCase, tailleCase - 5, tailleCase - 5);
                        try {
                            brick = ImageIO.read(new File("sortie.png"));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                        g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                    }

                    if (!c.estLibre()) {
                        if (((CaseTraversable) c).getContenu() instanceof Personnage) {
                            //g.setColor(Color.YELLOW);
                            //g.fillOval(y * tailleCase, x * tailleCase, tailleCase - 5, tailleCase - 5);
                            try {
                                brick = ImageIO.read(new File("mouton.png"));
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                            g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);

                        } else if (((CaseTraversable) c).getContenu() instanceof Joueur){
                            try {
                                brick = ImageIO.read(new File("Berger.png"));
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                            g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                        }

                        else if (((CaseTraversable) c).getContenu() instanceof Monstre) {
                            try {
                                brick = ImageIO.read(new File("monstre.png"));
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                            g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                        } else if (((CaseTraversable) c).getContenu() instanceof Obstacle) {
                            //g.setColor(new Color(0, 255, 0, ((CaseTraversable) c).getContenu().getResistance() * 50));
                            //g.fillRect(y * tailleCase, x * tailleCase, tailleCase, tailleCase);
                            try {
                                int r = ((CaseTraversable) c).getContenu().getResistance();
                                if (r==3) brick = ImageIO.read(new File("bouteille.png"));
                                else if (r==2) brick = ImageIO.read(new File("bouteille2.png"));
                                else if (r==1) brick = ImageIO.read(new File("bouteille3.png"));

                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                            g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                        }

                    }else if (c instanceof CaseLibre){
                        try {
                            brick = ImageIO.read(new File("sol.png"));
                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }
                        g.drawImage(brick, c.col * tailleCase, c.lig * tailleCase, tailleCase, tailleCase, this);
                    }
                }
            }
        }
        for(int i = 0 ; i < j.getResistance();i++){
            try {
                brick = ImageIO.read(new File("coeur.png"));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
            g.drawImage(brick, 32 + i*32, (hauteur-1)*tailleCase, tailleCase, tailleCase, this);
        }
    }

    public void ecranFinal(int n) {
        frame.remove(this);
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setSize(this.getSize());
        frame.getContentPane().add(label);
        frame.repaint();
    }public void ecranFinal2(int n) {
        frame.remove(this);
        JLabel lab = new JLabel("Loser ");
        lab.setFont(new Font("Verdana", 1, 50));
        lab.setSize(this.getSize());
        JLabel label = new JLabel("Score " + n);
        label.setFont(new Font("Verdana", 1, 20));
        frame.getContentPane().add(lab);
        frame.getContentPane().add(label);
        frame.repaint();
    }

    public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.ouest));
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Case c = terrain.getCaseJoueur();
            j.action(c, terrain.getCaseCibleJoueur(Direction.est));
        }if(e.getKeyCode() == KeyEvent.VK_UP) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.nord));
        }if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.sud));
        }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.exit(1);
        }
    }

    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.ouest));
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            Case c = terrain.getCaseJoueur();
            j.action(c, terrain.getCaseCibleJoueur(Direction.est));
        }if(e.getKeyCode() == KeyEvent.VK_UP) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.nord));
        }if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            j.action(terrain.getCaseJoueur(), terrain.getCaseCibleJoueur(Direction.sud));
        }if(e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        return;
    }
}


