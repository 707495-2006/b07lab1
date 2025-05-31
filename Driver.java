public class Driver {
    public static void main(String[] args) {
        // 1. Make polynomials
        Polynomial p1 = new Polynomial(new double[]{1, -2, 3}, new int[]{2, 1, 0}); // x² -2x +3
        Polynomial p2 = new Polynomial(new double[]{0, 1}, new int[]{1, 0});        // 0x +1
        
        // 2. Test operations
        Polynomial sum = p1.add(p2);
        System.out.println("Addition works? " + (sum != null));
        
        System.out.println("p1(2) = " + p1.evaluate(2)); // Should be 3
        System.out.println("Root? " + p2.hasRoot(1));    // Should be true
    }
}