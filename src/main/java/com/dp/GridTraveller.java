package com.dp;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Log4j2
public class GridTraveller {
    public static void main(String[] args) {
        Map<Grid, Long> cache = new HashMap<>();
        //log.info(Objects.hash(1, 2) + " " + Objects.hash(1 + 2));
        log.info(grid(1, 1, cache));
        log.info(grid(2, 3, cache));
        log.info(grid(3, 2, cache));
        log.info(grid(3, 3, cache));
        log.info(grid(4, 3, cache));
        log.info(grid(3, 4, cache));
        log.info(grid(18, 18, cache));
    }

    public static int grid(int row, int column) {
        if(row == 0 || column == 0)
            return 0;
        if(row == 1 && column == 1)
            return 1;
        return grid(row - 1, column) + grid(row, column - 1);
    }

    public static long grid(int row, int column, Map<Grid, Long> cache) {
        Grid grid = new Grid(row, column);
        if(cache.containsKey(grid))
            return cache.get(grid);
        if(row == 0 || column == 0)
            return 0;
        if(row == 1 && column == 1)
            return 1;
        cache.put(grid, grid(row - 1, column, cache) + grid(row, column - 1, cache));
        return cache.get(grid);
    }
}

class Grid {
    Integer row, col;

    public Grid(Integer row, Integer col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grid grid = (Grid) o;
        return (Objects.equals(row, grid.row) &&
                Objects.equals(col, grid.col)) ||
                (Objects.equals(row, grid.col) &&
                        Objects.equals(col, grid.row));
    }

    @Override
    public int hashCode() {
        return Objects.hash(row + col);
    }
}
