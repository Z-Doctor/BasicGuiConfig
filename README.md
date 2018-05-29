# BasicGuiConfig
This is not meant to be a stand alone mod, but to act as a submission of an idea and how it is supposed to be implemented. The premise of this code is to suggest a different way modders are supposed to create and use configs with or without guis using forge. Some modification may be required if forge decides to implement the code.

# Creating a Config
A config is intended to be create during the FMLPreInitializationEvent. There are two variants: EasyConfig and EasyConfigGui. EasyConfigGui is dependent on EasyConfig for functionality and handles displaying the gui to the player. After the config is created, the modder then creates Properties for that config. The config does not necessarily need a public variable, but a temporary one is needed when creating Properties. The config will also register itself to receive the ConfigChangedEvent.OnConfigChangedEvent. The intended order is to create the config, create the Properties and to call config.close(). Easy configs need the event, but can be called/stored in a class other than the Main one.

# Creating Properties
Properties are the interface for retrieving the config values. There are three built-in variants: Boolean, Integer, and String. Each takes a different number/types of parameters, but in general it is EasyConfig, Category, and defaultValue. By passing the EasyConfig you created the property registers itself to the config and loads the value from the config. If no value was there previously, the default value will become the value. The current value can be obtained by calling Property.getValue(). Properties should be an accessible field for other classes, but not necessarily static.

# Example Usage/Creation
```java
    // In your Main Mod class
    public static EasyConfigGui config;
    public static BooleanProperty prop;
    public static IntegerProperty prop1;
    public static StringProperty prop2;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        config = new EasyConfigGui(e);
        prop = new EasyConfig.BooleanProperty(config, "Booleans", "Bool1", false);
        prop1 = new EasyConfig.IntegerProperty(config, "Ints", "Int1", 50, 0, 100);
        prop2 = new EasyConfig.StringProperty(config, "Strings", "String1", "Test");
        config.close();
    }
    
    ...
    
    // Example Usuage
    public void foo() {
        if(prop.getValue()) {
            int chance = random.nextInt(prop1.getValue());
            if(chance > 75) {
                System.out.println(prop2.getValue());
            } else {
                System.out.println("Hello World");
            }
        }
    }
```