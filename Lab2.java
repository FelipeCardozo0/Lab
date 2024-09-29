public class Lab2 {
    public static double spaghettiStrands(){
        double length_spaghetti_strand = 25;
        double Earth_circumference = 40075;
        double Earth_circumference_converted =  Earth_circumference * 1e5;
        double number_spaghetti_strands =  Earth_circumference_converted/length_spaghetti_strand;

        return number_spaghetti_strands;
    }

    public static double cupsOfWater(){
        double volume_cup_of_water = 240;
        double volume_Mediterranean_Sea = 3750000;
        double volume_Mediterranean_Sea_converted =  volume_Mediterranean_Sea * 1e15;
        double number_cups_of_water = volume_Mediterranean_Sea_converted/volume_cup_of_water;

        return number_cups_of_water;
    }

    public static void main(String[] args){
        System.out.println("Question #1 is: How many strands of spaghetti would it take to wrap around the Earth??");
        System.out.println("Answer #1 is: " + spaghettiStrands() + " spaghetti strands");

        System.out.println("Question #2 is: How many cups of water would it take to fill the Mediterranean Sea?");
        System.out.println("Answer #2 is: " + cupsOfWater()+" cups of water");

    }
}
