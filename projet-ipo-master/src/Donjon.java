import javax.swing.Timer;

public class Donjon {
    public static void main(String[] args) {
        int tempo = 100;
        Jeu jeu = new Jeu("laby2.txt");
        FenetreJeu graphic = new FenetreJeu(jeu.terrain);
        Timer timer = new Timer(tempo, e -> {
            try{
                jeu.tour();
                graphic.repaint();
                if (jeu.partieFinie()) { graphic.ecranFinal(jeu.sortis); }
            }catch(RuntimeException rexcp){
                graphic.ecranFinal2(jeu.sortis);
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }
}
