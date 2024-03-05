import java.util.List;
import java.util.stream.Stream;

class Triangle {

    private final long differentSidesCount;

    Triangle(double side1, double side2, double side3) throws TriangleException {
        List<Double> sides = Stream.of( side1, side2, side3 )
                .sorted()
                .toList();
        if( sides.stream().anyMatch( s -> s <= 0 ) ){
            throw new TriangleException();
        }
        if( sides.get(0) + sides.get(1) <= sides.get(2) ){
            throw new TriangleException();
        }
        this.differentSidesCount = sides.stream().distinct().count();
    }

    boolean isEquilateral() {
        return this.differentSidesCount == 1;
    }

    boolean isIsosceles() {
        return this.differentSidesCount <= 2;
    }

    boolean isScalene() {
        return this.differentSidesCount == 3;
    }

}
