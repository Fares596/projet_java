package pandemic;


/** Classe modélisant les maladies du jeu */
public class Maladie {
    public static final int MAX_CUBES = 24 ;
    protected String nom; // nom de la maladie
    protected int cubesRestants;
    protected boolean aEteGuerie;

   /**
    * Constructeur de la classe Maladie
    * @param nom Le nom de la maladie
    */
    public Maladie(String nom){
        this.nom = nom;
        this.cubesRestants = Maladie.MAX_CUBES;
        this.aEteGuerie=false;
    }
    
    /**
     *@return Le nom de la maladie
     */
    public String getName(){
        return this.nom;
    }
    
    /**
     *@return Le nombre de cubes de maladie non utilisés
     */
    public int getCubes(){
        return this.cubesRestants;
    }

    /**
     * Enlève un cube de maladie du plateau
     */
    public void ajouteCubeStock(){
        if (this.cubesRestants < Maladie.MAX_CUBES) {
            this.cubesRestants += 1;
        }
    }

    /**
     * Ajoute un cube de maladie sur le plateau
     */
    public void retireCubeStock(){
        if (this.cubesRestants >0) {
            this.cubesRestants -= 1;
        }
    }

    /**
     * Permet de savoir si la maladie a été guérie ou non
     * @return true si un remède a été trouvé à la maladie, false sinon
     */
    public boolean estGuerie() {
    	return this.aEteGuerie;
    }
    
    /**
     * Guérit une maladie en changeant son attribut aEteGuerie à true pour que la maladie soit considérée guérie
     */
    public void guerir() {
    	this.aEteGuerie=true;
    }

    public String toString() {
    	return this.getName();
    }
}

