public class app {
    public static void main(String[] args) {
        int sum1 = 100 * 50;
        int sum2 = sum1 * 350;
        int sum3 = sum1 + sum2;
        int sum4 = 4000000 * sum1 * sum2 + 3; 
        int time = 19;
        int num = 40;
        for(int i = 350; i <= 1000; ++i) {
            System.out.printf("%d * %d = %d /n", num, i, num * i);
        }
        if (time > 17) {
            System.out.println("Good Night");
        } else {
            System.out.println("Good Evening");
        }
        System.out.println(sum1);
        System.out.println(sum2);
        System.out.println(sum3);
        System.out.println(sum4);
        System.out.println("Token 1: " + Math.random());
        System.out.println("Token 2: " + Math.random());
        System.out.println("Token 3: " + Math.random());
        
        double phi =  3.14;
        int r = 14;
        double L = phi * (r * r);
        System.out.println(L);

        double p = 314;
        int d = 25;
        double keliling = 2 * p *d;
        System.out.println(keliling);
                  
        double c = 22/7;
        int t = 50;
        double luas = c * (t * t);
        System.out.println(luas);

        double h2 = 20;
        int p1 = 15;
        int p2 = 8;
        double h1 = h2 * p1/p2;
        System.out.println(h1);

        float d3 = 150;
        byte h = 80;
        float s = 1/2 * d3 * h;
        System.out.println(s);

        
    }

}

