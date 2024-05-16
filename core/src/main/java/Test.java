import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) {
        String destination = "Shenzhen-CapeTown-Oakland-Texas";

        String[] routeNames = destination.split("-");


        for (int i = 0; i < routeNames.length ; i++) {
            if(i == routeNames.length-1){
                return;
            }
            String route1 = routeNames[i];
            String route2 = routeNames[i+1];
            System.out.println("routes are "+ route1 + " ---- "+ route2);
        }

        IntStream.range(0, routeNames.length - 1)
                .forEach(i -> System.out.println("routes are " + routeNames[i] + " ---- " + routeNames[i + 1]));
    }
}
