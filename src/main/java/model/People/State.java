package model.People;

public enum State {
    STANDING("strong"), DEFENSIVE("defensive"), AGGRESSIVE("aggressive");
    private static State[] states = {STANDING, DEFENSIVE, AGGRESSIVE};
    private String stateInString;

    private State(String stateInString) {
        this.stateInString = stateInString;
    }

    public static State getStateFromString(String stateInString) {
        for (State state : states) {
            if (state.stateInString.equals(stateInString)) {
                return state;
            }
        }
        return null;
    }
}
