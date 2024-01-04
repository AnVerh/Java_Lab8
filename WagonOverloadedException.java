package Lab8;

public class WagonOverloadedException extends Exception{

        // Parameterless Constructor
        //public WagonOverloadedException() {}

        // Constructor that accepts a message
        public static final String MESSAGE = "Too many people or luggage to load to a wagon";

        public WagonOverloadedException()
        {
            super(MESSAGE);
        }
}
