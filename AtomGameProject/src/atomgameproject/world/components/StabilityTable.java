/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package atomgameproject.world.components;

/**
 *
 * @author Owner
 */
public class StabilityTable {
    
    private static StabilityTable table = new StabilityTable();
    
    private int[][] stableRange;
    private String[] atomicSymbol;
    
    public StabilityTable() {
         stableRange = new int[][]{{1, 1},//1
         {1, 2},
         {3, 4},
         {5, 5},
         {5, 6}, //5
         {6, 7},
         {7, 8},
         {8, 10},
         {10, 10},
         {10, 12},//10
         {12, 12},
         {12, 14},
         {14, 14},
         {14, 16},
         {17, 17},//15
         {16, 18},
         {18, 20},
         {18, 22},
         {20, 22},
         {23, 24},//20
         {24, 24},
         {24, 28},
         {28, 28},
         {28, 30},
         {30, 30},//25
         {30, 32},
         {32, 32},
         {32, 36},
         {34, 36},
         {34, 38},//30
         {38, 40},
         {38, 42},
         {42, 42},
         {40, 46},
         {44, 46},//35
         {44, 50},
         {48, 48},
         {46, 50},
         {50, 50},
         {50, 54},//40
         {52, 52},
         {50, 56},
         {52, 55},
         {54, 58},
         {59, 59},//45
         {58, 62},
         {60, 62},
         {60, 64},
         {64, 64},
         {64, 72},//40
         {70, 72},
         {68, 72},
         {74, 74},
         {72, 80},
         {78, 78},//55
         {78, 82}
         };
         
         atomicSymbol = new String[]{"H", "He", "Li", "Be", "B", "C", "N", "O", "F", "Ne",
         "Na", "Mg", "Al", "Si", "P", "S", "Cl", "Ar", "K", "Ca", "Sc", "Ti", "V", "Cr", "Mn",
         "Fe", "Co", "Ni", "Cu", "Zn", "Ga", "Ge", "As", "Se", "Br", "Kr", "Rb", "Sr", "Y",
         "Zr", "Nb", "Mo", "Tc", "Ru", "Rh", "Pd", "Ag", "Cd", "In", "Sn", "Sb", "Te", "I", "Xe",
         "Cs", "Ba"};
    }
    
    public String getAtomicSymbol(AtomComponent ac) {
        if (ac.getProtons()>1 && ac.getProtons()<stableRange.length) {
            return atomicSymbol[((int)(ac.getProtons()-1))];
        } else {
       //     System.out.println("The element #"+ac.getProtons()+" has no data yet");
           // System.out.println("The element #"+ac.getProtons()+" has no data yet");
        }
        return null;
    }
    
    public int getMinStablity(int p) {
        try {
         return stableRange[(p-1)][0];
        } catch(ArrayIndexOutOfBoundsException e) {
         //   System.out.println("Out of bounds!");
            return 0;
        }
    }
    
    public int getMaxStablity(int p) {
        try {
         return stableRange[(p-1)][1];
        } catch(ArrayIndexOutOfBoundsException e) {
         //   System.out.println("Out of bounds!");
            return 0;
        }
    }
    
    public int getTableLength() {
        return stableRange.length;
    }
    
    public Stability checkStability(AtomComponent ac) {
       // System.out.println("Checking #"+ac.getProtons()+" With #N = "+ac.getNeutrons());
        //System.out.println("Min:"+getMinStablity(ac.getProtons())+"  Max:"+getMaxStablity(ac.getProtons()));
        if (ac.getProtons()>0 && ac.getProtons()<stableRange.length) {
            if (ac.getNeutrons()>=getMinStablity(ac.getProtons()) && ac.getNeutrons()<=getMaxStablity(ac.getProtons())) {
                //System.out.println("Stable!");
                return Stability.STABLE;
            } else {
                //System.out.println("I was no stable :<");
                if (ac.getNeutrons()<getMinStablity(ac.getProtons())) {
                  //  System.out.println("Not enough neutrons");
                    return Stability.UNDER;
                }
                if (ac.getNeutrons()>getMaxStablity(ac.getProtons())) {
                    //System.out.println("Too much neutrons");
                    return Stability.ABOVE;
                }
            }
        } else {
       //     System.out.println("The element #"+ac.getProtons()+" has no data yet");
        }
        return null;
    }
    
    public static StabilityTable getTable() {
        return table;
    }
    
}
