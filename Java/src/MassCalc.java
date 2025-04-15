import java.util.Scanner;

public class MassCalc
{
    @SuppressWarnings("resource")
    public static void main(String[] args)
    {
        double mass;
        double velInit;
        double velFinal;
        double delK;
        double delU;
        double delDelK;
        double delDelU;

        Scanner in = new Scanner(System.in);

        System.out.print("Enter Mass: ");
        mass = in.nextDouble();
        System.out.println();
        System.out.print("Enter Initial Velocity: ");
        velInit = in.nextDouble();
        System.out.println();
        System.out.print("Enter Final Velocity: ");
        velFinal = in.nextDouble();
        System.out.println();

        System.out.println();

        delK = calcDelK(mass, velInit, velFinal);
        System.out.println("Delta K: " + delK);
        delDelK = calcDelDelK(mass, velInit, velFinal, delK);
        System.out.println("Delta delta K: " + delDelK);
        delU = calcDelU(mass);
        System.out.println("Delta U: " + delU);
        delDelU = calcDelDelU(mass, delU);
        System.out.println("Delta delta U: " + delDelU);
    }

    public static double calcDelK(double m, double vi, double vf)
    {
        double delK = 0.0;
        delK = .5 * m * ((vf * vf) - (vi * vi));
        delK = Math.round(delK * 10000.0) / 10000.0;
        return delK;
    }

    public static double calcDelDelK(double m, double vi, double vf, double dk)
    {
        double delDelK = 0.0;
        double velFinalSq = vf * vf;
        double velInitSq = vi * vi;
        double velFinUnc = 9.216 * Math.pow(10.0, -5.0);
        double velInitUnc = 1.9321 * Math.pow(10.0, -4.0);
        delDelK = .5 * (m + .0005) * ((velFinalSq + velFinUnc) - (velInitSq - velInitUnc)) - dk;
        delDelK = Math.round(delDelK * 10000000.0) / 10000000.0;
        return delDelK;
    }

    public static double calcDelU(double m)
    {
        double delU = 0.0;
        delU = m * 9.81 * .5;
        delU = Math.round(delU * 1000.0) / 1000.0;
        return delU;
    }

    public static double calcDelDelU(double m, double du)
    {
        double delDelU = 0.0;
        delDelU = (m * 9.81 * .5005) - du;
        delDelU = Math.round(delDelU * 1000000.0) / 1000000.0;
        return delDelU;
    }
}
