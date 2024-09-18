package QuicksortTest;

import java.util.ArrayList;
import java.util.List;

public class ResultColumn {
    private ArrayList<String> cells = new ArrayList<>();
    private String columnName;
    private int length;

    private int height;

    public ResultColumn(String columnName) {
        this.columnName = columnName;
        length = columnName.length();
        height = getCellCount();
    }

    public void setCellLength(int l) {
        this.length = l;
    }

    public List<String> getCells() {
        return cells;
    }

    public int getCellLength() {
        return length;
    }

    public int getCellCount() {
        return cells.size();
    }

    public void addCell(String cellText) {
        cells.add(cellText);
        height = getCellCount();
        int max = columnName.length();
        for (String s : cells) {
            int l = s.length();
            if (l > max) {
                max = l;
            }
        }
        length = max;
    }

    @Override
    public String toString() {
        String s = " | " + columnName.toString();
        int nameLength = columnName.length();
        if (nameLength < length) {
            int diff = length - nameLength;
            for (int i = 0; i < diff; i++) {
                s += " ";
            }
        } else {

        }
        // for (int i = 0; i < length; i++) {
        // s += "-";
        // }
        // s += " | ";
        return s;
    }
}
