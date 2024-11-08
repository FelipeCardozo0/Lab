public class Lab8 {
    public static void main(String[] args) {
        int[][] elevationMap = {
                {100, 99, 200, 200, 200, 200, 200, 200, 200, 200},
                {2000, 98, 2000, 200, 200, 200, 200, 200, 200, 200},
                {2000, 97, 96, 200, 200, 200, 200, 200, 200, 200},
                {200, 200, 95, 200, 200, 200, 85, 84, 83, 200},
                {200, 200, 94, 93, 92, 200, 86, 2000, 82, 200},
                {200, 150, 200, 2000, 91, 200, 87, 2000, 81, 200},
                {200, 200, 200, 2000, 90, 89, 88, 2000, 80, 200},
                {200, 150, 100, 200, 200, 200, 200, 2000, 79, 200},
                {200, 200, 200, 200, 200, 200, 200, 200, 78, 77},
                {200, 200, 200, 200, 200, 200, 200, 200, 2000, 76}
        };

        int row = 2;
        int col = 2;

        boolean canFlow = canFlowOffMap(elevationMap, row, col);
        System.out.println("Can water flow off the map from (" + row + ", " + col + ")? " + canFlow);
    }




public static boolean canFlowOffMap(int[][] map, int row, int col) {
    if (row == 0 || row == map.length - 1 || col == 0 || col == map[0].length - 1) {
        return true;
    }

    int currentElevation = map[row][col];

    map[row][col] = Integer.MAX_VALUE;

    if ((row > 0 && map[row - 1][col] < currentElevation && canFlowOffMap(map, row - 1, col)) ||
            (row < map.length - 1 && map[row + 1][col] < currentElevation && canFlowOffMap(map, row + 1, col)) ||
            (col > 0 && map[row][col - 1] < currentElevation && canFlowOffMap(map, row, col - 1)) ||
            (col < map[0].length - 1 && map[row][col + 1] < currentElevation && canFlowOffMap(map, row, col + 1))) {
        return true;
    }

    map[row][col] = currentElevation;

    return false;
}
}
