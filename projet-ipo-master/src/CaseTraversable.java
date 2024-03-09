public class CaseTraversable extends Case{
    protected Entite contenu;
    public CaseTraversable(int l , int c, Entite e){
        super(l, c);
        contenu = e;
    }public CaseTraversable(int l , int c){
        super(l, c);
    }
    public Entite getContenu(){return contenu;}
    
    public void vide(){
        if(!estLibre()){
            contenu = null;
        }
        else throw new IllegalAccessError();
    }
    public boolean estLibre(){
        return (contenu==null);
    }
    public void entre (Entite e){
        contenu = e;
    }
    public void clearEntities(){
        if( contenu.estMort())
            this.vide();
    }
}
