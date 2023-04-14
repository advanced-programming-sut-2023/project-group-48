package model.People;

public class People {
    private String job;

    private int column;
    private int row;

    public People(String job, int column, int row) {
        this.job = job;
        this.column = column;
        this.row = row;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
