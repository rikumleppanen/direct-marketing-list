package my.marketing.list;

public class Contact {

    private String row;
    private int insertid;
    private Type type;

    public Contact(String row, int insertid) {
        this.row = row;
        this.insertid = insertid;
        this.type = null;
    }

    public boolean isEmail() {
        if (this.row.contains("@")) {
            return true;
        }
        return false;
    }

    public boolean isValidEmailAddress() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(this.row);
        return m.matches();
    }

    public boolean inNumberFormat() {
        if (this.row.matches("0[0-9]{5,9}")) {
            return true;
        }
        return false;
    }

    public void emailClean() {
        if (this.row.contains(" ")) {
            setRow(this.row.replace(" ", ""));
        }
        if (this.row.contains("-")) {
            setRow(this.row.replace("-", ""));
        }
        if (this.row.contains("(")) {
            setRow(this.row.replace("(", ""));
        }
        if (this.row.contains(")")) {
            setRow(this.row.replace(")", ""));
        }
        if (this.row.contains("+3580")) {
            setRow(this.row.replace("+3580", ""));
        }
        if (this.row.contains("+358")) {
            setRow(this.row.replace("+358", "0"));
        }
        if (this.row.startsWith("3580") == true) {
            setRow(this.row.replace("358", ""));
        }
        if (this.row.startsWith("358") == true) {
            setRow(this.row.replace("358", "0"));
        }

    }

    public String getRow() {
        return row;
    }

    public void setRow(String one) {
        this.row = one;
    }

    public int getInsertid() {
        return insertid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
