package QuicksortTest;

import java.util.ArrayList;

public class ResultTable {
    private ArrayList<ResultColumn> columns = new ArrayList<>();

    public void addColumn(ResultColumn column) {
        columns.add(column);
    }

    public int getWidth() {
        int width = 0;
        for (ResultColumn resultColumn : columns) {
            width += resultColumn.getCellLength();

        }
        return width;
    }

    public int getHeight() {
        int max = 0;
        for (ResultColumn resultColumn : columns) {
            int count = resultColumn.getCellCount();
            if (max < count) {
                max = count;
            }
        }

        return max;
    }

    @Override
    public String toString() {
        String table = "";
        for (ResultColumn resultColumn : columns) {
            table += resultColumn;
        }
        table += "\n";
        for (int i = 0; i < getWidth(); i++) {
            table += "-";
        }
        table += "\n";

        int rowHeight = getHeight() / columns.getFirst().getCellCount();

        for (int i = 0; i < getHeight(); i++) {
            int start = 0;
            if (i % rowHeight != 0) {
                table += " | ";
                for (int j = 0; j < columns.getFirst().getCellLength(); j++) {
                    table += " ";
                }
                start = 1;
            }
            for (int v = start, z = 0; v < columns.size(); v++, z++) {

                ResultColumn resultColumn = columns.get(v);

                table += " | ";
                if (resultColumn.getCellCount() > i || (resultColumn == columns.get(0))) {
                    String text = "";
                    if (resultColumn == columns.get(0)) {
                        text = resultColumn.getCells().get(i / 3);
                        table += text;
                    } else {
                        text = resultColumn.getCells().get(i);
                        table += text;
                    }

                    int nameLength = text.length();
                    if (nameLength < resultColumn.getCellLength()) {
                        int diff = resultColumn.getCellLength() - nameLength;
                        for (int j = 0; j < diff; j++) {
                            table += " ";
                        }
                    }
                } else {
                    int diff = resultColumn.getCellLength();
                    for (int j = 0; j < diff; j++) {
                        table += " ";
                    }
                }

            }

            table += "\n";

            // table += "\n";

        }

        for (int i = 0; i < getWidth(); i++) {
            table += "-";
        }
        return table;
    }
}
