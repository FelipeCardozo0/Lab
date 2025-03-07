    public class Lab8 {
        public static void main(String[] args) {
            int[][] map = {
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

            System.out.println(canFlowOffMap(map,0,0));
            System.out.println(canFlowOffMap(map , 8, 3));
            System.out.println(canFlowOffMap(map , 1, 3));
            System.out.println(canFlowOffMap(map , 0, 3));
            System.out.println(canFlowOffMap(map , 9, 8));


        }

        public static boolean canFlowOffMap(int[][] map, int row, int col){
            int Now_cell= map[row][col];

            if  (row == 0 ||col== 0||row == map.length - 1||col==map[0].length - 1) return true;

    //North
            if (row > 0 &&Now_cell>map[row-1][col]){
                if(canFlowOffMap(map, row-1,  col))return true;
            }




    // South
                 if (row < map.length - 1 &&Now_cell>map[row+1][col]){
                if (canFlowOffMap(map, row+1,  col))return true;
            }






    // East
                        if (col < map[0].length - 1 &&Now_cell>map[row][col+1]){
                if(canFlowOffMap(map, row,  col+1))return true;
            }




    // west
                            if (col > 0 &&Now_cell>map[row][col-1]){
                if(canFlowOffMap(map, row,  col-1))return true;
            }


            return false;

        }

    }
