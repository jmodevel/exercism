import java.util.List;

class Knapsack {

    int maximumValue( int maximumWeight, List<Item> items ) {
        return knapsack(
            items.stream().mapToInt( i -> i.weight ).toArray(),
            items.stream().mapToInt( i -> i.value  ).toArray(),
            items.size(),
            maximumWeight
        );
    }

    int knapsack( int[] weights, int[] values, int size, int maximumWeight ) {
        if( size <= 0 ){
            return 0;
        } else if( weights[ size-1 ] > maximumWeight ) {
            return knapsack( weights, values, size-1, maximumWeight );
        } else {
            return Math.max(
                knapsack( weights, values, size-1, maximumWeight ),
                values[ size-1 ] +
                    knapsack( weights, values, size-1, maximumWeight - weights[ size-1 ] )
            );
        }
    }

}