package test.client.utils.setting;

public class Setting<T> {
    private final String name;
    private final String description;
    private final T defaultValue;
    private T value;

    public Setting(String name, String description, T defaultValue) {
        this.name = name;
        this.description = description;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public boolean isAvailable() {
        return true;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public T getDefaultValue() {
        return defaultValue;
    }
}
