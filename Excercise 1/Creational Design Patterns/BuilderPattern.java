package EIDocs;

public class BuilderPattern {

    
    public static class PCBuilder {
        // Required attributes
        private String CPU;
        private String SSD;

        // Optional/changeable attributes' default values
        private String graphicsCard = "Nvidia GTX 2050";
        private String RAM = "8 GB";
        private boolean RGB = false;

        // Constructor for required parameters
        public PCBuilder(String CPU, String SSD) {
            this.CPU = CPU;
            this.SSD = SSD;
        }

        // Optional parameter setters
        public PCBuilder setGC(String GC) {
            this.graphicsCard = GC;
            return this;
        }

        public PCBuilder setRAM(String RAM) {
            this.RAM = RAM;
            return this;
        }

        public PCBuilder setRGB(boolean yn) {
            this.RGB = yn;
            return this;
        }

        //Build method
        public PCSystem build() {
            return new PCSystem(this);
        }
    }

    public static class PCSystem {
        private String CPU;
        private String SSD;
        private String graphicsCard;
        private String RAM;
        private boolean RGB;

        // Private constructor to build PCSystem from PCBuilder
        private PCSystem(PCBuilder pc) {
            this.CPU = pc.CPU;
            this.SSD = pc.SSD;
            this.graphicsCard = pc.graphicsCard;
            this.RAM = pc.RAM;
            this.RGB = pc.RGB;
        }

        // Getters
        public String getCPU() {
            return CPU;
        }

        public String getSSD() {
            return SSD;
        }

        public String getRAM() {
            return RAM;  
        }

        public String getGC() {
            return graphicsCard;
        }

        public boolean hasRGB() {
            return RGB;
        }

        // toString method to display the PC configuration
        @Override
        public String toString() {
            return "PC has: " + CPU + ", with " + RAM + " of RAM, a " + SSD + " SSD, and a " + graphicsCard + 
                   ". Does it have an RGB keyboard? " + (RGB ? "Yes." : "No.");
        }
    }

    // Main class to demonstrate the builder pattern
    public static class Main {
        public static void main(String[] args) {
            // Creating a PC using the builder
            PCSystem PC1 = new PCBuilder("13th Gen i9", "512GB SSD")
            		.setRAM("16 GB")
            		.setGC("Nvidia RTX 3080")
            		.setRGB(true)
            		.build();
            PCSystem PC2 = new PCBuilder("14th Gen i7", "1TB")
            		.setRAM("8GB")
            		.build();
            					

            // Print the PC details
            System.out.println(PC1);
            System.out.println(PC2);
        }
    }
}
