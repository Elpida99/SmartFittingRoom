package hua.it21735.thesis.persistence.model;

public enum TargetConsumer {

    F("Female"), M("Male"), CF("ChildFemale"), CM("ChildMale");
    private final String value;

    TargetConsumer(String value) {

        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
