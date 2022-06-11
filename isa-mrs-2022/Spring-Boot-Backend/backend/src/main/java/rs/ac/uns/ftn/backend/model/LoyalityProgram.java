package rs.ac.uns.ftn.backend.model;

public class LoyalityProgram {

    public static Long appProfit = 5l;

    public static Long silverPercent = 10l;

    public static Long goldPercent = 20l;

    public static Long silverPoints= 10l;

    public static Long goldPoints = 20l;


    public static Long getPercent(Long point){
        if(point> LoyalityProgram.silverPoints){
            System.out.println("USO U SLIVER");
            return silverPercent;
        }
        else if(point> LoyalityProgram.goldPoints){
            System.out.println("USO U GOLD");
            return goldPercent;
        }
        return 0l;
    }

}
