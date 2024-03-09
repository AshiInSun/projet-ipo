import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class Terrain {


    private int hauteur, largeur;
    private Case[][] carte;
    private ArrayList<Case> entities;
    private Case caseJoueur;
    /* Initialisation d'un terrain à partir de la description donnée par
       un fichier texte. Format du fichier de description :
       - hauteur et largeur sur la première ligne
       - puis dessin du terrain (un caractère == une case) avec le codage
         suivant
         '#' pour un mur
         ' ' (espace) pour une case libre
         'o' pour une sortie
         '@' pour une case libre contenant un obstacle
         '^', 'v', '>', '<' pour une case libre contenant un personnage
         'm', 'w', '»', '«' pour une case libre contenant un monstre
    */
    public Terrain(String file) {
        try {
            Scanner sc = new Scanner(new FileInputStream(file));
            this.hauteur = sc.nextInt();
            this.largeur = sc.nextInt();
            sc.nextLine();
            this.carte = new Case[hauteur][largeur];
            for (int l=0; l<hauteur; l++) {
                this.entities = new ArrayList<Case>();
                String line = sc.nextLine();
                for (int c=0; c<largeur; c++) {
                    Case cc;
                    Character ch = line.charAt(c);
                    switch (ch) {
                        case '#': cc = new CaseIntraversable(l, c); break;
                        case ' ': cc = new CaseLibre(l, c); break;
                        case 'H': cc = new CaseLibre(l, c,new Joueur(Direction.nord,5));break;
                        case 'o': cc = new Sortie(l, c); break;
                        case '@': cc = new CaseLibre(l, c, new Obstacle()); break;
                        case '^': case '>': case 'v': case '<':
                            cc = new CaseLibre(l, c, new Personnage(Direction.ofChar(ch)));
                            break;
                        case 'm': case '»': case 'w': case '«':
                            cc = new CaseLibre(l, c, new Monstre(Direction.ofChar(ch)));
                            break;
                        default:  cc = null;System.out.println(ch); break;
                    }
                    carte[l][c] = cc;
                }
            }
            sc.close();
        }

        catch (IOException e) { e.printStackTrace(); }
    }public void print(){
        for (int i = 0; i < hauteur; i++){
            for (int  y= 0; y < largeur; y++){
                System.out.print(carte[i][y]) ;
            }System.out.println();
        }System.out.println();
        System.out.println();
    }
    public void startFullEntities(){
        entities.clear();
        for (int i = 0; i < hauteur; i++){
            for (int  y= 0; y < largeur; y++){
                if(carte[i][y] instanceof CaseLibre){
                    if (((CaseTraversable)carte[i][y]).getContenu() instanceof EntiteMobile
                            && ((CaseTraversable)carte[i][y]).getContenu() != null)
                        entities.add(carte[i][y]);
                }
            }
        }
        //for (int i = 0 ; i< entities.size(); i++)System.out.println("élément :" + entities.get(i));
    }
    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }
    public boolean partiFini(){
        int compt = 0;
        for(Case c : entities){
            if( ((CaseTraversable)c).getContenu() instanceof Personnage )
                compt++;
        }return compt==0;
    }

    public Case getCase(int x,int y) {
        return carte[x][y];
    }

    public Case getCaseCouranteMob(int n){
        return entities.get(n);
    }
    public Case getCaseCibleMob(int n){
        Case c = entities.get(n);
        EntiteMobile e =(EntiteMobile)((CaseTraversable)carte[c.getLig()][c.getCol()]).getContenu();
        switch(e.getDir()){
            case ouest : return carte[c.getLig()][c.getCol()-1];
            case sud : return carte[c.getLig()+1][c.getCol()];
            case est : return carte[c.getLig()][c.getCol()+1];
            case nord : return carte[c.getLig()-1][c.getCol()];
            default : return c;
        }
    }
    //public void checkIndex(){
    //    if (++indexEntity>=entities.size())
    //    indexEntity = 0;
    //else indexEntity++;
    //}
    public int getSize(){return entities.size();}
    public Joueur getJoueur(){
        this.startFullEntities();
        for(Case c : entities){
            if( (((CaseTraversable)c).getContenu()) instanceof  Joueur){
                caseJoueur = c;
                return (Joueur)((CaseTraversable)c).getContenu();
            }

        }throw new IllegalArgumentException("Erreur");
    }public Case getCaseJoueur(){return caseJoueur;}
    public Case getCaseCibleJoueur(Direction d){
        switch(d){
            case ouest : if(!(carte[caseJoueur.getLig()][caseJoueur.getCol()-1] instanceof CaseIntraversable || ((CaseTraversable) carte[caseJoueur.getLig()][caseJoueur.getCol()-1]).getContenu() instanceof Obstacle))
                caseJoueur = carte[caseJoueur.getLig()][caseJoueur.getCol()-1];
                return carte[caseJoueur.getLig()][caseJoueur.getCol()];
            case sud : if(!(carte[caseJoueur.getLig()+1][caseJoueur.getCol()] instanceof CaseIntraversable|| ((CaseTraversable) carte[caseJoueur.getLig()+1][caseJoueur.getCol()]).getContenu() instanceof Obstacle))
                caseJoueur = carte[caseJoueur.getLig()+1][caseJoueur.getCol()];
                return carte[caseJoueur.getLig()][caseJoueur.getCol()];
            case est : if(!(carte[caseJoueur.getLig()][caseJoueur.getCol()+1] instanceof CaseIntraversable|| ((CaseTraversable) carte[caseJoueur.getLig()][caseJoueur.getCol()+1]).getContenu() instanceof Obstacle))
                caseJoueur = carte[caseJoueur.getLig()][caseJoueur.getCol()+1];
                return carte[caseJoueur.getLig()][caseJoueur.getCol()];
            case nord : if(!(carte[caseJoueur.getLig()-1][caseJoueur.getCol()] instanceof CaseIntraversable|| ((CaseTraversable) carte[caseJoueur.getLig()-1][caseJoueur.getCol()]).getContenu() instanceof Obstacle))
                caseJoueur = carte[caseJoueur.getLig()-1][caseJoueur.getCol()];
                return carte[caseJoueur.getLig()][caseJoueur.getCol()];
            default : return caseJoueur;
        }
    }
}
