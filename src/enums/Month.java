package enums;

public enum Month {
    JAN, FEB, MAR, APR, MAY, JUN, JUL, AUG, SEP, OCT, NOV, DEC;

    String getEngName(/* enums.Month this, */) {
        switch (this) {
            case JAN: return "January";
            case FEB: return "February";
            case MAR: return "March";
            case SEP: return "September";
            default:  return "totally fake return forced on me by Java";
        }
    }

    @Override
    public String toString() {
        return super.toString() + "="
                + getEngName();
    }
}
